package com.yunus.javafx.spring;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ListChangeListener;
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
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
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
    
    @Autowired
    protected AnnotationConfigApplicationContext applicationContext;
    @Autowired
    protected Stage primaryStage;
    @Autowired
    private CustomerDataModel customerDataModel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clmFirstName.setCellValueFactory(new PropertyValueFactory("firstName"));
        clmSecondName.setCellValueFactory(new PropertyValueFactory("secondName"));
        clmPhone.setCellValueFactory(new PropertyValueFactory("phone"));
        addCustomer.setOnAction(this::addCustomerActionEvent);
        tblCustomers.setItems(customerDataModel.getCustomerList());
        tblCustomers.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> customerDataModel.setCurrentSelectedCustomer(newSelection));
        customerDataModel.newCustomerProperty().addListener((obs, oldCustomer, newCustomer) -> {
            if( newCustomer != null ){
                System.out.println("New route added");
                System.out.println(newCustomer);
            }
        });
        
        customerDataModel.getCustomerList().addListener(new ListChangeListener<Customer>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Customer> c) {
                while(c.next()){
                    if(c.wasAdded()){
                        System.out.println("======================================");
                        for(Customer customer: c.getAddedSubList() ){
                            System.out.println("customer "+customer.getFirstName());
                        }
                        System.out.println("======================================");
                    }
                }
            }
        });
    }    
    
    @PostConstruct
    public void loadCustomers(){ customerDataModel.loadCustomers(); }
    
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
    
    @PreDestroy
    public void destroyCustomerController(){
        System.out.println("CustomersController destroyed");
    }
}
