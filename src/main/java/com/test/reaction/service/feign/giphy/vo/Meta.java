package com.test.reaction.service.feign.giphy.vo;

import lombok.Data;

@Data
public class Meta {
    private String msg;
    private Integer status;
    private String response_id;
}
