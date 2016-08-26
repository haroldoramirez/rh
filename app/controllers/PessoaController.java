package controllers;

import com.avaje.ebean.Ebean;
import models.*;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import validators.PessoaFormData;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class PessoaController extends Controller {

    @Inject
    FormFactory formFactory;

    /**
     * @return noticia form if auth OK or not authorized
     */
    public Result telaNovo(Long id) {

        PessoaFormData pessoaData = (id == 0) ? new PessoaFormData() : models.Pessoa.makePessoaFormData(id);

        Form<PessoaFormData> pessoaForm = formFactory.form(PessoaFormData.class);

        return ok(views.html.colaboradores.create.render(pessoaForm,
                Area.makeAreaMap(pessoaData),
                Beneficio.makeBeneficioMap(pessoaData),
                Cargo.makeCargoMap(pessoaData),
                Genero.makeGeneroMap(pessoaData),
                Tipo.makeTipoMap(pessoaData),
                EstadoCivil.makeEstadoCivilMap(pessoaData),
                Escolaridade.makeEscolaridadeMap(pessoaData)));
    }

    /**
     * @return render a detail form with a noticia data
     */
    public Result telaDetalhe(Long id) {
        try {
            Pessoa pessoa = Ebean.find(Pessoa.class, id);

            if (pessoa == null) {
                return notFound(views.html.mensagens.colaborador.erro.render("Objeto não encontrado."));
            }

            return ok(views.html.colaboradores.detail.render(pessoa));
        } catch (Exception e) {
            Logger.error(e.toString());
            return badRequest(views.html.mensagens.colaborador.erro.render(e.toString()));
        }
    }

    /**
     * @return render edit form with a noticia data
     */
    public Result telaEditar(Long id) {
        Form<Pessoa> pessoaForm = formFactory.form(Pessoa.class);
        return ok(views.html.colaboradores.edit.render(id,pessoaForm));
    }

    /**
     * Retrieve a list of all noticias
     *
     * @return a list of all noticias in a render template
     */
    public Result telaLista() {
        try {
            List<Pessoa> pessoas = Ebean.find(Pessoa.class).findList();
            return ok(views.html.colaboradores.list.render(pessoas, ""));
        } catch (Exception e) {
            Logger.error(e.toString());
            return badRequest(views.html.mensagens.colaborador.erro.render(e.toString()));
        }
    }

    /**
     * Save a noticia
     *
     * @return a render view to inform OK
     */
    public Result inserir(Long id) {
        PessoaFormData pessoaData = (id == 0) ? new PessoaFormData() : models.Pessoa.makePessoaFormData(id);

        //Resgata os dados do formulario atraves de uma requisicao e realiza a validacao dos campos
        Form<PessoaFormData> formData = formFactory.form(PessoaFormData.class).bindFromRequest();

        //se existir erros nos campos do formulario retorne o LivroFormData com os erros
        if (formData.hasErrors()) {
            return badRequest(views.html.colaboradores.create.render(formData,
                    Area.makeAreaMap(pessoaData),
                    Beneficio.makeBeneficioMap(pessoaData),
                    Cargo.makeCargoMap(pessoaData),
                    Genero.makeGeneroMap(pessoaData),
                    Tipo.makeTipoMap(pessoaData),
                    EstadoCivil.makeEstadoCivilMap(pessoaData),
                    Escolaridade.makeEscolaridadeMap(pessoaData)));
        }
        else {
            try {
                //Converte os dados do formularios para uma instancia
                Pessoa pessoa = Pessoa.makeInstance(formData.get());
                pessoa.setDataCadastro(new Date());
                pessoa.save();
                return created(views.html.mensagens.colaborador.cadastrado.render(pessoa.getNome()));
            } catch (Exception e) {
                Logger.error(e.getMessage());
                formData.reject("Não foi possível cadastrar, erro interno de sistema.");
                return badRequest(views.html.colaboradores.create.render(formData,
                        Area.makeAreaMap(pessoaData),
                        Beneficio.makeBeneficioMap(pessoaData),
                        Cargo.makeCargoMap(pessoaData),
                        Genero.makeGeneroMap(pessoaData),
                        Tipo.makeTipoMap(pessoaData),
                        EstadoCivil.makeEstadoCivilMap(pessoaData),
                        Escolaridade.makeEscolaridadeMap(pessoaData)));
            }

        }

    }

    /**
     * Update a noticia from id
     *
     * @param id variavel identificadora
     * @return a noticia updated with a form
     */
    public Result editar(Long id) {
        return TODO;
    }

    /**
     * Remove from a id
     *
     * @param id variavel identificadora
     * @return ok removed
     */
    public Result remover(Long id) {
        String pessoaNome;

        try {
            //busca para ser excluido
            Pessoa pessoa = Ebean.find(Pessoa.class, id);

            if (pessoa == null) {
                return notFound(views.html.mensagens.area.erro.render("Objeto não encontrado."));
            }

            pessoaNome = pessoa.getNome();

            Ebean.delete(pessoa);
            return ok(views.html.mensagens.colaborador.removido.render(pessoaNome));
        } catch (Exception e) {
            Logger.error(e.toString());
            return badRequest(views.html.mensagens.area.erro.render(e.toString()));
        }
    }
}
