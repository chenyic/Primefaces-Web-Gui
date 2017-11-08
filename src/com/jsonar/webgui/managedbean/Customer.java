package com.jsonar.webgui.managedbean;

import java.util.List;

public class Customer {
    private int number;
    private String name;
    private String contactLastName;
    private String contactFirstName;
    private List<OrderProductDetails> orderProductDetailsList;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public List<OrderProductDetails> getOrderProductDetailsList() {
        return orderProductDetailsList;
    }

    public void setOrderProductDetailsList(List<OrderProductDetails> orderProductDetailsList) {
        this.orderProductDetailsList = orderProductDetailsList;
    }
}
