package com.maepo.church.service;

import com.maepo.church.entity.Announcements;
import com.maepo.church.repositoy.AnnouncementsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    @Autowired
    // 공지사항 레포지토리
    private AnnouncementsRepository announcementsRepository;

    // 공지사항 제목 보이기
    @Transactional
    public List<Announcements> findByIsSelectedTrue(){

        return announcementsRepository.findByIsSelectedTrue();
    }
}
