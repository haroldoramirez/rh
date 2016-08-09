package controllers;

import com.avaje.ebean.Ebean;
import models.Pessoa;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class PessoaController extends Controller {

    @Inject
    FormFactory formFactory;

    /**
     * @return noticia form if auth OK or not authorized
     */
    public Result telaNovo() {
        Form<Pessoa> pessoaForm = formFactory.form(Pessoa.class);
        return ok(views.html.colaboradores.create.render(pessoaForm));
    }

    /**
     * @return render a detail form with a noticia data
     */
    public Result telaDetalhe(Long id) {
        try {
            Pessoa pessoa = Ebean.find(Pessoa.class, id);

            if (pessoa == null) {
                return notFound(views.html.mensagens.colaborador.erro.render());
            }

            return ok(views.html.colaboradores.detail.render(pessoa));
        } catch (Exception e) {
            Logger.error(e.toString());
            return badRequest(views.html.mensagens.colaborador.erro.render());
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
            return badRequest(views.html.mensagens.colaborador.erro.render());
        }
    }

    /**
     * Save a noticia
     *
     * @return a render view to inform OK
     */
    public Result inserir() {
        Pessoa pessoa = formFactory.form(Pessoa.class).bindFromRequest().get();
        pessoa.save();
        return created(views.html.mensagens.colaborador.cadastrado.render(pessoa.getNome()));

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
     * Remove a noticia from a id
     *
     * @param id variavel identificadora
     * @return ok noticia removed
     */
    public Result remover(Long id) {
        return TODO;
    }
}
