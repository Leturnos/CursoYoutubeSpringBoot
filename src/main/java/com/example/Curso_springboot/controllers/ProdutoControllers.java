package com.example.Curso_springboot.controllers;

import com.example.Curso_springboot.models.Produto;
import com.example.Curso_springboot.services.ProdutoServices;
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
    public ResponseEntity<Produto> buscarProduto (@PathVariable Long id) {
        return produtoServices.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
