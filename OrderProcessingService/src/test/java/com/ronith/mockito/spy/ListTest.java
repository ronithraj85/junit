package com.ronith.mockito.spy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class ListTest {

    @Mock
    List<String> myList = new ArrayList<>();

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test(){
        myList.add("Ronith");
        myList.add("Raj");
         when(myList.get(0)).thenReturn("Rambo");
        when(myList.size()).thenReturn(3);
        Assertions.assertEquals("Rambo", myList.get(0));
        Assertions.assertEquals(3, myList.size());
    }
}
