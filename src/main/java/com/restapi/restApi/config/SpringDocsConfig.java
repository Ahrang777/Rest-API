package com.restapi.restApi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Swagger3: http://localhost:8080/swagger-ui/index.html
/*@Configuration
public class SpringDocsConfig {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String version) {
        Info info = new Info().title("Rest API 문서")
                .version(version)
                .description("잘못된 부분이나 오류 발생 시 바로 말씀해주세요.")
                .contact(new Contact()
                        .name("JungIn Park")
                        .email("wjddlsee@gmail.com"));

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

    *//*@Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.restapi.restApi.api"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring API Document")
                .description("웹 서버 API 설명을 위한 문서입니다.")
                .version("1.0")
                .build();
    }*//*
}*/
