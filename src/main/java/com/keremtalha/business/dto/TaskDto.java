package com.keremtalha.business.dto;

import com.keremtalha.annotation.UniqueTaskName;
import com.keremtalha.audit.AuditingAwareBaseDto;
import com.keremtalha.task.ETask;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import java.io.Serializable;
import java.util.Date;
// LOMBOK
@Data // @Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
// @SneakyThrows
// TaskDto(M) RegisterDto(N)
    public class TaskDto extends AuditingAwareBaseDto implements Serializable {

        // SERILESTIRME
        public static final Long serialVersionUID=1L;
        // Task ID
        private Long taskId;
        // Task Name
        // Validation
        @NotEmpty(message = "{role.name.validation.constraints.NotNull.message}")
        @Builder.Default
        // Annotation kullanmalısın cunku database aynı task adında olmaması gerekiyor (unique)
        @UniqueTaskName
        private String taskName= ETask.USER.toString();
        // System Created Date
        private Date systemCreatedDate;
    } //end class TaskDto