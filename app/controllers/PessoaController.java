package controllers;

import actions.Secured;
import com.avaje.ebean.Ebean;
import models.*;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import validators.PessoaFormData;
import views.html.colaboradores.*;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.Date;

@Security.Authenticated(Secured.class)
public class PessoaController extends Controller {

    @Inject
    FormFactory formFactory;

    /**
     * @return a object user authenticated
     */
    @Nullable
    private Usuario atual() {
        String username = session().get("email");

        try {
            //retorna o usuário atual que esteja logado no sistema
            return Ebean.createQuery(Usuario.class, "find usuario where email = :email")
                    .setParameter("email", username)
                    .findUnique();
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return null;
        }
    }

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
                Escolaridade.makeEscolaridadeMap(pessoaData),
                Pais.makePaisMap(pessoaData), atual()));
    }

    /**
     * @return render a detail form with a noticia data
     */
    public Result telaDetalhe(Long id) {
        try {
            Pessoa pessoa = Ebean.find(Pessoa.class, id);

            if (pessoa == null) {
                return notFound(views.html.mensagens.colaborador.erro.render("Objeto não encontrado.", atual()));
            }

            return ok(views.html.colaboradores.detail.render(pessoa, atual()));
        } catch (Exception e) {
            Logger.error(e.toString());
            return badRequest(views.html.mensagens.colaborador.erro.render(e.toString(), atual()));
        }
    }

    /**
     * @return render edit form with a noticia data
     */
    public Result telaEditar(Long id) {

        try {
            //logica onde instanciamos um objeto que esteja cadastrado na base de dados
            PessoaFormData pessoaFormData = (id == 0) ? new PessoaFormData() : models.Pessoa.makePessoaFormData(id);

            //apos o objeto ser instanciado levamos os dados para o formdata e os dados serao carregados no form edit
            Form<PessoaFormData> pessoaForm = formFactory.form(PessoaFormData.class).fill(pessoaFormData);

            return ok(views.html.colaboradores.edit.render(id, pessoaForm, Area.makeAreaMap(pessoaFormData),
                    Beneficio.makeBeneficioMap(pessoaFormData),
                    Cargo.makeCargoMap(pessoaFormData),
                    Genero.makeGeneroMap(pessoaFormData),
                    Tipo.makeTipoMap(pessoaFormData),
                    EstadoCivil.makeEstadoCivilMap(pessoaFormData),
                    Escolaridade.makeEscolaridadeMap(pessoaFormData),
                    Pais.makePaisMap(pessoaFormData),atual()));
        } catch (Exception e) {
            Logger.error(e.toString());
            return badRequest(views.html.mensagens.colaborador.erro.render(e.toString(), atual()));
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
                    Escolaridade.makeEscolaridadeMap(pessoaData),
                    Pais.makePaisMap(pessoaData),atual()));
        }
        else {
            try {
                //Converte os dados do formularios para uma instancia
                Pessoa pessoa = Pessoa.makeInstance(formData.get());
                pessoa.setDataCadastro(new Date());
                pessoa.save();
                return created(views.html.mensagens.colaborador.cadastrado.render(pessoa.getNome(), atual()));
            } catch (Exception e) {
                Logger.error(e.getMessage());
                formData.reject("Erro interno de sistema. " + e);
                return badRequest(views.html.colaboradores.create.render(formData,
                        Area.makeAreaMap(pessoaData),
                        Beneficio.makeBeneficioMap(pessoaData),
                        Cargo.makeCargoMap(pessoaData),
                        Genero.makeGeneroMap(pessoaData),
                        Tipo.makeTipoMap(pessoaData),
                        EstadoCivil.makeEstadoCivilMap(pessoaData),
                        Escolaridade.makeEscolaridadeMap(pessoaData),
                        Pais.makePaisMap(pessoaData), atual()));
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

        //logica onde instanciamos um objeto que esteja cadastrado na base de dados
        PessoaFormData pessoaFormData = (id == 0) ? new PessoaFormData() : models.Pessoa.makePessoaFormData(id);


        //Resgata os dados do formulario atraves de uma requisicao e realiza a validacao dos campos
        Form<PessoaFormData> formData = formFactory.form(PessoaFormData.class).bindFromRequest();

        //se existir erros nos campos do formulario retorne o LivroFormData com os erros
        if (formData.hasErrors()) {
            return badRequest(views.html.colaboradores.edit.render(id, formData,
                    Area.makeAreaMap(pessoaFormData),
                    Beneficio.makeBeneficioMap(pessoaFormData),
                    Cargo.makeCargoMap(pessoaFormData),
                    Genero.makeGeneroMap(pessoaFormData),
                    Tipo.makeTipoMap(pessoaFormData),
                    EstadoCivil.makeEstadoCivilMap(pessoaFormData),
                    Escolaridade.makeEscolaridadeMap(pessoaFormData),
                    Pais.makePaisMap(pessoaFormData), atual()));
        }
        else {
            Pessoa pessoa = Pessoa.makeInstance(formData.get());

            try {
                pessoa.setId(id);
                pessoa.setDataAlteracao(new Date());
                pessoa.update();
                return ok(views.html.mensagens.colaborador.alterado.render(pessoa.getNome(), atual()));
            } catch (Exception e) {
                Logger.error(e.getMessage());
                formData.reject("Erro interno de sistema. " + e);
                return badRequest(views.html.colaboradores.edit.render(id,formData,
                        Area.makeAreaMap(pessoaFormData),
                        Beneficio.makeBeneficioMap(pessoaFormData),
                        Cargo.makeCargoMap(pessoaFormData),
                        Genero.makeGeneroMap(pessoaFormData),
                        Tipo.makeTipoMap(pessoaFormData),
                        EstadoCivil.makeEstadoCivilMap(pessoaFormData),
                        Escolaridade.makeEscolaridadeMap(pessoaFormData),
                        Pais.makePaisMap(pessoaFormData), atual()));
            }

        }

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
                return notFound(views.html.mensagens.area.erro.render("Objeto não encontrado.", atual()));
            }

            pessoaNome = pessoa.getNome();

            Ebean.delete(pessoa);
            return ok(views.html.mensagens.colaborador.removido.render(pessoaNome, atual()));
        } catch (Exception e) {
            Logger.error(e.toString());
            return badRequest(views.html.mensagens.area.erro.render(e.toString(), atual()));
        }
    }

    /**
     * Display the paginated list of object.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on computer names
     */
    public Result telaLista(int page, String sortBy, String order, String filter) {
        return ok(
                list.render(
                        Pessoa.page(page, 16, sortBy, order, filter),
                        sortBy, order, filter, atual())
        );
    }
}
