package ejb;

import java.util.List;

import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import modelo.Usuario;

@Stateless
@LocalBean
public class UsuarioFacadeImpl extends AbstractFacadeJPAImpl<Usuario> implements UsuarioFacade{
	@PersistenceContext(unitName = "Persistencia")
	private EntityManager em;
	
	public UsuarioFacadeImpl() {
		super(Usuario.class);
	}

	@PreDestroy
	private void destruc() {
		this.getEm().close();
	}
	
	@Override
	protected EntityManager getEm() {
		return this.em;
	}
	
	@Override
	public List<Usuario> findAll() {
		TypedQuery<Usuario> lUsuarios = this.getEm().createQuery("SELECT u FROM Usuario u", Usuario.class);
		return lUsuarios.getResultList();
	}

	@Override
	public Usuario login(Usuario u) {
		Usuario uComp = null;
		System.out.println(this.getEm());
		TypedQuery<Usuario> lUsuario = this.getEm().createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario AND u.clave = :clave", Usuario.class);
		lUsuario.setParameter("usuario", u.getUsuario())
			.setParameter("clave", u.getClave());
		
		try {
			uComp = lUsuario.getSingleResult();
		}catch (NoResultException e) {
		}
		return uComp;
	}
}
