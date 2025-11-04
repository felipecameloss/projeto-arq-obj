package br.edu.insper.projeto_arq_obj.banda.controller;

import br.edu.insper.projeto_arq_obj.autenticacao.service.UsuarioService;
import br.edu.insper.projeto_arq_obj.banda.dto.CreateBandaDTO;
import br.edu.insper.projeto_arq_obj.banda.dto.EditBandaDTO;
import br.edu.insper.projeto_arq_obj.banda.dto.ResponseBandaDTO;
import br.edu.insper.projeto_arq_obj.banda.service.BandaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banda")
@Tag(name = "API  de Bandas", description = "API de gerenciamneto de bandas para shows")
public class BandaController {

    @Autowired
    BandaService bandaService;

    @Autowired
    UsuarioService usuarioService;

    @Operation(
            summary = "Lista todas as bandas",
            description = "Retorna uma lista com todas as bandas salvas no banco de dados"
    )
    @GetMapping
    public List<ResponseBandaDTO> listarTodos() {return bandaService.listarTodos();}

    @Operation(
            summary = "Cria uma banda",
            description = "Cria uma banda que será armazenada no banco de dados e vinculada" +
                    "ao show e aos membros"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBandaDTO criarBanda(@RequestHeader(name = "token") String token, @RequestBody CreateBandaDTO banda) {

        usuarioService.validarToken(token);

        return bandaService.criarBanda(banda);
    }

    @Operation(
            summary = "Edita uma banda",
            description = "Edita uma banda modificando os campos inseridos"
    )
    @PutMapping("/{id}")
    public ResponseBandaDTO editarBanda(@RequestHeader(name = "token") String token, @PathVariable Integer id, @RequestBody EditBandaDTO banda) {

        usuarioService.validarToken(token);

        return bandaService.editarBanda(id, banda);

    }

    @Operation(
            summary = "Deleta uma banda",
            description = "Deleta uma banda removendo do banco de dados"
    )
    @DeleteMapping("/{id}")
    public void deletarBanda(@RequestHeader(name = "token") String token, @PathVariable Integer id) {

        usuarioService.validarToken(token);

        bandaService.deletarBanda(id);

    }


}
