package com.maepo.church.service;

import com.maepo.church.entity.CelebrateOrMourn;
import com.maepo.church.repositoy.CelebrateOrMournRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommCelebrateOrMournService implements PostService<CelebrateOrMourn> {

    @Autowired
    private CelebrateOrMournRepository celebrateOrMournRepository;

    // 쓰기
    @Override
    public void write(CelebrateOrMourn celebrateOrMourn) {

        celebrateOrMournRepository.save(celebrateOrMourn);
    }

    // 삭제
    @Override
    public void postDelete (Integer id) {

        celebrateOrMournRepository.deleteById(id);
    }

    // 보기
    @Override
    public CelebrateOrMourn postView(Integer id) {
        return celebrateOrMournRepository.findById(id).get();
    }

    // 목록
    @Override
    public Page<CelebrateOrMourn> postList(Pageable pageable){
        return celebrateOrMournRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public void incrementHit(Integer id) {

        celebrateOrMournRepository.incrementHit(id);
    }

    @Override
    public Page<CelebrateOrMourn> findByTitleContaining (String searchKeyword, Pageable pageable) {

        return celebrateOrMournRepository.findByTitleContaining(searchKeyword, pageable);
    }
}
