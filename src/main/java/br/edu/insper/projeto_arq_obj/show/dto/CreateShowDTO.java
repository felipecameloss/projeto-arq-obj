package br.edu.insper.projeto_arq_obj.show.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateShowDTO(String nome, LocalDate data, Integer idLocal) {
}
