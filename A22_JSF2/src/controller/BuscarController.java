package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
public class BuscarController implements Serializable{
	private static final long serialVersionUID = -7960966394642854337L;
	
	private List<Nota> lNotas;
	
	@Inject
	private Nota nota;
	
	@EJB
	private CategoriaFacade categoriaEJB;
	
	@EJB
	private NotaFacade notaEJB;

	@PostConstruct
	public void init() {
		this.nota.setCategoria(new Categoria());
	}
	
	public void buscarNotas() {
		Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		this.lNotas = this.notaEJB.findNotaFechaCat(this.nota, u);
	}
	
	public List<Nota> getlNotas() {
		return lNotas;
	}

	public void setlNotas(List<Nota> lNotas) {
		this.lNotas = lNotas;
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public CategoriaFacade getCategoriaEJB() {
		return categoriaEJB;
	}

	public void setCategoriaEJB(CategoriaFacade categoriaEJB) {
		this.categoriaEJB = categoriaEJB;
	}

	public NotaFacade getNotaEJB() {
		return notaEJB;
	}

	public void setNotaEJB(NotaFacade notaEJB) {
		this.notaEJB = notaEJB;
	}	
}
