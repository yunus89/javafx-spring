package com.yunus.javafx.spring;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataModel {
    private final ObservableList<Customer> customerList = FXCollections.observableArrayList();

    private final ObjectProperty<Customer> currentSelectedCustomer = new SimpleObjectProperty<>(null);
    private final ObjectProperty<Customer> newCustomer = new SimpleObjectProperty<>(null);

    public ObjectProperty<Customer> currentSelectedCustomerProperty() {
        return currentSelectedCustomer ;
    }

    public final Customer getCurrentSelectedCustomer() {
        return currentSelectedCustomerProperty().get();
    }

    public final void setCurrentSelectedCustomer(Customer customer) {
        currentSelectedCustomerProperty().set(customer);
    }
    
    public ObjectProperty<Customer> newCustomerProperty() {
        return newCustomer;
    }

    public final Customer getNewCustomer() {
        return newCustomerProperty().get();
    }

    public final void setNewCustomer(Customer customer) {
        newCustomerProperty().set(customer);
    }

    public ObservableList<Customer> getCustomerList() {
        return customerList;
    }
    
    public void loadCustomers(){
        customerList.setAll(getCustomers());
    }
    
    // Datasource
    private List<Customer> getCustomers(){
        List<Customer> l = new ArrayList<>();
        l.add(new Customer(1, "Name 1", "Second 1", "001"));
        l.add(new Customer(2, "Name 2", "Second 2", "002"));
        l.add(new Customer(1, "Name 3", "Second 3", "003"));
        l.add(new Customer(1, "Name 4", "Second 4", "004"));
        return l;
    }
}
