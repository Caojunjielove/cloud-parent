package org.cjj;

import org.cjj.filter.MessageDecryptFilter;
import org.cjj.filter.MessageEncryptFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public MessageDecryptFilter messageDecryptFilter(){
    	return new MessageDecryptFilter();
    }
    @Bean
    public MessageEncryptFilter messageEncryptFilter(){
    	return new MessageEncryptFilter();
    }
}