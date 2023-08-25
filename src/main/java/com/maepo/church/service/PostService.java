package com.maepo.church.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService<Communication> {

    // 게시글 쓰기
    void write(Communication post);

    // 게시글 목록
    Page<Communication> postList(Pageable pageable);

    // 게시글 보기
    Communication postView(Integer id);
    void incrementHit(Integer id);

    // 게시글 삭제
    void postDelete(Integer id);
    Page<Communication> findByTitleContaining (String searchKeyword, Pageable pageable);
}
