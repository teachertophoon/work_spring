package annotation01;

public class TestDrive {
	
	public static void main(String[] args) {
		UserA userA = new UserA("S001", "Kim");
		UserB userB = new UserB("1", "Hong");
		
		IdExtractor idExtractor = new IdExtractor();
		String idA = idExtractor.extractId(userA);
		String idB = idExtractor.extractId(userB);
		
		System.out.println("UserA의 id 값: " + idA);
		System.out.println("UserB의 id 값: " + idB);
	}
}
