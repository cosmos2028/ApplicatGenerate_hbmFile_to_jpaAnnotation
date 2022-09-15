package com.github.javaparser.pojo;

import java.util.Map;

public class Dictionnaire {
	
	private Map<String, String> mapHibernate; //= new HashMap<>()
	private Map<String, String> mapClasse;
	private Map<String, String> mapId;
	private Map<String, String> mapGenerator;
	private Map<String, String> mapCompositeId;
	private Map<String, String> mapDiscriminator;
	private Map<String, String> mapVersion;
	private Map<String, String> mapTimestamp;
	private Map<String, String> mapProperty;
	private Map<String, String> mapManyToOne;
	private Map<String, String> mapOneToOne;
	private Map<String, String> mapOneToMany;
	private Map<String, String> mapNaturalId;
	private Map<String, String> mapComponent;
	private Map<String, String> mapProperties;
	private Map<String, String> mapSubclass;
	private Map<String, String> mapJoinedSubclass;
	private Map<String, String> mapUnionSubclass;
	private Map<String, String> mapJoin;
	private Map<String, String> mapKey;
	private Map<String, String> mapColumn;
	private Map<String, String> mapFormula;
	private Map<String, String> mapImport;
	private Map<String, String> mapAny;
	
	public Dictionnaire() {
	}
	
//	public static void Initialize() {
//		mapAttributs.put("schema", "schema");
//		mapAttributs.put("catalog", "schema");
//	}
	
	public void initmapHibernate() {
		
	}
	
//	public static String HibernateMappingAttribut(String xmlString){
//		mapAttributs.put("schema", "schema");
//		mapAttributs.put("catalog", "schema");
//		mapAttributs.put("schema", "schema");
//		mapAttributs.put("schema", "schema");
//		
//		return mapAttributs.get(xmlString);
//	}
	
    public static void main(String[] args) {
    	Dictionnaire lul = new Dictionnaire();
//    	System.out.println(lul.mapAttributs);
	}
	
}
