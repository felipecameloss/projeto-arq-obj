package br.edu.insper.projeto_arq_obj.membro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import br.edu.insper.projeto_arq_obj.membro.repository.MembroRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MembroService {

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private BandaService bandaService;

    public ResponseMembroDTO criarMembro(CreatMembroDTO creatMembroDTO){
        Membro membro = new Membro();
        membro.setMembro(creatMembroDTO.membro());
        membro.setCpf(creatMembroDTO.cpf());
        membro.setOcupacao(creatMembroDTO.ocupacao());

        membroRepository.save(membro);

        return ResponseMembroDTO.toDto(membro);
    }

    public ResponseMembroDTO editarMembro(Integer id, EditMembroDTO membroAtualizar){
        Membro membro = buscarPorId(id);

        if (membroAtualizar.membro() != null){
            membro.setMembro(membroAtualizar.membro());
        }

        if (membroAtualizar.ocupacao() != null){
            membro.setOcupacao(membroAtualizar.ocupacao());
        }

        membroRepository.save(membro);

        return ResponseMembroDTO.toDto(membro);
    }

    public List<ResponseMembroDTO> listarTodos(){
        return membroRepository
                .findAll()
                .stream()
                .map(ResponseMembroDTO::toDto)
                .toList();
    }

    public void deletarMembro(Integer id){
        membroRepository.deleteById(id);
    }

    public Membro buscarPorId(Integer id){
        return membroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    
}
