package com.yunus.javafx.spring;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainApp extends Application {
    private AnnotationConfigApplicationContext applicationContext;
    private Stage primaryStage;
    
    @Override
    public void start(Stage stage) throws Exception {
        applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        primaryStage = applicationContext.getBean(Stage.class);
        SpringFXMLLoader<Parent, RootLayoutController> loader =
            SpringFXMLLoader.create()
                .applicationContext(applicationContext)
                .location(getClass().getResource("/fxml/rootLayout.fxml"))
                .build();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
