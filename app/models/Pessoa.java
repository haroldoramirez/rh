package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.PagedList;
import play.data.format.Formats;
import validators.PessoaFormData;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Pessoa extends Model {

    @Id
    private Long id;

    private String nome;

    private String cpf;

    private String localNascimento;

    private String nomeConjuge;

    private String nomePai;

    private String nomeMae;

    private String rg;

    private String orgaoEmissorRg;

    private String centroCusto;

    private String telefone;

    private String celular;

    private String email;

    private String salario;

    private String nomeBanco;

    private String nomeAgencia;

    private String contaNumero;

    private String saldoHoras;

    private String numeroPis;

    private String endereco;

    private String bairro;

    private String cidade;

    private String cep;

    private String complemento;

    //muitas pessoas tem uma escolaridade
    @ManyToOne
    private Escolaridade escolaridade;

    //muitas pessoas tem um genero
    @ManyToOne
    private Genero genero;

    //muitas pessoas tem um estado civil
    @ManyToOne
    private EstadoCivil estadoCivil;

    //muitas pessoas tem um tipo de funcionario
    @ManyToOne
    private Tipo tipo;

    //muitas pessoas tem um pais
    @ManyToOne
    private Pais pais;

    //muitas pessoas tem um cargo
    @ManyToOne
    private Cargo cargo;

    //muitas pessoas tem uma area
    @ManyToOne
    private Area area;

    //muitas pessoas tem muitos beneficios
//    @ManyToMany(cascade = CascadeType.ALL)
//    private List<Beneficio> beneficios;

    //muitas pessoas tem uma area
    @ManyToOne
    private Beneficio beneficio;

    @Formats.DateTime(pattern="yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Formats.DateTime(pattern="yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date dataEmissaoRg;

    @Formats.DateTime(pattern="yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date dataAdmissao;

    @Formats.DateTime(pattern="yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date dataDemissao;

    @Formats.DateTime(pattern="yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date dataFeriasInicio;

    @Formats.DateTime(pattern="yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date dataFeriasFim;

    @Formats.DateTime(pattern="yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    @Formats.DateTime(pattern="yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date dataAlteracao;

    public Pessoa() {}

    public Pessoa(Long id,
                  String nome,
                  String cpf,
                  String localNascimento,
                  String nomeConjuge,
                  String nomePai,
                  String nomeMae,
                  String rg,
                  String orgaoEmissorRg,
                  String centroCusto,
                  String telefone,
                  String celular,
                  String email,
                  Escolaridade escolaridade,
                  String salario,
                  String nomeBanco,
                  String nomeAgencia,
                  String contaNumero,
                  String saldoHoras,
                  String numeroPis,
                  String endereco,
                  String bairro,
                  String cidade,
                  String cep,
                  String complemento,
                  Genero genero,
                  EstadoCivil estadoCivil,
                  Tipo tipo,
                  Pais pais,
                  Cargo cargo,
                  Area area,
                  Beneficio beneficio,
                  Date dataNascimento,
                  Date dataAdmissao,
                  Date dataEmissaoRg) {
        this.setId(id);
        this.setNome(nome);
        this.setCpf(cpf);
        this.setLocalNascimento(localNascimento);
        this.setNomeConjuge(nomeConjuge);
        this.setNomePai(nomePai);
        this.setNomeMae(nomeMae);
        this.setRg(rg);
        this.setOrgaoEmissorRg(orgaoEmissorRg);
        this.setCentroCusto(centroCusto);
        this.setTelefone(telefone);
        this.setCelular(celular);
        this.setEmail(email);
        this.setEscolaridade(escolaridade);
        this.setSalario(salario);
        this.setNomeBanco(nomeBanco);
        this.setNomeAgencia(nomeAgencia);
        this.setContaNumero(contaNumero);
        this.setSaldoHoras(saldoHoras);
        this.setNumeroPis(numeroPis);
        this.setEndereco(endereco);
        this.setBairro(bairro);
        this.setCidade(cidade);
        this.setCep(cep);
        this.setComplemento(complemento);
        this.setGenero(genero);
        this.setEstadoCivil(estadoCivil);
        this.setTipo(tipo);
        this.setPais(pais);
        this.setCargo(cargo);
        this.setArea(area);
        this.setBeneficio(beneficio);
        this.setDataNascimento(dataNascimento);
        this.setDataAdmissao(dataAdmissao);
        this.setDataEmissaoRg(dataEmissaoRg);
    }

    public static Pessoa makeInstance(PessoaFormData formData) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(formData.nome);
        pessoa.setCpf(formData.cpf);
        pessoa.setLocalNascimento(formData.localNascimento);
        pessoa.setNomeConjuge(formData.nomeConjuge);
        pessoa.setNomePai(formData.nomePai);
        pessoa.setNomeMae(formData.nomeMae);
        pessoa.setRg(formData.rg);
        pessoa.setOrgaoEmissorRg(formData.orgaoEmissorRg);
        pessoa.setCentroCusto(formData.centroCusto);
        pessoa.setTelefone(formData.telefone);
        pessoa.setCelular(formData.celular);
        pessoa.setEmail(formData.email);
        pessoa.setEscolaridade(Escolaridade.findEscolaridade(formData.escolaridade));
        pessoa.setSalario(formData.salario);
        pessoa.setNomeBanco(formData.nomeBanco);
        pessoa.setNomeAgencia(formData.nomeAgencia);
        pessoa.setContaNumero(formData.contaNumero);
        pessoa.setSaldoHoras(formData.saldoHoras);
        pessoa.setNumeroPis(formData.numeroPis);
        pessoa.setEndereco(formData.endereco);
        pessoa.setBairro(formData.bairro);
        pessoa.setCidade(formData.cidade);
        pessoa.setCep(formData.cep);
        pessoa.setComplemento(formData.complemento);
        pessoa.setGenero(Genero.findGenero(formData.genero));
        pessoa.setEstadoCivil(EstadoCivil.findEstadoCivil(formData.estadoCivil));
        pessoa.setTipo(Tipo.findTipo(formData.tipo));
        pessoa.setPais(Pais.findPais(formData.pais));
        pessoa.setCargo(Cargo.findCargo(formData.cargo));
        pessoa.setArea(Area.findArea(formData.area));
        pessoa.setBeneficio(Beneficio.findBeneficio(formData.beneficio));
        pessoa.setDataNascimento(formData.dataNascimento);
        pessoa.setDataAdmissao(formData.dataAdmissao);
        pessoa.setDataEmissaoRg(formData.dataEmissaoRg);
        return pessoa;
    }

    public static PessoaFormData makePessoaFormData(Long id) {

        Pessoa pessoa = Ebean.find(Pessoa.class, id);

        if (pessoa == null) {
            throw new RuntimeException("Objeto não encontrado");
        }

        return new PessoaFormData(pessoa.nome,
                pessoa.cpf,
                pessoa.localNascimento,
                pessoa.nomeConjuge,
                pessoa.nomePai,
                pessoa.nomeMae,
                pessoa.rg,
                pessoa.orgaoEmissorRg,
                pessoa.centroCusto,
                pessoa.telefone,
                pessoa.celular,
                pessoa.email,
                pessoa.escolaridade,
                pessoa.salario,
                pessoa.nomeBanco,
                pessoa.nomeAgencia,
                pessoa.contaNumero,
                pessoa.saldoHoras,
                pessoa.numeroPis,
                pessoa.endereco,
                pessoa.bairro,
                pessoa.cidade,
                pessoa.cep,
                pessoa.complemento,
                pessoa.genero,
                pessoa.estadoCivil,
                pessoa.tipo,
                pessoa.pais,
                pessoa.cargo,
                pessoa.area,
                pessoa.beneficio,
                pessoa.dataNascimento,
                pessoa.dataAdmissao,
                pessoa.dataEmissaoRg);
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLocalNascimento() {
        return localNascimento;
    }

    public void setLocalNascimento(String localNascimento) {
        this.localNascimento = localNascimento;
    }

    public String getNomeConjuge() {
        return nomeConjuge;
    }

    public void setNomeConjuge(String nomeConjuge) {
        this.nomeConjuge = nomeConjuge;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrgaoEmissorRg() {
        return orgaoEmissorRg;
    }

    public void setOrgaoEmissorRg(String orgaoEmissorRg) {
        this.orgaoEmissorRg = orgaoEmissorRg;
    }

    public String getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(String centroCusto) {
        this.centroCusto = centroCusto;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Escolaridade getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public String getNomeAgencia() {
        return nomeAgencia;
    }

    public void setNomeAgencia(String nomeAgencia) {
        this.nomeAgencia = nomeAgencia;
    }

    public String getContaNumero() {
        return contaNumero;
    }

    public void setContaNumero(String contaNumero) {
        this.contaNumero = contaNumero;
    }

    public String getSaldoHoras() {
        return saldoHoras;
    }

    public void setSaldoHoras(String saldoHoras) {
        this.saldoHoras = saldoHoras;
    }

    public String getNumeroPis() {
        return numeroPis;
    }

    public void setNumeroPis(String numeroPis) {
        this.numeroPis = numeroPis;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Beneficio getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(Beneficio beneficio) {
        this.beneficio = beneficio;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataEmissaoRg() {
        return dataEmissaoRg;
    }

    public void setDataEmissaoRg(Date dataEmissaoRg) {
        this.dataEmissaoRg = dataEmissaoRg;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Date getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(Date dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    public Date getDataFeriasInicio() {
        return dataFeriasInicio;
    }

    public void setDataFeriasInicio(Date dataFeriasInicio) {
        this.dataFeriasInicio = dataFeriasInicio;
    }

    public Date getDataFeriasFim() {
        return dataFeriasFim;
    }

    public void setDataFeriasFim(Date dataFeriasFim) {
        this.dataFeriasFim = dataFeriasFim;
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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public static Finder<Long, Pessoa> find = new Finder<>(Pessoa.class);


    /**
     * Return a page of pessoa
     *
     * @param page Page to display
     * @param pageSize Number of pessoa per page
     * @param sortBy Cargo property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static PagedList<Pessoa> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
                find.where()
                        .ilike("nome", "%" + filter + "%")
                        .orderBy(sortBy + " " + order)
                        .fetch("cargo")
                        .findPagedList(page, pageSize);
    }
}
