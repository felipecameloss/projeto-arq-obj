package br.edu.insper.projeto_arq_obj.banda.service;

import br.edu.insper.projeto_arq_obj.banda.dto.CreateBandaDTO;
import br.edu.insper.projeto_arq_obj.banda.dto.EditBandaDTO;
import br.edu.insper.projeto_arq_obj.banda.dto.ResponseBandaDTO;
import br.edu.insper.projeto_arq_obj.banda.model.Banda;
import br.edu.insper.projeto_arq_obj.banda.repository.BandaRepository;
import br.edu.insper.projeto_arq_obj.membro.model.Membro;
import br.edu.insper.projeto_arq_obj.membro.service.MembroService;
import br.edu.insper.projeto_arq_obj.show.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BandaService {

    @Autowired
    private BandaRepository bandaRepository;

    @Autowired
    private ShowService showService;

    //aplicando o CRUD de banda
    public ResponseBandaDTO criarBanda(CreateBandaDTO createBandaDTO){

        Banda banda = new Banda();
        banda.setGenero(createBandaDTO.genero());
        banda.setGravadora(createBandaDTO.gravadora());
        banda.setNome(createBandaDTO.nome());

        showService.adicionarBanda(banda, createBandaDTO.idShow());

        bandaRepository.save(banda);

        return ResponseBandaDTO.toDto(banda);
    }

    public ResponseBandaDTO editarBanda(Integer id, EditBandaDTO bandaAtualizar){
        Banda banda = buscarPorId(id);

        if (bandaAtualizar.genero() != null){
            banda.setGenero(bandaAtualizar.genero());
        }

        if (bandaAtualizar.gravadora() != null){
            banda.setGravadora(bandaAtualizar.gravadora());
        }

        if (bandaAtualizar.nome() != null){
            banda.setNome(bandaAtualizar.nome());
        }

        bandaRepository.save(banda);

        return ResponseBandaDTO.toDto(banda);
    }

    public List<ResponseBandaDTO> listarTodos(){
        return bandaRepository
                .findAll()
                .stream()
                .map(ResponseBandaDTO::toDto)
                .toList();
    }

    public void deletarBanda(Integer id){
        bandaRepository.deleteById(id);
    }

    public Banda buscarPorId(Integer id){
        return bandaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
