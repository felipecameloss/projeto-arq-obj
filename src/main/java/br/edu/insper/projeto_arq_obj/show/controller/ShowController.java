package br.edu.insper.projeto_arq_obj.show.controller;


import br.edu.insper.projeto_arq_obj.autenticacao.service.UsuarioService;
import br.edu.insper.projeto_arq_obj.local.model.Local;
import br.edu.insper.projeto_arq_obj.show.dto.CreateShowDTO;
import br.edu.insper.projeto_arq_obj.show.dto.EditShowDTO;
import br.edu.insper.projeto_arq_obj.show.dto.ResponseShowDTO;
import br.edu.insper.projeto_arq_obj.show.model.Show;
import br.edu.insper.projeto_arq_obj.show.service.ShowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
@Tag(name = "API de shows", description = "API de gerenciamento de shows")
public class ShowController {
    @Autowired
    ShowService showService;

    @Autowired
    UsuarioService usuarioService;

    @Operation(
            summary = "Adicionar um show",
            description = "Adiciona um show que será armazenado no banco de dados"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseShowDTO adicionarShow(@RequestHeader(name = "token") String token, @RequestBody CreateShowDTO show) {
        usuarioService.validarToken(token);

        return showService.criarShow(show);
    }

    @Operation(
            summary = "Editar um show",
            description = "Edita um show, modificando os campos preenchidos"
    )
    @PutMapping("/{id}")
    public ResponseShowDTO editarShow(@RequestHeader(name = "token") String token, @PathVariable Integer id, @RequestBody EditShowDTO show) {
        usuarioService.validarToken(token);

        return showService.editarShow(id, show);
    }

    @Operation(
            summary = "Listar shows",
            description = "Lista todos os shows"
    )
    @GetMapping
    public List<ResponseShowDTO> listarTodos() {
        return showService.listarTodos();
    }

    @Operation(
            summary = "Buscar show por id",
            description = "Busca um show especifico por meu de seu id"
    )
    @GetMapping("/{id}")
    public Show buscarPorId(@PathVariable Integer id) {
        return showService.buscarPorId(id);
    }

    @Operation(
            summary = "Deletar show"
    )
    @DeleteMapping("/{id}")
    public void deletarShow(@RequestHeader(name = "token") String token, @PathVariable Integer id) {
        showService.deletarShow(id);
    }
}
