package controllers;

import models.Pessoa;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

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
        return ok(views.html.colaboradores.detail.render());
    }

    /**
     * @return render edit form with a noticia data
     */
    public Result telaEditar(Long id) {
        return ok(views.html.colaboradores.edit.render());
    }

    /**
     * Retrieve a list of all noticias
     *
     * @return a list of all noticias in a render template
     */
    public Result telaLista() {
        return ok(views.html.colaboradores.list.render());
    }

    /**
     * Save a noticia
     *
     * @return a render view to inform OK
     */
    public Result inserir() {
        Pessoa pessoa = formFactory.form(Pessoa.class).bindFromRequest().get();
        pessoa.save();
        return created(views.html.mensagens.cadastrado.render());

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
