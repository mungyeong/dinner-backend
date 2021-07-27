package com.github.gyeong5961.dinner.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("종각 음식점 추천 사이트")
                .description("API")
                .build();
    }

    @Bean
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api")
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.github.gyeong5961.dinner.controller"))
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

}
