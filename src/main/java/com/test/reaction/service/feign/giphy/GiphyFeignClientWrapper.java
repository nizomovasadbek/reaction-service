package com.test.reaction.service.feign.giphy;

import com.test.reaction.dto.GiphyType;
import com.test.reaction.exception.FeignErrorWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import static java.text.MessageFormat.format;

@Component
@RequiredArgsConstructor
public class GiphyFeignClientWrapper {
    private static final String GIPHY_CLIENT_ERROR_CODE = "giphy.client.error";
    private final GiphyFeignClient feignClient;
    private final FeignErrorWrapper errorWrapper;

    @Value("${test.reaction.giphy.api-key}")
    private String API_KEY;

    public GiphyResponse getGiphy(GiphyType type) {
        return errorWrapper.wrapException(
                () -> feignClient.getGiphyByKeyword(API_KEY, type.getValue()),
                format("Getting giphy by word: {0}", type.getValue()),
                GIPHY_CLIENT_ERROR_CODE);
    }
}
