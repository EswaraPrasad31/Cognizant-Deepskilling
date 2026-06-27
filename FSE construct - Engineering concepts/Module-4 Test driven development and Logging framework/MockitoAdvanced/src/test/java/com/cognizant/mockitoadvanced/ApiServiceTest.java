package com.cognizant.mockitoadvanced;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class ApiServiceTest {

    @Test
    public void testServiceWithMockRestClient() {

        RestClient mockRestClient = mock(RestClient.class);

        when(mockRestClient.getResponse())
                .thenReturn("Mock Response");

        ApiService apiService = new ApiService(mockRestClient);

        String result = apiService.fetchData();

        assertEquals("Fetched Mock Response", result);
    }

}