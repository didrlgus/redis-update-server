package com.shoppingmall;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class RedisUpdateServerApplication {

    private static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application-real.properties,"
            + "/home/ec2-user/app/config/springboot-webservice/real-redis-update-server-application.yml";
            //+ "C:\\config\\real-redis-update-server-application.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(RedisUpdateServerApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }
}
