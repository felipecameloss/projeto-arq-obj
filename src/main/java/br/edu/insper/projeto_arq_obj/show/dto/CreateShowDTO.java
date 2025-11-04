package br.edu.insper.projeto_arq_obj.show.dto;

import java.time.LocalDateTime;

public record CreateShowDTO(String nome, LocalDateTime data, Integer idLocal) {
}
