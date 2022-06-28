package com.example.demo.classes;

public class Customer {
    private Long nik;
    private String nama;
    private Long noTelp;
    private Long noRekening;
    private Long saldo;
    private Integer pin;

    public Customer() {}

    public Customer(Long nik, String nama, Long noTelp, Long noRekening, Long saldo, Integer pin) {
        this.nik = nik;
        this.nama = nama;
        this.noTelp = noTelp;
        this.noRekening = noRekening;
        this.saldo = saldo;
        this.pin = pin;
    }

    public Long getNik() {
        return nik;
    }

    public void setNik(Long nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Long getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(Long noTelp) {
        this.noTelp = noTelp;
    }

    public Long getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(Long noRekening) {
        this.noRekening = noRekening;
    }

    public Long getSaldo() {
        return saldo;
    }

    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }
}
