package br.edu.insper.projeto_arq_obj.local.repository;

import br.edu.insper.projeto_arq_obj.local.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalRepository extends JpaRepository<Local, Integer> {
}
