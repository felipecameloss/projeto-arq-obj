package br.edu.insper.projeto_arq_obj.show.repository;

import br.edu.insper.projeto_arq_obj.show.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Integer> {
}
