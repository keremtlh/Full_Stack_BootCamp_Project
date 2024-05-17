package com.keremtalha.data.entity;

import com.keremtalha.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data // @Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
//@SneakyThrows
// TaskDto

// ENTITY
@Entity(name = "Tasks")
@Table(name="tasks")
// Task (M) Register(N)
public class TaskEntity  extends AuditingAwareBaseEntity implements Serializable {

    // SERILESTIRME
    public static final Long serialVersionUID=1L;

    // Task ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    // Task Name
    // Validation = columnDefinition =>  Default USER olsun
    @Column(name = "task_name", length = 20,nullable = false, columnDefinition = "varchar(255) default USER")
    private String taskName;

    // System Created Date
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemCreatedDate;

} //end class TaskEntity