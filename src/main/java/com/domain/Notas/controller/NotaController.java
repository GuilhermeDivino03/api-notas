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
//@Api(value = "Notas API", tags = { "Notas" })
public class NotaController {

    private static final Logger logger = LoggerFactory.getLogger(NotaController.class);
    @Autowired
    private NotaService notaService;

    //@ApiOperation(value = "Obtém todas as notas", response = List.class)
    @GetMapping("/lista")
    public List<NotaModel> getNotas(){
        logger.debug("Exibindo lista de notas");
        return notaService.getNotas();
    }

    //@ApiOperation(value = "Obtém uma nota pelo ID", response = NotaModel.class)
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

    //@ApiOperation(value = "Adiciona uma nova nota", response = NotaModel.class)
    @PostMapping
    public NotaModel addNota(@RequestBody NotaModel novaNota){
        logger.debug("Adicionando nova nota: {}", novaNota);
        return notaService.addNota(novaNota);
    }

    //@ApiOperation(value = "Atualiza uma nota existente", response = NotaModel.class)
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

   // @ApiOperation(value = "Deleta uma nota existente", response = Void.class)
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
