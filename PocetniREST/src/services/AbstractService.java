package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Identifiable;
import dao.AbstractDao;
//@Path("/service")
public abstract class AbstractService<T extends Identifiable> {
	@Context
	ServletContext ctx;
	
	public AbstractService() {
		
	}
	
	@PostConstruct
	public void init() {
		if(ctx.getAttribute("dao") == null) {
			ctx.setAttribute("dao", getDao());
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<T> getAll() {
		@SuppressWarnings("unchecked")
		AbstractDao<T> dao = (AbstractDao<T>)ctx.getAttribute("dao");
		return dao.getAll();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public T create(T entity) {
		@SuppressWarnings("unchecked")
		AbstractDao<T> dao = (AbstractDao<T>)ctx.getAttribute("dao");
		return dao.create(entity);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public T find(@PathParam("id") String param) {
		@SuppressWarnings("unchecked")
		AbstractDao<T> dao = (AbstractDao<T>)ctx.getAttribute("dao");
		return dao.find(param);
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") String param) {
		@SuppressWarnings("unchecked")
		AbstractDao<T> dao = (AbstractDao<T>)ctx.getAttribute("dao");
		dao.delete(param);
	}
	
	public abstract AbstractDao<T> getDao();
}
