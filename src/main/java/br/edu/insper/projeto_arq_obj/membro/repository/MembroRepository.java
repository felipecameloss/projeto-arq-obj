package br.edu.insper.projeto_arq_obj.membro.repository;

import br.edu.insper.projeto_arq_obj.membro.model.Membro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembroRepository extends JpaRepository<Membro, Integer> {
}
