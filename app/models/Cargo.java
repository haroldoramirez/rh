package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import play.data.format.Formats;
import validators.CargoFormData;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Cargo extends Model {

    @Id
    private Long id;

    private String nome;

    @Formats.DateTime(pattern="yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    @Formats.DateTime(pattern="yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date dataAlteracao;

    public Cargo() {}

    public Cargo(Long id, String nome) {
        this.setId(id);
        this.setNome(nome);
    }

    public static Cargo makeInstance(CargoFormData formData) {
        Cargo cargo = new Cargo();
        cargo.setNome(formData.nome);
        return cargo;
    }

    public static CargoFormData makeCargoFormData(Long id) {

        Cargo cargo = Ebean.find(Cargo.class, id);

        if (cargo == null) {
            throw new RuntimeException("Objeto não encontrado.");
        }

        return new CargoFormData(cargo.nome);
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
