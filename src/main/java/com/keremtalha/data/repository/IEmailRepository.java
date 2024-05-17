package com.keremtalha.data.repository;

import com.keremtalha.data.entity.EmailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// CrudRepository<RoleEntity,Long>
// JpaRepository<RoleEntity,Long>
// PagingAndSortingRepository<RoleEntity,Long>

@Repository
public interface IEmailRepository extends CrudRepository<EmailEntity,Long> {

    // Delivered Query (database query
    Optional<EmailEntity> findByEmailTo(String emailTo);

} //end interface