package test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.koitt.board.dao.BoardDao;
import com.koitt.board.dao.UsersDao;
import com.koitt.board.model.Authority;
import com.koitt.board.model.AuthorityId;
import com.koitt.board.model.BoardException;
import com.koitt.board.model.Users;
import com.koitt.board.model.UsersException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test/config/applicationContext.xml")
public class JUnitTest {
	
	@Autowired
	private ApplicationContext context;
	
	// 픽스처(fixture)
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private Users users1;
	private Users users2;
	private Users users3;
	
	private Authority admin;
	private Authority user;
	private Set<Authority> adminSet = new HashSet<>();
	private Set<Authority> userSet = new HashSet<>();
	private Set<Authority> bothSet = new HashSet<>();
	
	
	@Before
	public void setUp() {
		String password = passwordEncoder.encode("1234");
		
		// 사용자 기본 정보를 생성
		this.users1 = new Users(null, "users1@koitt.com", password, "김영미", null);
		this.users2 = new Users(null, "users2@koitt.com", password, "이승훈", null);
		this.users3 = new Users(null, "users3@koitt.com", password, "윤성빈", null);
		
		// 각 사용자마다 권한을 부여하기
		// 1. 권한 객체 생성
		this.admin = new Authority(AuthorityId.ADMIN.getAuthorityId(), AuthorityId.ADMIN.name());
		this.user = new Authority(AuthorityId.USER.getAuthorityId(), AuthorityId.USER.name());
		
		/*
		 *  2. 1번에서 생성한 권한 객체를 Set 컬렉션에 추가하기
		 *  adminSet: 관리자 권한만 부여
		 *  userSet: 사용자 권한만 부여
		 *  bothSet: 관리자 권한과 사용자 권한 둘 다 부여 
		 */
		adminSet.add(admin);
		userSet.add(user);
		bothSet.add(admin);
		bothSet.add(user);
		
		// 3. 각 사용자에게 권한 Set 컬렉션을 저장
		this.users1.setAuthorities(adminSet);
		this.users2.setAuthorities(userSet);
		this.users3.setAuthorities(bothSet);
	}
	
	// 사용자 추가, 가져오기 테스트
	@Test
	public void addAndGetUsers() throws UsersException, BoardException {
		/*
		 *  글 작성한 사용자가 존재할 경우 users 테이블을 먼저 삭제 할 수 없기 때문에
		 *  board 테이블을 먼저 삭제 후 사용자를 추가해야 한다.
		 */
		boardDao.deleteAll();
		assertThat(boardDao.getCount(), is(0));
		
		/*
		 *  사용자의 권한이 등록되어 있기 때문에
		 *  users_authority 테이블을 먼저 삭제 후 사용자를 추가해야 한다.
		 */
		usersDao.deleteAllUsersAuthority();
		assertThat(usersDao.getCountUsersAuthority(), is(0));
		
		usersDao.deleteAll();
		assertThat(usersDao.getCount(), is(0));
		
		// 사용자 3명 입력이 정상적으로 완료됐는지 확인 테스트
		usersDao.insert(users1);
		users1.setNo(usersDao.selectLastInsertId());
		usersDao.insert(users2);
		users2.setNo(usersDao.selectLastInsertId());
		usersDao.insert(users3);
		users3.setNo(usersDao.selectLastInsertId());
		assertThat(usersDao.getCount(), is(3));
		
		// 사용자 3명의 권한 정보를 입력하고 정상적으로 완료됐는지 확인 테스트
		usersDao.insertAuthority(users1);
		usersDao.insertAuthority(users2);
		usersDao.insertAuthority(users3);
		assertThat(usersDao.getCountUsersAuthority(), is(4)); // users3 권한이 2개라서
		
		/*
		 *  테이블에 저장된 각 사용자의 정보를 제대로 가져왔는지 테스트
		 *  passwordEncoder의 matches 메소드는 결과가 boolean 타입이므로
		 *  JUnit의 assertTrue 메소드를 이용하여 테스트한다.
		 */
		Users usersget1 = usersDao.selectByEmail(users1.getEmail());
		assertThat(usersget1.getName(), is(users1.getName()));
		assertTrue(passwordEncoder.matches("1234", users1.getPassword()));
		
		Users usersget2 = usersDao.selectByEmail(users2.getEmail());
		assertThat(usersget2.getName(), is(users2.getName()));
		assertTrue(passwordEncoder.matches("1234", users2.getPassword()));
		
		Users usersget3 = usersDao.selectByEmail(users3.getEmail());
		assertThat(usersget3.getName(), is(users3.getName()));
		assertTrue(passwordEncoder.matches("1234", users3.getPassword()));
	}
	
}









