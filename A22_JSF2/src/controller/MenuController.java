package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import ejb.MenuFacade;
import modelo.EnumModelos.TipoMenu;
import modelo.Menu;
import modelo.Usuario;

@Named
@ViewScoped
public class MenuController implements Serializable{
	private static final long serialVersionUID = 5889281109606217582L;
	
	@EJB
	private MenuFacade menuEJB;
	
	private List<Menu> lista;
	
	private MenuModel model;
	
	@PostConstruct
	public void init() {
		//Carga los datos de la tabla menu a una lista
		this.listarMenus();
		//Instanciamos el menu con esta clase que es de primefaces
		model = new DefaultMenuModel();
		//Con este se carga el menu con los datos
		this.establecerPermisos();
	}
	
	public String mostrarUsuarioLogueado() {
		Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		//return u!=null?u.getUsuario():"No te has logueado";
		return u.getUsuario();
	}
	
	public void cerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
	
	private void listarMenus() {
		Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		this.lista = this.menuEJB.findUsu(u);
	}
	
	//En la base de datos podriamos poner que exista el campo icono con que haría referencia a item/submenu
	//en caso de no querer lo dejariamos a null
	private void establecerPermisos() {
		for (Menu menu : lista) {
			if(menu.getTipo()==TipoMenu.I) {
				DefaultMenuItem item = new DefaultMenuItem(menu.getNombre());
				String contextURL = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/";
				item.setUrl(contextURL+menu.getUrl());
				this.model.addElement(item);
			}
			else {
				DefaultSubMenu subMenu = genSubMenu(menu, new DefaultSubMenu(menu.getNombre()));
				
				/*for (Menu menuItem : menu.getListaMenus()) {
					DefaultMenuItem item = new DefaultMenuItem(menuItem.getNombre());
					item.setUrl(menuItem.getUrl());
					subMenu.addElement(item);
				}*/
				
				this.model.addElement(subMenu);
			}
		}
	}
	
	private DefaultSubMenu genSubMenu(Menu menu, DefaultSubMenu subMenu) {
		for (Menu menuItem : menu.getListaMenus())	
			if(menuItem.getTipo()==TipoMenu.I) {
				DefaultMenuItem item = new DefaultMenuItem(menuItem.getNombre());
				String contextURL = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()+"/";
				item.setUrl(contextURL+menuItem.getUrl());
				subMenu.addElement(item);
			}else {
				DefaultSubMenu subSubMenu = genSubMenu(menuItem, new DefaultSubMenu(menuItem.getNombre()));
				subMenu.addElement(subSubMenu);
			}
		
		return subMenu;
	}

	public MenuFacade getMenuEJB() {
		return menuEJB;
	}

	public void setMenuEJB(MenuFacade menuEJB) {
		this.menuEJB = menuEJB;
	}

	public List<Menu> getLista() {
		return lista;
	}

	public void setLista(List<Menu> lista) {
		this.lista = lista;
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}
}
