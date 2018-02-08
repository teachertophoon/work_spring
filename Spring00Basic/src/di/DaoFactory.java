package di;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DaoFactory {
	// dao 클래스들의 이름을 저장하는 Map
	private static Map<String, String> daos = new HashMap<String, String>();
	
	// 1. DaoFactory 클래스가 메모리에 올라가게 되면 실행되는 static 블록
	static {
		
		try {
			// 2. Dom Parser를 위한 factory 객체 생성
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			// 3. Dom Parser를 위한 factory에서 Dom Parser 객체 생성
			DocumentBuilder parser = factory.newDocumentBuilder();
			
			// 4. Dom Parser를 이용하여 config.xml 파일을 불러온다.
			Document dom = parser.parse(new FileInputStream("./src/di/config.xml"));
			
			// 5. 불러온 xml에서 최상위 root 앨리먼트(노드)를 가져온다.
			Element root = dom.getDocumentElement();
			
			// 6. root 앨리먼트(노드) 아래의 자식 앨리먼트(노드)들을 가져온다. 
			NodeList list = root.getChildNodes();
			
			// 7. 자식 앨리먼트(노드)들을 순회한다.
			for (int i = 0; i < list.getLength(); i++) {
				// 8. 만약 해당 아이템이 앨리먼트이라면
				if (list.item(i) instanceof Element) {
					// 9. 해당 앨리먼트를 가져온다.
					Element el = (Element) list.item(i);
					
					// 10. 앨리먼트에 정의된 id 속성(Attribute) 값을 가져온다.
					String id = el.getAttribute("id");
					
					// 11. 앨리먼트 사이의 텍스트 값을 가져온다.
					String className = el.getTextContent();
					
					// 12. daos Map에 id값과 className값을 저장한다.
					daos.put(id, className);
					
					// 13. 파싱한 id값과 className을 테스트삼아 출력해본다.
					System.out.println(id + ":" + className);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 *  getDao는 daos Map에 저장된 dao 클래스 이름을 가져온 뒤
	 *  해당 클래스의 객체를 만들어서 리턴해주는 메소드 
	 */
	public static UserDao getDao(String type) {
		/*
		 * 1. type 파라미터는 "my" 혹은 "ora" 값이 들어온다.
		 * 각 해당하는 type 값을 이용하여 daos Map에서 className 값을 가져온다.
		 */
		String className = daos.get(type);
		System.out.println("가져온 클래스 이름: " + className);
		
		try {
			// 2. 가져온 클래스 이름으로 클래스의 정보를 가져온다.
			Class<?> daoClass = Class.forName(className);
			
			/*
			 *  3. 가져온 클래스 정보 객체에 있는 newInstance 메소드를 이용하여
			 *  해당 클래스의 객체를 생성한다.
			 */
			Object obj = daoClass.newInstance();
			
			// 4. 생성한 객체가 UserDao 타입인지 확인 후 다운캐스팅하여 dao를 리턴한다.
			if (UserDao.class.isInstance(obj)) {
				UserDao dao = (UserDao) obj;
				return dao;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}














