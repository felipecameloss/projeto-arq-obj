package br.edu.insper.projeto_arq_obj.show.service;

import br.edu.insper.projeto_arq_obj.banda.model.Banda;
import br.edu.insper.projeto_arq_obj.banda.repository.BandaRepository;
import br.edu.insper.projeto_arq_obj.local.dto.ResponseLocalDTO;
import br.edu.insper.projeto_arq_obj.local.model.Local;
import br.edu.insper.projeto_arq_obj.local.repository.LocalRepository;
import br.edu.insper.projeto_arq_obj.show.dto.CreateShowDTO;
import br.edu.insper.projeto_arq_obj.show.dto.EditShowDTO;
import br.edu.insper.projeto_arq_obj.show.dto.ResponseShowDTO;
import br.edu.insper.projeto_arq_obj.show.model.Show;
import br.edu.insper.projeto_arq_obj.show.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    BandaRepository bandaRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    LocalRepository localRepository;

    public ResponseShowDTO criarShow(CreateShowDTO createShowDTO) {
        Show show = new Show();
        show.setNome(createShowDTO.nome());
        show.setData(createShowDTO.data());

        Local local = localRepository.findById(createShowDTO.idLocal())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        show.setLocal(local);

        showRepository.save(show);

        return ResponseShowDTO.toDto(show);
    }

    public ResponseShowDTO editarShow(Integer id, EditShowDTO showAtualizado) {
        Show show = buscarPorId(id);

        if (showAtualizado.nome() != null) {
            show.setNome(showAtualizado.nome());
        }

        if (showAtualizado.data() != null) {
            show.setData(showAtualizado.data());
        }

        if (showAtualizado.idLocal() != null) {
            Local local = localRepository.findById(showAtualizado.idLocal())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            show.setLocal(local);
        }

        if (showAtualizado.bandas() != null) {
            show.setBandas(showAtualizado.bandas());
        }

        showRepository.save(show);

        return ResponseShowDTO.toDto(show);
    }

    public List<ResponseShowDTO> listarTodos() {
        return showRepository
                .findAll()
                .stream()
                .map(ResponseShowDTO::toDto)
                .toList();
    }

    public Show buscarPorId(Integer id) {
        return showRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void deletarShow(Integer id) {
        showRepository.deleteById(id);
    }

    public void adicionarBanda(Banda banda, Integer id) {
        Show show = showRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        show.addBanda(banda);
    }

    public void setarLocal(Local local, Integer idShow) {
        Show show = showRepository.findById(idShow)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        show.setLocal(local);
    }

}
