package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import play.data.format.Formats;
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
public class EstadoCivil extends Model {

    @Id
    private Long id;

    private String nome;

    @Formats.DateTime(pattern="yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    @Formats.DateTime(pattern="yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date dataAlteracao;

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

    public static Finder<Long, EstadoCivil> find = new Finder<>(EstadoCivil.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<>();
        for (EstadoCivil estadoCivil : EstadoCivil.find.orderBy("nome").findList()) {
            options.put(estadoCivil.id.toString(),estadoCivil.nome);
        }
        return options;
    }

    /**
     * Create a map of Area name -> boolean where the boolean is true if the Area corresponds to the student.
     * @param cadastro A student with a Area.
     * @return A map of Area to boolean indicating which one is the student's Area.
     */
    public static Map<String, Boolean> makeEstadoCivilMap(PessoaFormData cadastro) {
        Map<String, Boolean> estadoCivilMap = new TreeMap<>();
        for (EstadoCivil estadoCivil : Ebean.find(EstadoCivil.class).findList()) {
            estadoCivilMap.put(estadoCivil.getNome(), cadastro!=null && (cadastro.estadoCivil != null && cadastro.estadoCivil.equals(estadoCivil.getNome())));
        }
        return estadoCivilMap;
    }

    /**
     * Return the GradeLevel instance in the database with name 'levelName' or null if not found.
     * @param nome The Level name.
     * @return The GradeLevel instance, or null if not found.
     */
    public static EstadoCivil findEstadoCivil(String nome) {
        for (EstadoCivil estadoCivil : Ebean.find(EstadoCivil.class).findList()) {
            if (nome.equals(estadoCivil.getNome())) {
                return estadoCivil;
            }
        }
        return null;
    }
}
