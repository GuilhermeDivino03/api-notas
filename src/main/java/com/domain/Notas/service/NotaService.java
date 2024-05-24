package com.domain.Notas.service;

import com.domain.Notas.controller.NotaController;
import com.domain.Notas.model.NotaModel;
import com.domain.Notas.repository.NotaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    private static final Logger logger = LoggerFactory.getLogger(NotaController.class);

    @Autowired
    private NotaRepository notaRepository;

    public List<NotaModel> getNotas() {
        logger.debug("Buscando todas as notas");
        return notaRepository.findAll();
    }

    public Optional<NotaModel> getNota(Long id){
        logger.debug("Buscando nota com id {}", id);
        return notaRepository.findById(id);
    }

    public NotaModel addNota(NotaModel novaNota){
        logger.debug("Salvando nova nota: {}", novaNota);
        return notaRepository.save(novaNota);
    }

    public Optional<NotaModel> updateNota(Long id, NotaModel notaAtualizada){
        logger.debug("Atualizando nota com id {}", id);
        return notaRepository.findById(id).map(notaModel -> {
            notaModel.setTitulo(notaAtualizada.getTitulo());
            notaModel.setConteudo(notaAtualizada.getConteudo());
            logger.debug("Nota atualizada: {}", notaAtualizada);
            return notaRepository.save(notaModel);
        });
    }

    public boolean deleteNota(Long id){
        logger.debug("Deletando nota com id {}", id);
        if (notaRepository.existsById(id)) {
            notaRepository.deleteById(id);
            logger.debug("Nota deletada com sucesso");
            return true;
        }
            logger.warn("Nota com id {} não encontrada para deleção", id);
            return false;
    }
}
