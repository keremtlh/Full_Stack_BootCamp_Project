package com.keremtalha.annotation;

import com.keremtalha.data.repository.ITaskRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

//LOMBOK
@RequiredArgsConstructor
//<A,T> A = Annotation, T = Type
public class UniqueTaskNameValidation implements ConstraintValidator<UniqueTaskName,String> {

    //Injection yapısı
    private final ITaskRepository iTaskRepository;

    //Database attributta aynı veri var mı yok mu ona bakıyoruz.
    @Override
    public boolean isValid(String taskName, ConstraintValidatorContext constraintValidatorContext) {
        Boolean isTasksFind = iTaskRepository.findByTaskName(taskName).isPresent();
        //Eğer böyle bir yapı varsa return false döndürmesi için.
        if(isTasksFind)
            return true;
        return false;
    }
}
