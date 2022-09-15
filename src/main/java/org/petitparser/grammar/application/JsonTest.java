package org.petitparser.grammar.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.petitparser.grammar.xml.XmlParser;
import org.petitparser.grammar.xml.ast.XmlAttribute;
import org.petitparser.grammar.xml.ast.XmlElement;
import org.petitparser.grammar.xml.ast.XmlNode;
import org.petitparser.parser.Parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.log4j.Log4j2;
import utilTools.UtilTools;
@Log4j2
public class JsonTest {

	static ObjectNode childTable;
	static ObjectNode table ;
	
	static 
	{
		 // les annotations JPA de la class au format JSON
		ObjectMapper  mapperTable = new ObjectMapper();
         table = mapperTable.createObjectNode();
        
        childTable = table.putObject("Table");
        childTable.put("name","null");
        childTable.put("schema","null");
        childTable.put("catalog","null");
        childTable.put("uniqueConstraints","null");
        childTable.put("indexes","null");
        //System.out.println(table);
	}
	public static void main(String[] args) 
	{
		

		/*
		 final ObjectMapper mapper = new ObjectMapper();
	        final ObjectNode root = mapper.createObjectNode();
	        root.set("integer", mapper.convertValue(1, JsonNode.class));
	        root.set("string", mapper.convertValue("string", JsonNode.class));
	        root.set("bool", mapper.convertValue(true, JsonNode.class));
	        root.set("array", mapper.convertValue(Arrays.asList("a", "b", "c"), JsonNode.class));
	        System.out.println(root);
	        
	        ObjectMapper mapper2 = new ObjectMapper();
			//ignorer la sensibilite sur la case
	        
	     
	        
	        ObjectNode root2 = mapper2.createObjectNode();

	        root2.put("name1", 1);
	        root2.put("name2", "someString");

	        ObjectNode child = root2.putObject("child");
	        child.put("name3", 2);
	        child.put("name4", "someString");
	        root2.put("name1", 2);
	        
	        
	        


	        ((ObjectNode)root2.get("child")).put("color", "red");
	        ((ObjectNode)root2.get("child")).put("color", UtilTools.upperCase("creen"));
	        System.out.println(root2);
	        
	         // les annotations JPA de la class au format JSON
	        ObjectMapper mapperTable = new ObjectMapper();
	        ObjectNode table = mapperTable.createObjectNode();
	        
	        ObjectNode childTable = table.putObject("Table");
	        childTable.put("name","null");
	        childTable.put("schema","null");
	        childTable.put("catalog","null");
	        childTable.put("uniqueConstraints","null");
	        childTable.put("indexes","null");
	        System.out.println(table);
	        
	        
	        JsonNode treeJsonClass;
			try {


			    URL url = JsonTest.class.getResource("/jsonFile/treeJsonClass.json");

			    if(url == null) {
		            throw new IllegalArgumentException("le fichier treeJsonClass.json est introuvable");
		        }
		        
		        File fileTreeJsonClass = new File(url.getFile());
		        
		        treeJsonClass = mapper.readTree(fileTreeJsonClass);
				
				Map<String, Object> mapTreeJsonClass = mapper.convertValue(treeJsonClass, new TypeReference<Map<String, Object>>(){});
				
				for (Map.Entry<String, Object> entry : mapTreeJsonClass.entrySet()) 
				{
					log.debug("field node: = \n{}", entry.getKey() + "/" + entry.getValue());
				}
				//ou ceci
			
						
				
				
			} catch (IOException e) 
			{
				e.printStackTrace();
			}

			*/
			Map<String, Map<String, String>> map = 
				    new HashMap<String, Map<String, String>>();
			
			map.put("Table", new HashMap<String, String>());
			 map.get("Table").put("name", "tableName");
			 map.get("Table").put("schema", "schemaName");
			 map.get("Table").put("catalog", "null");
			 
	        
			 for (Map.Entry<String, Map<String, String>>entry : map.entrySet()) 
				{
					log.debug("MAP 1: = \n{}", entry.getKey() + "/" + entry.getValue());
					
					for (Map.Entry<String, String>entry2 : entry.getValue().entrySet()) 
					{
						if(entry2.getValue().equals("null"))
						{
							log.debug("MAP 2: = \n{}", entry2.getKey() + "/" + entry2.getValue());

						}
					}
				}
			 
			 
			 
	        /* 
	        ObjectMapper objectMapper = new ObjectMapper();

	        JsonNode jsonNode;
			try 
			{
				jsonNode = objectMapper.readTree(table.toString());
		        System.out.println("node : "+jsonNode);
		        

		        jsonNode.forEach(field -> 
		        {
					 log.debug("field node: = \n{}", field);

		           
		          });
		        

		        JsonNode linksNode = jsonNode.get("Table");
		        Iterator<String> names = linksNode.fieldNames();
		        linksNode.forEach(node ->
		        log.debug("field node: = \n{}", node)
		            );
		        
		      
		    		// get node like the above example 1
		    	


				

			} catch (JsonProcessingException e) 
			{
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        
	       
	        
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
					recursiveConvertFieldxml(treeXmlNode);
					//System.out.println("lul");
				//}

			} catch (IOException e) {
				log.error(e.getMessage());
			}
	        */
	}

	public static void recursiveConvertFieldxml(XmlNode composant) {
        List<XmlNode> enfants = composant.getChildren();
        if (composant.getParent() != null) {
            XmlElement currentElement = (XmlElement) composant;
            System.out.println(currentElement.getName().getLocal());
            List<XmlAttribute> attributs = currentElement.getAttributes();
            for(XmlAttribute a : attributs) 
            {
            	if(table.has(UtilTools.capitalize(a.getName().getLocal())))
            	{
            	((ObjectNode)table.get(UtilTools.capitalize(a.getName().getLocal()))).put("name",UtilTools.getLastNamePath(a.getValue()));

           		 log.debug("Contenu json {}", table );
            		 //log.debug("Contenu UtilTools.capitalize {}", UtilTools.capitalize(a.getName().getLocal()));
            	}else if(childTable.has(a.getName().getLocal()) && !(a.getName().getLocal().contains("name")))

            	{
                	//((ObjectNode)table.get(UtilTools.capitalize(a.getName().getLocal()))).put(UtilTools.capitalize(a.getName().getLocal()),UtilTools.getLastNamePath(a.getValue()));

            		childTable.put(a.getName().getLocal(),UtilTools.getLastNamePath(a.getValue()));
            	}
            	
          		 log.debug("Contenu getName {}", a.getName().getLocal() );
          		log.debug("Contenu childTable {}: ",  table);
          		//log.debug("a.getName().getLocal() : ",a.getName().getLocal());

         		 //log.debug("Contenu getName {}", childTable.get() );
            }
        }
        if (enfants != null) {
            for(XmlNode n : enfants) {
                if (n.getClass().getSimpleName().equals("XmlElement")) {
                	recursiveConvertFieldxml(n);
                }
            }
        }
    }
}
