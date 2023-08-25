package com.maepo.church.repositoy;

import com.maepo.church.entity.CelebrateOrMourn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CelebrateOrMournRepository extends JpaRepository<CelebrateOrMourn, Integer> {

    @Query("update CelebrateOrMourn c "
            + "set c.hit = c.hit + 1 "
            + "where id = :id")
    @Modifying
    void incrementHit(@Param("id") Integer id);

    Page<CelebrateOrMourn> findByTitleContaining(String searchKeyword, Pageable pageable);
}
