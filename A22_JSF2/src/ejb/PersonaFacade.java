package ejb;

import java.util.List;

import javax.ejb.Local;

import modelo.Persona;

@Local
public interface PersonaFacade extends AbstractFacadeJPA<Persona>{
	public List<Persona> findAll();
}
