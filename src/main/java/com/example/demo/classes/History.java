package com.example.demo.classes;

public class History {
    private Long noRekening;
    private Long jumlah;
    private Long saldoAwal;
    private Long saldoAkhir;
    private String tipe;

    public History(Long noRekening, Long jumlah, Long saldoAwal, Long saldoAkhir, String tipe) {
        this.noRekening = noRekening;
        this.jumlah = jumlah;
        this.saldoAwal = saldoAwal;
        this.saldoAkhir = saldoAkhir;
        this.tipe = tipe;
    }

    public Long getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(Long noRekening) {
        this.noRekening = noRekening;
    }

    public Long getJumlah() {
        return jumlah;
    }

    public void setJumlah(Long jumlah) {
        this.jumlah = jumlah;
    }

    public Long getSaldoAwal() {
        return saldoAwal;
    }

    public void setSaldoAwal(Long saldoAwal) {
        this.saldoAwal = saldoAwal;
    }

    public Long getSaldoAkhir() {
        return saldoAkhir;
    }

    public void setSaldoAkhir(Long saldoAkhir) {
        this.saldoAkhir = saldoAkhir;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }
}
