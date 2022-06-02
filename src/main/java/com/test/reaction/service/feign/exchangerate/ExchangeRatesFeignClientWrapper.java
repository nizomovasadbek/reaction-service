package com.test.reaction.service.feign.exchangerate;

import com.test.reaction.exception.FeignErrorWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.text.MessageFormat.format;

@Component
@RequiredArgsConstructor
public class ExchangeRatesFeignClientWrapper {

    private static final String EXCHANGE_RATES_CLIENT_ERROR_CODE = "exchange.rates.client.error";
    private final ExchangeRatesFeignClient feignClient;
    private final FeignErrorWrapper errorWrapper;

    @Value("${test.reaction.exchange-rate.app-id}")
    private String APP_ID;

    public ExchangeRatesResponse getLatestExchangeRates(String symbol) {
        return errorWrapper.wrapException(
                () -> feignClient.getLatestExchangeRates(APP_ID, symbol),
                format("Getting exchange rates by symbol: {0}", symbol),
                EXCHANGE_RATES_CLIENT_ERROR_CODE);
    }

    public ExchangeRatesResponse getExchangeRatesByDate(String date, String symbol) {
        return errorWrapper.wrapException(
                () -> feignClient.getExchangeRatesByDate(date, APP_ID, symbol),
                format("Getting exchange rates by symbols: {0}", symbol),
                EXCHANGE_RATES_CLIENT_ERROR_CODE);
    }

}
