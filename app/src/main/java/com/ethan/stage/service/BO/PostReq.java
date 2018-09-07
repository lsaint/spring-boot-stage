package com.ethan.stage.service.BO;

import lombok.AllArgsConstructor;
import lombok.Data;

// request和reply的对象相同
@Data
@AllArgsConstructor
public class PostReq {
    private String title;
    private String body;
    private int userId;
}
