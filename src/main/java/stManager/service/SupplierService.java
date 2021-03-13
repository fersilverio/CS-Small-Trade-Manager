package stManager.service;

import java.util.List;

import stManager.model.Fornecedores;

public interface SupplierService {
    List<Fornecedores> getAllSuppliers();
    void saveSupplier (Fornecedores fornecedor);
    Fornecedores getSupplierById (long id);
    void deleteSupplierById (long id);
    List<Fornecedores> getAllSuppliersByNomeFantasia(String nome);
}
