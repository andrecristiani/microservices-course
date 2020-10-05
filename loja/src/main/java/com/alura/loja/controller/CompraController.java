package com.alura.loja.controller;

import com.alura.loja.dto.CompraDTO;
import com.alura.loja.model.Compra;
import com.alura.loja.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping("{id}")
    public Compra buscaCompraPorId(@PathVariable Long id) {
       return  compraService.buscaCompraPorId(id);
    }

    @PostMapping
    public Compra realizaCompra(@RequestBody CompraDTO compra) {
        return compraService.realizaCompra(compra);
    }
}
