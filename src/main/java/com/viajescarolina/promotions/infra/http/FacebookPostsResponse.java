package com.viajescarolina.promotions.infra.http;

import java.util.List;
import java.util.Map;

public record FacebookPostsResponse(
        List<PostData> data,
        Map<String, Object> paging
) {
    public record PostData(
            String id,
            String message,
            String created_time,
            String full_picture,
            String permalink_url
    ) {}
}
