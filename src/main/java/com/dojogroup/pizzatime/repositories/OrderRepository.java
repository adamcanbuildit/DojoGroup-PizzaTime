package main.java.com.dojogroup.pizzatime.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{
    Order findById(Long id);
    List<Order> findAll();}
