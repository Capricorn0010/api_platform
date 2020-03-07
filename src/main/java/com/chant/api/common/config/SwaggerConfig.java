package com.chant.api.common.config;

import com.chant.api.common.constant.CommonConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置
 *

 **/

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket createBackendApi() {
		ParameterBuilder tokenPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<>();
		tokenPar.name("Authorization")
				.description("Token")
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.required(false)
				.defaultValue(null)
				.build();
		pars.add(tokenPar.build());

		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("后台")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage(CommonConstant.BASE_PACKAGE))
				.paths(PathSelectors.any())
				.build()
				.globalOperationParameters(pars);
	}

	// 基本信息
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("个人测试-API")
				.description("create by capricorn")
				.version("1.0")
				.build();
	}

}
