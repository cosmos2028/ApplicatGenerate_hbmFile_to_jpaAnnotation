package poubelle;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Test {
    public static void main(String[] args){
        try {
//File Path
            String filePath = "C:\\tools_perso\\workspace\\petitparser_xml\\src\\main\\resources\\hbm\\Actualite.hbm.xml";
//Read XML file.
            File inputFile = new File(filePath);
//Create DocumentBuilderFactory object.
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
//Get DocumentBuilder object.
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//Parse XML file.
            Document document = dBuilder.parse(inputFile);
            document.getDocumentElement().normalize();
//Print root element.
            System.out.println("Root element:"
                    + document.getDocumentElement().getNodeName());
//Get element list.
            NodeList nodeList =
                    document.getElementsByTagName("property");
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node nNode = nodeList.item(temp);
                System.out.println("\nElement actuelle: "
                        + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("name: "
                            + eElement.getAttribute("name"));
                    System.out.println("type: "
                            + eElement.getAttribute("type"));
                }
            }
            System.out.println("lul");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
