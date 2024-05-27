package com.domain.Notas.controller;

import com.domain.Notas.model.NotaModel;
import com.domain.Notas.service.NotaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nota")
public class NotaController {

    private static final Logger logger = LoggerFactory.getLogger(NotaController.class);
    @Autowired
    private NotaService notaService;

    @GetMapping("/lista")
    public List<NotaModel> getNotas(){
        logger.debug("Exibindo lista de notas");
        return notaService.getNotas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaModel> getnota(@PathVariable Long id) {
        logger.debug("exibindo a nota com id {}", id);
        return notaService.getNota(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("Nota com id {} não encontrada", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public NotaModel addNota(@RequestBody NotaModel novaNota){
        logger.debug("Adicionando nova nota: {}", novaNota);
        return notaService.addNota(novaNota);
    }

    @PutMapping("/atualizar-nota/{id}")
    public ResponseEntity<NotaModel> updateNota(@PathVariable Long id, @RequestBody NotaModel notaAtualizada){
        logger.debug("Atualizando a nota com id {}", id);
        return notaService.updateNota(id, notaAtualizada)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("Nota com id {} não encontrada para atualização", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteNota(@PathVariable Long id){
        logger.debug("Deletando nota com id {}", id);
        if (notaService.deleteNota(id)) {
            return ResponseEntity.ok().build();
        } else
            logger.warn("Nota com id {} não encontrada para deleção", id);
            return ResponseEntity.notFound().build();
    }
}
