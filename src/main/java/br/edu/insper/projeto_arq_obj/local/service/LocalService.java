package br.edu.insper.projeto_arq_obj.local.service;

import br.edu.insper.projeto_arq_obj.banda.dto.EditBandaDTO;
import br.edu.insper.projeto_arq_obj.banda.dto.ResponseBandaDTO;
import br.edu.insper.projeto_arq_obj.banda.model.Banda;
import br.edu.insper.projeto_arq_obj.local.dto.CreateLocalDTO;
import br.edu.insper.projeto_arq_obj.local.dto.EditLocalDTO;
import br.edu.insper.projeto_arq_obj.local.dto.ResponseLocalDTO;
import br.edu.insper.projeto_arq_obj.local.model.Local;
import br.edu.insper.projeto_arq_obj.local.repository.LocalRepository;
import br.edu.insper.projeto_arq_obj.show.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LocalService {

    @Autowired
    LocalRepository localRepository;

    @Autowired
    ShowService showService;

    public ResponseLocalDTO criarLocal(CreateLocalDTO createLocalDTO) {

        Local local = new Local();
        local.setPais(createLocalDTO.pais());
        local.setEstado(createLocalDTO.estado());
        local.setCidade(createLocalDTO.cidade());
        local.setEndereco(createLocalDTO.endereco());
        local.setEstabelecimento(createLocalDTO.estabelecimento());

        localRepository.save(local);

        return ResponseLocalDTO.toDto(local);

    }

    public ResponseLocalDTO editarLocal(Integer id, EditLocalDTO localAtualizar) {
        Local local = buscarPorId(id);

        if (localAtualizar.pais() != null) {
            local.setPais(localAtualizar.pais());
        }

        if (localAtualizar.estado() != null) {
            local.setEstado(localAtualizar.estado());
        }

        if (localAtualizar.cidade() != null) {
            local.setCidade(localAtualizar.cidade());
        }

        if (localAtualizar.endereco() != null) {
            local.setEndereco(localAtualizar.endereco());
        }

        if (localAtualizar.estabelecimento() != null) {
            local.setEstabelecimento(localAtualizar.estabelecimento());
        }

        if (localAtualizar.idShow() != null) {
            showService.setarLocal(local, localAtualizar.idShow());
        }

        localRepository.save(local);

        return ResponseLocalDTO.toDto(local);

    }

    public List<ResponseLocalDTO> listarTodos(){
        return localRepository
                .findAll()
                .stream()
                .map(ResponseLocalDTO::toDto)
                .toList();
    }

    public Local buscarPorId(Integer id){
        return localRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deletarLocal(Integer id){
        localRepository.deleteById(id);
    }


}
