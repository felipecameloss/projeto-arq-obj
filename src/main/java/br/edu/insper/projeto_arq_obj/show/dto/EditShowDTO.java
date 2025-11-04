package br.edu.insper.projeto_arq_obj.show.dto;

import br.edu.insper.projeto_arq_obj.banda.model.Banda;
import br.edu.insper.projeto_arq_obj.local.model.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record EditShowDTO(String nome, LocalDate data, Integer idLocal, List<Banda> bandas) {
}
