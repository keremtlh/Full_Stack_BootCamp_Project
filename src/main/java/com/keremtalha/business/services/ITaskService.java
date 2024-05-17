package com.keremtalha.business.services;

//Dto : D
//Entitiy : E

import com.keremtalha.business.dto.TaskDto;
import com.keremtalha.data.entity.TaskEntity;

import java.util.List;

public interface ITaskService<D,E> {

    //Entitiy i dto ya dto you en e çevirmek için yapılar var bunun için model mapper kullanıyoruz.
    //Model Mapper
    public D entityToDto(E e); // Interface te gövdesiz metod şeklinde.
    public E dtoToEntity(D d);

    //########################################################################################################
    //Model Mapper
    TaskDto entityToDto(TaskEntity taskEntity);

    TaskEntity dtoToEntity(TaskDto taskDto);

    //########################################################################################################
    //TASK CRUD
    // Task Create
    public D taskServiceCreate(D d);

    // Task List
    public List<D> taskServiceList();

    // Task Find
    public D taskServiceFindById(Long id);

    //Task Update Id, Object
    public D taskServiceUpdateById(Long id, D d);

    //Task Delete
    public D taskServiceDeleteById(Long id);
}
