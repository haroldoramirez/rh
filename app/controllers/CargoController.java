package controllers;

import com.avaje.ebean.Ebean;
import models.Cargo;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import validators.CargoFormData;
import views.html.cargos.list;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class CargoController extends Controller {

    @Inject
    FormFactory formFactory;

    /**
     * @return object form
     */
    public Result telaNovo() {
        Form<CargoFormData> cargoForm = formFactory.form(CargoFormData.class);
        return ok(views.html.cargos.create.render(cargoForm));
    }

    /**
     * @return render a detail form with data
     */
    public Result telaDetalhe(Long id) {
        try {
            Cargo cargo = Ebean.find(Cargo.class, id);

            if (cargo == null) {
                return notFound(views.html.mensagens.area.erro.render("Objeto não encontrado."));
            }

            return ok(views.html.cargos.detail.render(cargo));
        } catch (Exception e) {
            Logger.error(e.toString());
            return badRequest(views.html.mensagens.cargo.erro.render(e.toString()));
        }
    }

    /**
     * @return render a edit form with data
     */
    public Result telaEditar(Long id) {
        try {
            //logica onde instanciamos um objeto que esteja cadastrado na base de dados
            CargoFormData cargoFormData = (id == 0) ? new CargoFormData() : models.Cargo.makeCargoFormData(id);

            //apos o objeto ser instanciado levamos os dados para o formData e os dados serao carregados no form edit
            Form<CargoFormData> formData = formFactory.form(CargoFormData.class).fill(cargoFormData);

            return ok(views.html.cargos.edit.render(id,formData));
        } catch (Exception e) {
            Logger.error(e.toString());
            return badRequest(views.html.mensagens.cargo.erro.render(e.toString()));
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
                        Cargo.page(page, 16, sortBy, order, filter),
                        sortBy, order, filter
                )
        );
    }

    /**
     * Save a object
     *
     * @return a render created view to inform OK
     */
    public Result inserir() {
        //Resgata os dados do formulario atraves de uma requisicao
        Form<CargoFormData> formData = formFactory.form(CargoFormData.class).bindFromRequest();

        //se existir erros nos campos do formulario retorne os erros
        if (formData.hasErrors()) {
            return badRequest(views.html.cargos.create.render(formData));
        } else {
            try {
                //Converte os dados do formulario para uma instancia a ser salva na base de dados
                Cargo cargo = Cargo.makeInstance(formData.get());
                cargo.setDataCadastro(new Date());
                cargo.save();
                return created(views.html.mensagens.cargo.cadastrado.render(cargo.getNome()));
            } catch (Exception e) {
                Logger.error(e.toString());
                formData.reject(e.toString());
                return badRequest(views.html.cargos.create.render(formData));
            }
        }

    }

    /**
     * Update a object from id
     *
     * @param id variavel identificadora
     * @return updated object with a form
     */
    public Result editar(Long id) {
        //Resgata os dados do formulario atraves de uma requisicao e realiza a validacao dos campos
        Form<CargoFormData> formData = formFactory.form(CargoFormData.class).bindFromRequest();

        //verificar se tem erros no formData, caso tiver retornar o formulario com os erros e caso não tiver continua o processo de alteracao
        if (formData.hasErrors()) {
            return badRequest(views.html.cargos.edit.render(id,formData));
        } else {
            try {
                Cargo cargoBusca = Ebean.find(Cargo.class, id);

                if (cargoBusca == null) {
                    return notFound(views.html.mensagens.cargo.erro.render("Objeto não encontrado."));
                }

                //Converte os dados do formulario para uma instancia
                Cargo cargo = Cargo.makeInstance(formData.get());

                cargo.setId(id);
                cargo.setDataAlteracao(new Date());
                cargo.update();
                return ok(views.html.mensagens.cargo.alterado.render(cargo.getNome()));
            } catch (Exception e) {
                Logger.error(e.toString());
                return badRequest(views.html.mensagens.cargo.erro.render(e.toString()));
            }
        }

    }

    /**
     * Remove a object from a id
     *
     * @param id variavel identificadora
     * @return ok if removed
     */
    public Result remover(Long id) {

        String cargoNome;

        try {
            //busca para ser excluido
            Cargo cargo = Ebean.find(Cargo.class, id);

            if (cargo == null) {
                return notFound(views.html.mensagens.cargo.erro.render("Objeto não encontrado."));
            }

            cargoNome = cargo.getNome();

            Ebean.delete(cargo);
            return ok(views.html.mensagens.cargo.removido.render(cargoNome));
        } catch (Exception e) {
            Logger.error(e.toString());
            return badRequest(views.html.mensagens.cargo.erro.render(e.toString()));
        }
    }
}
