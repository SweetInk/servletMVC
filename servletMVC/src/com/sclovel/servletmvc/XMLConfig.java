package com.sclovel.servletmvc;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLConfig {
	
	public static Map<Object,Object> getMvcConfig(String url){
		HashMap<Object,Object> config = new HashMap<Object,Object>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		System.out.println("开始加载配置文件...");
		
		DocumentBuilder builder = null;
		try {
			builder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = null;
		try {
			doc = builder.parse(new  File(url));
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		doc.getDocumentElement();
		System.out.println(doc.getDocumentElement().getNodeName());
		NodeList list = doc.getElementsByTagName("action");
		System.out.println(list.getLength());
		for(int i = 0; i<list.getLength();i++){
			String name ="";
			ActionMapping m = new ActionMapping();
			Node node = list.item(i);
			//System.out.println("Node Name:"+node.getNodeName());
			Element e = (Element)node;
		//	System.out.println("--------------");
			if(node.getNodeType()==Node.ELEMENT_NODE){
				m.setName(e.getAttribute("name"));
				m.setClassName(e.getAttribute("class"));
				m.setMethodName(e.getAttribute("method"));
				//System.out.println("action-name:"+e.getAttribute("name"));
				name = e.getAttribute("name");
			//	System.out.println("action-class:"+e.getAttribute("class"));
				NodeList l = e.getElementsByTagName("result");
				for(int j = 0; j< l.getLength();j++){
					Element ee = (Element)l.item(j);
					m.getResult().put(ee.getAttribute("name"), ee.getTextContent());
					//System.out.println("result-name:"+ee.getAttribute("name")+"->"+ee.getTextContent());
				}
			}
			config.put(name, m);
		}
		return config;
	}
	public static void main(String[] args) {
		Map<Object, Object> m = getMvcConfig("C:\\Users\\SUCHU\\workspace-j2ee\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\ServletMVC\\WEB-INF\\classes\\"+"mvc-config.xml");
	//	Java代码 
		System.out.println("MAP－－－－－");

		for(Entry<Object, Object> entry:m.entrySet()){    
		     System.out.println(entry.getKey()+"--->"+entry.getValue());    
		}   
	}
}

