/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devsu.account.integration;

import com.devsu.account.application.command.create.CreateClientCommand;
import com.devsu.account.domain.interfaces.repositories.IClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.runner.RunWith;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author diego
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WithMockUser(roles = "ADMIN")
public class ClientAdminControllerIntegrationTest {

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_create_one_client() throws Exception {

        CreateClientCommand client = new CreateClientCommand();
        client.setIdentification("1700086430");
        client.setPassword("12345");
        client.setNames("Jose Garcia");
        client.setEmail("test@test.com");
        client.setPhone("0991234567");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(client);

        this.mockMvc.perform(post("/api/admin/client")
                .contentType(APPLICATION_JSON)
                .content(requestJson)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        assertThat(this.clientRepository.getAll().get()).hasSize(1);
    }

}
