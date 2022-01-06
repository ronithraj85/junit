package com.ronith.junit5.bo;

import com.ronith.junit5.bo.exception.BOException;
import com.ronith.junit5.dao.OrderDAO;
import com.ronith.junit5.dto.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderBOImplTest {

    public static final int ORDER_ID = 123;

    @Mock
    OrderDAO dao;

    @InjectMocks
    private OrderBOImpl orderBO;

    @Test
    void placeOrder_Should_Create_Order_Pass() throws SQLException, BOException {
         Order order = new Order();
        when(dao.createOrder(any(Order.class))).thenReturn(new Integer(1));
        boolean result = orderBO.placeOrder(order);
        assertTrue(result);
        verify(dao).createOrder(any(Order.class));
    }

    @Test
    void placeOrder_Should_Create_Order_Fail() throws SQLException, BOException {
        Order order = new Order();
        when(dao.createOrder(any(Order.class))).thenReturn(new Integer(0));
        boolean result = orderBO.placeOrder(order);
        assertFalse(result);
        verify(dao).createOrder(any(Order.class));
    }

    @Test
    void placeOrder_Should_Create_Order_And_Throw_Exception() throws SQLException, BOException {
        Order order = new Order();
        when(dao.createOrder(any(Order.class))).thenThrow(SQLException.class);
        assertThrows(BOException.class, ()-> orderBO.placeOrder(order));
    }

    @Test
    void cancelOrder_Should_Cancel_Order_Pass() throws SQLException, BOException {
        Order order = new Order();
        when(dao.readOrder(anyInt())).thenReturn(order);
        when(dao.updateOrder(any(Order.class))).thenReturn(1);
        boolean result = orderBO.cancelOrder(ORDER_ID);
        assertTrue(result);
        verify(dao).readOrder(anyInt());
        verify(dao).updateOrder(any(Order.class));
    }

    @Test
    void cancelOrder_Should_Cancel_Order_Fail() throws SQLException, BOException {
        Order order = new Order();
        when(dao.readOrder(anyInt())).thenReturn(order);
        when(dao.updateOrder(any(Order.class))).thenReturn(0);
        boolean result = orderBO.cancelOrder(ORDER_ID);
        assertFalse(result);
        verify(dao).readOrder(anyInt());
        verify(dao).updateOrder(any(Order.class));
    }

    @Test
    void cancelOrder_Should_Cancel_Order_And_Throw_BOException_On_Read() throws SQLException {
        when(dao.readOrder(anyInt())).thenThrow(SQLException.class);
       assertThrows(BOException.class, ()->orderBO.cancelOrder(ORDER_ID));
    }

    @Test
    void cancelOrder_Should_Cancel_Order_And_Throw_BOException_On_Update() throws SQLException {
        Order order = new Order();
        when(dao.readOrder(anyInt())).thenReturn(order);
        when(dao.updateOrder(any(Order.class))).thenThrow(SQLException.class);
        assertThrows(BOException.class,()->orderBO.cancelOrder(ORDER_ID));
    }

    @Test
    void deleteOrder_Should_Delete_Order_Pass() throws SQLException, BOException {
        when(dao.deleteOrder(anyInt())).thenReturn(1);
        boolean result = orderBO.deleteOrder(ORDER_ID);
        assertTrue(result);
        verify(dao).deleteOrder(anyInt());
    }

    @Test
    void deleteOrder_Should_Delete_Order_Fail() throws SQLException, BOException {
        when(dao.deleteOrder(anyInt())).thenReturn(0);
        boolean result = orderBO.deleteOrder(ORDER_ID);
        assertFalse(result);
        verify(dao).deleteOrder(anyInt());
    }

    @Test
    void deleteOrder_Should_Throw_BOException_on_Delete() throws SQLException {
        doThrow(SQLException.class).when(dao).deleteOrder(anyInt());
        assertThrows(BOException.class, ()->orderBO.deleteOrder(ORDER_ID));
    }


}