package com.predictions.emojist.repos;

import com.predictions.emojist.domain.Predictions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionsRepo extends JpaRepository<Predictions, Long> {
}
