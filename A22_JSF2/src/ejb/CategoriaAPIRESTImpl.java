package ejb;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import modelo.Categoria;

public class CategoriaAPIRESTImpl implements CategoriaAPIREST {
	
	@Override
	public Categoria create(Categoria entity) {
		return (Categoria) ClientBuilder.newClient()
				.target("http://localhost:8080/categoria")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(entity)).readEntity(Categoria.class);
	}

	@Override
	public void update(Categoria entity) {
		
		
	}

	@Override
	public void remove(Categoria entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Categoria find(Object id) {
		// TODO Auto-generated method stub
		return null;
	}
}
