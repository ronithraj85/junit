package com.ronith.junit5.bo;

import com.ronith.junit5.bo.exception.BOException;
import com.ronith.junit5.dto.Order;

public interface OrderBO {

    boolean placeOrder(Order order) throws BOException;

    boolean cancelOrder(int id) throws BOException;

    boolean deleteOrder(int id) throws BOException;

}
