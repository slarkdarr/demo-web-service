package com.example.demo.controllers;

import com.example.demo.classes.History;
import com.example.demo.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryController {
    @Autowired
    HistoryService historyService;

    @GetMapping(path = "/history")
    public List<History> getHistory() {
        return historyService.getHistory();
    }
}
