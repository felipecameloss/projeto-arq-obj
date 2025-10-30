package br.edu.insper.projeto_arq_obj.banda.dto;

import br.edu.insper.projeto_arq_obj.banda.model.Banda;

public record ResponseBandaDTO(Integer id, String nome, String genero, String gravadora) {

    public static ResponseBandaDTO toDto(Banda banda) {
        return new ResponseBandaDTO(
                banda.getId(),
                banda.getNome(),
                banda.getGenero(),
                banda.getGravadora()
        );
    }

}
