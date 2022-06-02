package com.test.reaction.service;

import com.test.reaction.dto.GiphyType;
import com.test.reaction.exception.cases.CurrencyNotFoundException;
import com.test.reaction.service.feign.exchangerate.ExchangeRatesFeignClientWrapper;
import com.test.reaction.service.feign.giphy.GiphyFeignClientWrapper;
import com.test.reaction.service.feign.giphy.GiphyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ReactionService {

    private final ExchangeRatesFeignClientWrapper exchangeRatesClient;
    private final GiphyFeignClientWrapper giphyClient;
    public GiphyResponse getReaction(String currency) {

        Double latestRate = exchangeRatesClient
                .getLatestExchangeRates(currency).getRates().get(currency);

        Double rateOfPastDay = exchangeRatesClient
                .getExchangeRatesByDate(getLastDateToPath(), currency).getRates().get(currency);

        return getGiphyResponse(latestRate, rateOfPastDay);
    }

    private GiphyResponse getGiphyResponse(Double latestRate, Double rateOfPastDay) {
        GiphyResponse result;
        if (latestRate != null && rateOfPastDay != null){
            if (latestRate > rateOfPastDay){
                result = giphyClient.getGiphy(GiphyType.RICH);
            }else {
                result = giphyClient.getGiphy(GiphyType.BROKE);
            }
        }else{
            throw new CurrencyNotFoundException();
        }
        return result;
    }

    private String getLastDateToPath(){
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       return LocalDate.now().minusDays(1).format(formatter) + ".json";
    }

}
