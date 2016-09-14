package controllers;

import com.avaje.ebean.Ebean;
import models.*;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import validators.PessoaFormData;
import views.html.colaboradores.list;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
                Escolaridade.makeEscolaridadeMap(pessoaData),
                Pais.makePaisMap(pessoaData)));
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
                    Pais.makePaisMap(pessoaFormData)));
        } catch (Exception e) {
            Logger.error(e.toString());
            return badRequest(views.html.mensagens.colaborador.erro.render(e.toString()));
        }


    }

    /**
     * Retrieve a list of all noticias
     *
     * @return a list of all noticias in a render template
     */
//    public Result telaLista() {
//        try {
//            List<Pessoa> pessoas = Ebean.find(Pessoa.class).findList();
//            return ok(views.html.colaboradores.list.render(pessoas, ""));
//        } catch (Exception e) {
//            Logger.error(e.toString());
//            return badRequest(views.html.mensagens.colaborador.erro.render(e.toString()));
//        }
//    }

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
                    Pais.makePaisMap(pessoaData)));
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
                formData.reject("Erro interno de sistema. " + e);
                return badRequest(views.html.colaboradores.create.render(formData,
                        Area.makeAreaMap(pessoaData),
                        Beneficio.makeBeneficioMap(pessoaData),
                        Cargo.makeCargoMap(pessoaData),
                        Genero.makeGeneroMap(pessoaData),
                        Tipo.makeTipoMap(pessoaData),
                        EstadoCivil.makeEstadoCivilMap(pessoaData),
                        Escolaridade.makeEscolaridadeMap(pessoaData),
                        Pais.makePaisMap(pessoaData)));
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
                    Pais.makePaisMap(pessoaFormData)));
        }
        else {
            Pessoa pessoa = Pessoa.makeInstance(formData.get());

            try {
                pessoa.setId(id);
                pessoa.setDataAlteracao(new Date());
                pessoa.update();
                return ok(views.html.mensagens.colaborador.alterado.render(pessoa.getNome()));
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
                        Pais.makePaisMap(pessoaFormData)));
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

    /**
     * Display the paginated list of computers.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on computer names
     */
    public Result telaLista(int page, String sortBy, String order, String filter) {
        return ok(
                list.render(
                        Pessoa.page(page, 10, sortBy, order, filter),
                        sortBy, order, filter
                )
        );
    }
}
