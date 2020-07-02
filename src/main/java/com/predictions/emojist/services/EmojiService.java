package com.predictions.emojist.services;

import com.predictions.emojist.repos.EmojiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmojiService {
    @Autowired
    EmojiRepo emojiRepo;
}
