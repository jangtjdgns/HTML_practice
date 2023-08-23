package com.maepo.church.dto;

import com.maepo.church.entity.Announcements;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class AnnouncementsDTO {

    private String title;
    private String content;
    private String author;
    private Integer hit;
    private boolean isSelected;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AnnouncementsDTO() {
    }

    public AnnouncementsDTO(Announcements announcements) {
        this.title = announcements.getTitle();
        this.content = announcements.getContent();
        this.author = announcements.getAuthor();
        this.hit = announcements.getHit();
        this.isSelected = announcements.isSelected();
        this.createdAt = announcements.getCreatedAt();
        this.updatedAt = announcements.getUpdatedAt();
    }

    // 엔티티로 변환
    public Announcements toEntity() {
        Announcements announcements = new Announcements();
        announcements.setTitle(this.title);
        announcements.setContent(this.content);
        announcements.setAuthor(this.author);
        announcements.setHit(0);
        announcements.setSelected(false);
        announcements.setCreatedAt(this.createdAt);
        announcements.setUpdatedAt(this.updatedAt);
        return announcements;
    }

}
