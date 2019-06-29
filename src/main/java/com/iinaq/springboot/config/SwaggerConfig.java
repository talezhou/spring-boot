package com.iinaq.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {

        /**添加head参数start*/
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        //添加head参数end
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("com.developlee.HangZhou.ZheJiang")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)) //有该注解的生成doc
                .apis(RequestHandlerSelectors.basePackage("com.developlee.swagger"))   // 自行修改为自己的包路径
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars) //set Header
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DevelopLee的Swagger在线文档")
                .description("嗯哼嗯哼额~~~swagger文档很强！")
                .termsOfServiceUrl("http://github.com/developlee")
                .version("1.0")
                .build();
    }
}
