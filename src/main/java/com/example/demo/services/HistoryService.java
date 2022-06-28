package com.example.demo.services;

import com.example.demo.classes.History;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService {
    List<History> historyList = new ArrayList<>();
    public void setHistory(Long noRekening, Long jumlah, Long saldoAwal, Long saldoAkhir, String tipe) {
        historyList.add(new History(noRekening, jumlah, saldoAwal, saldoAkhir, tipe));
    }

    public List<History> getHistory() {
        return historyList;
    }
}
