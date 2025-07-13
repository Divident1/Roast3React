package com.wecp.w3day5task1.repository;


import com.wecp.w3day5task1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    // extends JpaRepository to manage the CRUD operations for the User entity.
    Optional<User> findByUsername(String username);
}
