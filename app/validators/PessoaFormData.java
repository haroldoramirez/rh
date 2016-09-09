package validators;

import models.*;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PessoaFormData {

    public String nome = "";
    public String cpf = "";
    public String localNascimento = "";
    public String nomeConjuge = "";
    public String nomePai = "";
    public String nomeMae = "";
    public String rg = "";
    public String orgaoEmissorRg = "";
    public String centroCusto = "";
    public String telefone = "";
    public String celular = "";
    public String email = "";
    public String escolaridade = "";
    public String salario = "";
    public String nomeBanco = "";
    public String nomeAgencia = "";
    public String contaNumero = "";
    public String saldoHoras = "";
    public String numeroPis = "";
    public String genero = "";
    public String estadoCivil;
    public String tipo = "";
    public String pais = "";
    public String cargo = "";
    public String area = "";
    public String beneficio = "";
    public Date dataNascimento = null;
    public Date dataAdmissao = null;
    public Date dataEmissaoRg = null;


    /** Necessario para instanciar o form */
    public PessoaFormData() {}

    public PessoaFormData(String nome,
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
        this.nome = nome;
        this.cpf = cpf;
        this.localNascimento = localNascimento;
        this.nomeConjuge = nomeConjuge;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.rg = rg;
        this.orgaoEmissorRg = orgaoEmissorRg;
        this.centroCusto = centroCusto;
        this.telefone = telefone;
        this.celular = celular;
        this.email = email;
        this.escolaridade = escolaridade.getNome();
        this.salario = salario;
        this.nomeBanco = nomeBanco;
        this.nomeAgencia = nomeAgencia;
        this.contaNumero = contaNumero;
        this.saldoHoras = saldoHoras;
        this.numeroPis = numeroPis;
        this.genero = genero.getNome();
        this.estadoCivil = estadoCivil.getNome();
        this.tipo = tipo.getNome();
        this.pais = pais.getNome();
        this.cargo = cargo.getNome();
        this.area = area.getNome();
        this.beneficio = beneficio.getNome();
        this.dataNascimento = dataNascimento;
        this.dataAdmissao = dataAdmissao;
        this.dataEmissaoRg = dataEmissaoRg;
    }

    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();

        if (nome == null || nome.length() == 0) {
            errors.add(new ValidationError("nome", "Preencha o nome"));
        }

        if (area == null || area.length() == 0) {
            errors.add(new ValidationError("area", "Selecione a área"));
        }

        if (beneficio == null || beneficio.length() == 0) {
            errors.add(new ValidationError("beneficio", "Selecione o benefício"));
        }

        if (cargo == null || cargo.length() == 0) {
            errors.add(new ValidationError("cargo", "Selecione o cargo"));
        }

        if (escolaridade == null || escolaridade.length() == 0) {
            errors.add(new ValidationError("escolaridade", "Selecione a escolaridade "));
        }

        if (genero == null || genero.length() == 0) {
            errors.add(new ValidationError("genero", "Selecione o gênero "));
        }

        if (estadoCivil == null || estadoCivil.length() == 0) {
            errors.add(new ValidationError("estadoCivil", "Selecione o estado civil "));
        }

        if (tipo == null || tipo.length() == 0) {
            errors.add(new ValidationError("tipo", "Selecione o tipo "));
        }

        if (pais == null || pais.length() == 0) {
            errors.add(new ValidationError("pais", "Selecione o país "));
        }

        if (dataNascimento == null) {
            errors.add(new ValidationError("dataNascimento", "Selecione a data "));
        }

        if (dataAdmissao == null) {
            errors.add(new ValidationError("dataAdmissao", "Selecione a data "));
        }

        if (dataEmissaoRg == null) {
            errors.add(new ValidationError("dataEmissaoRg", "Selecione a data "));
        }

        return errors.isEmpty() ? null : errors;
    }
}
