package com.dojogroup.pizzatime.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import com.dojogroup.pizzatime.models.Order;


@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{
    Optional<Order> findById(Long id);
    List<Order> findAll();
}
