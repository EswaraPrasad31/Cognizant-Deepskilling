package com.cognizant.mockitoadvanced;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class ServiceTest {

    @Test
    public void testServiceWithMockRepository() {

        Repository mockRepository = mock(Repository.class);

        when(mockRepository.getData())
                .thenReturn("Mock Data");

        Service service = new Service(mockRepository);

        String result = service.processData();

        assertEquals("Processed Mock Data", result);
    }

}