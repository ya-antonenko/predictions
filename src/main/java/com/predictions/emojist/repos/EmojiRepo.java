package com.predictions.emojist.repos;

import com.predictions.emojist.domain.Emoji;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmojiRepo extends JpaRepository<Emoji, Long> {
}
