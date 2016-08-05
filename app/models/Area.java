package models;

import com.avaje.ebean.Model;
import validators.AreaFormData;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Area extends Model {


    @Id
    private Long id;

    private String nome;

    private String nomeGerente;

    public Area() {}

    public Area(Long id, String nome, String nomeGerente) {
        this.setId(id);
        this.setNome(nome);
        this.setNomeGerente(nomeGerente);
    }

    public static Area makeInstance(AreaFormData formData) {
        Area area= new Area();
        area.setNome(formData.nome);
        area.setNomeGerente(formData.nomeGerente);
        return area;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeGerente() {
        return nomeGerente;
    }

    public void setNomeGerente(String nomeGerente) {
        this.nomeGerente = nomeGerente;
    }
}
