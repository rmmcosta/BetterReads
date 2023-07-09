package com.rmmcosta.betterreadsbootstrap;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Configuration
public class AppConfig {
    /*
     * Use the standard Cassandra driver API to create a com.datastax.oss.driver.api.core.CqlSession instance.
     */
    public @Bean CqlSession session() {
        return CqlSession.builder()
                .withCloudSecureConnectBundle(Paths.get("src/main/resources/secure-connect.zip"))
                .withAuthCredentials("mtgnCvjeYBSHaEXbnnqmxsJl","mHqiZb,EX2B39Unia0_gApD4zh4KMigzZPeTaPMSUjWzLxFYpqfXWecZ3gnnv,.q6jMKDCxtdeyR6WwvKe.hKbGBTkXGPJCotRIt+Df4L33c.Yl+Yg0sc-81+pTzNNP4")
                .build();
    }
}
