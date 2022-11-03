package br.com.futurodev.semana3.springfox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SpringFoxConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.SWAGGER_2) // informa qual o tipo de documentação que vamos usar
                .select() // retornamos um builder para selecionar os endpoints que deve ser expostos
                .apis(RequestHandlerSelectors.any()) // especificar o que queremos, e quais controladores, endpoints que o springfox orá scanear
                .build() // montamos nosso sumário Docket
                .apiInfo(metaData());
//                .tags(new Tag("Clientes","api sobre cliente"),
//                      new Tag("Forma de Pagamento","api forma de pagamento"));

    }

    private ApiInfo metaData(){
        return new ApiInfoBuilder()
                .title("Spring Boot REST API")
                .description("Exercícios semana 3 e 4 - FuturoDev - REST API ")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();

    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html") // informamos o arquivo a ser renderizado no navegador
                .addResourceLocations("classpath:/META-INF/resources/"); // informamos o caminho do arquivo

        registry.addResourceHandler("/webjars/**") // informa outros arquivos de css, js, png dentre outros
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}
