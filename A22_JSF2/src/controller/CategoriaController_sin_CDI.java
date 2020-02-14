package controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ejb.CategoriaFacade;
import modelo.Categoria;


@Named("catControllerSinCDI")
@ViewScoped
public class CategoriaController_sin_CDI implements Serializable{
	private static final long serialVersionUID = -7663139106146945365L;

	@EJB
	private CategoriaFacade categoriaEJB;
	
	private Categoria categoria;

	@PostConstruct
	public void init() {
		this.categoria = new Categoria();
	}
	
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
