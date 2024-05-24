package com.domain.Notas.controller;

import com.domain.Notas.model.NotaModel;
import com.domain.Notas.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nota")
public class NotaController {
    @Autowired
    private NotaService notaService;

    @GetMapping("/lista")
    public List<NotaModel> getNotas(){
        return notaService.getNotas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaModel> getnota(@PathVariable Long id){
        return notaService.getNota(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public NotaModel addNota(@RequestBody NotaModel novaNota){
        return notaService.addNota(novaNota);
    }

    @PutMapping("/atualizar-nota/{id}")
    public ResponseEntity<NotaModel> updateNota(@PathVariable Long id, @RequestBody NotaModel notaAtualizada){
        return notaService.updateNota(id, notaAtualizada)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deleteNota(@PathVariable Long id){
        if (notaService.deleteNota(id)) {
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }

}
