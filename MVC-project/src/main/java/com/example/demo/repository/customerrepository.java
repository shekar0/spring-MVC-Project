package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modals.customer;
@Repository
public interface customerrepository extends CrudRepository<customer, Integer> {

}
