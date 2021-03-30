package com.uem.simple.manager.service;

import com.uem.simple.manager.model.Cliente;
import com.uem.simple.manager.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository cr;

    @Autowired
    public ClienteService(ClienteRepository cr) {
        this.cr = cr;
    }

    public List<Cliente> findAll() {
        return cr.findAll();
    }

    public Cliente getClienteById(Long id) {
        Optional<Cliente> clienteOptional = cr.findById(id);
        Cliente cliente = null;
        if (clienteOptional.isPresent()) {
            cliente = clienteOptional.get();
        } else {
            throw new RuntimeException("Cliente not found for id: " + id);
        }
        return cliente;
    }
}
