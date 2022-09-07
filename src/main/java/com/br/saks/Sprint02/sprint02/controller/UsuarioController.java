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
import com.br.saks.Sprint02.sprint02.model.Usuario;
import com.br.saks.Sprint02.sprint02.repository.UsuarioRepository;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repo;

    @GetMapping
    public List<Usuario> getUsuarios(){
        return repo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<Usuario> getUsuarioById(@PathVariable long id){
        return repo.findById(id);
    }

    @PostMapping
    public Usuario postUsuario(@RequestBody Usuario usuario){
        usuario.setStatus(1);
        return repo.save(usuario);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return repo.findById(id)
                .map(record -> {
                    record.setEmail(usuario.getEmail());
                    record.setSenha(usuario.getSenha());
                    record.setStatus(usuario.getStatus());
                    Usuario usuarioUpdated = repo.save(record);
                    return ResponseEntity.ok().body(usuarioUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUsuario(@PathVariable Long id){
        return repo.findById(id).map(record->{
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
