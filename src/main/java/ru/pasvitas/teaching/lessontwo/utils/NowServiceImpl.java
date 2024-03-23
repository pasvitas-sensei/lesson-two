package ru.pasvitas.teaching.lessontwo.utils;

import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class NowServiceImpl implements NowService {
    
    @Override
    public Date getCurrentDate() {
        return new Date();
    }
}
