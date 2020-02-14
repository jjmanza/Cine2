package controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import modelo.Usuario;

@Named
@ViewScoped
public class PlantillaController implements Serializable{
	private static final long serialVersionUID = -3949145030165635320L;
	
	public void verificarSesion() throws IOException{
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario u = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
		
		if(u==null) 
			context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath()+"/permisos.xhtml");
		
	}
}
