package com.yunus.javafx.spring;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RootLayoutController implements Initializable {

    @FXML
    private BorderPane borderPane;
    
    @FXML
    private MenuItem customers;
    
    @Autowired
    protected AnnotationConfigApplicationContext applicationContext;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customers.setOnAction(this::loadCustomers);
    }    
    
    private void loadCustomers(ActionEvent event){
        try {
            Parent loader =
                    SpringFXMLLoader.create()
                            .applicationContext(applicationContext)
                            .location(getClass().getResource("/fxml/customers.fxml"))
                            .load();
            borderPane.setCenter(loader);
        } catch (IOException ex) {
            Logger.getLogger(RootLayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
