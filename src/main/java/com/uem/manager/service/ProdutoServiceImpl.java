package com.uem.manager.service;

import java.util.List;
import java.util.Optional;

import com.uem.manager.model.Produto;
import com.uem.manager.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Produto> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Produto produto) {
        this.productRepository.save(produto);

    }

    @Override
    public Produto getProductById(long id) {
        Optional<Produto> optional = productRepository.findById(id);
        Produto produto = null;
        if (optional.isPresent()){
            produto = optional.get();
        }
        else{
            throw new RuntimeException("Product not found for id:: " + id);
        }
        return produto;
    }

    @Override
    public void deleteProductById(long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public List<Produto> getAllProductsByNome(String nome) {
        if (nome != null){
            return productRepository.getAllByNome(nome);
        }
        return productRepository.findAll();
    }
    
}
