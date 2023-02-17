package com.umut.account.service;

import com.umut.account.dto.CreateCustomerRequest;
import com.umut.account.dto.CustomerDto;
import com.umut.account.dto.converter.CustomerDtoConverter;
import com.umut.account.exception.CustomerNotFoundException;
import com.umut.account.model.Customer;
import com.umut.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter converter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    public Set<CustomerDto> getCustomers() {
        return converter.convertToCustomerDto(customerRepository.findAll());
    }

    public CustomerDto getCustomerById(String customerId) {
        return converter.convertToCustomerDto(findCustomerById(customerId));
    }

    public CustomerDto createCustomer(CreateCustomerRequest request) {
        Customer customer = new Customer("",request.getName(), request.getSurname(), Set.of());
        return converter.convertToCustomerDto(customerRepository.save(customer));
    }

    public void deleteCustomer(String id) {
        customerRepository.delete(findCustomerById(id));
    }

    protected Customer findCustomerById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer could not find by id "+id));
    }
}
