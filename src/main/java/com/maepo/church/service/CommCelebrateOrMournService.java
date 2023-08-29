package com.maepo.church.service;

import com.maepo.church.entity.CelebrateOrMourn;
import com.maepo.church.repositoy.CelebrateOrMournRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    // 리스트 전부 가져오기
    public List<CelebrateOrMourn> postId() {
        return celebrateOrMournRepository.findAll();
    }

    // OccasionType(경조사 구분) 가져오기
    public String getOccasionType(Integer id){ return celebrateOrMournRepository.getOccasionType(id); }

    // roleDescription(직분) 가져오기
    public String getRoleDescription(Integer id){ return celebrateOrMournRepository.getRoleDescription(id); }

    // 페이지 리스트 가져오기
    public Page<CelebrateOrMourn> findByNameContaining (String searchKeyword, Pageable pageable) {

        return celebrateOrMournRepository.findByNameContaining(searchKeyword, pageable);
    }
}
