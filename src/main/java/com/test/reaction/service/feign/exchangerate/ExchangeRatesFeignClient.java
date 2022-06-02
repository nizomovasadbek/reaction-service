package com.test.reaction.service.feign.exchangerate;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "exchange-rates-client",
        url = "${test.reaction.service.exchange-rates-service}")
public interface ExchangeRatesFeignClient {

    @GetMapping("/latest.json")
    ExchangeRatesResponse getLatestExchangeRates(@RequestParam String app_id,
                                                 @RequestParam String symbol);

    @GetMapping("/historical/{date}")
    ExchangeRatesResponse getExchangeRatesByDate(@PathVariable String date,
                                                 @RequestParam String app_id,
                                                 @RequestParam String symbol);


}
