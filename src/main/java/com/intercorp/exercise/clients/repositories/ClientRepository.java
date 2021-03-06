package com.intercorp.exercise.clients.repositories;

import com.intercorp.exercise.clients.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
