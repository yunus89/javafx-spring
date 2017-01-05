package com.yunus.javafx.spring;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CustomersController implements Initializable {

    @FXML
    private TableView<Customer> tblCustomers;
    @FXML
    private TableColumn<Customer, String> clmFirstName;
    @FXML
    private TableColumn<Customer, String> clmSecondName;
    @FXML
    private TableColumn<Customer, String> clmPhone;
    @FXML
    private Button addCustomer;

    private final ObservableList<Customer> customerList = FXCollections.observableArrayList();
    private final ListProperty<Customer> customerListProperty = new SimpleListProperty<>();
    
    @Autowired
    protected AnnotationConfigApplicationContext applicationContext;
    @Autowired
    protected Stage primaryStage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clmFirstName.setCellValueFactory(new PropertyValueFactory("firstName"));
        clmSecondName.setCellValueFactory(new PropertyValueFactory("secondName"));
        clmPhone.setCellValueFactory(new PropertyValueFactory("phone"));
        customerListProperty.set(customerList);
        tblCustomers.itemsProperty().bind(customerListProperty);
        addCustomer.setOnAction(this::addCustomerActionEvent);
        
        customerList.add(new Customer(1, "Name 1", "Second 1", "001"));
        customerList.add(new Customer(2, "Name 2", "Second 2", "002"));
        customerList.add(new Customer(1, "Name 3", "Second 3", "003"));
        customerList.add(new Customer(1, "Name 4", "Second 4", "004"));
        
    }    
    
    private void addCustomerActionEvent(ActionEvent event){
        try {
            Parent loader
                    = SpringFXMLLoader.create()
                            .applicationContext(applicationContext)
                            .location(getClass().getResource("/fxml/addUpdateCustomer.fxml"))
                            .load();
            Stage addUpdateCustomerModal = new Stage();
            addUpdateCustomerModal.initOwner(primaryStage);
            addUpdateCustomerModal.centerOnScreen();
            addUpdateCustomerModal.setScene(new Scene(loader));
            addUpdateCustomerModal.show();
        } catch (IOException ex) {
            Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @EventListener
    public void addCustomerEventListener(AddCustomerEvent addCustomerEvent){
        System.out.println("Heey");
        customerList.add(addCustomerEvent.getCustomer());
        customerList.add(new Customer(1, "Name 5", "Second 5", "005"));
    }
}
