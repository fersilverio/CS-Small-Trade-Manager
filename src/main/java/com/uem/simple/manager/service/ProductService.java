package com.uem.simple.manager.service;

import java.util.List;

import com.uem.simple.manager.model.Produto;
import com.uem.simple.manager.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductService {
    private final ProductRepository pr;

    @Autowired
    public ProductService(ProductRepository pr){
        this.pr = pr;
    }

    public List<Produto> findAll(){
        return pr.findAll();
    }

    public List<Produto> findAllByQtd(){
        return pr.findAllByQtd();
    }

    public String count(){
        Integer count = pr.countProductsLowerThanMinimum().size();
        if (count > 0){
            return "AVISO: Há " + count + " produtos com quantidades abaixo do mínimo esperado no estoque, recomenda-se o reabastecimento dos produtos listados abaixo.";
        }
        else{
            return "Estoque em ordem, nenhuma observação no momento";
        }
        
    }

    public List<Produto> listagemProdutosEmFalta(){
        return pr.listProductsLowerThanMinimum();
    }

    public Produto getProductById(Long id){
        Optional<Produto> productOpc = pr.findById(id);
        Produto produto = null;
        if (productOpc.isPresent()){
            produto = productOpc.get();
        }
        else{
            throw new RuntimeException("Product not found for ID: " + id);
        }
        return produto;
    }



    
}
