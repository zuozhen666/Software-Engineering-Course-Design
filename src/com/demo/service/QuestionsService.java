package com.demo.service;

import com.demo.entity.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionsService {

    public List<Question> getQuestions(int grade);

    public void buildFile(String path, List<Question> questions) throws IOException;

    public void updateScores(String userName, Integer addScores);
}
