package com.maepo.church.entity;

import com.maepo.church.controller.CommCelebrateOrMournController;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class CelebrateOrMourn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                         // 경조사 게시글 번호

    private String name;                        // 작성자

    private Integer churchOfficerId;            // 직분

    private Integer occasionId;                 // 경조사 구분 id

    private String celebrateName;               // 경사 종류

    private String mournName;                   // 조사 종류

    private Integer condolenceId;               // 조사 부고 종류

    private String customInput;                 // 직접 입력한 텍스트

    private String occasionVenue;               // 경조사 장소

    private String content;                     // 세부 내용

    private LocalDateTime occasionDate;         // 경조사 일시

    @Column(name = "created_at")
    private LocalDateTime createdAt;            // 작성 시간

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;            // 업데이트 시간

    // 엔티티 생성될 때 자동으로 호출됨
    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    // 엔티티 업데이트될 때 자동으로 호출됨
    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }


    // join
    @Transient
    private String occasionType;                // occasion 조인

    @Transient
    private String roleDescription;             // church_officer 조인
}
