package com.umut.account.service;
import com.umut.account.dto.converter.AccountDtoConverter;
import com.umut.account.dto.converter.CustomerDtoConverter;
import com.umut.account.repository.AccountRepository;
import com.umut.account.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

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
