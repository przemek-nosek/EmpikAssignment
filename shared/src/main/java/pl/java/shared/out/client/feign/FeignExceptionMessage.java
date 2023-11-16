package pl.java.shared.out.client.feign;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
record FeignExceptionMessage(
        String timestamp,
        int status,
        String error,
        String message,
        String path,
        String url,
        String requestUri,
        String key
) {
}
