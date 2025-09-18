package com.example.Curso_springboot.controllers;

import com.example.Curso_springboot.exceptions.RecursoNaoEncontradoException;
import com.example.Curso_springboot.models.Produto;
import com.example.Curso_springboot.services.ProdutoServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoControllers {

    private final ProdutoServices produtoServices;

    public ProdutoControllers(ProdutoServices produtoServices) {
        this.produtoServices = produtoServices;
    }

    @GetMapping
    public List<Produto> listarProdutos () {
        return produtoServices.listarProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProduto (@PathVariable Long id) {
        Produto produto = produtoServices.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoServices.salvarProduto(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> deletarProduto(@PathVariable Long id){
        produtoServices.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
