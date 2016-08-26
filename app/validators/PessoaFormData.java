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
    public String ufNascimento = "";
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
    public String contaDigito = "";
    public String saldoHoras = "";
    public String numeroPis = "";
    public String genero = "";
    public String estadoCivil;
    public String tipo = "";
    public String endereco = "";
    public String cargo = "";
    public String area = "";
    public String beneficio = "";
    public Date dataNascimento = null;
    public Date dataAdmissao = null;


    /** Necessario para instanciar o form */
    public PessoaFormData() {}

    public PessoaFormData(String nome,
                          String cpf,
                          String localNascimento,
                          String ufNascimento,
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
                          String contaDigito,
                          String saldoHoras,
                          String numeroPis,
                          Genero genero,
                          EstadoCivil estadoCivil,
                          Tipo tipo,
                          Endereco endereco,
                          Cargo cargo,
                          Area area,
                          Date dataNascimento,
                          Date dataAdmissao) {
        this.nome = nome;
        this.cargo = cpf;
        this.localNascimento = localNascimento;
        this.ufNascimento = ufNascimento;
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
        this.contaDigito = contaDigito;
        this.saldoHoras = saldoHoras;
        this.numeroPis = numeroPis;
        this.genero = genero.getNome();
        this.estadoCivil = estadoCivil.getNome();
        this.tipo = tipo.getNome();
        this.endereco = endereco.getNomeRua();
        this.cargo = cargo.getNome();
        this.area = area.getNome();
        this.dataNascimento = dataNascimento;
        this.dataAdmissao = dataAdmissao;
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

        return errors.isEmpty() ? null : errors;
    }
}
