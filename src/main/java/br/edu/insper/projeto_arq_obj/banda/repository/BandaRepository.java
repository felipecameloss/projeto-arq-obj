package br.edu.insper.projeto_arq_obj.banda.repository;

import br.edu.insper.projeto_arq_obj.banda.model.Banda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandaRepository extends JpaRepository<Banda, Integer> {
}
