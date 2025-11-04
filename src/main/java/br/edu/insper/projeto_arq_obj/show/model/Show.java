package br.edu.insper.projeto_arq_obj.show.model;

import br.edu.insper.projeto_arq_obj.banda.model.Banda;
import br.edu.insper.projeto_arq_obj.local.model.Local;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "id_local")
    private Local local;

    @ManyToMany
    @JoinTable(
            name = "show_banda",
            joinColumns = @JoinColumn(name = "id_show"),
            inverseJoinColumns = @JoinColumn(name = "id_banda")
    )
    private Set<Banda> bandas = new HashSet<>();


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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Set<Banda> getBandas() {
        return bandas;
    }

    public void setBandas(Set<Banda> bandas) {
        this.bandas = bandas;
    }

    public void addBanda(Banda banda) {
        bandas.add(banda);
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }
}
