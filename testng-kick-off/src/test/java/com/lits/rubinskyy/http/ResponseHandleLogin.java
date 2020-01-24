package com.lits.rubinskyy.http;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseHandleLogin {
    public class R {
        @JsonProperty("r")
        private ResponseInfo r;

        public ResponseInfo getR() {
            return r;
        }

        public void setR(ResponseInfo r) {
            this.r = r;
        }

        @Override
        public String toString() {
            return "R{" +
                    "r=" + r +
                    '}';
        }
    }

    public static class ResponseInfo {
        @JsonProperty("access_token")
        private String AccessToken;
        @JsonProperty("scope")
        private String Scope;
        @JsonProperty("expires_in")
        private String ExpiresIn;
        @JsonProperty("token_type")
        private String TokenType;

        public String getAccessToken() {
            return AccessToken;
        }

        public void setAccessToken(String accessToken) {
            AccessToken = accessToken;
        }

        public String getScope() {
            return Scope;
        }

        public void setScope(String scope) {
            Scope = scope;
        }

        public String getExpiresIn() {
            return ExpiresIn;
        }

        public void setExpiresIn(String expiresIn) {
            ExpiresIn = expiresIn;
        }

        public String getTokenType() {
            return TokenType;
        }

        public void setTokenType(String tokenType) {
            TokenType = tokenType;
        }

        @Override
        public String toString() {
            return "ResponseHandleLogin{" +
                    "AccessToken='" + AccessToken + '\'' +
                    ", Scope='" + Scope + '\'' +
                    ", ExpiresIn='" + ExpiresIn + '\'' +
                    ", TokenType='" + TokenType + '\'' +
                    '}';
        }
    }
}
