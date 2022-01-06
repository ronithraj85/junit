package com.ronith.junit5.dao;

import com.ronith.junit5.dto.Order;

import java.sql.SQLException;

public interface OrderDAO {

    int createOrder(Order order) throws SQLException;

    Order readOrder(int id) throws SQLException;

    int updateOrder(Order order) throws SQLException;

    int deleteOrder(int id) throws SQLException;

}
