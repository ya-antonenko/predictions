package com.predictions.emojist.repos;

import com.predictions.emojist.domain.Predictions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictionsRepo extends JpaRepository<Predictions, Long> {
    @Query("select c from Predictions c where c.keywords like :keywords")
    List<Predictions> findAllPredictionsByKeywordsEmoji(@Param("keywords") String keywords);
    @Query("select c from Predictions c")
    List<Predictions> findAll();
}
