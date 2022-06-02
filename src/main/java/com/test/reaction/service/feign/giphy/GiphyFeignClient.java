package com.test.reaction.service.feign.giphy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "giphy-client",
        url = "${test.reaction.service.giphy-service}")
public interface GiphyFeignClient {

    @GetMapping("/random")
    GiphyResponse getGiphyByKeyword(@RequestParam String api_key,
                                    @RequestParam String tag);
}
