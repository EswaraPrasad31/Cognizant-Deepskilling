package com.cognizant.springlearn;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

import com.cognizant.springlearn.controller.CountryController;
import com.cognizant.springlearn.controller.DepartmentController;
import com.cognizant.springlearn.controller.EmployeeController;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpringLearnApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private DepartmentController departmentController;

    @Autowired
    private CountryController countryController;

    @Test
    @Order(1)
    public void contextLoads() {
        assertNotNull(employeeController);
        assertNotNull(departmentController);
        assertNotNull(countryController);
    }

    @Test
    @Order(2)
    public void testGetEmployees() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    @Order(3)
    public void testPutEmployee() throws Exception {
        String validEmployeeJson = """
                {
                    "id": 1,
                    "name": "Alice Modified",
                    "salary": 80000.0,
                    "permanent": true,
                    "dateOfBirth": "15/05/1995"
                }
                """;

        mockMvc.perform(put("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validEmployeeJson))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/employees/2"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void testValidationFailure() throws Exception {
        String invalidCountryJson = """
                {
                    "code": "I",
                    "name": ""
                }
                """;

        mockMvc.perform(post("/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidCountryJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Validation Failed"));
    }

    @Test
    @Order(6)
    public void testEmployeeNotFound() throws Exception {
        mockMvc.perform(delete("/employees/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Employee Not Found"));
    }
}
