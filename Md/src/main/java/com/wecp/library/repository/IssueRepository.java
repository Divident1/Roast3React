package com.wecp.library.repository;

import com.wecp.library.domain.Issue;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IssueRepository extends JpaRepository<Issue,Long>{

   

}
