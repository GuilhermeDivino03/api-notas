package com.domain.Notas.service;

import com.domain.Notas.model.NotaModel;
import com.domain.Notas.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {
    @Autowired
    private NotaRepository notaRepository;

    public List<NotaModel> getNotas() {
        return notaRepository.findAll();
    }

    public Optional<NotaModel> getNota(Long id){
        return notaRepository.findById(id);
    }

    public NotaModel addNota(NotaModel novaNota){
        return notaRepository.save(novaNota);
    }

    public Optional<NotaModel> updateNota(Long id, NotaModel notaAtualizada){
        return notaRepository.findById(id).map(notaModel -> {
            notaModel.setTitulo(notaAtualizada.getTitulo());
            notaModel.setConteudo(notaAtualizada.getConteudo());
            return notaRepository.save(notaModel);
        });
    }

    public boolean deleteNota(Long id){
        if (notaRepository.existsById(id)) {
            notaRepository.deleteById(id);
            return true;
        }
            return false;
    }
}
