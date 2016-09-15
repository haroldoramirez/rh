package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.PagedList;
import play.data.format.Formats;
import validators.AreaFormData;
import validators.PessoaFormData;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.*;

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

    public static Finder<Long, Area> find = new Finder<>(Area.class);

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<>();
        for (Area a : Area.find.orderBy("nome").findList()) {
            options.put(a.id.toString(),a.nome);
        }
        return options;
    }

    /**
     * Create a map of Area name -> boolean where the boolean is true if the Area corresponds to the student.
     * @param cadastro A student with a Area.
     * @return A map of Area to boolean indicating which one is the student's Area.
     */
    public static Map<String, Boolean> makeAreaMap(PessoaFormData cadastro) {
        Map<String, Boolean> areaMap = new TreeMap<>();
        for (Area area : Ebean.find(Area.class).findList()) {
            areaMap.put(area.getNome(), cadastro!=null && (cadastro.area != null && cadastro.area.equals(area.getNome())));
        }
        return areaMap;
    }

    /**
     * Return the GradeLevel instance in the database with name 'levelName' or null if not found.
     * @param nome The Level name.
     * @return The GradeLevel instance, or null if not found.
     */
    public static Area findArea(String nome) {
        for (Area area : Ebean.find(Area.class).findList()) {
            if (nome.equals(area.getNome())) {
                return area;
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
    public static PagedList<Area> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
                find.where()
                        .ilike("nome", "%" + filter + "%")
                        .orderBy(sortBy + " " + order)
                        .findPagedList(page, pageSize);
    }

}
