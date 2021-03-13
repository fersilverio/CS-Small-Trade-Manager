package stManager.service;

import java.util.List;
import java.util.Optional;

import stManager.model.Fornecedores;
import stManager.repository.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Fornecedores> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public void saveSupplier(Fornecedores fornecedor) {
        this.supplierRepository.save(fornecedor);

    }

    @Override
    public Fornecedores getSupplierById(long id) {
        Optional<Fornecedores> optional = supplierRepository.findById(id);
        Fornecedores fornecedor = null;
        if (optional.isPresent()){
            fornecedor = optional.get();
        }
        else{
            throw new RuntimeException("Fornecedor nao encontrado para o id: " + id);
        }
        return fornecedor;
    }

    @Override
    public void deleteSupplierById(long id) {
        this.supplierRepository.deleteById(id);
    }

    @Override
    public List<Fornecedores> getAllSuppliersByNomeFantasia(String nome) {
        if (nome != null){
            return supplierRepository.getAllByNomeFantasia(nome);
        }
        return supplierRepository.findAll();
    }
    
}
