package com.viajescarolina.promotions.infra.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Response de Facebook API para obtener cuentas/páginas del usuario.
 * GET /v24.0/me/accounts
 */
public class FacebookAccountsResponse {

    @JsonProperty("data")
    public List<FacebookAccount> data;

    @JsonProperty("paging")
    public PagingInfo paging;

    public static class FacebookAccount {
        @JsonProperty("id")
        public String id;

        @JsonProperty("name")
        public String name;

        @JsonProperty("access_token")
        public String accessToken;

        public FacebookAccount() {}

        public FacebookAccount(String id, String name, String accessToken) {
            this.id = id;
            this.name = name;
            this.accessToken = accessToken;
        }

        @Override
        public String toString() {
            return "FacebookAccount{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static class PagingInfo {
        @JsonProperty("cursors")
        public CursorInfo cursors;

        @JsonProperty("next")
        public String next;

        public static class CursorInfo {
            @JsonProperty("before")
            public String before;

            @JsonProperty("after")
            public String after;
        }
    }

    public FacebookAccountsResponse() {}

    public FacebookAccountsResponse(List<FacebookAccount> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FacebookAccountsResponse{" +
                "data=" + data +
                '}';
    }
}

