package com.cognizant.mockito;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class VerifyInteractionTest {

    @Test
    public void testVerifyInteraction() {

        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        MyService myService = new MyService(mockApi);

        myService.fetchData();

        verify(mockApi).getData();
    }
}