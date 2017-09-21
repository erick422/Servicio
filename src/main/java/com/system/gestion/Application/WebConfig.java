package com.system.gestion.Application;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.system.gestion.Controller","com.system.gestion.Service","com.system.gestion.Dao","com.system.gestion.Model","com.system.gestion.Utils"})
public class WebConfig extends WebMvcConfigurerAdapter {

    private int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(0);
        return messageSource;
    }

    // additional webmvc-related beans
    /*@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/paginas/");
        resolver.setSuffix(".jsp");
       /// resolver.setCache(false);
       /// resolver.setViewClass(JstlView.class);
        return resolver;
    }*/

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/paginas/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     * Supports FileUploads.
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(50000000);
        return multipartResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("locale");
        return localeChangeInterceptor;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("en_US"));
        return localeResolver;
    }

    // implementing WebMvcConfigurer
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

   /* @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJacksonHttpMessageConverter());
    }*/

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(0);
        factory.setValidationMessageSource(messageSource);
        return factory;
    }

    @Bean
    public CommonsMultipartResolver MultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(maxUploadSizeInMb * 2);
        multipartResolver.setMaxInMemorySize(maxUploadSizeInMb); //bytes
        return multipartResolver;
    }

}