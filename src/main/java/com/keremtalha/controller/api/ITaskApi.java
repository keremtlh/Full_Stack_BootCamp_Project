package com.keremtalha.controller.api;

import org.springframework.http.ResponseEntity;

import java.util.List;

// API INTERFACE (ITaskApi)
// D: Dto
public interface ITaskApi<D> {

    // ROLE CRUD
    // Task Create
    public ResponseEntity<?> taskApiCreate(D d);

    // Task List
    public ResponseEntity<List<D>> taskApiList();

    // Task Find ID
    public ResponseEntity<?> taskApiFindById(Long id);

    // Task Update ID, Object
    public ResponseEntity<?> taskApiUpdateById(Long id, D d);

    // Task Delete ID
    public ResponseEntity<?> taskApiDeleteById(Long id);

} //end api interface