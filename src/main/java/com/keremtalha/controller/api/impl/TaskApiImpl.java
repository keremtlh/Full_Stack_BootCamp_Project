package com.keremtalha.controller.api.impl;

import com.keremtalha.business.dto.TaskDto;
import com.keremtalha.business.services.ITaskService;
import com.keremtalha.controller.api.ITaskApi;
import com.keremtalha.error.ApiResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

//API (REST) Representational State Transfer Bir yerden başka bir yere pro. şekilde veri aktarmak için api yazmalıyız.
@RestController
@RequestMapping("/task/api/v1.0.0")
@CrossOrigin // CORS: Hatası portların biribirini tanıması için 4444 ve 3000 portları arası gibi.
//@CrossOrigin(origins = ProjectUrl.REACT_FRONTEND_PORT_URL)
//@CrossOrigin(origins = "localhost:3000")
public class TaskApiImpl implements ITaskApi<TaskDto> {

    // Injection
    private final ITaskService iTaskService;
    // Error
    private ApiResult apiResult;

    // CREATE Task(Api)
    // http://localhost:4444/task/api/v1.0.0/create
    @PostMapping("/create")
    @Override
    public ResponseEntity<?> taskApiCreate(@Valid @RequestBody TaskDto taskDtoData) {
        TaskDto taskCreateApi=(TaskDto)iTaskService.taskServiceCreate(taskDtoData);
        // Eğer kaydederken null değer gelirse
        if(taskCreateApi==null){
            ApiResult apiResultCreate=ApiResult.builder()
                    .status(404)
                    .error("Task Eklenmedi")
                    .message("Task Dto bulunmadı")
                    .path("localhost:4444/task/api/v1.0.0/create")
                    .createdDate(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.status(404).body(apiResultCreate);
        }
        else if(taskCreateApi.getTaskId()==0){
            ApiResult apiResultCreate=ApiResult.builder()
                    .status(400)
                    .error("Task Eklenmedi")
                    .message("Task Dto Bad Request")
                    .path("localhost:4444/task/api/v1.0.0/create")
                    .createdDate(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.status(400).body(apiResultCreate);
        }
        log.info("Task Api eklendi");
        return ResponseEntity.status(201).body(iTaskService.taskServiceCreate(taskDtoData));
    }
    // LIST Task (Api)
    // http://localhost:4444/task/api/v1.0.0/list
    @GetMapping("/list")
    @Override
    public ResponseEntity<List<TaskDto>> taskApiList() {
        log.info("Task Api listelendi");
        return ResponseEntity.ok(iTaskService.taskServiceList());
    }
    // FIND Task(Api)
    // http://localhost:4444/task/api/v1.0.0/find
    // http://localhost:4444/task/api/v1.0.0/find/0
    // http://localhost:4444/task/api/v1.0.0/find/1
    @Override
    @GetMapping({"/find","/find/{id}"})
    public ResponseEntity<?> taskApiFindById(@PathVariable(name="id",required = false)Long id) {
        TaskDto taskFindApi=( TaskDto)iTaskService.taskServiceFindById(id);
        if(taskFindApi==null) {
            // Eğer kaydederken null değer gelirse
            ApiResult apiResultFind = ApiResult.builder()
                    .status(404)
                    .error("Task Bulunamadı")
                    .message("Task Dto bulunmadı")
                    .path("localhost:4444/task/api/v1.0.0/find")
                    .createdDate(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.status(404).body(apiResultFind);
        }
        log.info("Task Api bulundu");
        return ResponseEntity.ok(iTaskService.taskServiceFindById(id));
    }

    // UPDATE Task(Api)
    // http://localhost:4444/task/api/v1.0.0/update
    // http://localhost:4444/task/api/v1.0.0/update/0
    // http://localhost:4444/task/api/v1.0.0/update/1

    @Override
    @PutMapping({"/update","/update/{id}"})
    public ResponseEntity<?> taskApiUpdateById(@PathVariable(name = "id", required = false) Long id, @Valid @RequestBody TaskDto taskDto) {

        TaskDto taskUpdateApi=( TaskDto)iTaskService.taskServiceUpdateById(id,taskDto);
        if(taskUpdateApi==null) {
            // Eğer kaydederken null değer gelirse
            ApiResult apiResultFind = ApiResult.builder()
                    .status(404)
                    .error("Task Bulunamadı")
                    .message("Task Dto bulunmadı")
                    .path("localhost:4444/task/api/v1.0.0/update")
                    .createdDate(new Date(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.status(404).body(apiResultFind);
        }
        return null;
    }

    // DELETE Task(Api)
    // http://localhost:4444/task/api/v1.0.0/delete
    // http://localhost:4444/task/api/v1.0.0/delete/0
    // http://localhost:4444/task/api/v1.0.0/delete/1
    @Override
    @DeleteMapping({"/delete","/delete/{id}"})
    public ResponseEntity<?> taskApiDeleteById(Long id) {
        TaskDto taskDto=(TaskDto)iTaskService.taskServiceDeleteById(id);
        log.info("Task Api Silindi");
        return ResponseEntity.ok(taskDto);
    }

}//end TaskApiImpl
