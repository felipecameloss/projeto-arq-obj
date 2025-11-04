package br.edu.insper.projeto_arq_obj.local.controller;

import br.edu.insper.projeto_arq_obj.autenticacao.service.UsuarioService;
import br.edu.insper.projeto_arq_obj.banda.dto.CreateBandaDTO;
import br.edu.insper.projeto_arq_obj.banda.dto.EditBandaDTO;
import br.edu.insper.projeto_arq_obj.banda.dto.ResponseBandaDTO;
import br.edu.insper.projeto_arq_obj.local.dto.CreateLocalDTO;
import br.edu.insper.projeto_arq_obj.local.dto.EditLocalDTO;
import br.edu.insper.projeto_arq_obj.local.dto.ResponseLocalDTO;
import br.edu.insper.projeto_arq_obj.local.service.LocalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/local")
@Tag(name = "API  de Locais", description = "API de gerenciamneto de locais para shows")
public class LocalController {

    @Autowired
    LocalService localService;

    @Autowired
    UsuarioService usuarioService;

    @Operation(
            summary = "Lista todos os locais",
            description = "Retorna uma lista com todos os locais salvos no banco de dados"
    )
    @GetMapping
    public List<ResponseLocalDTO> listarTodos() {return localService.listarTodos();}

    @Operation(
            summary = "Cria um local",
            description = "Cria um local que será armazenado no banco de dados e vinculado" +
                    "ao show "
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseLocalDTO criarLocal(@RequestHeader(name = "token") String token, @RequestBody CreateLocalDTO local) {

        usuarioService.validarToken(token);

        return localService.criarLocal(local);
    }

    @Operation(
            summary = "Edita um local",
            description = "Edita um local modificando os campos inseridos"
    )
    @PutMapping("/{id}")
    public ResponseLocalDTO editarLocal(@RequestHeader(name = "token") String token, @PathVariable Integer id, @RequestBody EditLocalDTO local) {

        usuarioService.validarToken(token);

        return localService.editarLocal(id, local);

    }

    @Operation(
            summary = "Deleta um local",
            description = "Deleta um local removendo do banco de dados"
    )
    @DeleteMapping("/{id}")
    public void deletarLocal(@RequestHeader(name = "token") String token, @PathVariable Integer id) {

        usuarioService.validarToken(token);

        localService.deletarLocal(id);

    }


}
