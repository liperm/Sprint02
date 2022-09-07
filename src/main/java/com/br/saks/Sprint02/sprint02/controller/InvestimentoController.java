package com.br.saks.Sprint02.sprint02.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.saks.Sprint02.sprint02.model.Investimento;
import com.br.saks.Sprint02.sprint02.repository.InvestimentoRepository;

@RestController
@RequestMapping("/investimentos")
public class InvestimentoController {
    @Autowired
    private InvestimentoRepository repo;

    @GetMapping
    private List<Investimento> getInvestimentos(){
        return repo.findAll();
    }

    @GetMapping(value="/{id}")
    private Optional<Investimento> getInvestimentoById(@PathVariable Long id){
        return repo.findById(id);
    }

    @PostMapping
    public Investimento postInvestimento(@RequestBody Investimento investimento){
        return repo.save(investimento);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Investimento investimento) {
        return repo.findById(id)
                .map(record -> {
                    record.setRentabilidade(investimento.getRentabilidade());
                    Investimento investimentoUpdated = repo.save(record);
                    return ResponseEntity.ok().body(investimentoUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteInvestimento(@PathVariable Long id){
        return repo.findById(id).map(record->{
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
