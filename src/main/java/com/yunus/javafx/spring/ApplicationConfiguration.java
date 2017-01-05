package com.yunus.javafx.spring;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = 
        {"com.yunus.javafx.spring"})
public class ApplicationConfiguration {
    @Bean(name = "primaryStage")
    public Stage getPrimaryStage() {
        return new Stage(StageStyle.DECORATED);
    }
}
