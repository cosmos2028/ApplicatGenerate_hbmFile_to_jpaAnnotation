package com.github.javaparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.junit.Ignore;
import org.junit.Test;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Modifier.Keyword;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.printer.YamlPrinter;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import com.github.javaparser.utils.SourceRoot;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class JavaParserTest 
{
	@Ignore
	@Test
	public void compilationUnit_Parser_File() 
	{
		log.debug("Debut du test 1");
		File actualiteFile = new File("//ApplicatGenerate_hbmFile_to_jpaAnnotation//src//main//java//com//github//javaparser//pojo//Actualite.java");
        CompilationUnit compilationUnitActualitePojo=null;
        try 
        {
        	//parser le fichier java
        	compilationUnitActualitePojo =StaticJavaParser.parse(actualiteFile);
        	
        	//afficher l'arbre syntaxie abstraite(AST) N1
        	log.debug("contenu du fichier qui vient d'etre parse =\n{}", compilationUnitActualitePojo.toString());
        	
		} catch (FileNotFoundException e) 
        {
			log.error(e.getMessage());
		}
	}
	
	//parser tous les fichiers d'un repertoire ou package donnés
	@Ignore
	@Test
	public void getALLFilefromPathDirectoryOrPackage()
	{	

		log.debug("\n saut de ligne");
		log.debug("Debut du test 2 ");
		//preciser le chemin du package
		Path  path = Paths.get("src/main/java/com/github/javaparser/pojo"); 
		SourceRoot sourceRoot =  new SourceRoot(path);
 		try 
 		{
			sourceRoot.tryToParse().forEach
			 (
			 	action ->
			 	{ 
			 		Optional<CompilationUnit> compilationUnit = action.getResult();
			 		
			 		if(compilationUnit.isPresent())
			 		{
			 			//Recuperer le nom de la class en cours 
			         	log.debug("nom de la class à partir de AST = {}",compilationUnit.get().getPrimaryTypeName().get());
			 		}
			 	}
			 );
		} catch (IOException e) 
 		{
			log.error(e.getMessage());
		}
 		
		}
	//comment afficher AST (abstract syntax tree) en parsant un fichier java
	@Ignore
	@Test
	public void show_AST() 
	{	
		log.debug("Debut du test 3");
		File actualiteFile = new File("C:\\Users\\vincent.sabi\\Desktop\\ApplicatGenerate_hbmFile_to_jpaAnnotation\\src\\main\\java\\com\\github\\javaparser\\pojo\\Actualite.java");
        CompilationUnit compilationUnitActualitePojo=null;
        try 
        {
        	//parser le fichier java
        	compilationUnitActualitePojo =StaticJavaParser.parse(actualiteFile);
        	
        	//afficher l'arbre syntaxie abstraite(AST) N1
        	//log.debug("AST content={}", new XmlPrinter(true).output(compilationUnitActualitePojo));

        	//afficher l'arbre syntaxie abstraite(AST) N2
        	log.debug("AST content={}", new YamlPrinter(true).output(compilationUnitActualitePojo));

        	//afficher l'arbre syntaxie abstraite(AST) N3
        	//log.debug("AST content={}", new DotPrinter(true).output(compilationUnitActualitePojo));


		} catch (FileNotFoundException e) 
        {
			log.error(e.getMessage());
		}
	}
	
	//comment utiliser AST (abstract syntax tree) en parsant un fichier java
	@Ignore
	@Test
	public void Use_AST() 
	{	
		log.debug("Debut du test 4");
		File actualiteFile = new File("C:\\Users\\vincent.sabi\\Desktop\\ApplicatGenerate_hbmFile_to_jpaAnnotation\\src\\main\\java\\com\\github\\javaparser\\pojo\\Actualite.java");
        CompilationUnit compilationUnitActualitePojo=null;
        try 
        {
        	//parser le fichier java
        	compilationUnitActualitePojo =StaticJavaParser.parse(actualiteFile);
        	
        	
         	//parcourir les information de la class
            
        	//Recuperer le package de la class
         	log.debug("recuperer le package de la class = {}",compilationUnitActualitePojo.getPackageDeclaration());
         	
         	//Recuperer l'import de la class
         	log.debug("premier import(mais on peut choisir d'afficher tous les imports avec getImports() ) = {}",compilationUnitActualitePojo.getImport(0));
         	
        	//Recuperer le nom de la class
         	log.debug("nom de la class à partir de AST = {}",compilationUnitActualitePojo.getPrimaryTypeName());
         	
         	
         	 if(compilationUnitActualitePojo.getPrimaryTypeName().isPresent())
         	 {
             	 Optional<ClassOrInterfaceDeclaration> actualitePojoClass= compilationUnitActualitePojo.getClassByName(compilationUnitActualitePojo.getPrimaryTypeName().get());         	

             	 //recuperer la class qui est etendu
              	log.debug("class extends = {}",actualitePojoClass.get().getExtendedTypes(0));
              	
              	//recuperer la liste des implementation de la class
              	log.debug("class implements (mais on peut afficher la liste des interfaces ) = {}",actualitePojoClass.get().getImplementedTypes(0));
              	
              	//recuperer le commentaire de la class
              	log.debug("commentaire de la class = {}",actualitePojoClass.get().getComment());
              	
              	//recuperer le constructeur par default ou sans parametre
              	log.debug("get constructeur par default ou sans parametre = {}",actualitePojoClass.get().getDefaultConstructor());
              	
              //recuperer la liste de tous les constructeurs 
              	log.debug("get all constructeur = {}",actualitePojoClass.get().getConstructors());

         	
              //une autre façon de recuperer la class à partir de son nom si on le connait en avaance.
        	 //Optional<ClassOrInterfaceDeclaration> actualitePojoClass= compilationUnitActualitePojo.getClassByName("Actualite");
        	 
        	  //parcourir les information des attriuts
        	 actualitePojoClass.get().getFields().forEach
        	 (field ->
        	 	{
        	 		//Recuperer le commentaire de chaque attribut
        	 		log.debug("commentaire = {}",field.getComment());
        	 		
        	 		//mot cle sur la portée de l'attribut
        	 		log.debug("porte de l'attribut = {}",field.getModifiers());
        	 		
        	 		//On recupere le type de l'attribut
         			log.debug("Type de l'attribut = {}", field.getElementType());
         			
             		//On recupere le nom de l'attribut
             		log.debug("nom de l'attribut  = {}",field.getVariable(0).getNameAsString());
             		
             		//On recupere la valeur initialise de l'attribut
             		log.debug("valeur de l'attribut  = {}",field.getVariable(0).getInitializer());
             		
             		//On recupere tout l'attribut
             		log.debug("get all 'attribut  = {}",field);
        	 	}
        		);
            
        	//parcourir les information d'une methode
             actualitePojoClass.get().getMethods().forEach
             (
             	method ->
             	{ 
             		//mot cle sur la portée de la methode
             		log.debug("porte de la methode = {}",method.getModifiers());
             		
             		//On recupere le type de retour de la methode 
         			log.debug("Type de retour de la methode = {}", method.getType());
         			
             		//On recupere uniquement le nom de la methode sans signature
             		log.debug("methodName = {}",method.getNameAsString());
             		
             		//On recupere la list des parametre de la methode
             		method.getParameters().forEach(parameter -> 
             		{
             			//type du parametre
             			log.debug("type du parametre = {}", parameter.getType());
             			//le nom du parametre
             			log.debug("nom du parametre = {}", parameter.getNameAsString());
         				log.debug("All parameter = {}", parameter);
         			});
             		
             		
         			//on recupere chaque methode avec son contenu
             		log.debug("all method = {}",method);
             		
         			//On recupere les commentaire servant au javadoc(mais à exécute si seulement elle existe sinon erreur)
         			//log.debug("getDescription={}", method.getJavadoc().get().getDescription().toText());
             		
             	}
             );
            }

		} catch (FileNotFoundException e) 
        {
			log.error(e.getMessage());
		}
	}
	
	//comment garder la structure exact du fichier java d'origne(avec indentation,espace,etc) en creant un nouveau fichier java
	@Ignore
	@Test
	public void keep_the_exact_structure_of_the_original_file_java() 
	{	
		log.debug("Debut du test 5");
		//declaration du fichier
		String pathActualiteFile = "C:\\Users\\vincent.sabi\\Desktop\\ApplicatGenerate_hbmFile_to_jpaAnnotation\\src\\main\\java\\com\\github\\javaparser\\pojo\\Actualite.java";
		File actualiteFile2 = new File("C:\\Users\\vincent.sabi\\Desktop\\ApplicatGenerate_hbmFile_to_jpaAnnotation\\src\\main\\java\\com\\github\\javaparser\\pojo\\Actualite.java");

		String contentActualiteFile = null;
		CompilationUnit compilationUnitActualitePojo=null;
		
        try 
        {
        	contentActualiteFile = Files.readString(Paths.get(pathActualiteFile));
        	ParserConfiguration parserConfiguration = new ParserConfiguration().setLexicalPreservationEnabled(true);
        	
        	//parser le fichier java (par contre ici le LexicalPreservation a besoin d'un stringProvider au lieu d'un fichier, c'est pourquoi on n'a convertit le fichier en string)
        	compilationUnitActualitePojo =  new  JavaParser(parserConfiguration).parse(ParseStart.COMPILATION_UNIT, new StringProvider(contentActualiteFile)).getResult().get();

        	log.debug("LexicalPreservation = {}",LexicalPreservingPrinter.print(compilationUnitActualitePojo));
        	
        	//parser le fichier java (par contre ici le LexicalPreservation a besoin d'un file): mais soit ou l'autre selon le besoin de l'utilisateur en terme de flux d'entrée.
        	
        	compilationUnitActualitePojo =  new  JavaParser(parserConfiguration).parse(ParseStart.COMPILATION_UNIT,
        			new StreamProvider(new FileInputStream(actualiteFile2), Charset.forName("UTF-8")) ).getResult().get();
        	
        	log.debug("LexicalPreservation = {}",LexicalPreservingPrinter.print(compilationUnitActualitePojo));

		} catch (FileNotFoundException e) 
        {
			log.error(e.getMessage());
		} catch (IOException e) 
        {
			log.error(e.getMessage());
		}
	}
	
	//Comment generer un code ou fichier java
	@Ignore
	@Test
	public void creation_code_file_java() 
	{
		log.debug("Debut du test 6");
		
		CompilationUnit compilationUnit = new CompilationUnit();
		//Preciser un package
		compilationUnit.setPackageDeclaration("com.github.javaparser.pojo");
		
		//ajouter les imports
		compilationUnit.addImport("java.io.Serializable");
		compilationUnit.addImport("java.time.LocalDateTime");
				
		//creation de la class
		ClassOrInterfaceDeclaration actualiteBis = compilationUnit.addClass("ActualiteBis");
		
		//creation de l'héritage
		actualiteBis.addExtendedType("Object");
		
		//creation de l'interface
		actualiteBis.addImplementedType("Serializable");
		
		//ajouter le commentaire à la class (à voir plus tard)
		//actualiteBis.add
		
		//ajouter les attribut
        String nameGenerationType="Sequence";
        NormalAnnotationExpr generationType = new NormalAnnotationExpr();
        generationType.addPair("strategy", "GenerationType."+nameGenerationType);
        actualiteBis.addField("Long", "id").addModifier(Keyword.PRIVATE)
        .addMarkerAnnotation("Id").addSingleMemberAnnotation("GeneratedValue",generationType);
		
		//ajouter un constructeur avec les parametre
	              		
	    actualiteBis.addConstructor(Keyword.PUBLIC)
	    	.addParameter("Long", "id")
	        	.setBody(new BlockStmt()
	        			.addStatement(new ExpressionStmt(new AssignExpr(
	        					new FieldAccessExpr(new ThisExpr(), "id"),
	                            	new NameExpr("id"),
	                                	AssignExpr.Operator.ASSIGN))) );
	    //ajouter la methode
	    actualiteBis.addMethod("getId", Keyword.PUBLIC).setBody(
	            new BlockStmt().addStatement(new ReturnStmt(new NameExpr("id"))));
	    
	    //afficher le code genere en console
	    log.debug("Code genere = \n{}",compilationUnit.toString());
	    
	    //ajouter le code dans un fichier et le placer dans un package
	    //Files.write(new File("Modified.java").toPath(), compilationUnit.toString(), StandardCharsets.UTF_8);
/*
	    try {
			FileUtils.writeStringToFile(
					new File("src/main/java/com/github/javaparser/pojo" + "ActualiteBis" + ".java"),
					compilationUnit.toString(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	    */
       
	}
	
	//Comment generer un code ou fichier java avec les annotation JPA
	@Ignore
	@Test
    public void creation_code_file_java_avec_annotation_jpa1()
    {
        log.debug("Debut du test 7");

        CompilationUnit compilationUnit = new CompilationUnit();
        //Preciser un package
        compilationUnit.setPackageDeclaration("com.github.javaparser.pojo");

        //ajouter les imports
        compilationUnit.addImport("java.io.Serializable");
        compilationUnit.addImport("java.time.LocalDateTime");

        //creation de la class
        ClassOrInterfaceDeclaration actualiteBis = compilationUnit.addClass("ActualiteBis");

        //creation de l'héritage
        actualiteBis.addExtendedType("Object");

        //creation de l'interface
        actualiteBis.addImplementedType("Serializable");

        //ajouter les annotations sur la class
        actualiteBis.addAnnotation("MySecretAnnotation");
//        NodeList<MemberValuePair> duo = new NodeList<MemberValuePair>().ad;
//        actualiteBis.addAnnotation(new NormalAnnotationExpr("Nullable",));
        
//        actualiteBis.addAnnotation(new MarkerAnnotationExpr("Nullable"));
//        Name lul = new NameExpr(null, null)
//        
//        MarkerAnnotationExpr lol = new MarkerAnnotationExpr().
        
        //actualiteBis.addMarkerAnnotation("Entity");
        //ajouter les attribut
//        String nameGenerationType="Sequence";
//        NormalAnnotationExpr generationType = new NormalAnnotationExpr();
//        generationType.addPair("strategy", "GenerationType."+nameGenerationType);
//        actualiteBis.addField("Long", "id").addModifier(Keyword.PRIVATE)
//        .addMarkerAnnotation("Id").addSingleMemberAnnotation("GeneratedValue", generationType);
        
        
        String nameGenerationType="Sequence";
        NormalAnnotationExpr generationType = new NormalAnnotationExpr();
        generationType.addPair("strategy", "GenerationType."+nameGenerationType);
        String generator;
        actualiteBis.addField("Long", "id").addModifier(Keyword.PRIVATE)
       .addMarkerAnnotation("Id").addSingleMemberAnnotation("GeneratedValue", "strategy = GenerationType.SEQUENCE");
        
        //afficher le code genere en console
        log.debug("Code genere = \n{}",compilationUnit.toString());
    }
	
	@Ignore
	@Test
    public void creation_code_file_java_avec_annotation_jpa2()
    {
        log.debug("Debut du test 8");

		ClassOrInterfaceDeclaration myClass = new ClassOrInterfaceDeclaration();
		myClass.setComment(new LineComment("A very cool class!"));
		myClass.setName("MyClass");
		myClass.addField("String", "foo");
		myClass.addAnnotation("MySecretAnnotation");
		
        //afficher le code genere en console
        log.debug("Code genere = \n{}",myClass.toString());
    }
	
	//Comment generer un code ou fichier java avec les annotation JPA
    @Test
    public void creation_code_file_java_avec_annotation_jpa3()
    {
        log.debug("Debut du test 9");

        CompilationUnit compilationUnit = new CompilationUnit();
        //Preciser un package
        compilationUnit.setPackageDeclaration("com.github.javaparser.pojo");

        //ajouter les imports
        compilationUnit.addImport("java.io.Serializable");
        compilationUnit.addImport("java.time.LocalDateTime");

        //creation de la class
        ClassOrInterfaceDeclaration actualiteBis = compilationUnit.addClass("ActualiteBis");

        //creation de l'héritage
        actualiteBis.addExtendedType("Object");

        //creation de l'interface
        actualiteBis.addImplementedType("Serializable");

        //ajouter les annotations sur la class
        actualiteBis.addAnnotation("Entity");
        actualiteBis.addMarkerAnnotation("Entity");
        //ajouter les attribut
        String nameGenerationType="Sequence";
        NormalAnnotationExpr generationType = new NormalAnnotationExpr();
        generationType.addPair("strategy", "\"GenerationType."+nameGenerationType+"\"");
        generationType.addPair("strategy", "GenerationType."+nameGenerationType);
        generationType.addPair("nullable", "false");
        generationType.setName("GeneratedValue");

        actualiteBis.addField("Long", "id").addModifier(Keyword.PRIVATE)
        .addMarkerAnnotation("Id")
        .addAnnotation(generationType);
        //afficher le code genere en console
        log.debug("Code genere = \n{}",compilationUnit.toString());
        log.debug("Code genere = \n{}",generationType);
    }
	
    @Ignore
    @Test
    public void get_all_file_hbm_and_java_from_package() 
    {    
        log.debug("Debut du test 10");

        try {
            // pour parcourir tous les fichiers d'un repertoire HBM
            File dir = new File("src/main/resources");
            String fichierHbm;



            System.out.println("Getting all files in " + dir.getCanonicalPath() + " including those in subdirectories");
            List<File> listFiles = (List<File>) FileUtils.listFiles(dir, TrueFileFilter.INSTANCE,
                    TrueFileFilter.INSTANCE);

            for (File file : listFiles) 
            {
                 fichierHbm=  FileUtils.readFileToString(file, StandardCharsets.UTF_8);

                //log.debug("file name: = \n{}", fichierHbm);
            }

             //pour parcourir tous les fichiers d'un package java
            // preciser le chemin du package
            Path path = Paths.get("src/main/java/com/github/javaparser/pojo");
            SourceRoot sourceRoot = new SourceRoot(path);

            sourceRoot.tryToParse().forEach(action -> {
                Optional<CompilationUnit> listCompilationUnit = action.getResult();

                if (listCompilationUnit.isPresent()) {
                    // Recuperer le nom de la class en cours
                    //log.debug("nom de la class à partir de AST = {}", listCompilationUnit.get().getClassByName("Actualite").get().toString());
                    log.debug("nom de la class à partir de AST = {}", listCompilationUnit.get().getPrimaryTypeName().get());
                }
            });

        } catch (IOException e) 
        {
            log.error(e.getMessage());
        }

    }
 	
}
