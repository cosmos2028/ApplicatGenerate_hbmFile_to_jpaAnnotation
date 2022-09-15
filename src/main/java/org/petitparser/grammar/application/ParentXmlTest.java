package org.petitparser.grammar.application;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.petitparser.grammar.xml.XmlParser;
import org.petitparser.grammar.xml.ast.XmlElement;
import org.petitparser.grammar.xml.ast.XmlNode;
import org.petitparser.parser.Parser;

import lombok.extern.log4j.Log4j2;
@Log4j2
public class ParentXmlTest 
{
	static Map<String, Map<String, String>> mapIdAnnotation;
	static List<String>  listIdParent;
	static XmlNode treeXmlNode ;
	static Map<String, List<XmlElement>> dataTreeXmlNode = new HashMap<String, List<XmlElement>>();

	
	static 
	{
		
		mapIdAnnotation = new HashMap<String, Map<String, String>>();
		
		mapIdAnnotation.put("Id", new HashMap<String, String>());
		mapIdAnnotation.get("Id").put("name", "null");
		mapIdAnnotation.get("Id").put("type", "null");
		
		mapIdAnnotation.put("Column", new HashMap<String, String>());
		mapIdAnnotation.get("Column").put("name", "null");
		mapIdAnnotation.get("Column").put("scale", "null");
		
		
		mapIdAnnotation.put("Generator", new HashMap<String, String>());
		mapIdAnnotation.get("Generator").put("class", "null");
		mapIdAnnotation.get("Generator").put("name", "null");
		mapIdAnnotation.get("Generator").put("value", "null");
		
		listIdParent = new ArrayList<String>();
		listIdParent.add("id");
		listIdParent.add("column");
		listIdParent.add("generator");
		

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
				 treeXmlNode = parser.parse(fichierHbm).get();
				//log.debug("Contenu arborescent {}", treeXmlNode);
				recursive(treeXmlNode);
				
				for (Entry<String, List<XmlElement>>  entry : dataTreeXmlNode.entrySet()) {
				    String key = entry.getKey();
				  log.debug("\nkey map : = {}", key);
				    for (XmlElement  listnodeByKey : entry.getValue()) {
				    	
				    	log.debug("\nlistnodeByKey : = {}", listnodeByKey.getAttributes().toString());
				    }
				}
			
		} catch (IOException e) {
			log.error(e.getMessage());
		}

	}
	
	public static void recursive(XmlNode composant) 
	{
		boolean trouverBaliseClass=false;
		int compteur=0;

		// Partie traitement
		// Tous les éléments de l'arbre sont des XmlNodes dont XmlElement hérite
		// Tout enfant est un noeud ou une feuille i.e. un XmlElement
		// Seule la racine ne rentre pas dans cette condition != XmlElement
		if (composant.getParent() != null) 
		{
			//recuperer le parent
			if(composant.getParent().getClass().getSimpleName().equals("XmlElement"))
			{
				XmlElement elementcourant = (XmlElement) composant;
				//recuperer la balise du noeud
				
				String nomBaliseXmlElement = elementcourant.getName().getLocal();
				
				if (nomBaliseXmlElement.equals("class")) {
					
					trouverBaliseClass=true;
					List<XmlNode> enfants = elementcourant.getChildren();
					dataTreeXmlNode.put(nomBaliseXmlElement, Arrays.asList(elementcourant));
					
					for(XmlNode enfant : enfants) {
						
						if(enfant.getClass().getSimpleName().equals("XmlElement")) {
							XmlElement elementEnfant = (XmlElement) enfant;
							
							String nomEnfant = elementEnfant.getName().getLocal();
							
							 //log.debug("\n nom enfant: = {}", nomEnfant);
							if (dataTreeXmlNode.containsKey(nomEnfant))
							{
								dataTreeXmlNode.computeIfAbsent(nomEnfant, k -> new ArrayList<>()).add(elementEnfant);

							}else
							{
								dataTreeXmlNode.computeIfAbsent(nomEnfant, k -> new ArrayList<>()).add(elementEnfant);
							}
						}
					}
					
				}
				
			}
		}

		// Partie récursive sur les noeuds
		if (composant.getChildren() != null && !trouverBaliseClass) {
			List<XmlNode> enfants = composant.getChildren();
			for (XmlNode n : enfants) {
				if (n.getClass().getSimpleName().equals("XmlElement")) {
					recursive(n);
				}
			}
		}
	}
	
}

