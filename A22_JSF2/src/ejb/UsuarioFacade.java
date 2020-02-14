package ejb;

import java.util.List;

import javax.ejb.Local;

import modelo.Usuario;

@Local
public interface UsuarioFacade extends AbstractFacadeJPA<Usuario>{
	public List<Usuario> findAll();
	public Usuario login(Usuario u);
}
