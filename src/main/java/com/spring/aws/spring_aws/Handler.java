package com.spring.aws.spring_aws;

import java.io.*;
import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.*;
import com.amazonaws.serverless.proxy.spring.*;
import com.amazonaws.services.lambda.runtime.*;

public class Handler implements RequestStreamHandler {
    private static final SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    static {
        try {
            handler = new SpringBootProxyHandlerBuilder<AwsProxyRequest>()
                    .defaultProxy()
                    .asyncInit()
                    .springBootApplication(SpringAWSApplication.class)
                    .buildAndInitialize();
        } catch (ContainerInitializationException e) {
            // if we fail here, we re-throw the exception to force another cold start
            e.printStackTrace();
            throw new RuntimeException("Could not initialize Spring Boot application", e);
        }
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
            throws IOException {
        handler.proxyStream(inputStream, outputStream, context);
    }
}