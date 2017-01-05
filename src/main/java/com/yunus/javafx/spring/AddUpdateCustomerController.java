package com.yunus.javafx.spring;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class AddUpdateCustomerController implements Initializable {

    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtSecondName;
    @FXML
    private TextField txtPhone;
    
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSave.setOnAction(this::handleSaveCustomer);
    }    
    
    private void handleSaveCustomer(ActionEvent event){
        String firstName = txtFirstName.getText();
        String secondName = txtSecondName.getText();
        String phone = txtPhone.getText();
        
        Customer c = new Customer(1, firstName, secondName, phone);
        AddCustomerEvent addCustomerEvent = new AddCustomerEvent(c);
        applicationEventPublisher.publishEvent(addCustomerEvent);
    }
}
