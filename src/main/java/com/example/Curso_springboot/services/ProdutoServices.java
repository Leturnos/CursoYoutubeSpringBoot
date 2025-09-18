package com.example.Curso_springboot.services;

import com.example.Curso_springboot.exceptions.RecursoNaoEncontradoException;
import com.example.Curso_springboot.models.Produto;
import com.example.Curso_springboot.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServices {

    private final ProdutoRepository produtoRepository;

    public ProdutoServices(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto de id: " + id + " não encontrado"));
    }

    public Produto salvarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id){
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto de id: " + id + " não encontrado");
        }

        produtoRepository.deleteById(id);
    }
}
