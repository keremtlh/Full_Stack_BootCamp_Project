package com.keremtalha.business.services.Impl;

import com.keremtalha.bean.ModelMapperBeanClass;
import com.keremtalha.business.dto.TaskDto;
import com.keremtalha.business.services.ITaskService;
import com.keremtalha.data.entity.TaskEntity;
import com.keremtalha.data.repository.ITaskRepository;
import com.keremtalha.exception.HamitMizrakException;
import com.keremtalha.exception.Resource404NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// LOMBOK
@RequiredArgsConstructor
@Log4j2
@Service // Service asıl iş yükünü sırtlayan yapı
@Component//("taskServicesImpl")
public class TaskServicesImpl implements ITaskService<TaskDto, TaskEntity> {

    //Injection Lombok --> Constructor
    private final ITaskRepository iTaskRepository;
    //private final ModelMapper modelMapper;
    private final ModelMapperBeanClass modelMapperBeanClass;

    //########################################################################################################
    //Model Mapper
    @Override
    public TaskDto entityToDto(TaskEntity taskEntity) {
        return modelMapperBeanClass.modelMapperMethod().map(taskEntity, TaskDto.class);
    }

    @Override
    public TaskEntity dtoToEntity(TaskDto taskDto) {
        return modelMapperBeanClass.modelMapperMethod().map(taskDto, TaskEntity.class);
    }

    //#######################################################################################################
    // Crude

    //Create (TASK)
    @Override
    @Transactional
    public TaskDto taskServiceCreate(TaskDto taskDto) {
        TaskEntity taskEntity1;
        taskEntity1 = dtoToEntity(taskDto);
        taskEntity1.setTaskName(taskEntity1.getTaskName().toUpperCase());
        //Kaydetme
        TaskEntity taskEntity2 = iTaskRepository.save(taskEntity1);
        //Id ve Date'i dto üzerinde Set yaptık.
        taskDto.setTaskId(taskEntity2.getTaskId());
        taskDto.setSystemCreatedDate(taskEntity2.getSystemCreatedDate());
        return taskDto ;
    }//end Create

    //LIST (TASK)
    @Override
    public List<TaskDto> taskServiceList() {
        //Entity List
        List<TaskEntity> taskEntityList1 = iTaskRepository.findAll();
        //Dto List
        List<TaskDto> taskDtoList = new ArrayList<>();
        //Entity To Dto List
        for (TaskEntity tempEntity : taskEntityList1) {
            TaskDto taskDto1 = entityToDto(tempEntity);
            taskDtoList.add(taskDto1);

        }
        return taskDtoList;
    }//end List

    // FIND (TASK)
    @Override
    public TaskDto taskServiceFindById(Long id) {
        Boolean booleanTaskEntityFindById = iTaskRepository.findById(id).isPresent();
        TaskEntity taskEntity = null;
        //if(id!=null){
        if (booleanTaskEntityFindById) {
            taskEntity = iTaskRepository.findById(id).orElseThrow(
                    () -> new Resource404NotFoundException(id + " nolu ID Bulunamadı")
            );
        } else if (!booleanTaskEntityFindById) {
            throw new HamitMizrakException("Tasks Dto id boş değer geldi");
        }
        return entityToDto(taskEntity);
    }

    //UPDATE (TASK)
    @Override
    @Transactional

    public TaskDto taskServiceUpdateById(Long id, TaskDto taskDto) {

        //Find
        TaskDto taskDtoFind = taskServiceFindById(id);

        //Update
        TaskEntity taskUpdateEntity = dtoToEntity(taskDtoFind); // Update işlemi için taskentity yapısı üzerinde ilerlememiz lazım.
        taskUpdateEntity.setTaskName(taskDto.getTaskName());
        iTaskRepository.save(taskUpdateEntity);
        //ID ve date Dto üzerinde set yapıyoruz.
        taskDto.setTaskId(taskUpdateEntity.getTaskId());
        taskDto.setSystemCreatedDate(taskUpdateEntity.getSystemCreatedDate());
        return entityToDto(taskUpdateEntity);
    }

    //DELETE (TASK)
    @Override
    @Transactional
    public TaskDto taskServiceDeleteById(Long id) {

        //Find
        TaskDto taskDtoFind = taskServiceFindById(id);

        TaskEntity taskDeleteEntity = dtoToEntity(taskDtoFind);
        if (taskDeleteEntity != null){
            iTaskRepository.deleteById(id);
            return taskDtoFind;
        }else {
            throw new HamitMizrakException(taskDtoFind + " nolu data silinemedi");
        }
        //return null;
    }
}  //end Find



