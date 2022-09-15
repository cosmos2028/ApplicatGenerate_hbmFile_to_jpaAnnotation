package org.petitparser.grammar.application;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.petitparser.grammar.xml.XmlParser;
import org.petitparser.grammar.xml.ast.XmlAttribute;
import org.petitparser.grammar.xml.ast.XmlElement;
import org.petitparser.grammar.xml.ast.XmlNode;
import org.petitparser.parser.Parser;

import lombok.extern.log4j.Log4j2;
import utilTools.UtilTools;
@Log4j2
public class MapTest 
{
	static Map<String, Map<String, String>> mapClassAnnotation;
	
	static 
	{
		
		mapClassAnnotation = new HashMap<String, Map<String, String>>();
		
		mapClassAnnotation.put("Entity", new HashMap<String, String>());
		mapClassAnnotation.get("Entity").put("name", "null");
		
		mapClassAnnotation.put("Table", new HashMap<String, String>());
		mapClassAnnotation.get("Table").put("name", "null");
		mapClassAnnotation.get("Table").put("schema", "null");
		mapClassAnnotation.get("Table").put("catalog", "null");
		mapClassAnnotation.get("Table").put("uniqueConstraints", "null");
		mapClassAnnotation.get("Table").put("indexes", "null");

	}
	public static void main(String[] args)
	{
	
		try 
        {
			// pour parcourir tous les fichiers d'un repertoire HBM
			File dir = new File("src/main/resources/hbm");
			String fichierHbm;
			Parser parser = new XmlParser();

			log.debug("Getting all files in  including those in subdirectories {}", dir.getCanonicalPath());
			List<File> listFiles = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE,
					TrueFileFilter.INSTANCE);

			//for (File file : listFiles) {
				fichierHbm = FileUtils.readFileToString(listFiles.get(0), StandardCharsets.UTF_8);
				// log.debug("file name: = \n{}", fichierHbm);
				XmlNode treeXmlNode = parser.parse(fichierHbm).get();
				//log.debug("Contenu arborescent {}", treeXmlNode);
				recursive(treeXmlNode);
			//}
				
				//apres le remplissage de ma map j'effectue ou rajout des annotation correspondante de chaque Map
				 for (Map.Entry<String, Map<String, String>>entry : mapClassAnnotation.entrySet()) 
					{
						log.debug("\nMAP 1: = {}", entry.getKey() + " : " + entry.getValue());
						
						for (Map.Entry<String, String>entry2 : entry.getValue().entrySet()) 
						{
							if(!(entry2.getValue().equals("null")))
							{
								//ici je remplis les annotation pour les clés qui n'ont pas de valeur null
								log.debug("\nMAP 2: = {}", entry2.getKey() + "/" + entry2.getValue());
							}
						}
					}

		} catch (IOException e) {
			log.error(e.getMessage());
		}
		
		
	}

	  public static void setMapClassAnnotation(List<XmlAttribute> attributs ) 
	  {
	          for(XmlAttribute a : attributs) 
	          {
	        	  
	          	if(mapClassAnnotation.containsKey(UtilTools.capitalize(a.getName().getLocal())))
	          	{
	          		
	          	(mapClassAnnotation.get(UtilTools.capitalize(a.getName().getLocal()))).put("name",UtilTools.getLastNamePath(a.getValue()));
	         		 log.debug("dans setMapClassAnnotation() {}", mapClassAnnotation );
	         		 
	          	}else if(mapClassAnnotation.get("Table").containsKey(a.getName().getLocal()) && !(a.getName().getLocal().contains("name")))
	          	{
	          		//si la valeur (a.getName().getLocal()) appartient à une des clé de ma map avec pour cle Table, on modifie son contenu
	          		mapClassAnnotation.get("Table").put(a.getName().getLocal(),UtilTools.getLastNamePath(a.getValue()));
	          	}
	        		 log.debug("dans setMapClassAnnotation(): {}", a.getName().getLocal() );
	        		log.debug("dans setMapClassAnnotation(): {}: ",  mapClassAnnotation);
	          }
	  }
	  
	  public static void recursive(XmlNode composant) 
		{

			// Partie traitement
			// Tous les éléments de l'arbre sont des XmlNodes dont XmlElement hérite
			// Tout enfant est un noeud ou une feuille i.e. un XmlElement
			// Seule la racine ne rentre pas dans cette condition != XmlElement
			if (composant.getParent() != null) {
				XmlElement elementcourant = (XmlElement) composant;
			}

			// Partie récursive sur les noeuds
			if (composant.getChildren() != null) {
				List<XmlNode> enfants = composant.getChildren();
				
				for (XmlNode n : enfants) 
				{
					if (n.getClass().getSimpleName().equals("XmlElement")) 
					{
						setMapClassAnnotation(n.getAttributes());
						recursive(n);
					}
				}
			}
		}
}
