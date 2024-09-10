package com.example.pract2.service;

import com.example.pract2.model.Transport;
import com.example.pract2.repository.TransportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class inMemoryTransportService implements TransportServise{
    private final TransportRepository studentRepository;
    public inMemoryTransportService(TransportRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Override
    public List<Transport> findAllTransport() {
        return studentRepository.findAll();
    }
    @Override
    public Transport addTransport(Transport student) {
        return studentRepository.save(student);
    }
    @Override
    public Transport updateTransport(Transport student) {
        return studentRepository.save(student);
    }
    @Override
    public void deleteTransport(Long id) {
        studentRepository.deleteById(id);
    }
    @Override
    public void deleteTransportAll() {
        studentRepository.deleteAll();
    }
    @Override
    public Transport IsDeleteTransportTrue(Transport student) {
        return studentRepository.save(student);
    }
}
