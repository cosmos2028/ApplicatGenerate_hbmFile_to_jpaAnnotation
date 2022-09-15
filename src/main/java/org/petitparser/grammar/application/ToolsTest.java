package org.petitparser.grammar.application;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.petitparser.grammar.xml.ast.XmlAttribute;
import org.petitparser.grammar.xml.ast.XmlElement;
import org.petitparser.grammar.xml.ast.XmlNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;

import lombok.extern.log4j.Log4j2;
import utilTools.UtilTools;

@Log4j2
public class ToolsTest 
{
static Map<String, Map<String, String>> mapClassAnnotation;
static Map<String,String> mapPathsAllJavaFile;
	
	static 
	{
		//charger dans la map pour chaque fichier son chemin.
		ToolsTest.showFiles() ;
		
		//charger dans la map le modele.
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
	
	public static void recursive(XmlNode composant) 
	{

		// Partie traitement
		// Tous les éléments de l'arbre sont des XmlNodes dont XmlElement hérite
		// Tout enfant est un noeud ou une feuille i.e. un XmlElement
		// Seule la racine ne rentre pas dans cette condition != XmlElement
		if (composant.getParent() != null) {
			XmlElement elementcourant = (XmlElement) composant;
			hbm_to_annotation_java(elementcourant);
		}

		// Partie récursive sur les noeuds
		if (composant.getChildren() != null) {
			List<XmlNode> enfants = composant.getChildren();
			for (XmlNode n : enfants) {
				if (n.getClass().getSimpleName().equals("XmlElement")) {
					recursive(n);
				}
			}
		}
	}

	public static void hbm_to_annotation_java(XmlElement elementcourant) 
	{
		String nomBaliseXmlElement = elementcourant.getName().getLocal();
		switch (nomBaliseXmlElement) 
		{
		
		case "class":
			
			List<XmlAttribute> attributs = elementcourant.getAttributes();
			NormalAnnotationExpr annotationClass = new NormalAnnotationExpr();
			CompilationUnit compilationUnit=null;
			Optional<ClassOrInterfaceDeclaration> cuClassParse=null;
			String classeJavaSimpleNom;
			for (XmlAttribute attribut : attributs) 
			{
				if(attribut.getName().toXmlString().equals("name")) 
				{
					classeJavaSimpleNom = UtilTools.getLastNamePath(attribut.getValue());
					
					//tous les POJO doivent obligatoirement avoir une annotation Entity sans parametre ou mettre le nom de la class(Entity(name="nomDeLaClass"))
					compilationUnit = initialisation(classeJavaSimpleNom);
					//Preciser un package
			        compilationUnit.setPackageDeclaration("com.github.javaparser.pojo.gen");

					//ajouter les imports JPA
					compilationUnit.addImport("javax.persistence.*");
			        cuClassParse= compilationUnit.getClassByName(classeJavaSimpleNom);
			        cuClassParse.get().addMarkerAnnotation("Entity");
			        
			        //setMapClassAnnotation est appele pour charger la map mapClassAnnotation et le recuperer pour effectuer les annotations 
			        setMapClassAnnotation(attributs);
			        
			      //apres le remplissage de ma map j'effectue ou rajout des annotation correspondante de chaque Map
					 for (Map.Entry<String, Map<String, String>>rootMapClassAnnotation : mapClassAnnotation.entrySet()) 
						{
							log.debug("\nMAP 1: = {}", rootMapClassAnnotation.getKey() + " : " + rootMapClassAnnotation.getValue());
							
							for (Map.Entry<String, String>chieldMapClassAnnotation : rootMapClassAnnotation.getValue().entrySet()) 
							{
								//Vérifier si c'est le dernier element pour rajouter l'annotation
								int nbElementchieldMapClassAnnotation = rootMapClassAnnotation.getValue().entrySet().size() - 1;
								
								if (nbElementchieldMapClassAnnotation-- == 0) 
								{
									//chaque cle est considere comme une annotation
									cuClassParse.get().addAnnotation(annotationClass);
							    }
								
								if(!(chieldMapClassAnnotation.getValue().equals("null")))
								{
									//ici je remplis les annotation pour les clés qui n'ont pas de valeur null
									log.debug("\nMAP 2: = {}", chieldMapClassAnnotation.getKey() + "/" + chieldMapClassAnnotation.getValue());
									
							        annotationClass.addPair(chieldMapClassAnnotation.getKey() , UtilTools.motAsString(chieldMapClassAnnotation.getValue()));
							        
							        //rajouter le nom de l'annotation pour la cle pairs/valeur
							        annotationClass.setName(rootMapClassAnnotation.getKey());
								}
							}
						}

					 log.debug("AST de la class = {}", compilationUnit.toString());
			        //je sors de la premier boucle for
					 
					//pour l'instant c'est une BETA je generer le fichier java avec les annotations ici
					 
					 try {
							FileUtils.writeStringToFile(
									new File("src/main/java/com/github/javaparser/pojo/gen/" + classeJavaSimpleNom + ".java"),
									compilationUnit.toString(), StandardCharsets.UTF_8);
						} catch (IOException e) {
							log.error(e.getMessage());
						}
					 
			        break;
				}
			}
			
			break;
		case "id":
			
		default:
		}
		System.out.println(elementcourant.getName().getLocal());
		//System.out.println(attribut.getName().getLocal());
		//System.out.println(attribut.getValue());
	}

    //à modifier
	public static CompilationUnit initialisation(String nomClasseJava) 
	{
		CompilationUnit compilationUnitActualitePojo=null;
		
	try 
	{
		File actualiteFile=  mapPathsAllJavaFile.containsKey(UtilTools.addExtensionFileJavaToFileName(nomClasseJava)) ? new File(mapPathsAllJavaFile.get(UtilTools.addExtensionFileJavaToFileName(nomClasseJava))):null;
		
		//parser le fichier java
    	compilationUnitActualitePojo =StaticJavaParser.parse(actualiteFile);
    	
	   
	    
	    
	    if (compilationUnitActualitePojo !=null) {
            // Recuperer le nom de la class en cours
            //log.debug("nom de la class à partir de AST = {}", listCompilationUnit.get().getClassByName("Actualite").get().toString());
            log.debug("nom de la class à partir de AST = {}", compilationUnitActualitePojo.toString());
        }

	   
	} catch (IOException e) 
    {
        log.error(e.getMessage());
    }
	
	return compilationUnitActualitePojo;
	}
	
  public static enum Keyword {
	
	CLASS("class"),
	NAME("name"),
	ID("id");

    private final String mot;

    Keyword(String mot) {
        this.mot = mot;
    }
    
    public String asString() {
        return mot;
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
  
  public static void showFiles() 
	 {
	  		//charger pour chaque fichier son chemin
			mapPathsAllJavaFile = new HashMap<String,String>();
			
			try {
				// pour parcourir tous les fichiers d'un repertoire HBM
				File dir = new File("src/main/java/com/github/javaparser/pojo");

				log.debug("recuperer tous les fichiers d'un repertoire y compris les sous repertoires {}", dir.getCanonicalPath());
				List<File> listFiles = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE,
						TrueFileFilter.INSTANCE);

				for (File file : listFiles) 
				{
					 Path filePath = Path.of(file.toString());
					 //Path directory = filePath.getParent();
					 
					 mapPathsAllJavaFile.put(file.getName(), filePath.toString());
				}
				 log.debug("\n map: = {}", mapPathsAllJavaFile);

			} catch (IOException e) 
			{
				log.error(e.getMessage());
			}
	  
	 }
}
