package controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.NotaFacade;
import modelo.Nota;

@Named
@ViewScoped
public class ValorarController implements Serializable {

	private static final long serialVersionUID = -5741096065780974935L;

	@Inject
	private Nota nota;
	
	@EJB
	private NotaFacade notaEJB;
	
	/*
	 * 
	 * private ComentarController comentarController --> otra forma de hacerlo.
	 * 
	 * */
	
	@PostConstruct
	public void init() {
		
		this.nota =(Nota) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nota");
		
	}
	
	public String valoracion() {
		
			String ruta = null;
			
			try {
				
				this.notaEJB.update(this.nota);
				
				ruta ="comentar";
				
			}catch(Exception e) {
					
				
				
			}
			
			return ruta;
		
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}
	
	
	
}
