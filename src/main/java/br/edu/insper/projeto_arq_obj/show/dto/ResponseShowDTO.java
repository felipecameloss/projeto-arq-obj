package br.edu.insper.projeto_arq_obj.show.dto;

import br.edu.insper.projeto_arq_obj.show.model.Show;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ResponseShowDTO(String nome, LocalDate data) {

    public static ResponseShowDTO toDto(Show show) {
        return new ResponseShowDTO(
                show.getNome(),
                show.getData()
        );
    }

}
