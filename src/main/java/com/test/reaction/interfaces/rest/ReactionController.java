package com.test.reaction.interfaces.rest;

import com.test.reaction.service.ReactionService;
import com.test.reaction.service.feign.giphy.GiphyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/reaction")
public class ReactionController {

    private final ReactionService reactionService;

    @GetMapping("/currency/{currency}")
    public GiphyResponse getReaction(@PathVariable String currency){
        return reactionService.getReaction(currency);
    }

}
