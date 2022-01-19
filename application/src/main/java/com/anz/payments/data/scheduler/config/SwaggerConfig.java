package com.anz.payments.data.scheduler.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author : Fucheng Li
 * @since : 4/11/2021, Thu
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Scheduler API",
                "Scheduler API",
                "API",
                "Terms of service",
                new Contact("", "", ""),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.anz.payments.data.scheduler"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * SwaggerUI information
     */

    @Bean
    UiConfiguration uiConfig() {

        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.MODEL)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.METHOD)
                .showExtensions(false)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }
}
