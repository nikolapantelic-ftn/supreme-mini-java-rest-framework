package services;

import javax.ws.rs.Path;

import beans.User;
import dao.AbstractDao;
import dao.TestDao;

@Path("/test")
public class UserService extends AbstractService<User> {

	@Override
	public AbstractDao<User> getDao() {
		return new TestDao();
	}

}
