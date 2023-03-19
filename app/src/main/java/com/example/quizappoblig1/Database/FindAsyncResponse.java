package com.example.quizappoblig1.Database;

import java.util.List;

public interface FindAsyncResponse {
    void onFindTaskCompleted(List<Animal> animals);
}
