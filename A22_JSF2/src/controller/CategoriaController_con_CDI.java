package controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.CategoriaFacade;
import modelo.Categoria;

@Named("catControllerConCDI")
@ViewScoped
public class CategoriaController_con_CDI implements Serializable{
	private static final long serialVersionUID = -3236459407666875103L;

	@EJB
	private CategoriaFacade categoriaEJB;

	//Ahora utilizaremos la anotación @Inject para crear el objeto categoria
	//gracias a la injección de dependencia, por lo tanto eliminaremos también
	//el método init
	@Inject
	private Categoria categoria;

	/*@PostConstruct
	public void init() {
		this.categoria = new Categoria();
	}*/
	
	public void registrar() {
		try {
			this.categoriaEJB.create(this.categoria);
		}catch (Exception e) {
		}
	}

	public CategoriaFacade getCategoriaEJB() {
		return categoriaEJB;
	}

	public void setCategoriaEJB(CategoriaFacade categoriaEJB) {
		this.categoriaEJB = categoriaEJB;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
