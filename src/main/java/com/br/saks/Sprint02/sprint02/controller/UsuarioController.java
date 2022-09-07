package com.br.saks.Sprint02.sprint02.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        BCryptPasswordEncoder criptografar = new BCryptPasswordEncoder();
        String senha = criptografar.encode(usuario.getSenha());
        usuario.setSenha(senha);
        usuario.setStatus(1);
        return repo.save(usuario);
    }
}
