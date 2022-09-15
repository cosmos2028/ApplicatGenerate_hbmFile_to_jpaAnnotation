package poubelle;

import java.io.File;
import java.io.FileNotFoundException;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.comments.LineComment;

public class Test2{

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		File actualiteFile = new File(
				"C:\\Users\\vincent.sabi\\Desktop\\ApplicatGenerate_hbmFile_to_jpaAnnotation\\src\\main\\java\\com\\github\\javaparser\\pojo\\Actualite.java");
		CompilationUnit compilationUnitActualitePojo = null;
		try {
			// parser le fichier java
			compilationUnitActualitePojo = StaticJavaParser.parse(actualiteFile);
			System.out.println("lul");
			// afficher l'arbre syntaxie abstraite(AST) N1

		} catch (FileNotFoundException e) {
		}
}

}
