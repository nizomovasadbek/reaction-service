package com.test.reaction.dto;

import lombok.Setter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Setter
@Getter
@RequiredArgsConstructor
public enum GiphyType {

    RICH("rich"), BROKE("broke");

    private final String value;
}
