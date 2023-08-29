package com.maepo.church.repositoy;

import com.maepo.church.entity.CelebrateOrMourn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CelebrateOrMournRepository extends JpaRepository<CelebrateOrMourn, Integer> {

    Page<CelebrateOrMourn> findByNameContaining(String searchKeyword, Pageable pageable);

    @Query ("SELECT o.occasionType " +
            "FROM CelebrateOrMourn AS cm " +
            "LEFT JOIN Occasion AS o ON o.id = cm.occasionId " +
            "WHERE cm.id = :id")
    String getOccasionType(@Param("id") Integer id);

    @Query ("SELECT co.roleDescription " +
            "FROM CelebrateOrMourn AS cm " +
            "LEFT JOIN ChurchOfficer AS co ON cm.churchOfficerId = co.id " +
            "WHERE cm.id = :id")
    String getRoleDescription(@Param("id") Integer id);

    @Query ("SELECT c.condolenceName " +
            "FROM CelebrateOrMourn AS cm " +
            "LEFT JOIN Condolence AS c ON cm.condolenceId = c.id " +
            "WHERE cm.id = :id")
    String getCondolenceName(@Param("id") Integer id);
}
