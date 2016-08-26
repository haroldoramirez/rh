package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import play.data.format.Formats;
import validators.CargoFormData;
import validators.PessoaFormData;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

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

    public static Finder<Long, Cargo> find = new Finder<>(Cargo.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<>();
        for (Cargo c : Cargo.find.orderBy("nome").findList()) {
            options.put(c.id.toString(),c.nome);
        }
        return options;
    }

    /**
     * Create a map of Area name -> boolean where the boolean is true if the Area corresponds to the student.
     * @param cadastro A student with a Area.
     * @return A map of Area to boolean indicating which one is the student's Area.
     */
    public static Map<String, Boolean> makeCargoMap(PessoaFormData cadastro) {
        Map<String, Boolean> cargoMap = new TreeMap<>();
        for (Cargo cargo : Ebean.find(Cargo.class).findList()) {
            cargoMap.put(cargo.getNome(), cadastro!=null && (cadastro.cargo != null && cadastro.cargo.equals(cargo.getNome())));
        }
        return cargoMap;
    }

    /**
     * Return the GradeLevel instance in the database with name 'levelName' or null if not found.
     * @param nome The Level name.
     * @return The GradeLevel instance, or null if not found.
     */
    public static Cargo findCargo(String nome) {
        for (Cargo cargo : Ebean.find(Cargo.class).findList()) {
            if (nome.equals(cargo.getNome())) {
                return cargo;
            }
        }
        return null;
    }
}
