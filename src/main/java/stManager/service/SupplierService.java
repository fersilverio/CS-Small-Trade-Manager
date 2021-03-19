package stManager.service;

import stManager.model.Fornecedores;
import stManager.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepository sr;

    @Autowired
    public SupplierService(SupplierRepository sr) {
        this.sr = sr;
    }

    public List<Fornecedores> findAll() {
        return sr.findAll();
    }

    public Fornecedores getSupplierById(Long id) {
        Optional<Fornecedores> supplierOpc = sr.findById(id);
        Fornecedores supplier = null;
        if (supplierOpc.isPresent()) {
            supplier = supplierOpc.get();
        } else {
            throw new RuntimeException("Supplier not found for ID: " + id);
        }
        return supplier;
    }
}
