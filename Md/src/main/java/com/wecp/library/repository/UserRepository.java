package com.wecp.library.repository;

import com.wecp.library.domain.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// public interface UserRepository 

public interface UserRepository extends JpaRepository<User,Long>{ 

    Optional<User> findById(Long id);

    User save(User user);

}
