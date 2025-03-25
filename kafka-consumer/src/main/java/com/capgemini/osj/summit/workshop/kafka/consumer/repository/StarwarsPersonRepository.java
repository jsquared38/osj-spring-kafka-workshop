package com.capgemini.osj.summit.workshop.kafka.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.osj.summit.workshop.kafka.consumer.entity.StarwarsPerson;

@Repository
public interface StarwarsPersonRepository extends JpaRepository<StarwarsPerson, Long> {

}
