package com.umut.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.umut.account.dto.CreateAccountRequest;
import com.umut.account.dto.converter.AccountDtoConverter;
import com.umut.account.model.Customer;
import com.umut.account.repository.AccountRepository;
import com.umut.account.repository.CustomerRepository;
import com.umut.account.service.AccountService;
import com.umut.account.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "server-port=0",
        "command.line.runner.enabled=false"
})
@RunWith(SpringRunner.class)
@DirtiesContext
class AccountControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") // warning suppress
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountDtoConverter converter;

    private AccountService service = new AccountService(accountRepository, customerService, converter);

    private ObjectMapper mapper = new ObjectMapper();

   @BeforeEach
   public void setUp() {
       mapper.registerModule(new JavaTimeModule());
       mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
   }

   @Test
    public void testCreateAccount_whenCustomerIdExists_shouldCreateAccountAndReturnAccountDto() throws Exception{
       Customer customer = customerRepository.save(new Customer("Umut", "Atagün"));
       CreateAccountRequest request = new CreateAccountRequest(customer.getId(), new BigDecimal(100));

       mockMvc.perform(post("/v1/account")
               .contentType(MediaType.APPLICATION_JSON)
               .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
               .andExpect(status().is2xxSuccessful())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.id", notNullValue()))
               .andExpect(jsonPath("$.balance", is(100)))
               .andExpect(jsonPath("$.customer.id", is(customer.getId())))
               .andExpect(jsonPath("$.customer.name", is(customer.getName())))
               .andExpect(jsonPath("$.customer.surname", is(customer.getSurname())))
               .andExpect(jsonPath("$.transactions", hasSize(1)));
   }
}
