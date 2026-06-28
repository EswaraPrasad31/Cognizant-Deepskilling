package com.cognizant.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ArgumentMatchingTest {

    @Test
    public void testArgumentMatching() {

        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        when(mockApi.getData(eq("Eswar")))
                .thenReturn("Welcome Eswar");

        MyService myService = new MyService(mockApi);

        String result = myService.fetchData("Eswar");

        assertEquals("Welcome Eswar", result);

        verify(mockApi).getData(eq("Eswar"));
    }
}