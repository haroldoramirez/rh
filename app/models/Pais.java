package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import validators.PessoaFormData;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Entity
public class Pais extends Model {

    @Id
    private Long id;

    private String nome;

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

    public static Finder<Long, Pais> find = new Finder<>(Pais.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<>();
        for (Pais p : Pais.find.orderBy("nome").findList()) {
            options.put(p.id.toString(),p.nome);
        }
        return options;
    }

    /**
     * Create a map of Area name -> boolean where the boolean is true if the Area corresponds to the student.
     * @param cadastro A student with a Area.
     * @return A map of Area to boolean indicating which one is the student's Area.
     */
    public static Map<String, Boolean> makePaisMap(PessoaFormData cadastro) {
        Map<String, Boolean> paisMap = new TreeMap<>();
        for (Pais pais : Ebean.find(Pais.class).findList()) {
            paisMap.put(pais.getNome(), cadastro!=null && (cadastro.pais != null && cadastro.pais.equals(pais.getNome())));
        }
        return paisMap;
    }

    /**
     * Return the GradeLevel instance in the database with name 'levelName' or null if not found.
     * @param nome The Level name.
     * @return The GradeLevel instance, or null if not found.
     */
    public static Pais findPais(String nome) {
        for (Pais pais : Ebean.find(Pais.class).findList()) {
            if (nome.equals(pais.getNome())) {
                return pais;
            }
        }
        return null;
    }

}
