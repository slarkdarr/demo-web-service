package com.example.demo.services;

import com.example.demo.classes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Predicate;

@Service
public class CustomerService {
    List<Customer> listOfCustomers = new ArrayList<>();

    @Autowired
    HistoryService historyService;

    public void register(Long nik, String nama, Long noTelp, Integer pin) {
        long newNoRekening = new Random().nextLong(1000000000);

        // Asumsi saldo 0 ketika pendaftaran
        listOfCustomers.add(new Customer(nik, nama, noTelp, newNoRekening, 0L, pin));
    }

    public Long tarikTunai(Customer customer, Long amount, Integer pin) {
        long currentSaldo = customer.getSaldo();
        long newSaldo = currentSaldo - amount;

        if (!Objects.equals(pin, customer.getPin())) {
            return -1L;
        }
        if (newSaldo < 0) {
            return -2L;
        }

        customer.setSaldo(newSaldo);
        historyService.setHistory(customer.getNoRekening(), amount, currentSaldo, newSaldo, "TT");
        return newSaldo;
    }

    public Long setorTunai(Customer customer, Long amount, Integer pin) {
        long currentSaldo = customer.getSaldo();
        long newSaldo = currentSaldo + amount;

        if (!Objects.equals(pin, customer.getPin())) {
            return -1L;
        }

        customer.setSaldo(newSaldo);
        historyService.setHistory(customer.getNoRekening(), amount, currentSaldo, newSaldo, "ST");
        return newSaldo;
    }

    public Customer getCustomerByNik(Long nik) {
        Predicate<Customer> byNik = p -> p.getNik().equals(nik);
        return listOfCustomers.stream().filter(byNik).findFirst().orElse(null);
    }

    public Customer getCustomerByNoTelp(Long noTelp) {
        Predicate<Customer> byNoTelp = p -> p.getNoTelp().equals(noTelp);
        return listOfCustomers.stream().filter(byNoTelp).findFirst().orElse(null);
    }

    public Customer getCustomerByNoRekening(Long noRekening) {
        Predicate<Customer> byNoRekening = p -> p.getNoRekening().equals(noRekening);
        return listOfCustomers.stream().filter(byNoRekening).findFirst().orElse(null);
    }

    public Long getSaldo(Customer customer) {
        return customer.getSaldo();
    }
}
