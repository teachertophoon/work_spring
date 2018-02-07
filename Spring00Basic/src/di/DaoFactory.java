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
	private static Map<String, String> daos = new HashMap<String, String>();
	
	static {
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder parser = factory.newDocumentBuilder();
			
			Document dom = parser.parse(new FileInputStream("./src/di/config.xml"));
			Element root = dom.getDocumentElement();
			NodeList list = root.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				if (list.item(i) instanceof Element) {
					Element el = (Element) list.item(i);
					String id = el.getAttribute("id");
					String className = el.getTextContent();
					daos.put(id, className);
					System.out.println(id + ":" + className);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
