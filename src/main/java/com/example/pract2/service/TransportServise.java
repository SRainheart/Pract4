package com.example.pract2.service;

import com.example.pract2.model.Transport;

import java.util.List;

public interface TransportServise {
    public List<Transport> findAllTransport();
    public Transport addTransport(Transport student);
    public Transport updateTransport(Transport student);
    void deleteTransport(int id);
}
