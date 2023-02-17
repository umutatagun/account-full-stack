package com.umut.account.service;
import com.umut.account.dto.CreateAccountRequest;
import com.umut.account.dto.CustomerDto;
import com.umut.account.dto.converter.AccountDtoConverter;
import com.umut.account.dto.converter.CustomerDtoConverter;
import com.umut.account.exception.CustomerNotFoundException;
import com.umut.account.model.Account;
import com.umut.account.model.Customer;
import com.umut.account.repository.AccountRepository;
import com.umut.account.repository.CustomerRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest {
    private AccountService service;
    private AccountRepository repository;
    private AccountDtoConverter converter;

    //Customer
    private CustomerService customerService;
    private CustomerRepository customerRepository;
    private CustomerDtoConverter customerDtoConverter;

    @BeforeEach
    public void setUp() {
        repository = mock(AccountRepository.class);
        converter = mock(AccountDtoConverter.class);
        customerDtoConverter = mock(CustomerDtoConverter.class);
        customerRepository = mock(CustomerRepository.class);
        customerService = new CustomerService(customerRepository, customerDtoConverter);

        service = new AccountService(repository, customerService, converter);
    }


}
