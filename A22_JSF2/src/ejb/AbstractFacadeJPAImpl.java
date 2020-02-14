package ejb;

import javax.persistence.EntityManager;

public abstract class AbstractFacadeJPAImpl<T> implements AbstractFacadeJPA<T>{
    private Class<T> entityClass;
    protected abstract EntityManager getEm();
    
    public AbstractFacadeJPAImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

	@Override
	public T create(T entity) {
		getEm().persist(entity);
		//Forma de poder obtener datos actualizados después del persist
		//getEm().detach(entity);
		return entity;
	}

	@Override
	public void update(T entity) {
		getEm().merge(entity);
	}

	@Override
	public void remove(T entity) {
		getEm().remove(getEm().merge(entity));
	}

	@Override
	public T find(Object id) {
		return getEm().find(entityClass, id);
	}
	
	public void refresh(T entity) {
		getEm().refresh(entity);
	}
}
