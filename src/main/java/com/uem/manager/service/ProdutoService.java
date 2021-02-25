package com.uem.manager.service;

import java.util.List;

import com.uem.manager.model.Produto;

public interface ProdutoService {
    List<Produto> getAllProducts();
    void saveProduct (Produto produto);
    Produto getProductById (long id);
    void deleteProductById (long id);
}
