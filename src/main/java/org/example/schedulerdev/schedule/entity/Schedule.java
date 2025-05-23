package org.example.schedulerdev.schedule.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.schedulerdev.common.BaseEntity;

@Getter
@Entity
@Table ( name = "schedule")
@RequiredArgsConstructor
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    public Schedule(String username, String title, String contents) {
        this.username = username;
        this.title = title;
        this.contents = contents;
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }
}
