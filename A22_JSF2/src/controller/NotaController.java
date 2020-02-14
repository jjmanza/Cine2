package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.CategoriaFacade;
import ejb.NotaFacade;
import modelo.Categoria;
import modelo.Nota;
import modelo.Usuario;

@Named
@ViewScoped
public class NotaController implements Serializable{
	private static final long serialVersionUID = 9019978697044694475L;

	@EJB
	private NotaFacade notaEJB;
	
	@EJB
	private CategoriaFacade categoriaEJB;
	
	@Inject
	private Nota nota;
	
	private List<Categoria> lCategorias;
	
	/*
	 * Es mejor que directamente injecte Categoria en vez de crearla con el constructor
	 * */
	@PostConstruct
	public void init() {
		this.nota.setCategoria(new Categoria());
		this.lCategorias = cargarCategoria();
	}
	
	private List<Categoria> cargarCategoria(){
		return this.categoriaEJB.findAll();
	}
	
	public void registrarNota() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Usuario u = (Usuario) ctx.getExternalContext().getSessionMap().get("usuario");
		this.nota.setPersona(u.getCodigo());
		
		try {
			this.notaEJB.create(this.nota);
			ctx.addMessage("mensajesRegistro", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registró"));
		}catch (Exception e) {
			e.printStackTrace();
			ctx.addMessage("mensajesRegistro", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo crear la nota"));
		}		
	}
	
	public NotaFacade getNotaEJB() {
		return notaEJB;
	}

	public void setNotaEJB(NotaFacade notaEJB) {
		this.notaEJB = notaEJB;
	}

	public CategoriaFacade getCategoriaEJB() {
		return categoriaEJB;
	}

	public void setCategoriaEJB(CategoriaFacade categoriaEJB) {
		this.categoriaEJB = categoriaEJB;
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public List<Categoria> getlCategorias() {
		return lCategorias;
	}

	public void setlCategorias(List<Categoria> lCategorias) {
		this.lCategorias = lCategorias;
	}
}
