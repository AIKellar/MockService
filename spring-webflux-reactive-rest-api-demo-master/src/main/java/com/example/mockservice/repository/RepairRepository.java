package com.example.mockservice.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.mockservice.model.Repair;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RepairRepository extends ReactiveMongoRepository<Repair, String> {
	Flux<Repair> findByRepairStatusCode(String statusCode);
}
