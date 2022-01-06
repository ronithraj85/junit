package com.ronith.junit5.dao;

import com.ronith.junit5.dto.Order;

import java.sql.SQLException;

public class OrderDAOImpl implements OrderDAO{
    @Override
    public int createOrder(Order order) throws SQLException {
        return 0;
    }

    @Override
    public Order readOrder(int id) throws SQLException {
        return null;
    }

    @Override
    public int updateOrder(Order order) throws SQLException {
        return 0;
    }

    @Override
    public int deleteOrder(int id) throws SQLException {
        return 0;
    }
}
