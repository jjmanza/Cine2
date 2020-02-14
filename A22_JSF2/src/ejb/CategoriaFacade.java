package ejb;

import java.util.List;

import javax.ejb.Local;

import modelo.Categoria;

@Local
public interface CategoriaFacade extends AbstractFacadeJPA<Categoria> {
	public List<Categoria> findAll();
}
