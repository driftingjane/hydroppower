package com.mountainside.hydroppower.backendserver.common.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author : sxj
 * @Date : 2018/11/29 15:23
 * @Version : 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swaggerUrl}")
    private String swaggerUrl;
    /**
     * UI页面显示信息
     */
    private final String SWAGGER2_API_BASE_PACKAGE = "com.mountainside.hydroppower.backendserver";
    private final String SWAGGER2_API_TITLE = "backen-server-API";
    private final String SWAGGER2_API_DESCRIPTION = "com.mountainside.hydroppower.backendserver";
    private final String SWAGGER2_API_VERSION = "1.0";
    /**
     * createRestApi
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .host(swaggerUrl)
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER2_API_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }
    /**
     * apiInfo
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(SWAGGER2_API_TITLE)
                .description(SWAGGER2_API_DESCRIPTION)
                .version(SWAGGER2_API_VERSION)
                .build();
    }
}
