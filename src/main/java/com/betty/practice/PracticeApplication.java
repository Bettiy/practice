package com.betty.practice;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author Betty
 */
@SpringBootApplication
public class PracticeApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(PracticeApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

}
