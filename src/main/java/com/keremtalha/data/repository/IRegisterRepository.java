package com.keremtalha.data.repository;


import com.keremtalha.data.entity.RegisterEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// CrudRepository<TaskEntity,Long>
// JpaRepository<TaskEntity,Long>
// PagingAndSortingRepository<TaskEntity,Long>

@Repository
public interface IRegisterRepository extends CrudRepository<RegisterEntity,Long> {

    // Delivered Query (database query
    // Database Task Name bulmak
    // select * from Tasks as task where task.task_name
    Optional<RegisterEntity> findByRegisterEmail(String email);

    // Query: Karmaşık sorgular için @Qery kullanıyoruz
    @Query("select reg from Registers reg join reg.tasks task where task.taskName=:taskNameParam")
    List<RegisterEntity> findAllByRegisterInJoinTasksTaskName(@Param("taskNameParam") String taskName );


} //end interface
