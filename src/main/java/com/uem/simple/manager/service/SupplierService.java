package com.uem.simple.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.uem.simple.manager.model.Fornecedor;
import com.uem.simple.manager.repository.SupplierRepository;

@Service
public class SupplierService {

    private final SupplierRepository sr;

    @Autowired
    public SupplierService(SupplierRepository sr) {
        this.sr = sr;
    }

    public List<Fornecedor> findAll() {
        return sr.findAll();
    }

    public Fornecedor getSupplierById(Long id) {
        Optional<Fornecedor> supplierOpc = sr.findById(id);
        Fornecedor supplier = null;
        if (supplierOpc.isPresent()) {
            supplier = supplierOpc.get();
        } else {
            throw new RuntimeException("Supplier not found for ID: " + id);
        }
        return supplier;
    }
}
