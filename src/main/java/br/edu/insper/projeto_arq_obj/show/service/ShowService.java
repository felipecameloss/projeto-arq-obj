package br.edu.insper.projeto_arq_obj.show.service;

import br.edu.insper.projeto_arq_obj.banda.model.Banda;
import br.edu.insper.projeto_arq_obj.banda.repository.BandaRepository;
import br.edu.insper.projeto_arq_obj.local.model.Local;
import br.edu.insper.projeto_arq_obj.show.model.Show;
import br.edu.insper.projeto_arq_obj.show.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    BandaRepository bandaRepository;

    @Autowired
    ShowRepository showRepository;

    public void adicionarBanda(Banda banda, Integer idShow) {

        Show show = showRepository.findById(idShow)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        show.addBanda(banda);

    }

    public void setLocal(Local local, Integer idShow) {
        Show show = showRepository.findById(idShow)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        show.setLocal(local);
    }

}
