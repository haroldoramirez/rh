package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import play.data.format.Formats;
import validators.AreaFormData;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Area extends Model {

    @Id
    private Long id;

    private String nome;

    private String nomeGerente;

    @Formats.DateTime(pattern="yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    @Formats.DateTime(pattern="yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date dataAlteracao;

    public Area() {}

    public Area(Long id, String nome, String nomeGerente) {
        this.setId(id);
        this.setNome(nome);
        this.setNomeGerente(nomeGerente);
    }

    public static Area makeInstance(AreaFormData formData) {
        Area area = new Area();
        area.setNome(formData.nome);
        area.setNomeGerente(formData.nomeGerente);
        return area;
    }

    public static AreaFormData makeAreaFormData(Long id) {

        Area area = Ebean.find(Area.class, id);

        if (area == null) {
            throw new RuntimeException("Objeto não encontrado");
        }

        return new AreaFormData(area.nome, area.nomeGerente);
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

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
