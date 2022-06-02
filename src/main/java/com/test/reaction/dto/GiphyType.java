package com.test.reaction.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GiphyType {

    RICH("rich"), BROKE("broke");

    private final String value;
}
