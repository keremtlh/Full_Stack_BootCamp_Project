package com.keremtalha.data.repository;

import com.keremtalha.data.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//JpaRepository
//CrudRepository
//PagingandsortingRepository Repository Yapıları Kullanılabilir

@Repository
public interface ITaskRepository extends JpaRepository<TaskEntity, Long> {

    //Sorgu işlemlerinde kullanılan ***Delivired Query*** yapıları var
    //DataBase den Task name bulmak için
    Optional<TaskEntity> findByTaskName(String taskName);

    @Query("select task from Registers reg join reg.tasks task where reg.registerEmail=:emailParam")
    TaskEntity registerEmailFindTask(@Param("emailParam") String email);

}//end interface
