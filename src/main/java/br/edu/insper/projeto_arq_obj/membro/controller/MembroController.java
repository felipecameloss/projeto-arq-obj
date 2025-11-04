package br.edu.insper.projeto_arq_obj.membro.controller;

import br.edu.insper.projeto_arq_obj.autenticacao.service.UsuarioService;
import br.edu.insper.projeto_arq_obj.membro.dto.CreateMembroDTO;
import br.edu.insper.projeto_arq_obj.membro.dto.EditMembroDTO;
import br.edu.insper.projeto_arq_obj.membro.dto.ResponseMembroDTO;
import br.edu.insper.projeto_arq_obj.membro.service.MembroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membro")
@Tag(name = "API de membros", description = "API de gerenciamento de membros para shows")
public class MembroController {
    @Autowired
    MembroService membroService;

    @Autowired
    UsuarioService usuarioService;

    @Operation(
            summary = "Lista todos os membros",
            description = "Retorna uma lista com todos os membros salvos no banco de dados"
    )

    @GetMapping
    public List<ResponseMembroDTO> listarTodos() {return membroService.listarTodos();}

    @Operation(
            summary = "Cria um membro",
            description = "Cria um membro que será armazenada no banco de dados"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMembroDTO criarBanda(@RequestHeader(name = "token") String token, @RequestBody CreateMembroDTO membro) {

        usuarioService.validarToken(token);

        return membroService.criarMembro(membro);
    }

    @Operation(
            summary = "Edita um membro",
            description = "Edita um membro modificando os campos inseridos"
    )
    @PutMapping("/{id}")
    public ResponseMembroDTO editarMembro(@RequestHeader(name = "token") String token, @PathVariable Integer id, @RequestBody EditMembroDTO membro) {

        usuarioService.validarToken(token);

        return membroService.editarMembro(id, membro);

    }

    @Operation(
            summary = "Deleta um membro",
            description = "Deleta um membro removendo do banco de dados"
    )
    @DeleteMapping("/{id}")
    public void deletarMembro(@RequestHeader(name = "token") String token, @PathVariable Integer id) {

        usuarioService.validarToken(token);

        membroService.deletarMembro(id);

    }
}
