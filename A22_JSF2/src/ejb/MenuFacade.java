package ejb;

import java.util.List;

import javax.ejb.Local;

import modelo.Menu;
import modelo.Usuario;

@Local
public interface MenuFacade extends AbstractFacadeJPA<Menu>{
	public List<Menu> findAll();
	public List<Menu> findUsu(Usuario u);
}
