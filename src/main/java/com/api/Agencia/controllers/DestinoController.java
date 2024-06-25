package com.api.Agencia.controllers;

import com.api.Agencia.entities.Destino;
import com.api.Agencia.services.DestinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinos")
public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    // Endpoint: Cadastro de Destinos de Viagem
    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody Destino destino) {
        try {
            destinoService.salvar(destino);
            return ResponseEntity.ok("Destino salvo com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao salvar o destino: " + e.getMessage());
        }
    }

    // Endpoint: Listagem de Destinos de Viagem
    @GetMapping
    public ResponseEntity<List<Destino>> listar() {
        try {
            List<Destino> destinos = destinoService.listar();
            return ResponseEntity.ok(destinos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // Endpoint: Pesquisa de Destinos
    @GetMapping("/buscar")
    public ResponseEntity<List<Destino>> buscarPorNomeOuLocalidade(@RequestParam String query) {
        try {
            List<Destino> destinos = destinoService.buscarPorNomeOuLocalidade(query);
            return ResponseEntity.ok(destinos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // Endpoint: Visualização de Informações Detalhadas
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Destino destino = destinoService.buscarPorId(id);
            if (destino != null) {
                return ResponseEntity.ok(destino);
            } else {
                return ResponseEntity.status(404).body("Destino não encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao buscar o destino: " + e.getMessage());
        }
    }

    // Endpoint: Avaliação de Destino de Viagens
    @PostMapping("/{id}/avaliar")
    public ResponseEntity<String> avaliarDestino(@PathVariable Long id, @RequestParam double nota) {
        try {
            boolean sucesso = destinoService.avaliarDestino(id, nota);
            if (sucesso) {
                return ResponseEntity.ok("Avaliação registrada com sucesso!");
            } else {
                return ResponseEntity.status(404).body("Destino não encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao avaliar o destino: " + e.getMessage());
        }
    }

    // Endpoint: Exclusão de Destinos de Viagem
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        try {
            boolean sucesso = destinoService.excluir(id);
            if (sucesso) {
                return ResponseEntity.ok("Destino excluído com sucesso!");
            } else {
                return ResponseEntity.status(404).body("Destino não encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao excluir o destino: " + e.getMessage());
        }
    }
}
