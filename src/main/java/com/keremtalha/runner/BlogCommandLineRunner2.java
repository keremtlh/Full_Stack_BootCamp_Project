package com.keremtalha.runner;
import com.keremtalha.business.dto.RegisterDto;
import com.keremtalha.business.services.IRegisterServices;
import com.keremtalha.business.services.ITaskService;
import com.keremtalha.data.entity.TaskEntity;
import com.keremtalha.data.repository.IRegisterRepository;
import com.keremtalha.data.repository.ITaskRepository;
import com.keremtalha.task.ETask;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


// LOMBOK
@RequiredArgsConstructor
@Log4j2

@Component // Spring Boot bir parçasısın.
public class BlogCommandLineRunner2 implements CommandLineRunner{
    // Injection
    private final ITaskRepository iTaskRepository;
    private final ITaskService iTaskService;

    private final IRegisterRepository iRegisterRepository;
    private final IRegisterServices iRegisterServices;

    // Task ve Register Ekleme
    private void taskAndRegisterCreate(){
        //log.info("task And Register Create");
        System.out.println("task And Register Create");
        synchronized (this){
            Long adminTaskID= iTaskRepository.save(TaskEntity.builder().taskId(0L).taskName(ETask.ADMIN.toString()).build()).getTaskId();
            Long writerTaskID= iTaskRepository.save(TaskEntity.builder().taskId(0L).taskName(ETask.WRITER.toString()).build()).getTaskId();
            Long userTaskID= iTaskRepository.save(TaskEntity.builder().taskId(0L).taskName(ETask.USER.toString()).build()).getTaskId();

            // Thread
            Thread thread= new Thread(new Runnable() {
                @Override
                public void run() {

                }
            });

            for (long i = 1; i <=3 ; i++) {

                // REGISTER
                RegisterDto registerDto=new RegisterDto();
                registerDto.setRegisterNickName("nickname"+i);
                registerDto.setRegisterName("name"+i);
                registerDto.setRegisterSurname("surname"+i);
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append("email").append("@gmail.com");
                registerDto.setRegisterEmail(stringBuilder.toString());
                registerDto.setRegisterPassword("Java12345@.");

                // USER DETAILS
                registerDto.setIsEnabled(true);
                registerDto.setIsCredentialsNonExpired(true);
                registerDto.setIsAccountNonExpired(true);
                registerDto.setIsAccountNonLocked(true);

                // KAYDET
                iRegisterServices.registerServiceCreate(i,registerDto);
                System.out.println("EKLENDI");
            }

        }
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Command Line Runner Bean-2");
        taskAndRegisterCreate();
    }

} //end BlogCommandLineRunner1
