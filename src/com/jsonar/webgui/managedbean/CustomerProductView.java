package com.jsonar.webgui.managedbean;

import org.primefaces.event.ToggleEvent;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "customerProductView")
@ViewScoped
public class CustomerProductView implements Serializable {
    private Customer selectedCustomer;

    @ManagedProperty("#{customerProductService}")
    private CustomerProductService customerProductService;

    public List<Customer> getCustomers() {
        return customerProductService.getCustomers();
    }

    public void onRowToggle(ToggleEvent toggleEvent) {
        this.selectedCustomer = (Customer) toggleEvent.getData();
    }

    public List<OrderProductDetails> getOrderProductDetails() {
        if (selectedCustomer == null) {
            return new ArrayList<>();
        }
        return customerProductService.getOrderProductDetails(selectedCustomer.getNumber());
    }

    public void setCustomerProductService(CustomerProductService customerProductService) {
        this.customerProductService = customerProductService;
    }
}
