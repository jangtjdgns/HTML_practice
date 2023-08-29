package com.maepo.church.repositoy;

import com.maepo.church.entity.Announcements;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

;

@Repository
public interface AnnouncementsRepository extends JpaRepository<Announcements, Integer> {

    // 조회수 증가
    @Query("update Announcements a " +
           "set a.hit = a.hit + 1 " +
           "where id = :id")
    @Modifying
    void incrementHit(@Param("id") Integer id);

    // is_selected false로 초기화
    @Query("update Announcements a " +
            "set a.isSelected = false " +   // entity 내의 변수명과 같아야함
            "where a.id > 0")
    @Modifying
    void resetSelected();


    @Query("update Announcements a " +
            "set a.isSelected = true " +
            "where id = :id")
    @Modifying
    void updateIsSelectedById(@Param("id") Integer id);


    // 메인 화면 공지사항란에 선택한 공지 출력
    List<Announcements> findByIsSelectedTrue();

    // 검색
    Page<Announcements> findByTitleContaining(String searchKeyword, Pageable pageable);
}
