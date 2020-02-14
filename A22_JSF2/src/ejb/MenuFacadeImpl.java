package ejb;

import java.util.List;

import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import modelo.Menu;
import modelo.Usuario;

@Stateless
@LocalBean
public class MenuFacadeImpl extends AbstractFacadeJPAImpl<Menu> implements MenuFacade {
	@PersistenceContext(unitName = "Persistencia")
	private EntityManager em;
	
	public MenuFacadeImpl() {
		super(Menu.class);
	}

	@PreDestroy
	public void destruct() {
		getEm().close();
	}
	
	@Override
	protected EntityManager getEm() {
		return this.em;
	}

	@Override
	public List<Menu> findAll() {
		TypedQuery<Menu> lMenu = this.getEm().createQuery("SELECT m FROM Menu m", Menu.class);
		return lMenu.getResultList();
	}

	@Override
	public List<Menu> findUsu(Usuario u) {
		TypedQuery<Menu> lMenu = this.getEm().createQuery("SELECT m FROM Menu m WHERE m.submenu IS NULL AND m.tipoUsuario = ?1", Menu.class);
		lMenu.setParameter(1, u.getTipo());
		return lMenu.getResultList();
	}

	/*@Override
	public Map<Menu, List<Menu>> findUsu(Usuario u) {
		Map<Menu, List<Menu>> mapMenu = new HashMap<>();
		TypedQuery<Menu> lMenu = this.getEm().createQuery("SELECT list(m) FROM Menu m WHERE tipoUsuario = ?", Menu.class);
		
		for (Menu m : lMenu.getResultList()) {
			if(!mapMenu.containsKey(m) && m.getCodigo_submenu()==null)
				mapMenu.put(m, new ArrayList<>());
			else
				for(Menu supMenu : mapMenu.keySet())
					if(supMenu.getCodigo()==m.getCodigo_submenu())
						mapMenu.get(supMenu).add(m);
		}
		
		return mapMenu;
	}*/

}
