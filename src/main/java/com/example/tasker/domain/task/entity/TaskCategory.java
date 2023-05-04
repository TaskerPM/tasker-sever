package com.example.tasker.domain.task.entity;

import com.example.tasker.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskCategory extends BaseTimeEntity {

    @Id
    @Column(name = "task_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TaskCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

}