package com.umut.account.dto.converter;

import com.umut.account.dto.AccountCustomerDto;
import com.umut.account.model.Customer;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomerDtoConverter {

    public AccountCustomerDto convertToAccountCustomerDto(Customer from) {
        if(from == null) {
            return new AccountCustomerDto("", "", "");
        }
        return new AccountCustomerDto(from.getId(), from.getName(), from.getSurname());
    }

}
