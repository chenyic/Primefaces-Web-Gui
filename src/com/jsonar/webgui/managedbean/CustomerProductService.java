package com.jsonar.webgui.managedbean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "customerProductService", eager = true)
@ApplicationScoped
public class CustomerProductService implements Serializable {

    public List<Customer> getCustomers() {
        String customerSql = "Select customerNumber, customerName, contactLastName, contactFirstName from customers";
        ResultSet rs = null;
        PreparedStatement pst = null;
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = getConnection()) {
            pst = conn.prepareStatement(customerSql);
            pst.execute();
            rs = pst.getResultSet();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setNumber(rs.getInt("customerNumber"));
                customer.setName(rs.getString("customerName"));
                customer.setContactLastName(rs.getString("contactLastName"));
                customer.setContactFirstName(rs.getString("contactFirstName"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    public List<OrderProductDetails> getOrderProductDetails(int customerNumber) {
        String orderProductDetailsSql = "SELECT \n" +
                "    orders.orderNumber,\n" +
                "    orderdetails.orderLineNumber,\n" +
                "    orders.orderDate,\n" +
                "    orders.requiredDate,\n" +
                "    orders.shippedDate,\n" +
                "    orders.status,\n" +
                "    orders.comments,\n" +
                "    orderdetails.quantityOrdered,\n" +
                "    orderdetails.priceEach,\n" +
                "    products.productName,\n" +
                "    products.productScale\n" +
                "FROM\n" +
                "    orders\n" +
                "        INNER JOIN\n" +
                "    orderdetails ON orderdetails.orderNumber = orders.orderNumber\n" +
                "        INNER JOIN\n" +
                "    products ON products.productCode = orderdetails.productCode\n" +
                "WHERE\n" +
                "    orders.customerNumber = ?\n" +
                "ORDER BY orders.orderDate , orderdetails.orderLineNumber";
        ResultSet rs = null;
        PreparedStatement pst = null;
        List<OrderProductDetails> orderProductDetailsList = new ArrayList<>();
        try (Connection conn = getConnection()) {
            pst = conn.prepareStatement(orderProductDetailsSql);
            pst.setInt(1, customerNumber);
            pst.execute();
            rs = pst.getResultSet();

            while (rs.next()) {
                OrderProductDetails orderProductDetails = new OrderProductDetails();
                orderProductDetails.setOrderNumber(rs.getInt("orderNumber"));
                orderProductDetails.setOrderLineNumber(rs.getInt("orderLineNumber"));
                orderProductDetails.setOrderDate(rs.getString("orderDate"));
                orderProductDetails.setRequiredDate(rs.getString("requiredDate"));
                orderProductDetails.setShippedDate(rs.getString("shippedDate"));
                orderProductDetails.setOrderStatus(rs.getString("status"));
                orderProductDetails.setComments(rs.getString("comments"));
                orderProductDetails.setOrderedQuantity(rs.getInt("quantityOrdered"));
                orderProductDetails.setPricePerUnit(rs.getDouble("priceEach"));
                orderProductDetails.setProductName(rs.getString("productName"));
                orderProductDetails.setProductScale(rs.getString("productScale"));
                orderProductDetailsList.add(orderProductDetails);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderProductDetailsList;
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/classicmodels?user=root&password=1234&useSSL=false&serverTimezone=UTC");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
