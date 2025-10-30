package br.edu.insper.projeto_arq_obj.local.dto;

import br.edu.insper.projeto_arq_obj.local.model.Local;

public record ResponseLocalDTO(String pais, String estado, String cidade,
                               String endereco, String estabelecimento) {

    public static ResponseLocalDTO toDto(Local local) {

        return new ResponseLocalDTO(
            local.getPais(),
            local.getEstado(),
            local.getCidade(),
            local.getEndereco(),
            local.getEstabelecimento()
        );

    }

}
