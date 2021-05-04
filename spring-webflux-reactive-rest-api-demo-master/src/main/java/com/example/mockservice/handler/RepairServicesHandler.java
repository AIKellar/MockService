package com.example.mockservice.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.mockservice.model.Repair;
import com.example.mockservice.repository.RepairRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RepairServicesHandler {

	@Autowired
	private RepairRepository repairRepository;

	public Mono<ServerResponse> getAllRepairs(ServerRequest serverRequest) {
		Flux<Repair> repairs = repairRepository.findAll();
		System.out.println("hi");
		System.out.println(repairs);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(repairs, Repair.class);
	}

	public Mono<ServerResponse> createRepair(ServerRequest serverRequest) {
		return serverRequest.bodyToMono(Repair.class)
				.flatMap(repair -> Mono.just(repairRepository.save(repair)).flatMap(repairs -> {
					return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(repairs, Repair.class);
				}));
	}

	public Mono<ServerResponse> updateRepair(ServerRequest serverRequest) {
		Mono<Repair> repairMono = serverRequest.bodyToMono(Repair.class);
		String id = serverRequest.pathVariable("id");
		
		 repairMono = repairMono.flatMap(repair -> repairRepository.findById(id)
	                .map(foundRepair -> {                 
	                    foundRepair.setRepairTypeDescription(repair.getRepairTypeDescription());
	                    foundRepair.setPurchaseOrderNumber(repair.getPurchaseOrderNumber());
	                    foundRepair.setPurchaseOrderNumber(repair.getPurchaseOrderNumber());
	                    return foundRepair;
	                })
	                .flatMap(repairRepository::save));

	        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(repairMono, Repair.class);
	}
	
	public Mono<ServerResponse> deleteRepair(ServerRequest serverRequest) {
		String id = serverRequest.pathVariable("id");
		return repairRepository.deleteById(id)
				.flatMap(deletedRepair -> {
					return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).build();
				});
	}
	
	public Mono<ServerResponse> getRepairsByStatusCode(ServerRequest serverRequest) {
		String statusCode = serverRequest.pathVariable("statusCode");
		Flux<Repair> repairs = repairRepository.findByRepairStatusCode(statusCode);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(repairs, Repair.class);	
	}
}
