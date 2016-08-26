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
public class Tipo extends Model {

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

    public static Finder<Long, Tipo> find = new Finder<>(Tipo.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<>();
        for (Tipo t : Tipo.find.orderBy("nome").findList()) {
            options.put(t.id.toString(),t.nome);
        }
        return options;
    }

    /**
     * Create a map of Area name -> boolean where the boolean is true if the Area corresponds to the student.
     * @param cadastro A student with a Area.
     * @return A map of Area to boolean indicating which one is the student's Area.
     */
    public static Map<String, Boolean> makeTipoMap(PessoaFormData cadastro) {
        Map<String, Boolean> tipoMap = new TreeMap<>();
        for (Tipo tipo : Ebean.find(Tipo.class).findList()) {
            tipoMap.put(tipo.getNome(), cadastro!=null && (cadastro.tipo != null && cadastro.tipo.equals(tipo.getNome())));
        }
        return tipoMap;
    }

    /**
     * Return the GradeLevel instance in the database with name 'levelName' or null if not found.
     * @param nome The Level name.
     * @return The GradeLevel instance, or null if not found.
     */
    public static Tipo findTipo(String nome) {
        for (Tipo tipo : Ebean.find(Tipo.class).findList()) {
            if (nome.equals(tipo.getNome())) {
                return tipo;
            }
        }
        return null;
    }
}
