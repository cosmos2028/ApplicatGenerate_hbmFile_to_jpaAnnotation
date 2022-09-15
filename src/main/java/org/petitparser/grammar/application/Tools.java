package org.petitparser.grammar.application;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.petitparser.grammar.xml.ast.XmlAttribute;
import org.petitparser.grammar.xml.ast.XmlData;
import org.petitparser.grammar.xml.ast.XmlElement;
import org.petitparser.grammar.xml.ast.XmlNode;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;

import lombok.extern.log4j.Log4j2;
import utilTools.UtilTools;
@Log4j2
public class Tools 
{
	//declaration des map de données
	static Map<String, Map<String, String>> mapClassAnnotation=null;
	static Map<String,String> mapPathsAllJavaFile =null;
	static Map<String, Map<String, String>> mapIdAnnotation =null;
	static XmlNode treeXmlNode =null ;
	static Map<String, List<XmlElement>> dataTreeXmlNode = new HashMap<String, List<XmlElement>>();
	static CompilationUnit compilationUnit=null;
	static String classeJavaSimpleNom=null;

	static Optional<ClassOrInterfaceDeclaration> cuClassParse=null;
	static Map<String,String> mapTypeStringAttributAnnotation =null;
	static Map<String, Map<String, String>> mapPropertyAnnotation=new HashMap<String, Map<String, String>>();;
	static List<Map<String, Map<String, String>>> listMapPropertyAnnotation = new ArrayList<Map<String, Map<String, String>>>();
	 
		
		static 
		{
			//initialiser les map
			initialiser_all_map_static();
			//charger dans la map pour chaque fichier son chemin.
			Tools.showFiles() ;
		}
		
		public static void effectuer_annotation() 
		{
			//annotation de la class
			mettre_annotationClass();
			//annotation de la balise id
			mettre_annotationID();
			//annotation de toutes les balises property
			mettre_annotationProperty();
		}
		
		
		public static void charger_data_in_All_map() 
		{
			//cette methode permet de charger les données dans la map mapClassAnnotation
			setMapClassAnnotation(dataTreeXmlNode.get("class"));
			//cette methode permet de charger les données dans la map mapIdAnnotation
			setMapIdAnnotation(dataTreeXmlNode.get("id").get(0));
			//cette methode permet de charger les données dans la map mapPropertyAnnotation
			setMapPropertyAnnotation(dataTreeXmlNode.get("property"));
			
		}
		
		public static void mettre_annotationClass()
		{
			classeJavaSimpleNom = UtilTools.getLastNamePath(mapClassAnnotation.get("Class").get("name"));
			 NormalAnnotationExpr annotationClass = new NormalAnnotationExpr();
		      //apres le remplissage de ma map j'effectue ou rajout des annotation correspondante de chaque Map
				 for (Map.Entry<String, Map<String, String>>rootMapClassAnnotation : mapClassAnnotation.entrySet()) 
					{
						//Vérifier si c'est le dernier element pour rajouter l'annotation
						int nbElementchieldMapClassAnnotation = rootMapClassAnnotation.getValue().entrySet().size() - 1;
						
						log.debug("\nMAP 1: = {}", rootMapClassAnnotation.getKey() + " : " + rootMapClassAnnotation.getValue());
						
					 switch (rootMapClassAnnotation.getKey()) 
						{
						
						case "Entity":
							
							//tous les POJO doivent obligatoirement avoir une annotation Entity sans parametre ou mettre le nom de la class(Entity(name="nomDeLaClass"))
							compilationUnit = obtenir_compilateurUnit_fichierJava(classeJavaSimpleNom);
							//Preciser un package
					        compilationUnit.setPackageDeclaration("com.github.javaparser.pojoAnnote");

							//ajouter les imports JPA
							compilationUnit.addImport("javax.persistence.*");
					        cuClassParse= compilationUnit.getClassByName(classeJavaSimpleNom);
					        cuClassParse.get().addMarkerAnnotation("Entity");
							break;
						case "Table":
							
								for (Map.Entry<String, String>chieldMapClassAnnotation : rootMapClassAnnotation.getValue().entrySet()) 
								{
									if (nbElementchieldMapClassAnnotation-- == 0) 
									{
										//rajouter le nom de l'annotation pour la cle pairs/valeur
								        annotationClass.setName(rootMapClassAnnotation.getKey());
										//chaque cle est considere comme une annotation
										cuClassParse.get().addAnnotation(annotationClass);
								    }
									
									if(!(chieldMapClassAnnotation.getValue().equals("null")))
									{
										//ici je remplis les annotation pour les clés qui n'ont pas de valeur null
										log.debug("\nMAP 2: = {}", chieldMapClassAnnotation.getKey() + "/" + chieldMapClassAnnotation.getValue());
										
								        annotationClass.addPair(chieldMapClassAnnotation.getKey() , UtilTools.motAsString(chieldMapClassAnnotation.getValue()));
									}
								}
							break;
							
						case "GeneratorType":
							break;
						default:
						}
					}
			
		}
		
		public static void mettre_annotationID()
		{
			if(null!=compilationUnit)
			{
				String nameProperty=null;
				String typeProperty=null;
			//apres le remplissage de ma map j'effectue ou rajout des annotation correspondante de chaque Map
			 for (Map.Entry<String, Map<String, String>>rootMapIdAnnotation : mapIdAnnotation.entrySet()) 
				{
					
					//Vérifier si c'est le dernier element pour rajouter l'annotation
					int nbElementchieldMapIdAnnotation = rootMapIdAnnotation.getValue().entrySet().size() - 1;
					
					log.debug("\n mapIdAnnotation : = {}", rootMapIdAnnotation.getKey() + " : " + rootMapIdAnnotation.getValue());
					
					NormalAnnotationExpr annotationId = new NormalAnnotationExpr();
					
				 switch (rootMapIdAnnotation.getKey()) 
					{
					
					case "Id":
						
						for (Map.Entry<String, String>chieldMapIdAnnotation : rootMapIdAnnotation.getValue().entrySet()) 
						{
							if(!(chieldMapIdAnnotation.getValue().equals("null")))
							{
								
								//ici je remplis les annotation pour les clés qui n'ont pas de valeur null
								if(chieldMapIdAnnotation.getKey().equals("name"))
								{
									nameProperty = chieldMapIdAnnotation.getValue();
								}else if(chieldMapIdAnnotation.getKey().equals("type"))
								{
									typeProperty= chieldMapIdAnnotation.getValue();
								}
								
								log.debug("\nMAP 2: = {}", chieldMapIdAnnotation.getKey() + "/" + chieldMapIdAnnotation.getValue());
								
							}
						}
						if(cuClassParse.get().getFieldByName(nameProperty).isPresent() )
			        	{
			        		cuClassParse.get().getFieldByName(nameProperty).get()
			        		.addMarkerAnnotation("Id");
			        	}
				       
				        
						break;
					case "Column":
						
						//on verifie si le nom et le type de la property est identique à celui du fichier java avant de rajouter l'annotation
						if(null!=nameProperty && null!=typeProperty)
				        {
							NormalAnnotationExpr annotationColm = new NormalAnnotationExpr();
							
							int nbElementchieldMapColumnAnnotation = rootMapIdAnnotation.getValue().entrySet().size() - 1;
							
						for (Map.Entry<String, String>chieldidMapColumnAnnotation : rootMapIdAnnotation.getValue().entrySet()) 
						{
							

						
							if (nbElementchieldMapColumnAnnotation-- == 0) 
							{
								
								//chaque cle est considere comme une annotation
								log.debug("\nCU 2: = {}", cuClassParse.get());
								log.debug("\nMAP 2: = {}", cuClassParse.get().getFieldByName(nameProperty));

						        	if(cuClassParse.get().getFieldByName(nameProperty).isPresent() )
						        	{
						        		//rajouter le nom de l'annotation pour la cle pairs/valeur
						        		annotationColm.setName(rootMapIdAnnotation.getKey());
										
						        		cuClassParse.get().getFieldByName(nameProperty).get()
						        		.addAnnotation(annotationColm);
						        		
						        	}
						    }
							//ici je remplis les annotation pour les clés qui n'ont pas de valeur null
							if(!(chieldidMapColumnAnnotation.getValue().equals("null")))
							{
								//verifie si l attribut n'est pas de type string pour rajouter les ""
								if(mapTypeStringAttributAnnotation.containsKey(chieldidMapColumnAnnotation.getKey().toString()))
								{
									
									log.debug("\nMAP 2: = {}", chieldidMapColumnAnnotation.getKey() + "/" + chieldidMapColumnAnnotation.getValue());
									
									annotationColm.addPair(chieldidMapColumnAnnotation.getKey() , UtilTools.motAsString(chieldidMapColumnAnnotation.getValue()));
								}else
								{
									annotationColm.addPair(chieldidMapColumnAnnotation.getKey() ,chieldidMapColumnAnnotation.getValue());
								}
							
							}
						}
				        }
						break;
						
					case "SequenceGenator":
						
						String generatedType=null,classnAME=null,nameS=null,sequenceName=null;
						for (Map.Entry<String, String>chieldidMapSeqAnnotation : rootMapIdAnnotation.getValue().entrySet()) 
						{
							if(chieldidMapSeqAnnotation.getKey().contentEquals("class"))
							{
								classnAME = chieldidMapSeqAnnotation.getValue();
								if(null!=classnAME)
								{
									if(classnAME.equals("sequence"))
									{
										generatedType="SEQUENCE";
										
									}else if(classnAME.equals("table"))
									{
										generatedType="TABLE";
									}else if(classnAME.equals("identity"))
									{
										generatedType="IDENTITY";
									}else if(classnAME.equals("auto"))
									{
										generatedType="AUTO";
									}
								}
								
							}else if(chieldidMapSeqAnnotation.getKey().contentEquals("name"))
							{
								nameS=chieldidMapSeqAnnotation.getValue();
							}else if(chieldidMapSeqAnnotation.getKey().contentEquals("sequenceName"))
							{
								sequenceName=chieldidMapSeqAnnotation.getValue();
							}
						}
						
						if(null!=nameProperty && null!=typeProperty && null!=generatedType)
				        {
				        	
				        	if(cuClassParse.get().getFieldByName(nameProperty).isPresent() )
				        	{
				        		 NormalAnnotationExpr generationType = new NormalAnnotationExpr();
				        	        generationType.addPair("strategy", "GenerationType."+generatedType);
				        	        generationType.addPair("generator", UtilTools.motAsString(nameS));
				        	        generationType.setName("GeneratedValue");
				        	        cuClassParse.get().getFieldByName(nameProperty).get()
					        		.addAnnotation(generationType);
				        	}
				        }
						break;
					default:
					}
				 
				 
					
				}
			}
		}
		
	
		public static void mettre_annotationProperty()
		{
			if(null!=compilationUnit)
			{
				for(Map<String, Map<String, String>> listItemPropertyAnnotation : listMapPropertyAnnotation)
				{
					log.debug("\n mapPropertyAnnotation : = {}", listItemPropertyAnnotation);

					String nameProperty=null;
					String typeProperty=null;
					NormalAnnotationExpr annotationcolumn = new NormalAnnotationExpr();
					
					 for (Map.Entry<String, Map<String, String>>rootMapPropertyAnnotationBis: listItemPropertyAnnotation.entrySet()) 
						{
						 if(rootMapPropertyAnnotationBis.getKey().equals("property"))
							{
								for (Map.Entry<String, String>chieldMapPropertyAnnotationBis : rootMapPropertyAnnotationBis.getValue().entrySet()) 
									{
											//ici je remplis les annotation pour les clés qui n'ont pas de valeur null
											if(chieldMapPropertyAnnotationBis.getKey().equals("name"))
											{
												nameProperty = chieldMapPropertyAnnotationBis.getValue();
											}
											if(chieldMapPropertyAnnotationBis.getKey().equals("type"))
											{
												typeProperty= chieldMapPropertyAnnotationBis.getValue();
											}
											
											log.debug("\nMAP 2: = {}", chieldMapPropertyAnnotationBis.getKey() + "/" + chieldMapPropertyAnnotationBis.getValue());
											
										
										
									}
							}
						}
					//apres le remplissage de ma map j'effectue ou rajout des annotation correspondante de chaque Map
					 for (Map.Entry<String, Map<String, String>>rootMapPropertyAnnotation: listItemPropertyAnnotation.entrySet()) 
						{
							
							//Vérifier si c'est le dernier element pour rajouter l'annotation
							int nbElementchieldMapIdAnnotationBis = rootMapPropertyAnnotation.getValue().entrySet().size() - 1;
							
							log.debug("\n mapPropertyAnnotation : = {}", rootMapPropertyAnnotation.getKey() + " : " + rootMapPropertyAnnotation.getValue());
							
							
						 switch (rootMapPropertyAnnotation.getKey()) 
							{
							case "Column":
								
								//on verifie si le nom et le type de la property est identique à celui du fichier java avant de rajouter l'annotation
								if(null!=nameProperty && null!=typeProperty)
						        {
								for (Map.Entry<String, String>chieldMapColumnAnnotation : rootMapPropertyAnnotation.getValue().entrySet()) 
								{
								
									if (nbElementchieldMapIdAnnotationBis-- == 0) 
									{
										
										//chaque cle est considere comme une annotation
										log.debug("\nCU 2: = {}", cuClassParse.get());
										log.debug("\nMAP 2: = {}", cuClassParse.get().getFieldByName(nameProperty));

								        	if(cuClassParse.get().getFieldByName(nameProperty).isPresent() )
								        	{
								        		//rajouter le nom de l'annotation pour la cle pairs/valeur
												annotationcolumn.setName(rootMapPropertyAnnotation.getKey());
												
								        		cuClassParse.get().getFieldByName(nameProperty).get()
								        		.addAnnotation(annotationcolumn);
								        		
								        	}
								        	
								        	
								        	
								    }
									//ici je remplis les annotation pour les clés qui n'ont pas de valeur null
									if(!(chieldMapColumnAnnotation.getValue().equals("null")))
									{
										//verifie si l attribut n'est pas de type string pour rajouter les ""
										if(mapTypeStringAttributAnnotation.containsKey(chieldMapColumnAnnotation.getKey().toString()))
										{
											
											log.debug("\nMAP 2: = {}", chieldMapColumnAnnotation.getKey() + "/" + chieldMapColumnAnnotation.getValue());
											
											annotationcolumn.addPair(chieldMapColumnAnnotation.getKey() , UtilTools.motAsString(chieldMapColumnAnnotation.getValue()));
										}else
										{
											annotationcolumn.addPair(chieldMapColumnAnnotation.getKey() ,chieldMapColumnAnnotation.getValue());
										}
									
									}
								}
						        }
								break;
								
							default:
							}
						}
				    
				}
			}
			
		}
		public static  void generer_fichier_java_avec_annotion()
		{
			 log.debug("AST de la class = {}", compilationUnit.toString());
	        //je sors de la premier boucle for
			 
			//pour l'instant c'est une BETA je generer le fichier java avec les annotations ici
			 
			 try {
					FileUtils.writeStringToFile(
							new File("src/main/java/com/github/javaparser/pojoAnnote/" + classeJavaSimpleNom + ".java"),
							compilationUnit.toString(), StandardCharsets.UTF_8);
				} catch (IOException e) {
					log.error(e.getMessage()); 
				}
		}
		  //Recuperer et parser le fichier java
		public static CompilationUnit obtenir_compilateurUnit_fichierJava(String nomClasseJava) 
		{
			CompilationUnit compilationUnit=null;
		try 
		{
			File actualiteFile=  mapPathsAllJavaFile.containsKey(UtilTools.addExtensionFileJavaToFileName(nomClasseJava)) ? new File(mapPathsAllJavaFile.get(UtilTools.addExtensionFileJavaToFileName(nomClasseJava))):null;
			
			//parser le fichier java
			compilationUnit =StaticJavaParser.parse(actualiteFile);
	    	
		   
		    
		    
		    if (compilationUnit !=null) {
	            // Recuperer le nom de la class en cours
	            //log.debug("nom de la class à partir de AST = {}", listCompilationUnit.get().getClassByName("Actualite").get().toString());
	            log.debug("AST de la class parser = {}", compilationUnit.toString());
	        }

		   
		} catch (IOException e) 
	    {
	        log.error(e.getMessage());
	    }
		
		return compilationUnit;
		}
		
		
		
		
		
		
		 public static void setMapIdAnnotation(XmlElement XmlElementId ) 
		{
			XmlNode composant = (XmlNode) XmlElementId;

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
					
					switch (nomBaliseXmlElement) 
					{
					
					case "id":
						for(XmlAttribute  attributsId: elementcourant.getAttributes()) 
				          {
							if(mapIdAnnotation.get("Id").containsKey(attributsId.getName().getLocal()))
				          	{
								mapIdAnnotation.get("Id").put(attributsId.getName().getLocal(),UtilTools.getLastNamePath(attributsId.getValue()));
				          	} 

				          }
						log.debug("attr de la class id = {}", elementcourant.getAttributes());
						
						break;
					case "column":
						
						for(XmlAttribute  attributsId: elementcourant.getAttributes()) 
				          {
							if(mapIdAnnotation.get("Column").containsKey(attributsId.getName().getLocal()))
				          	{
								mapIdAnnotation.get("Column").put(attributsId.getName().getLocal(),UtilTools.getLastNamePath(attributsId.getValue()));
				          	} 

				          }
						
						log.debug("attr de la class column = {}", elementcourant.getAttributes());

						break;
					case "generator":
						
						for(XmlAttribute  attributsId: elementcourant.getAttributes()) 
				          {
							if(mapIdAnnotation.get("SequenceGenator").containsKey(attributsId.getName().getLocal()))
				          	{
								mapIdAnnotation.get("SequenceGenator").put(attributsId.getName().getLocal(),UtilTools.getLastNamePath(attributsId.getValue()));
				          	} 

				          }
						log.debug("attr de la class generator = {}", elementcourant.getAttributes());

						break;
					case "param":
						
						for(XmlAttribute  attributsId: elementcourant.getAttributes()) 
				          {
							if(mapIdAnnotation.get("SequenceGenator").containsKey(attributsId.getName().getLocal()))
				          	{
								mapIdAnnotation.get("SequenceGenator").put(attributsId.getName().getLocal(),attributsId.getValue());
				          	}
							
							if(elementcourant.getChildren().get(0).getClass().getSimpleName().equals("XmlText")) 
				          	{
				          		XmlData dataXmlText =  (XmlData)(elementcourant.getChildren().get(0));
				          		
								mapIdAnnotation.get("SequenceGenator").put("sequenceName",dataXmlText.toString());

				          		log.debug("data param = {}", dataXmlText.getData());
							
				          	}

				          }
						log.debug("attr de la param generator = {}", elementcourant.getAttributes());

						break;
						
					
					default:
					}
				}
			}

			// Partie récursive sur les noeuds
			if (composant.getChildren() != null) {
				List<XmlNode> enfants = composant.getChildren();
				for (XmlNode n : enfants) {
					if (n.getClass().getSimpleName().equals("XmlElement")) {
						setMapIdAnnotation((XmlElement) n);
					}
				}
			}
		}	
		 
		 public static void setMapPropertyAnnotation (List<XmlElement>  XmlElementPropertys)
		 {
			 //rajouter pour chaque property ses données stocké dans une map dans une list : listMapPropertyAnnotation
			 XmlElementPropertys.forEach((xmlElementProperty) -> 
			 {
				 initialiser_all_mapmapPropertyAnnotation();
				 addMapPropertyAnnotation(xmlElementProperty);
				 log.debug("mapPropertyAnnotation = {}",    mapPropertyAnnotation);
				 
			 listMapPropertyAnnotation .add(mapPropertyAnnotation);
			 log.debug("listMapPropertyAnnotation size = {}",  listMapPropertyAnnotation.size());
			}
			);
		 }

		private static void addMapPropertyAnnotation(XmlElement xmlElementProperty)
		{
			XmlNode composant = (XmlNode) xmlElementProperty;

			
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
					
					switch (nomBaliseXmlElement) 
					{
					
					case "property":
						for(XmlAttribute  attributsId: elementcourant.getAttributes()) 
				          {
							if(mapPropertyAnnotation.get("property").containsKey(attributsId.getName().getLocal()))
				          	{
								mapPropertyAnnotation.get("property").put(attributsId.getName().getLocal(),UtilTools.getLastNamePath(attributsId.getValue()));
				          	} 

				          }
						log.debug("attr de la class id = {}", elementcourant.getAttributes());
						
						break;
					case "column":
						
						for(XmlAttribute  attributsId: elementcourant.getAttributes()) 
				          {
							if(mapPropertyAnnotation.get("Column").containsKey(attributsId.getName().getLocal()))
				          	{
								mapPropertyAnnotation.get("Column").put(attributsId.getName().getLocal(),UtilTools.getLastNamePath(attributsId.getValue()));
				          	} 

				          }
						
						log.debug("attr de la class column = {}", elementcourant.getAttributes());

						break;
						
					
					default:
					}
				}
			}

			// Partie récursive sur les noeuds
			if (composant.getChildren() != null) {
				List<XmlNode> enfants = composant.getChildren();
				for (XmlNode n : enfants) {
					if (n.getClass().getSimpleName().equals("XmlElement")) 
					{
						
						addMapPropertyAnnotation((XmlElement) n);
					}
				}
			
			}
		}
		  public static void setMapClassAnnotation(List<XmlElement> XmlElementClass ) 
		  {
			  
			 // List<XmlAttribute> attributs = XmlElementClass.getAttributes();
			  
			  if(XmlElementClass.size()<2)
			  {
				  for(XmlAttribute  attributsClass: XmlElementClass.get(0).getAttributes()) 
		          {
		        	  
		          	if(mapClassAnnotation.containsKey(UtilTools.capitalize(attributsClass.getName().getLocal())))
		          	{
		          		
		          	(mapClassAnnotation.get(UtilTools.capitalize(attributsClass.getName().getLocal()))).put("name",UtilTools.getLastNamePath(attributsClass.getValue()));
		         		 log.debug("dans setMapClassAnnotation() {}", mapClassAnnotation );
		         		 
		          	}else if(mapClassAnnotation.get("Table").containsKey(attributsClass.getName().getLocal()) && !(attributsClass.getName().getLocal().contains("name")))
		          	{
		          		//si la valeur (a.getName().getLocal()) appartient à une des clé de ma map avec pour cle Table, on modifie son contenu
		          		mapClassAnnotation.get("Table").put(attributsClass.getName().getLocal(),UtilTools.getLastNamePath(attributsClass.getValue()));
		          		
		          	}else if(mapClassAnnotation.get("Class").containsKey(attributsClass.getName().getLocal()) )
		          	{
		          		mapClassAnnotation.get("Class").put(attributsClass.getName().getLocal(),UtilTools.getLastNamePath(attributsClass.getValue()));
		          	}
		        		 log.debug("dans setMapClassAnnotation(): {}", attributsClass.getName().getLocal() );
		        		log.debug("dans setMapClassAnnotation(): {}: ",  mapClassAnnotation);
		          }
			  }
		          
		  }
		
		public static void recursive_chargerMap_xmlElement_by_balise(XmlNode composant) 
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
							List<XmlElement> listItem = new ArrayList<XmlElement>();
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
						recursive_chargerMap_xmlElement_by_balise(n);
					}
				}
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
		 
		  public static void initialiser_all_map_static()
		  {
			  declaration_all_variable();
			//Cette Map concerne uniquemet les annotation de la Class.
				mapClassAnnotation = new LinkedHashMap<String, Map<String, String>>();
				mapClassAnnotation.clear();
				
				mapClassAnnotation.put("Entity", new LinkedHashMap<String, String>());
				mapClassAnnotation.get("Entity").put("name", "null");
				mapClassAnnotation.put("Table", new LinkedHashMap<String, String>());
				mapClassAnnotation.get("Table").put("name", "null");
				mapClassAnnotation.get("Table").put("schema", "null");
				mapClassAnnotation.get("Table").put("catalog", "null");
				mapClassAnnotation.get("Table").put("uniqueConstraints", "null");
				mapClassAnnotation.get("Table").put("indexes", "null");
				mapClassAnnotation.put("Class", new LinkedHashMap<String, String>());
				mapClassAnnotation.get("Class").put("name", "null");
				
				//Cette Map concerne uniquemet les annotation de la balise ID.
				
				mapIdAnnotation = new LinkedHashMap<String, Map<String, String>>();
				mapIdAnnotation.clear();
				
				mapIdAnnotation.put("Id", new LinkedHashMap<String, String>());
				mapIdAnnotation.get("Id").put("name", "null");
				mapIdAnnotation.get("Id").put("type", "null");
				mapIdAnnotation.put("Column", new LinkedHashMap<String, String>());
				mapIdAnnotation.get("Column").put("name", "null");
				mapIdAnnotation.get("Column").put("unique", "null");
				mapIdAnnotation.get("Column").put("nullable", "null");
				mapIdAnnotation.get("Column").put("insertable", "null");
				mapIdAnnotation.get("Column").put("updatable", "null");
				mapIdAnnotation.get("Column").put("columnDefinition", "null");
				mapIdAnnotation.get("Column").put("table", "null");
				mapIdAnnotation.get("Column").put("length", "null");
				mapIdAnnotation.get("Column").put("precision", "null");
				mapIdAnnotation.get("Column").put("scale", "null");
				mapIdAnnotation.put("SequenceGenator", new LinkedHashMap<String, String>());
				mapIdAnnotation.get("SequenceGenator").put("class", "null");
				mapIdAnnotation.get("SequenceGenator").put("name", "null");
				mapIdAnnotation.get("SequenceGenator").put("sequenceName", "null");
				
				//Cette Map concerne uniquemet les attributs avec qui sont des String.
				mapTypeStringAttributAnnotation = new LinkedHashMap<String, String>();
				mapTypeStringAttributAnnotation.clear();
				mapTypeStringAttributAnnotation.put("name", "string");
				mapTypeStringAttributAnnotation.put("schema", "string");
				mapTypeStringAttributAnnotation.put("catalog", "string");
				mapTypeStringAttributAnnotation.put("name", "string");
				mapTypeStringAttributAnnotation.put("columnDefinition", "string");
				mapTypeStringAttributAnnotation.put("table", "string");
				
				//Cette Map concerne uniquemet les attributs de l'annotation column.
				mapPropertyAnnotation = new HashMap<String, Map<String, String>>();
				mapPropertyAnnotation.clear();
				mapPropertyAnnotation.put("Column", new LinkedHashMap<String, String>());
				mapPropertyAnnotation.put("property", new LinkedHashMap<String, String>());
				mapPropertyAnnotation.get("property").put("name", "null");
				mapPropertyAnnotation.get("property").put("type", "null");
				mapPropertyAnnotation.get("Column").put("name", "null");
				mapPropertyAnnotation.get("Column").put("unique", "null");
				mapPropertyAnnotation.get("Column").put("nullable", "null");
				mapPropertyAnnotation.get("Column").put("insertable", "null");
				mapPropertyAnnotation.get("Column").put("updatable", "null");
				mapPropertyAnnotation.get("Column").put("columnDefinition", "null");
				mapPropertyAnnotation.get("Column").put("table", "null");
				mapPropertyAnnotation.get("Column").put("length", "null");
				mapPropertyAnnotation.get("Column").put("precision", "null");
				mapPropertyAnnotation.get("Column").put("scale", "null");
				
				//charger dans la map pour chaque fichier son chemin.
				Tools.showFiles() ;
		  }
		  public static void initialiser_all_mapmapPropertyAnnotation()
		  {
			//Cette Map concerne uniquemet les attributs de l'annotation column.
				mapPropertyAnnotation = new HashMap<String, Map<String, String>>();
				mapPropertyAnnotation.clear();
				mapPropertyAnnotation.put("Column", new LinkedHashMap<String, String>());
				mapPropertyAnnotation.put("property", new LinkedHashMap<String, String>());
				mapPropertyAnnotation.get("property").put("name", "null");
				mapPropertyAnnotation.get("property").put("type", "null");
				mapPropertyAnnotation.get("Column").put("name", "null");
				mapPropertyAnnotation.get("Column").put("unique", "null");
				mapPropertyAnnotation.get("Column").put("nullable", "null");
				mapPropertyAnnotation.get("Column").put("insertable", "null");
				mapPropertyAnnotation.get("Column").put("updatable", "null");
				mapPropertyAnnotation.get("Column").put("columnDefinition", "null");
				mapPropertyAnnotation.get("Column").put("table", "null");
				mapPropertyAnnotation.get("Column").put("length", "null");
				mapPropertyAnnotation.get("Column").put("precision", "null");
				mapPropertyAnnotation.get("Column").put("scale", "null");
		  }

		  public static void declaration_all_variable() 
			 {
			//declaration des map de données
				 mapClassAnnotation=null;
				 mapPathsAllJavaFile =null;
				  mapIdAnnotation =null;
				 XmlNode treeXmlNode =null ;
				 dataTreeXmlNode = new LinkedHashMap<String, List<XmlElement>>();
				 compilationUnit=null;
				  classeJavaSimpleNom=null;
				 cuClassParse=null;
				 listMapPropertyAnnotation = new ArrayList<Map<String, Map<String, String>>>();
			  
			 }
		  
}
