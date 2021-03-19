package stManager.controller;

import stManager.model.Cidade;
import stManager.model.Fornecedores;
import stManager.repository.SupplierRepository;
import stManager.repository.CidadeRepository;
import stManager.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

    private final SupplierService supplierService;
    private final SupplierRepository supplierRepository;
    private final CidadeRepository cidadeRepository;

    @Autowired
    public SupplierController(SupplierService supplierService, SupplierRepository supplierRepository, CidadeRepository cidadeRepository) {
        this.supplierService = supplierService;
        this.supplierRepository = supplierRepository;
        this.cidadeRepository = cidadeRepository;
    }

    @GetMapping
    public String supplier(Model m) {
        m.addAttribute("Listafornecedores", supplierService.findAll());
        return "supplier/home";
    }

    @GetMapping("/new")
    public String addSupplier(Model m) {
        Fornecedores supplier = new Fornecedores();
        m.addAttribute("fornecedores", supplier);
        return "supplier/new";
    }

    @PostMapping("/add")
    public String salvaSupplier(@Valid Fornecedores supplier, BindingResult br, RedirectAttributes ra) {
        if (br.hasErrors()){
            return "supplier/new";
        }
        // Cidade cidade = new Cidade();
        // cidade.setEstado(supplier.getEndereco().getCidade().getEstado());
        // cidade.setNome(supplier.getEndereco().getCidade().getNome());
        // cidadeRepository.saveAndFlush(cidade);
        // supplier.getEndereco().setCidade(cidade);
        supplierRepository.saveAndFlush(supplier);
        return "redirect:/supplier";
    }

    // @GetMapping("/view/{id}")
    // public String viewSupplier(@PathVariable Long id, Model m) {
    //     Fornecedores supplier = supplierService.getSupplierById(id);
    //     m.addAttribute("fornecedores", supplier);
    //     m.addAttribute("update", false);
    //     return "supplier/manage";
    // }

    // @GetMapping("/edit/{id}")
    // public String editSupplier(@PathVariable Long id, Model m) {
    //     Fornecedores supplier = supplierService.getSupplierById(id);
    //     m.addAttribute("fornecedores", supplier);
    //     m.addAttribute("update", true);
    //     return "supplier/manage";
    //}

    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable Long id) {
        Fornecedores supplier = supplierService.getSupplierById(id);
        supplierRepository.delete(supplier);
        return "redirect:/supplier";
    }

}
