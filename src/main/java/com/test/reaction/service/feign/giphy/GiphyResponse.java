package com.test.reaction.service.feign.giphy;

import com.test.reaction.service.feign.giphy.vo.Giphy;
import com.test.reaction.service.feign.giphy.vo.Meta;
import lombok.Data;

@Data
public class GiphyResponse {

    private Giphy data;

    private Meta meta;
}
