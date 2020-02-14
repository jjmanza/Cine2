package controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.UsuarioFacade;
import modelo.Persona;
import modelo.Usuario;

@Named
@ViewScoped
public class UsuarioController implements Serializable{
	private static final long serialVersionUID = -6004071469836183158L;
	
	@Inject
	private Usuario usuario;
	
	@Inject
	private Persona persona;

	@EJB
	private UsuarioFacade usuarioEJB;
	
	public void registrar() {
		try {			
			this.usuario.setCodigo(persona);
			this.usuarioEJB.create(usuario);
			
			FacesContext.getCurrentInstance().addMessage("mensaje1", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se registro el usuario"));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage("mensaje1", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se ha podido registrar"));
		}
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public UsuarioFacade getUsuarioEJB() {
		return usuarioEJB;
	}

	public void setUsuarioEJB(UsuarioFacade usuarioEJB) {
		this.usuarioEJB = usuarioEJB;
	}
}
