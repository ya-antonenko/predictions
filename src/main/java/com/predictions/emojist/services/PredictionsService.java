package com.predictions.emojist.services;

import com.predictions.emojist.repos.PredictionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PredictionsService {
    @Autowired
    PredictionsRepo predictionsRepo;
}
