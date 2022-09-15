package poubelle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

public class DictionnaireBrouillon {
//	
//	//Code parfait
//    public static enum HibernateMappingAttribut {
//    	
//    	schema("schema"),
//    	catalog("catalog");
//
//        private final String xmlString;
//
//        HibernateMappingAttribut(String xmlString) {
//            this.xmlString = xmlString;
//        }
//        
//        public String jpaString() {
//            return xmlString;
//        }
//        
//        public static String conversion(String xmlString) {
//        	HibernateMappingAttribut jpa = HibernateMappingAttribut.valueOf(xmlString);
//        	return jpa.jpaString();
//        }
//        
//    }
//	
//    public static void main(String[] args) {
//    	
//    	String xml = "DEFAULT";
//    	System.out.println(HibernateMappingAttribut.conversion(xml));
//	}
//	
//	
//	
//	
//	/*
//	public enum Xml {
//		TATA("cucu"),
//		TOTO("coco");
//		
//		private static final String jpa;
//
//        
//		static {
//			jpa = TATA;
//		}
//		
//        Xml(String jpa) {
//			// TODO Auto-generated constructor stub
////        	this.jpa = jpa;
//		}
//
//
//
//		public static String conversionJpa() {
//            return jpa;
//        }
//        
////        public String test() {
////        	for(Xml valeur : Xml.class.getEnumConstants())
////        		valeur
////            return jpa;
////        }
//        
//	}
//	
////	 On abandonne ceux-là
//	
////	class LOL {
////		private static final String tata = "tutu";
////		
////		public static String conversion() {
////			return tata;
////		}
////		
////	}
////
////	class LUL {
////		private final static String tata = "tutu";
////		private final static String toto = "tete";
////		
////		public static String conversion() {
////			LUL.tata //pas de valueof() donc pas possible de récupérer comme
////			for(String valeur : )
////			return LUL.;
////		}
////		
////	}
//
//	
//
////	public String conv () {
////		HashMap<String, ArrayList<Dictionary>> hmap = new HashMap<String, ArrayList<Dictionary>>();
////		
////        //Loop to through Enum class called Dictionary
////        //Loading in hashmap
////        for (Dictionary dict : Dictionary.values()) {
////
////                //Store keyword using method created in Enum class
////                String keyword = dict.getKeyword();
////
////                //Add values to arrayList based on keyword
////                ArrayList<Dictionary> list = (hmap.containsKey(keyword)) ? hmap.get(keyword) : new ArrayList<Dictionary>();
////                list.add(dict);
////                hmap.put(keyword, list);		
////        }
////		
////	}
//	
//	public static enum Jpa {
//		TITI, TUTU;
//	}
//	
////	public static final EnumMap<Xml, Jpa> DICO;
////	static {
////		EnumMap<Xml, Jpa> correspondances = new EnumMap<>(Xml.class);
////		correspondances.put(Xml.TATA, Jpa.TITI);
////		correspondances.put(Xml.TOTO, Jpa.TUTU);
////	
////	DICO = EnumMap.(correspondances);
////	}
////	
////	public static Jpa conversion(Xml valeur){
////		EnumMap<Xml, Jpa> correspondances = new EnumMap<>(Xml.class);
////		correspondances.put(Xml.TATA, Jpa.TITI);
////		correspondances.put(Xml.TOTO, Jpa.TUTU);
////		return correspondances.get(valeur);
////	}
//	
//	
//    public static enum Keyword {
//
//        DEFAULT("default"),
//        PUBLIC("public"),
//        PROTECTED("protected"),
//        PRIVATE("private"),
//        ABSTRACT("abstract"),
//        STATIC("static"),
//        FINAL("final"),
//        TRANSIENT("transient"),
//        VOLATILE("volatile"),
//        SYNCHRONIZED("synchronized"),
//        NATIVE("native"),
//        STRICTFP("strictfp"),
//        TRANSITIVE("transitive");
//
//        private final String codeRepresentation;
//
//        Keyword(String codeRepresentation) {
//            this.codeRepresentation = codeRepresentation;
//        }
//        
//        public String asString() {
//            return codeRepresentation;
//        }
//    }
//    
//    public static enum Type {
//
//        DEFAULT("default"),
//        PUBLIC("public"),
//        PROTECTED("protected"),
//        PRIVATE("private"),
//        ABSTRACT("abstract"),
//        STATIC("static"),
//        FINAL("final"),
//        TRANSIENT("transient"),
//        VOLATILE("volatile"),
//        SYNCHRONIZED("synchronized"),
//        NATIVE("native"),
//        STRICTFP("strictfp"),
//        TRANSITIVE("transitive");
//
//        private final String codeRepresentation;
//
//        Type(String codeRepresentation) {
//            this.codeRepresentation = codeRepresentation;
//        }
//        
//        public String asString() {
//            return codeRepresentation;
//        }
//    }
//    
//    public enum Civilite {  // dans le fichier Civilite.java  
//    	  
//        MADAME("MME"), MADEMOISELLE("MLLE"), MONSIEUR("MR") ;  
//          
//         private String abreviation ;  
//          
//         private Civilite(String abreviation) {  
//             this.abreviation = abreviation ;  
//        }  
//          
//         public String getAbreviation() {  
//             return  this.abreviation ;  
//        }  
//    }
//    public static void main(String[] args) {
//    	
////    	System.out.println(Keyword.g);
//    	Keyword cacahuete = Keyword.ABSTRACT;
////    	Keyword.ABSTRACT.
//    	String ahah = "ABSTRACT";
//    	System.out.println(cacahuete.asString());
////    	System.out.println(Keyword());
//    	System.out.println(Keyword.DEFAULT+ahah);
//    	
////    	System.out.println(conversion(Xml.TATA));
//    	
////    	new Xml("cucu");
//    	
//    	List<String> lol = new ArrayList<>();
//    	System.out.println(lol.getClass()); // il y a deux choses, la variable lol de type List et son objet de type arraylist
//    	//getclass donne le type de l'objet mais pour obtenir le type de la variable, que faire?
//    	//runtime type vs static type
//    	//Java est un langage à typage rigoureux qui ne possède pas de transtypage automatique lorsque ce transtypage risque de conduire à une perte d'information.
//    	System.out.println(Dictionnaire.LOL.conversion());
//    	
//    	System.out.println("lul");
//    	
//    	Xml couille = Xml.valueOf("TATA");
//    	System.out.println(Xml.);
//    	System.out.println(couille.conversionJpa());
//    	
//    	String sortiexml = "DEFAULT"; 
//    	
//    	Keyword cacahuette = Keyword.valueOf(sortiexml);
//    	System.out.println(cacahuete.asString());
//    	
//    	System.out.println(Keyword.DEFAULT.asString());
//	}
//	*/
}
