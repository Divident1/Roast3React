package com.wecp.library_management_system_jwt.repository;

import com.wecp.library_management_system_jwt.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}