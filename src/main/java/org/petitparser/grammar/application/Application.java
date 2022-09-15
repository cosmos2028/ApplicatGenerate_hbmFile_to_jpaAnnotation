package org.petitparser.grammar.application;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.petitparser.grammar.xml.XmlParser;
import org.petitparser.grammar.xml.ast.XmlElement;
import org.petitparser.grammar.xml.ast.XmlNode;
import org.petitparser.parser.Parser;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Application {

	public static void main(String[] args) {
		try {
			// pour parcourir tous les fichiers d'un repertoire HBM
			File dir = new File("src/main/resources/hbm");
			String fichierHbm;
			Parser parser = new XmlParser();

			log.debug("Scan tous les fichiers y compris les sous packages {}", dir.getCanonicalPath());
			List<File> listFiles = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE,
					TrueFileFilter.INSTANCE);

			for (File file : listFiles) 
			{
				//le fichier doit etre en String pour effectuer le parsing par l'outil petit parser.
				fichierHbm = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
				
				// le parsing est effectuer ici de notre fichier HBM
				XmlNode treeXmlNode = parser.parse(fichierHbm).get();
				log.debug("Contenu arborescent apres le parsing : {}", treeXmlNode);
				
				//charger la map pour obtenir chaque node par balise
				Tools.recursive_chargerMap_xmlElement_by_balise(treeXmlNode);
				
				//charger les map en fonction des balises du fichier hbm.xml
				Tools.charger_data_in_All_map() ;
				
				//mettre les annotations
				Tools.effectuer_annotation() ;
				
				//Generer le fichier java avec les annotations
				Tools.generer_fichier_java_avec_annotion();
				
				//Initialiser les map de données pour le nouveau fichier
				Tools.initialiser_all_map_static();
			}

		} catch (IOException e) {
			log.error(e.getMessage());
		}

	}

}
