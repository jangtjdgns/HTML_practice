package com.maepo.church.service;

import com.maepo.church.entity.Announcements;
import com.maepo.church.repositoy.AnnouncementsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommAnnouncementsService implements PostService<Announcements>{

    // 공지사항 //
    @Autowired
    private AnnouncementsRepository announcementsRepository;

    // 게시글 쓰기
    @Override
    public void write(Announcements announcements) {

        announcementsRepository.save(announcements);
    }

    // 게시글 목록
    @Override
    public Page<Announcements> postList(Pageable pageable) {

        return announcementsRepository.findAll(pageable);
    }

    // 게시글 보기
    @Override
    public Announcements postView(Integer id) {

        return announcementsRepository.findById(id).get();
    }

    // 조회수 증가 -> @Query, @Modifying 사용시 @Transactional 사용해야함
    @Transactional
    @Override
    public void incrementHit(Integer id) {

        announcementsRepository.incrementHit(id);
    }

    // 공지사항 게시글 설정 -팝업창, 내림차순으로 가져오기
    public List<Announcements> announcementsList() {
        List<Announcements> list = announcementsRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return list;
    }

    // 공지사항 db is_selected false로 초기화
    @Transactional
    public void resetSelected(){

        announcementsRepository.resetSelected();
    }

    // 공지사항 선택된 체크박스 id에 true 값 넣기
    @Transactional
    public void updateIsSelectedById(Integer id){

        announcementsRepository.updateIsSelectedById(id);
    }

    // 게시글 삭제
    @Override
    public void postDelete (Integer id){

        announcementsRepository.deleteById(id);
    }

    // 게시글 제목 검색
    @Override
    public Page<Announcements> findByTitleContaining (String searchKeyword, Pageable pageable) {

        return announcementsRepository.findByTitleContaining(searchKeyword, pageable);
    }
}
