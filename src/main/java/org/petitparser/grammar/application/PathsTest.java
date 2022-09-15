package org.petitparser.grammar.application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import lombok.extern.log4j.Log4j2;
import utilTools.UtilTools;
@Log4j2
public class PathsTest 
{
	static Map<String,String> mapPathsAllJavaFile;

	static
	{
		//charger pour chaque fichier son chemin
		mapPathsAllJavaFile = new HashMap<String,String>();
		
		try {
			// pour parcourir tous les fichiers d'un repertoire HBM
			File dir = new File("src/main/java/com/github/javaparser/pojo");
			
			String fichierHbm;

			log.debug("recuperer tous les fichiers d'un repertoire y compris les sous repertoires {}", dir.getCanonicalPath());
			List<File> listFiles = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE,
					TrueFileFilter.INSTANCE);

			for (File file : listFiles) 
			{
				//fichierHbm = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
				//log.debug("file path: = \n{}", fichierHbm);

				//mapPathsAllJavaFile.put(UtilTools.getLastNamePathFile(file.toString()), file.toString());
				
				
				//log.debug("file path: = \n{}", mapPathsAllJavaFile);
				
				 Path filePath = Path.of(file.toString());
				 Path directory = filePath.getParent();
				 
				 mapPathsAllJavaFile.put(file.getName(), filePath.toString());
				 
				    log.debug("\nfile path: = {}", filePath);
				    log.debug("\ndirectory path: = {}", directory);
				    log.debug("\ndirectory path: = {}", file.getName());
				    
				   // ctrl.fileList.getItems().add(directory.toString());
				
			}
			 log.debug("\n map: = {}", mapPathsAllJavaFile);

		} catch (IOException e) {
			log.error(e.getMessage());
		}
				
	}

	public static void main(String[] args) 
	{
		
		
		System.out.println("test map paths");
		/*
		File dir = new File("src/main/java/com/github/javaparser/pojo");
        showFiles(dir.listFiles());
		

		//Recuperer les elements de la map
		 for (Entry<String, String> rootMapPathsAllJavaFile : mapPathsAllJavaFile.entrySet()) 
			{
				log.debug("\nmapPathsAllJavaFile name file: = {}", rootMapPathsAllJavaFile.getKey() + " value : " + rootMapPathsAllJavaFile.getValue());
				
			}
		*/
		
	}
	
	 public static void showFiles(File[] files) 
	 {
	        for (File file : files) {
	            if (file.isDirectory()) {
	                System.out.println("Directory: " + file.getAbsolutePath());
	                showFiles(file.listFiles()); // Calls same method again.
	            } else {
	                System.out.println("File : " + file.getAbsolutePath());
	            }
	        }
	    }
	 

}
