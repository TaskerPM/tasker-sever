package com.example.tasker.domain.user.entity;

import com.example.tasker.domain.task.entity.Task;
import com.example.tasker.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "profile_intro")
    private String profileIntro;

    @Column(name = "profile_img")
    private String profileImg;

    @Column(name = "phone_number_sha")
    private String phoneNumberSha;

    @OneToOne(mappedBy = "user")
    private UserRefreshToken userRefreshToken;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        this.tasks.add(task);
        task.setUser(this);
    }

}
