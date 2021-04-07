package com.zigix.todoapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zigix.todoapp.audit.Audit;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TaskGroup extends Audit<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Long groupId;
    private String description;
    private LocalDateTime deadline;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskGroup")
    private Set<Task> tasks = new HashSet<>();

    @ManyToOne
    private User user;

}
