package com.domain.Notas.repository;

import com.domain.Notas.model.NotaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository <NotaModel, Long> {
}
