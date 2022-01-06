package com.ronith.junit5.bo;

import com.ronith.junit5.bo.exception.BOException;
import com.ronith.junit5.dao.OrderDAO;
import com.ronith.junit5.dto.Order;

import java.sql.SQLException;

public class OrderBOImpl implements OrderBO{

    private OrderDAO orderDAO;

    @Override
    public boolean placeOrder(Order order) throws BOException {
        try {
            int result = orderDAO.createOrder(order);
            if(result==0){
                return  false;
            }

        } catch (SQLException e) {
            throw new BOException(e);
        }
        return true;
    }

    @Override
    public boolean cancelOrder(int id) throws BOException {
        try {
             Order order = orderDAO.readOrder(id);
             order.setStatus("Cancelled");
            final int i = orderDAO.updateOrder(order);
            if(i==0){
                return false;
            }
        } catch (SQLException e) {
            throw new BOException(e);
        }
        return true;
    }

    @Override
    public boolean deleteOrder(int id) throws BOException {
        try {
            final int result = orderDAO.deleteOrder(id);
            if(result==0){
                return false;
            }
        } catch (SQLException e) {
           throw new BOException(e);
        }
        return true;
    }

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
}
