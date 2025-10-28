package br.edu.insper.projeto_arq_obj.local;

import br.edu.insper.projeto_arq_obj.banda.model.Banda;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String pais;
    private String estado;
    private String cidade;
    private String endereco;
    private String estabelecimento;

    @OneToMany(mappedBy = "local")
    private List<Banda> bandas;

}
