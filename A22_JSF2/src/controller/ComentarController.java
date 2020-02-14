package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ejb.NotaFacade;
import modelo.Nota;

@Named
@ViewScoped
public class ComentarController implements Serializable{
	private static final long serialVersionUID = -4268717784749861204L;
	
	/*@Inject
	private Nota nota;*/
	
	@EJB
	private NotaFacade notaEJB;
	
	private List<Nota> lNotas;
	
	@PostConstruct
	public void init() {
		this.lNotas = obtNotas();
	}
	
	public String valorar(Nota nota) {
		String ruta = null;
		try {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nota", nota);
			ruta = "valorar";/*?faces-redirect=true";*/
		}catch (Exception e) {
			
		}
		return ruta;
	}
	
	/*public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}*/
	
	private List<Nota> obtNotas(){
		return this.notaEJB.findAll();
	}

	public NotaFacade getNotaEJB() {
		return notaEJB;
	}

	public void setNotaEJB(NotaFacade notaEJB) {
		this.notaEJB = notaEJB;
	}

	public List<Nota> getlNotas() {
		return lNotas;
	}

	public void setlNotas(List<Nota> lNotas) {
		this.lNotas = lNotas;
	}
}
