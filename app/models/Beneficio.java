package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.PagedList;
import play.data.format.Formats;
import validators.BeneficioFormData;
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
public class Beneficio extends Model {

    @Id
    private Long id;

    private String nome;

    @Formats.DateTime(pattern="yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    @Formats.DateTime(pattern="yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date dataAlteracao;

    public Beneficio() {}

    public Beneficio(Long id, String nome) {
        this.setId(id);
        this.setNome(nome);
    }

    public static Beneficio makeInstance(BeneficioFormData formData) {
        Beneficio beneficio = new Beneficio();
        beneficio.setNome(formData.nome);
        return beneficio;
    }

    public static BeneficioFormData makeBeneficioFormData(Long id) {

        Beneficio beneficio = Ebean.find(Beneficio.class, id);

        if (beneficio == null) {
            throw new RuntimeException("Objeto não encontrado.");
        }

        return new BeneficioFormData(beneficio.nome);
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

    public static Finder<Long, Beneficio> find = new Finder<>(Beneficio.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<>();
        for (Beneficio b : Beneficio.find.orderBy("nome").findList()) {
            options.put(b.id.toString(),b.nome);
        }
        return options;
    }

    /**
     * Create a map of Area name -> boolean where the boolean is true if the Area corresponds to the student.
     * @param cadastro A student with a Area.
     * @return A map of Area to boolean indicating which one is the student's Area.
     */
    public static Map<String, Boolean> makeBeneficioMap(PessoaFormData cadastro) {
        Map<String, Boolean> beneficioMap = new TreeMap<>();
        for (Beneficio beneficio : Ebean.find(Beneficio.class).findList()) {
            beneficioMap.put(beneficio.getNome(), cadastro!=null && (cadastro.beneficio != null && cadastro.beneficio.equals(beneficio.getNome())));
        }
        return beneficioMap;
    }

    /**
     * Return the GradeLevel instance in the database with name 'levelName' or null if not found.
     * @param nome The Level name.
     * @return The GradeLevel instance, or null if not found.
     */
    public static Beneficio findBeneficio(String nome) {
        for (Beneficio beneficio : Ebean.find(Beneficio.class).findList()) {
            if (nome.equals(beneficio.getNome())) {
                return beneficio;
            }
        }
        return null;
    }

    /**
     * Return a page of area
     *
     * @param page Page to display
     * @param pageSize Number of pessoa per page
     * @param sortBy Cargo property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static PagedList<Beneficio> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
                find.where()
                        .ilike("nome", "%" + filter + "%")
                        .orderBy(sortBy + " " + order)
                        .findPagedList(page, pageSize);
    }

}
