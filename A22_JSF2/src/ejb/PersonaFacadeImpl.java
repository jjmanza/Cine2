package ejb;

import java.util.List;

import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import modelo.Persona;

@Stateless
@LocalBean
public class PersonaFacadeImpl extends AbstractFacadeJPAImpl<Persona> implements PersonaFacade{
	@PersistenceContext(unitName = "Persistencia")
	private EntityManager em;

	public PersonaFacadeImpl() {
		super(Persona.class);
	}
	
	@PreDestroy
	public void destruct() {
		getEm().close();
	}

	@Override
	public List<Persona> findAll() {
		TypedQuery<Persona> lPersonas = this.getEm().createQuery("SELECT p FROM Persona p", Persona.class);
		return lPersonas.getResultList();
	}

	@Override
	protected EntityManager getEm() {
		return this.em;
	}
}
