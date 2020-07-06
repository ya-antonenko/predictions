package com.predictions.emojist.repos;

import com.predictions.emojist.domain.Emoji;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmojiRepo extends JpaRepository<Emoji, Long> {
    @Query("select c from Emoji c where c.keywords like :keywords")
    List<Emoji> findAllEmojisByKeywordsPrediction(@Param("keywords") String keywords);
}
