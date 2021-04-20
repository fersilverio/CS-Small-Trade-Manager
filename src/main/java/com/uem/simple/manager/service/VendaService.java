
package com.uem.simple.manager.service;

import java.util.List;
import java.util.Optional;

import com.uem.simple.manager.model.Venda;
import com.uem.simple.manager.repository.VendaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {
    
    private final VendaRepository vr;

    @Autowired
    public VendaService(VendaRepository vr){
        this.vr = vr;
    }

    public List<Venda> findAll(){
        return vr.findAll();
    }

    public List<Venda> findAllByData(){
        return vr.findAllByData();
    }

    public Venda getVendaById(Long id){
        Optional<Venda> saleOpc = vr.findById(id);
        Venda sale = null;
        if(saleOpc.isPresent()){
            sale = saleOpc.get();
        }
        else{
            throw new RuntimeException("Sale not found for ID: " + id);
        }
        return sale;
    }


}