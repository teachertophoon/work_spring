package di;

public interface UserDao {
	
	// 사용자 데이터베이스에 저장
	void insert(User user);
	
	// 데이터베이스로부터 id값을 이용해 사용자 불러오기
	User select(int id);
}
