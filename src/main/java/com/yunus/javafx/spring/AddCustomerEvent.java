package com.yunus.javafx.spring;

public class AddCustomerEvent {
    private Customer customer;

    public AddCustomerEvent(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
    
    
}
