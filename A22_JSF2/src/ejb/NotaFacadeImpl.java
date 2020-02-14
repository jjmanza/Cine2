package ejb;


import java.util.Calendar;

import java.util.List;

import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import modelo.Nota;
import modelo.Usuario;

@Stateless
@LocalBean
public class NotaFacadeImpl extends AbstractFacadeJPAImpl<Nota> implements NotaFacade{
	@PersistenceContext(unitName = "Persistencia")
	EntityManager em;
	
	public NotaFacadeImpl() {
		super(Nota.class);
	}
	
	@PreDestroy 
	public void destruct() {
		getEm().close();
	}

	@Override
	public List<Nota> findAll() {
		TypedQuery<Nota> lNotas = this.getEm().createQuery("SELECT n FROM Nota n", Nota.class);
		return lNotas.getResultList();
	}
	
	@Override
	public List<Nota> findNotaFechaCat(Nota n, Usuario u){
		Calendar c = Calendar.getInstance();
		c.setTime(n.getFecha());
		//c.add(Calendar.HOUR, 1);
		//Con esto lo que hago es "transformarlo" de CET a UTC ya que se llevan una hora de diferencia
		//Es una solución cutre lo suyo sería que la conexión se llevase mediante CET o que el servidor sea UTC y la BD sea UTC
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		//TypedQuery<Nota> lNotas = this.getEm().createQuery("SELECT n FROM Nota n WHERE SUBSTRING(n.fecha, 1, 10) = ?1 AND n.categoria = ?2", Nota.class);
		TypedQuery<Nota> lNotas = this.getEm().createQuery("SELECT n FROM Nota n WHERE n.persona = ?1 AND n.fecha >= ?2 AND n.fecha < ?3 AND n.categoria = ?4", Nota.class);
		//lNotas.setParameter(1, df.format(n.getFecha()));
		lNotas.setParameter(1, u.getCodigo());
		lNotas.setParameter(2, c, TemporalType.DATE);
		c.add(Calendar.DATE, 1);
		lNotas.setParameter(3, c, TemporalType.DATE);
		lNotas.setParameter(4, n.getCategoria());
		return lNotas.getResultList();
	}
	
	@Override
	protected EntityManager getEm() {
		return em;
	}

}
