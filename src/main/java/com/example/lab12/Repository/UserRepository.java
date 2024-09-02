package com.example.lab12.Repository;

import com.example.lab12.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Integer> {


    User findUserById (Integer id) ;
    User findUserByUsername (String username) ;


}
