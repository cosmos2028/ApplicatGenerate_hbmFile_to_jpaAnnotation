package poubelle;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.petitparser.grammar.xml.XmlParser;
import org.petitparser.grammar.xml.ast.XmlAttribute;
import org.petitparser.grammar.xml.ast.XmlElement;
import org.petitparser.grammar.xml.ast.XmlNode;
import org.petitparser.parser.Parser;

public class AppTest 
{
//	public static void recursive(XmlNode composant) {
//		
//		Dictionnaire dico = new Dictionnaire();
//		String annotationJpa;
//		
//		//Partie traitement
//		//Tous les éléments de l'arbre sont des XmlNodes dont XmlElement hérite
//		//Tout enfant est un noeud ou une feuille i.e. un XmlElement
//		//Seule la racine ne rentre pas dans cette condition != XmlElement
//        if (composant.getParent() != null) {
//            XmlElement currentElement = (XmlElement) composant;
//            System.out.println(currentElement.getName().getLocal());
//            //Appel du dictionnaire selon le type de la balise
//            annotationJpa = dico(currentElement.getName().getLocal());
//            
//            List<XmlAttribute> attributs = currentElement.getAttributes();
//            for(XmlAttribute a : attributs) {
//                System.out.println(a.getName().getLocal());
//                System.out.println(a.getValue());
//                //Appel du dictionnaire selon les attributs de la balise
//                annotationJpa = dico();
//            }
//        }
//        
//        //Partie récursive sur les noeuds
//        if (composant.getChildren()!= null) {
//            List<XmlNode> enfants = composant.getChildren();
//            for(XmlNode n : enfants) {
//                if (n.getClass().getSimpleName().equals("XmlElement")) {
//                    recursive(n);
//                }
//            }
//        }
//    }
//
//	public static void main(String[] args) 
//	{
//		 final Parser parser = new XmlParser();
//
//	
//		/*
//		 * Charger la ressource du fichier hbm.xml
//		 */
//		String name = "Actualite";
//		InputStream fichierHbmIS;
//		try 
//		{
//			fichierHbmIS = AppTest.class.getResourceAsStream("/hbm/" + name + ".hbm.xml");
//			 String fichierHbm = new String(fichierHbmIS.readAllBytes(), StandardCharsets.UTF_8);
//			 
//			 //System.out.println(fichierHbm);
//			 /*
//			  * parse String hbm to nodes
//			  */
//			 
//			  XmlNode treeXmlNode = parser.parse(fichierHbm).get();
//			  //Un exemple d'utilisation que tu mets dans le main()
//			  recursive(treeXmlNode);
//			  /*
//			  System.out.println(treeXmlNode.getChildren().getClass());
//			  for (XmlNode node : treeXmlNode.getChildren()) 
//			  {
//				  //System.out.println(node.getClass().getSimpleName());
//				  if(node!=null && node.getClass()!=null &&(node.getClass().getSimpleName().equals("XmlElement")))
//				  {
//					  XmlElement monelement = (XmlElement) node;
//					  if(monelement!=null && monelement.getName()!=null && (monelement.getName().getLocal().equals("hibernate-mapping")))
//					  {
//						  System.out.println(monelement.getName().getLocal());
//						  break;
//					  }
//					
//				  }
//			  }
//			  System.out.println("test");
//			  */
//			 
//			  /*
//			  //XmlNode root = treeXmlNode.getRoot();
//			  //System.out.println(treeXmlNode);
//			  String targetNamehibernate_mapping = "hibernate-mapping";
//			  treeXmlNode.getChildren().indexOf(targetNamehibernate_mapping);
//			  int indextargetNamehibernate_mapping=treeXmlNode.getChildren().indexOf(targetNamehibernate_mapping);
//			  if(indextargetNamehibernate_mapping!=-1)
//			  {
//				  XmlNode XmlNodeDocument = treeXmlNode.getChildren().get(indextargetNamehibernate_mapping);
//				  System.out.println(XmlNodeDocument);
//			  }
//			  XmlNode XmlNodeDocument = treeXmlNode.getChildren().get(2);
//			  
//			  //XmlNode treeXmlNode = parser.parse(XmlNodeDocument).get();
//			  System.out.println(XmlNodeDocument.toString());
//			  //System.out.println(XmlNodeDocument);
//			  //System.out.println(XmlNodeDocument.toString());
//			 
//			  for (XmlNode node : treeXmlNode.getChildren()) 
//			  {
//				  System.out.println(node.get);
//			      for (XmlNode child : node.getChildren()) 
//			      {
//			    	  System.out.println(child);
//			      }
//			      for (XmlNode child : node.getAttributes()) 
//			      {
//			    	  System.out.println(child);
//			      }
//			    }
//			    */
//			  //List<XmlNode> listTreeXmlNodeChildren =treeXmlNode.getChildren();
//			  //String targetNamehibernate_mapping = "hibernate-mapping";
////			  for (XmlNode node : treeXmlNode.getChildren())  
////			    {
////			        if (targetNamehibernate_mapping.equals(node.getName())) {
////			            result = c;
////			            break;
////			        }
////			    }
//			  /*
//			  for(int i=0; i < listTreeXmlNodeChildren.size(); i++) 
//			  {
//			        //String s = listTreeXmlNodeChildren.get(i);
//			  XmlNode XmlNodeDocument = treeXmlNode.getChildren().get(i);
//			        //search the string
//			        if(null!=XmlNodeDocument && XmlNodeDocument.getClass().getName().equals(targetNamehibernate_mapping)) 
//			        {
//			            //return i
//			        }
//			    }		   
//			  */
//			
//		} catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//				
//	}
//
//	
}
