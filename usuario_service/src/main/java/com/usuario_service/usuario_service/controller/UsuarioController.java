package com.usuario_service.usuario_service.controller;

import java.util.List;

import com.usuario_service.usuario_service.servicios.*;
import com.usuario_service.usuario_service.entidades.Usuario;
import com.usuario_service.usuario_service.modelo.Carro;
import com.usuario_service.usuario_service.modelo.Moto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioservice;

    @GetMapping
    public ResponseEntity<List<Usuario>> ListarUsuarios() {
        List<Usuario> listaNueva = usuarioservice.GetAll();
        if (listaNueva.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaNueva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> EncontrarUsuario(@PathVariable("id") int id) {
        Usuario fined = usuarioservice.UsuarioById(id);
        if (fined == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fined);
    }

    @PostMapping
    public ResponseEntity<Usuario> GuardarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioinsertado = usuarioservice.GuardarUsuario(usuario);
        return ResponseEntity.ok(usuarioinsertado);
    }

    @GetMapping("/carro/{usuarioid}")
    public ResponseEntity<List<Carro>> CarrosUsuario(@PathVariable("usuarioid") int id) {
        List<Carro> fined = usuarioservice.getCarros(id);
        if (fined == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fined);
    }

    @GetMapping("/moto/{usuarioid}")
    public ResponseEntity<List<Moto>> MotosUsuario(@PathVariable("usuarioid") int id) {
        List<Moto> fined = usuarioservice.getMotos(id);
        if (fined == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fined);
    }

}
