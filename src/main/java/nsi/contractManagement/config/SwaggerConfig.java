package nsi.contractManagement.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

/** @author Tao */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.OAS_30)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
        .paths(PathSelectors.any())
        .build();
  }
  private List<SecurityScheme> security() {
    ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
    return Collections.singletonList(apiKey);
  }
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("合同管理系统")
        .description("接口文档")
        .contact(new Contact("Tao", "", ""))
        .version("1.0")
        .build();
  }
}
