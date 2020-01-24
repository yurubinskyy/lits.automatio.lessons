package com.lits.rubinskyy.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.lits.rubinskyy.json.Owner;
import com.lits.rubinskyy.lits.LitsHttpClient;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lits.rubinskyy.lits.LitsHttpClient.convert;

public class TestGetGoogle {

    private LitsHttpClient client = new LitsHttpClient();

    @Test
    public void testGetGoogle() throws IOException {

        // Initialize HTTP Client
        Response getGoogleResponse = client.GET("https://google.com");

        String responseContentType = getGoogleResponse.header("Content-Type");
        Assert.assertEquals(getGoogleResponse.code(), 200);
        Assert.assertEquals(responseContentType, "text/html; charset=ISO-8859-1");
    }

    @Test
    public void testGetCurrencyExchangeRate() throws IOException {

        String url = "https://bank.gov.ua/NBU_Exchange/exchange?date=20.01.2020&json";

        Response getNbuResponse = client.GET(url);

        CurrencyRate[] currencyRates = convert(
                        getNbuResponse,
                        CurrencyRate[].class);

        for (CurrencyRate currencyRate : currencyRates) {
            System.out.println(currencyRate);
        }

        // HOMEWORK WRITE ASSERTS
        // Check Currency rate by currency code
        // USD amount is 24.2527
    }

    @Test
    public void testLogin() throws IOException {

        // 1 DOWNLOAD POSTMAN
        // 2 REGISTER USER (via POSTMAN)
        // 3 ACTIVATE USER (via EMAIL)

        // 4 LOGIN USER (via JAVA CODE)

        // REQUEST URL
        String url = "https://europe-west2-search-app-263e2.cloudfunctions.net/webapp/api/auth/login";

        // REQUEST BODY
        HashMap<String, String> body = new HashMap<>();
        body.put("email", "drolgmaks+16@gmail.com");
        body.put("password", "Qwerty123456");

        // POST and receive response
        Response loginResponse = client.POST(url, Headers.of(), body);

        // 5 CONVERT RESPONSE TO JAVA MODEL
        final Map<String, Map<String, String>> loginResponseData = convert(loginResponse, Map.class);

        // EXTRACT TOKEN FROM RESPONSE
        String accessToken = loginResponseData.get("r").get("access_token");

        // ADD Authorization http header to GET request
        // Our response status is 200 OK
        Response searchResponse = client.GET("https://europe-west2-search-app-263e2.cloudfunctions.net/webapp/api/v1/search?q=",
                Headers.of("Authorization", "Bearer " + accessToken));

        Map searchResponseData = convert(searchResponse, Map.class);

        System.out.println(searchResponse.code());
        System.out.println(searchResponseData);
    }


    public static class CurrencyRate {
        @JsonProperty("StartDate")
        private String StartDate;
        @JsonProperty("TimeSign")
        private String TimeSign;
        @JsonProperty("CurrencyCode")
        private String CurrencyCode;
        @JsonProperty("CurrencyCodeL")
        private String CurrencyCodeL;
        @JsonProperty("Units")
        private int Units;
        @JsonProperty("Amount")
        private BigDecimal Amount;

        public String getStartDate() {
            return StartDate;
        }

        public void setStartDate(String startDate) {
            StartDate = startDate;
        }

        public String getTimeSign() {
            return TimeSign;
        }

        public void setTimeSign(String timeSign) {
            TimeSign = timeSign;
        }

        public String getCurrencyCode() {
            return CurrencyCode;
        }

        public void setCurrencyCode(String currencyCode) {
            CurrencyCode = currencyCode;
        }

        public String getCurrencyCodeL() {
            return CurrencyCodeL;
        }

        public void setCurrencyCodeL(String currencyCodeL) {
            CurrencyCodeL = currencyCodeL;
        }

        public int getUnits() {
            return Units;
        }

        public void setUnits(int units) {
            Units = units;
        }

        public BigDecimal getAmount() {
            return Amount;
        }

        public void setAmount(BigDecimal amount) {
            Amount = amount;
        }

        @Override
        public String toString() {
            return "CurrencyRate{" +
                    "StartDate='" + StartDate + '\'' +
                    ", TimeSign='" + TimeSign + '\'' +
                    ", CurrencyCode='" + CurrencyCode + '\'' +
                    ", CurrencyCodeL='" + CurrencyCodeL + '\'' +
                    ", Units='" + Units + '\'' +
                    ", Amount='" + Amount + '\'' +
                    '}';
        }


    }







    @Test
    public void test1() throws IOException {

        List<String> strings = Files.readAllLines(Paths.get("/testng-kick-off/src/test/resources/owner.json"));

        String s = strings
               .stream()
               .reduce((res, curr) -> res+=curr)
               .orElse("");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Owner owner = mapper.readValue(s, Owner.class);

        System.out.println();

    }




    @Test
    public void test2() throws IOException {

        List<String> strings = Files.readAllLines(Paths.get("/testng-kick-off/src/test/resources/owner-lits.json"));

        String s = strings
                .stream()
                .reduce((res, curr) -> res+=curr)
                .orElse("");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        CollectionType collectionType = mapper.getTypeFactory()
                .constructCollectionType(List.class, Owner.class);

        List<Owner> owner = mapper.readValue(s, collectionType);



        System.out.println();

    }






}
