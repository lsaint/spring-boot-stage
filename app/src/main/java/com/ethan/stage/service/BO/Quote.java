package com.ethan.stage.service.BO;

import lombok.Data;

@Data
public class Quote {
    private String type;
    private Value value;

    @Data
    class Value {
        private Long id;
        private String quote;
    }
}
