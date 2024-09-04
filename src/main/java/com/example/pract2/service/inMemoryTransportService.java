package com.example.pract2.service;

import com.example.pract2.model.Transport;
import com.example.pract2.repository.InMemoryTransportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class inMemoryTransportService implements TransportServise{
    private final InMemoryTransportRepository studentRepository;

    public inMemoryTransportService(InMemoryTransportRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Transport> findAllTransport() {
        return studentRepository.findAllTransport();
    }

    @Override
    public Transport addTransport(Transport student) {
        return studentRepository.addTransport(student);
    }

    @Override
    public Transport updateTransport(Transport student) {
        return studentRepository.updateTransport(student);
    }

    @Override
    public void deleteTransport(int id) {
        studentRepository.deleteTransport(id);
    }
}
