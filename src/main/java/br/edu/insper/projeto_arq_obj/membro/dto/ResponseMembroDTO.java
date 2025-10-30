package br.edu.insper.projeto_arq_obj.membro.dto;

import br.edu.insper.projeto_arq_obj.membro.model.Membro;

public record ResponseMembroDTO(Integer id, String membro,
                                String cpf, String ocupacao) {

    public static ResponseMembroDTO toDto(Membro membro) {
        return new ResponseMembroDTO(
                membro.getId(),
                membro.getNome(),
                membro.getCpf(),
                membro.getOcupacao()
        );
    }

}
