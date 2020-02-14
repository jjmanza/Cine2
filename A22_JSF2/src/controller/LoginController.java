package controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ejb.UsuarioFacade;
import modelo.Usuario;

@Named
@ViewScoped
public class LoginController implements Serializable{
	private static final long serialVersionUID = 2855603690448780856L;

	@Inject
	private Usuario usuario;
	
	@EJB
	private UsuarioFacade usuarioEJB;

	public String login() {
		Usuario u = this.usuarioEJB.login(this.usuario);
		String ruta = null;
		
		//FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
		/*switch (u.getTipo().name()) {
			case "A":
				ruta = "usuario/administrador";
				break;
			case "O":
				ruta = "usuario/operador";
				break;
			default:
				FacesContext.getCurrentInstance()
					.addMessage("loginMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login", "No se pudo iniciar sesión (Usuario/Contraseña) incorrectos"));
		}*/
		
		try {
			if(u!=null) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", u);
				/*
				 * Navegacion implicita porque no se ve la ruta en la url
				 * si quiero que se vea añadir ?faces-redirect=true
				 * redireccion="/protegido/principal?faces-redirect=true"
				 * */
				ruta = "/protegido/principal?faces-redirect=true";
			}else
				FacesContext.getCurrentInstance()
					.addMessage("loginMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login", "No se pudo iniciar sesión (Usuario/Contraseña) incorrectos"));
		}catch (Exception e) {
			FacesContext.getCurrentInstance()
				.addMessage("loginMensaje", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio un error inexperado"));
		}
				
		return ruta;
	}

	public String login1() {
		Usuario u = this.usuarioEJB.login(this.usuario);
		String ruta = null;
		
		try {
			if(u!=null) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", u);
				/*
				 * Navegacion implicita porque no se ve la ruta en la url
				 * si quiero que se vea añadir ?faces-redirect=true
				 * redireccion="/protegido/principal?faces-redirect=true"
				 * */
				ruta = "/protegido/principal1?faces-redirect=true";
			}else
				FacesContext.getCurrentInstance()
					.addMessage("loginMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login", "No se pudo iniciar sesión (Usuario/Contraseña) incorrectos"));
		}catch (Exception e) {
			FacesContext.getCurrentInstance()
				.addMessage("loginMensaje", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Ocurrio un error inexperado"));
		}
				
		return ruta;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioFacade getUsuarioEJB() {
		return usuarioEJB;
	}

	public void setUsuarioEJB(UsuarioFacade usuarioEJB) {
		this.usuarioEJB = usuarioEJB;
	}
}
