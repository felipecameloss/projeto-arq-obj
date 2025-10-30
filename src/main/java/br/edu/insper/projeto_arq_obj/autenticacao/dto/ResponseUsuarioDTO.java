package br.edu.insper.projeto_arq_obj.autenticacao.dto;

import br.edu.insper.projeto_arq_obj.autenticacao.model.Usuario;

public record ResponseUsuarioDTO(Integer id, String email) {
    public static ResponseUsuarioDTO toDto(Usuario usuario){
        return new ResponseUsuarioDTO(
                usuario.getId(),
                usuario.getEmail()
        );
    }
}
