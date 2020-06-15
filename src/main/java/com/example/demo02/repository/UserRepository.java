package com.example.demo02.repository;

import com.example.demo02.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.name like ?1")
    List<User> findByName(String name);

}
