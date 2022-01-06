package com.junit.scrapbook;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ATest {

    @Mock
    B b;

    @InjectMocks
    A a;

    @Test
    public void usesVoidMethod_Should_Call_VoidMethod() throws Exception {
        doNothing().when(b).voidMethod();
        Assertions.assertEquals(1, a.usesVoidMethod());
        verify(b).voidMethod();
    }

    @Test
    public void usesVoidMethod_Should_Throw_RuntimeException() throws Exception {
        doThrow(Exception.class).when(b).voidMethod();
        Assertions.assertThrows(RuntimeException.class, ()->a.usesVoidMethod());
    }

    @Test
    public void test_Consecutive_Calls() throws Exception {
        doNothing().doThrow(Exception.class).when(b).voidMethod();
        a.usesVoidMethod();
        verify(b).voidMethod();
        Assertions.assertThrows(RuntimeException.class, ()->a.usesVoidMethod());
    }
}