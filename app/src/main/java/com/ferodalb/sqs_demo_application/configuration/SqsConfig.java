package com.ferodalb.sqs_demo_application.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsAsyncClientBuilder;

import java.net.URI;

@Configuration
public class SqsConfig {

    @Value("${aws.sqs.endpoint-url:}")
    private String sqsEndpointUrl;

    @Value("${aws.region:us-east-1}")
    private String region;

    @Bean
    public SqsAsyncClient sqsAsyncClient(){
        SqsAsyncClientBuilder sqsAsyncClientBuilder = SqsAsyncClient.builder()
                .region(Region.of(region))
                .credentialsProvider(DefaultCredentialsProvider.create());

        if (StringUtils.hasText(sqsEndpointUrl)){
            sqsAsyncClientBuilder.endpointOverride(URI.create(sqsEndpointUrl));
        }

        return sqsAsyncClientBuilder.build();
    }

}
