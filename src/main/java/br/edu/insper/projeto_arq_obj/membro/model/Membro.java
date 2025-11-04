package br.edu.insper.projeto_arq_obj.membro.model;

import br.edu.insper.projeto_arq_obj.banda.model.Banda;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Membro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false)
    private String ocupacao;

    @ManyToMany
    @JoinTable(
            name = "banda_membro",
            joinColumns = @JoinColumn(name = "id_membro"),
            inverseJoinColumns = @JoinColumn(name = "id_banda")
    )
    private List<Banda> bandas = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public List<Banda> getBandas() {
        return bandas;
    }

    public void setBandas(List<Banda> bandas) {
        this.bandas = bandas;
    }

    public void setMembro(String membro) {

    }
}
