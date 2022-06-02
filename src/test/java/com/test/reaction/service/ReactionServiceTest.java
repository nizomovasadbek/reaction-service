package com.test.reaction.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.test.reaction.exception.cases.CurrencyNotFoundException;
import com.test.reaction.service.feign.exchangerate.ExchangeRatesFeignClientWrapper;
import com.test.reaction.service.feign.exchangerate.ExchangeRatesResponse;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReactionService.class})
@ExtendWith(SpringExtension.class)
class ReactionServiceTest {
    @MockBean
    private ExchangeRatesFeignClientWrapper exchangeRatesFeignClientWrapper;

    @Autowired
    private ReactionService reactionService;

    @Test
    void testGetReaction() {
        ExchangeRatesResponse exchangeRatesResponse = new ExchangeRatesResponse();
        exchangeRatesResponse.setBase("Base");
        exchangeRatesResponse.setDisclaimer("Disclaimer");
        exchangeRatesResponse.setLicense("License");
        exchangeRatesResponse.setRates(new HashMap<>());
        exchangeRatesResponse.setTimestamp(10L);

        ExchangeRatesResponse exchangeRatesResponse1 = new ExchangeRatesResponse();
        exchangeRatesResponse1.setBase("Base");
        exchangeRatesResponse1.setDisclaimer("Disclaimer");
        exchangeRatesResponse1.setLicense("License");
        exchangeRatesResponse1.setRates(new HashMap<>());
        exchangeRatesResponse1.setTimestamp(10L);
        when(this.exchangeRatesFeignClientWrapper.getExchangeRatesByDate((String) any(), (String) any()))
                .thenReturn(exchangeRatesResponse1);
        when(this.exchangeRatesFeignClientWrapper.getLatestExchangeRates((String) any())).thenReturn(exchangeRatesResponse);
        assertThrows(CurrencyNotFoundException.class, () -> this.reactionService.getReaction("GBP"));
        verify(this.exchangeRatesFeignClientWrapper).getExchangeRatesByDate((String) any(), (String) any());
        verify(this.exchangeRatesFeignClientWrapper).getLatestExchangeRates((String) any());
    }

    @Test
    void testGetReaction2() {
        ExchangeRatesResponse exchangeRatesResponse = new ExchangeRatesResponse();
        exchangeRatesResponse.setBase("Base");
        exchangeRatesResponse.setDisclaimer("Disclaimer");
        exchangeRatesResponse.setLicense("License");
        exchangeRatesResponse.setRates(new HashMap<>());
        exchangeRatesResponse.setTimestamp(10L);
        when(this.exchangeRatesFeignClientWrapper.getExchangeRatesByDate((String) any(), (String) any()))
                .thenThrow(new CurrencyNotFoundException());
        when(this.exchangeRatesFeignClientWrapper.getLatestExchangeRates((String) any())).thenReturn(exchangeRatesResponse);
        assertThrows(CurrencyNotFoundException.class, () -> this.reactionService.getReaction("GBP"));
        verify(this.exchangeRatesFeignClientWrapper).getExchangeRatesByDate((String) any(), (String) any());
        verify(this.exchangeRatesFeignClientWrapper).getLatestExchangeRates((String) any());
    }
}

