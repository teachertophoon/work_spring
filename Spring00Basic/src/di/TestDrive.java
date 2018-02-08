package di;

public class TestDrive {
	
	public static void main(String[] args) {
		// 1. 사용자 정보
		User user1 = new User(1, 27, "Lee");
		
		// 2. 데이터베이스에 저장하기 위해 dao 객체 생성
		/*
		 * TestDrive 클래스는 OraUserDao 클래스에 의존적이다.
		 * (OraUserDao 클래스가 없다면 TestDrive 클래스의 역할을 수행할 수 없다.)
		 * 
		 * 그렇다면 서로 의존적인 관계를 끊더라도
		 * 의존적인 관계로 만들어 줄 수 있는 방법이 없을까?
		 * ==> 의존성 주입 DI(Dependency Injection)가 해답이다.
		 */
		// DI-1
		//OraUserDao dao = new OraUserDao();
		
		// DI-2
		//UserDao dao = new OraUserDao();
		
		// DI-3: Factory를 이용하자
		UserDao dao = DaoFactory.getDao("my");
		
		// 3. dao 객체를 이용하여 데이터베이스에 정보 저장
		dao.insert(user1);
		
		// 4. dao 객체를 이용하여 데이터베이스로부터 사용자를 불러오기
		User selected = dao.select(1);
		
		// 5. 불러온 사용자 정보 출력
		System.out.println(selected);
	}
}
