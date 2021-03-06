package com.ethan.stage.domain.BO;

import lombok.AllArgsConstructor;
import lombok.Data;

// request和reply的对象相同
@Data
@AllArgsConstructor
public class Post {
    private String title;
    private String body;
    private int userId;
}
