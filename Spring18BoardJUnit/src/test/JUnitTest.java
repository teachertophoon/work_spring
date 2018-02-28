package test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.koitt.board.dao.BoardDao;
import com.koitt.board.dao.UsersDao;
import com.koitt.board.model.Users;
import com.koitt.board.model.UsersException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test/config/applicationContext.xml")
public class JUnitTest {
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private UsersDao usersDao;
	
	@Test
	public void addAndGet() throws UsersException {
		List<Users> list = usersDao.selectAll();
		System.out.println(list);
	}
	
}









