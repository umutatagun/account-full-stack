package com.umut.account.service;

import com.umut.account.dto.AccountDto;
import com.umut.account.dto.CreateAccountRequest;
import com.umut.account.dto.converter.AccountDtoConverter;
import com.umut.account.model.Account;
import com.umut.account.model.Customer;
import com.umut.account.model.Transaction;
import com.umut.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter converter;

    public AccountService(AccountRepository accountRepository, CustomerService customerService, AccountDtoConverter converter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.converter = converter;
    }

    public AccountDto createAccount(CreateAccountRequest createAccountRequest) {
        Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());

        Account account = new Account(
                customer,
                createAccountRequest.getInitialCredit(),
                LocalDateTime.now());

        // compareTo büyükse: 1, esitse : 0 , kucukse: -1 döner o yüzden 0'dan büyükse yazdık
        if (createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
            Transaction transaction = new Transaction(createAccountRequest.getInitialCredit(), account);
            account.getTransactions().add(transaction);
        }

        return converter.convert(accountRepository.save(account));
    }

}
