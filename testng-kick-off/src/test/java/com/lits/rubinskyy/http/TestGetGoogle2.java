package com.lits.rubinskyy.http;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.lits.rubinskyy.lits.LitsHttpClient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class TestGetGoogle2 {

    private LitsHttpClient client = new LitsHttpClient();
    @Test
    public void testGetGoogle() throws IOException {
        //Initialize HTTP Client
        Response getGoogleResponse =client.GET("https://google.com");

        String stringResponse = getGoogleResponse.body().string();

        Assert.assertEquals(getGoogleResponse.code(), 200);

        int code = getGoogleResponse.code();

        System.out.println("Status:" + code);
        System.out.println("Header:" + getGoogleResponse.headers());
        System.out.println("Body:" + stringResponse);
    }

    @Test
    public void testGetGoogle1() throws IOException {
        //Initialize HTTP Client
        OkHttpClient okHttpClient = new OkHttpClient();

        // Prepare HTTP request
        Request getGoogleRequest = new Request.Builder()
                .url("https://www.google.com/").build();

        // Execute Request / Obtain response
        Response getGoogleResponse = okHttpClient.newCall(getGoogleRequest).execute();

        String stringResponse = getGoogleResponse.body().string();

        Assert.assertEquals(getGoogleResponse.code(), 200);


        int code = getGoogleResponse.code();

        System.out.println("Status:" + code);
        System.out.println("Header:" + getGoogleResponse.headers());
        System.out.println("Body:" + stringResponse);
    }

    @Test
    public void testGetCurrencyExchangeRate() throws IOException {
        //Initialize HTTP Client
        Response getNbuResponse = client.GET("https://bank.gov.ua/NBU_Exchange/exchange?date=20.01.2020&json");

        CurrencyRate[] currencyRates = LitsHttpClient
                .convert(getNbuResponse,
                        CurrencyRate[].class);

        for (CurrencyRate currencyRate: currencyRates){
            System.out.println(currencyRate);
        }
    }

    @Test
    public void testCheckUsdRate() throws IOException {
        //Initialize HTTP Client
        OkHttpClient okHttpClient = new OkHttpClient();

        // Prepare HTTP request
        Request getNbuRequest = new Request.Builder()
                .url("https://bank.gov.ua/NBU_Exchange/exchange?date=20.01.2020&json")
                .build();

        // Execute Request / Obtain response

        Response getNbuResponse = okHttpClient
                .newCall(getNbuRequest)
                .execute();

        // Create
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // Response string
        String string = getNbuResponse.body().string();

        // Tells Jackson how to read array
        CollectionType collectionType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, CurrencyRate.class);


        List<CurrencyRate> currencyRates = objectMapper
                .readValue(string, collectionType);

        for (CurrencyRate currencyRate: currencyRates){
            if(currencyRate.getCurrencyCodeL().equals("USD")) {
                Assert.assertEquals(currencyRate.getAmount(), BigDecimal.valueOf(24.2527));
            }
        }

    }
}
