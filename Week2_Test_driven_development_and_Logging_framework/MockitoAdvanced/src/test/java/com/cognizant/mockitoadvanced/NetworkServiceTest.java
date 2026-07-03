package com.cognizant.mockitoadvanced;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class NetworkServiceTest {

    @Test
    public void testServiceWithMockNetworkClient() {

        NetworkClient mockNetworkClient = mock(NetworkClient.class);

        when(mockNetworkClient.connect())
                .thenReturn("Mock Connection");

        NetworkService networkService =
                new NetworkService(mockNetworkClient);

        String result = networkService.connectToServer();

        assertEquals("Connected to Mock Connection", result);
    }

}