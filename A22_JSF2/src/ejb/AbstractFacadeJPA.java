package ejb;

public interface AbstractFacadeJPA<T> {
	T create(T entity);
	
	void update(T entity);
	
	void remove(T entity);
	
	T find(Object id);
}
