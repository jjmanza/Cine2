package ejb;

import java.util.List;

import javax.ejb.Local;

import modelo.Nota;
import modelo.Usuario;


@Local
public interface NotaFacade extends AbstractFacadeJPA<Nota>{
	public List<Nota> findAll();
	public List<Nota> findNotaFechaCat(Nota n, Usuario u);
}
