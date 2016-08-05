package validators;

import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class AreaFormData {

    public String nome = "";
    public String nomeGerente = "";

    /** Necessario para instanciar o form */
    public AreaFormData() {
    }

    public AreaFormData(String nome, String nomeGerente) {
        this.nome = nome;
        this.nomeGerente = nomeGerente;
    }

    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();

        if (nome == null || nome.length() == 0) {
            errors.add(new ValidationError("nome", "Preencha o nome"));
        }

        if (nomeGerente == null || nomeGerente.length() == 0) {
            errors.add(new ValidationError("nomeGerente", "Preencha o nome do gerente"));
        }

        return errors.isEmpty() ? null : errors;
    }
}
