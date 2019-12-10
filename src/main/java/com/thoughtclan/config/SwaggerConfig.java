package com.thoughtclan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

/**
 * @author thimmv
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    final static String TRADE_MARK = "\u2122";

    public static final Contact DEFAULT_CONTACT = new Contact(
            "VINODH" + TRADE_MARK,
            "https://www.github.com",
            "vinodh5052@gmail.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "VINODH " + TRADE_MARK,
            "VINODH" + TRADE_MARK,
            "v1",
            "VINODH related terms" + TRADE_MARK,
            DEFAULT_CONTACT,
            "License details",
            "http://www.vinodh.com/licenses",
            Collections.emptyList());

    @Bean
    public Docket slotBookingAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(basePackage("com.thoughtclan.web"))
                .paths(PathSelectors.ant("/v1/*/**"))
                .build()
                .apiInfo(DEFAULT_API_INFO);
    }
}