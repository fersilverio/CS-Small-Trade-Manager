package com.uem.simple.manager.service;

import java.util.List;
import java.util.Optional;

import com.uem.simple.manager.model.Servico;
import com.uem.simple.manager.repository.ServicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {
    private final ServicoRepository sr;

    @Autowired
    public ServicoService(ServicoRepository sr){
        this.sr = sr;
    }

    public List<Servico> findAll(){
        return sr.findAll();
    }

    public Servico getServicoById(Long id){
        Optional<Servico> servicoOpc = sr.findById(id);
        Servico servico = null;
        if (servicoOpc.isPresent()){
            servico = servicoOpc.get();
        }
        else{
            throw new RuntimeException("Product not found for ID: " + id);
        }
        return servico;
    }


}
