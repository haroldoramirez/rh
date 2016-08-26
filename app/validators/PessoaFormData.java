package validators;

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
    public String contaAgencia = "";
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
    public PessoaFormData() {
    }

    public PessoaFormData(String nome) {
        this.nome = nome;
    }

    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();

        if (nome == null || nome.length() == 0) {
            errors.add(new ValidationError("nome", "Preencha o nome"));
        }

        if (area == null || area.length() == 0) {
            errors.add(new ValidationError("area", "Preencha a área"));
        }

        if (beneficio == null || beneficio.length() == 0) {
            errors.add(new ValidationError("beneficio", "Preencha o benefício"));
        }

        if (cargo == null || cargo.length() == 0) {
            errors.add(new ValidationError("cargo", "Preencha o cargo"));
        }

        return errors.isEmpty() ? null : errors;
    }
}
