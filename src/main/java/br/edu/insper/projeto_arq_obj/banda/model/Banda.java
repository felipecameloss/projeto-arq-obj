package br.edu.insper.projeto_arq_obj.banda.model;

import br.edu.insper.projeto_arq_obj.membro.model.Membro;
import br.edu.insper.projeto_arq_obj.show.model.Show;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Banda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nome;
    @Column(nullable = false)
    private String genero;
    private String gravadora;

    @ManyToMany(mappedBy = "membros")
    private List<Membro> membros;

    @ManyToMany
    @JoinTable(
            name = "banda_show",
            joinColumns = @JoinColumn(name = "id_banda"),
            inverseJoinColumns = @JoinColumn(name = "id_show")
    )
    private List<Show> shows;

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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getGravadora() {
        return gravadora;
    }

    public void setGravadora(String gravadora) {
        this.gravadora = gravadora;
    }

    public List<Membro> getMembros() {
        return membros;
    }

    public void setMembros(List<Membro> membros) {
        this.membros = membros;
    }
}
