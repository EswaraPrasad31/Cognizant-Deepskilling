package com.cognizant.springlearn;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Base64;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringLearnApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private String getBasicAuthHeader(String username, String password) {
        String credentials = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }

    @Test
    public void testAuthenticationEndpoint_withoutCredentials() throws Exception {
        mockMvc.perform(get("/authenticate"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("Unauthorized Access"));
    }

    @Test
    public void testAuthenticationEndpoint_invalidCredentials() throws Exception {
        mockMvc.perform(get("/authenticate")
                        .header(HttpHeaders.AUTHORIZATION, getBasicAuthHeader("user", "wrongpwd")))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("Unauthorized Access"));
    }

    @Test
    public void testAuthenticationEndpoint_successfulLoginAndJwtGeneration() throws Exception {
        MvcResult result = mockMvc.perform(get("/authenticate")
                        .header(HttpHeaders.AUTHORIZATION, getBasicAuthHeader("user", "pwd")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Map<?, ?> responseMap = objectMapper.readValue(responseBody, Map.class);
        String token = (String) responseMap.get("token");
        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    public void testGetCountries_withoutJwt() throws Exception {
        mockMvc.perform(get("/countries"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetCountries_withValidJwt() throws Exception {
        // 1. Authenticate to get a valid token
        MvcResult authResult = mockMvc.perform(get("/authenticate")
                        .header(HttpHeaders.AUTHORIZATION, getBasicAuthHeader("user", "pwd")))
                .andReturn();
        String responseBody = authResult.getResponse().getContentAsString();
        Map<?, ?> responseMap = objectMapper.readValue(responseBody, Map.class);
        String token = (String) responseMap.get("token");

        // 2. Perform GET using Bearer Token
        mockMvc.perform(get("/countries")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetCountries_withInvalidJwt() throws Exception {
        mockMvc.perform(get("/countries")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer invalid_token"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.messages[0]").value("Malformed or invalid JWT token"));
    }

    @Test
    public void testGetCountries_forbiddenAccess() throws Exception {
        // 1. Authenticate as standard USER
        MvcResult authResult = mockMvc.perform(get("/authenticate")
                        .header(HttpHeaders.AUTHORIZATION, getBasicAuthHeader("user", "pwd")))
                .andReturn();
        String responseBody = authResult.getResponse().getContentAsString();
        Map<?, ?> responseMap = objectMapper.readValue(responseBody, Map.class);
        String token = (String) responseMap.get("token");

        // 2. Try to access ADMIN-only endpoint
        mockMvc.perform(get("/countries/admin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.error").value("Access Denied"));
    }

    @Test
    public void testGetCountries_adminAccess() throws Exception {
        // 1. Authenticate as ADMIN
        MvcResult authResult = mockMvc.perform(get("/authenticate")
                        .header(HttpHeaders.AUTHORIZATION, getBasicAuthHeader("admin", "pwd")))
                .andReturn();
        String responseBody = authResult.getResponse().getContentAsString();
        Map<?, ?> responseMap = objectMapper.readValue(responseBody, Map.class);
        String token = (String) responseMap.get("token");

        // 2. Access ADMIN-only endpoint
        mockMvc.perform(get("/countries/admin")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk());
    }
}
