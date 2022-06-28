package com.example.demo.controllers;

import com.example.demo.classes.Customer;
import com.example.demo.services.CustomerService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
//    private static final String template = "Hello %s!";
//    private final AtomicLong counter = new AtomicLong();
    @Autowired
CustomerService customerService;

    @PostMapping(path = "/register")
    public ResponseEntity<String> register(@RequestBody ObjectNode jsonNodes) {
        long nik = jsonNodes.get("nik").asLong();
        String name = jsonNodes.get("nama").asText();
        long noTelp = jsonNodes.get("no_telp").asLong();
        int pin = jsonNodes.get("pin").asInt();

        if (customerService.getCustomerByNik(nik) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("NIK already exists!");
        }
        if (customerService.getCustomerByNoTelp(noTelp) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No. Telepon already exists!");
        }

        customerService.register(nik, name, noTelp, pin);
        return ResponseEntity.status(HttpStatus.OK).body("Customer successfully registered!");
    }

    @PostMapping(path = "/tarik-tunai")
    public ResponseEntity<String> tarikTunai(@RequestBody ObjectNode jsonNodes) {
        long noRekening = jsonNodes.get("no_rekening").asLong();
        long amount = jsonNodes.get("amount").asLong();
        int pin = jsonNodes.get("pin").asInt();
        Customer customer = customerService.getCustomerByNoRekening(noRekening);

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer doesn't exist!");
        }

        long succeed = customerService.tarikTunai(customer, amount, pin);
        if (succeed == -1L) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong PIN!");
        } else if (succeed == -2L) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The amount can't be bigger than your current amount!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Tarik tunai succeeded!");
    }

    @PostMapping(path = "/setor-tunai")
    public ResponseEntity<String> setorTunai(@RequestBody ObjectNode jsonNodes) {
        long noRekening = jsonNodes.get("no_rekening").asLong();
        long amount = jsonNodes.get("amount").asLong();
        int pin = jsonNodes.get("pin").asInt();
        Customer customer = customerService.getCustomerByNoRekening(noRekening);

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer doesn't exist!");
        }

        long succeed = customerService.setorTunai(customer, amount, pin);
        if (succeed == -1L) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong PIN!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Setor tunai succeeded!");
    }

    @GetMapping(path = "/customer")
    public Customer getCustomer(@RequestParam Long nik) {
        return customerService.getCustomerByNik(nik);
    }

    @GetMapping(path = "/saldo")
    public ResponseEntity<Long> getSaldo(@RequestParam Long noRekening) {
        Customer customer = customerService.getCustomerByNoRekening(noRekening);

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(-1L);
        }
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getSaldo(customer));
    }
}
