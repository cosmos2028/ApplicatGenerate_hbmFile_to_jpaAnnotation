package com.github.javaparser.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
 * la class actualite represente dans la BDD une actualite dans le monde reel
 */
public class Actualite extends Object implements Serializable
{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * id qui va servir de clé primaire dans la BDD
	 */
	private Long id;
	//Titre de l'actualite
	private String titre;
	private String texte;
	private LocalDateTime dateDebut;
	private LocalDateTime dateFin;
	private boolean flash;
	private Integer nombreRepetition ;
	private String documentLieTitre;
	private String documentLieUrl;
	
	public Actualite() {}

	public Actualite(String titre, String texte, LocalDateTime dateDebut) {
		super();
		this.titre = titre;
		this.texte = texte;
		this.dateDebut = dateDebut;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDateTime getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDateTime dateFin) {
		this.dateFin = dateFin;
	}

	public boolean isFlash() {
		return flash;
	}

	public void setFlash(boolean flash) {
		this.flash = flash;
	}

	public Integer getNombreRepetition() {
		return nombreRepetition;
	}

	public void setNombreRepetition(Integer nombreRepetition) {
		this.nombreRepetition = nombreRepetition;
	}

	public String getDocumentLieTitre() {
		return documentLieTitre;
	}

	public void setDocumentLieTitre(String documentLieTitre) {
		this.documentLieTitre = documentLieTitre;
	}

	public String getDocumentLieUrl() {
		return documentLieUrl;
	}

	public void setDocumentLieUrl(String documentLieUrl) {
		this.documentLieUrl = documentLieUrl;
	}
	
	
	
	
	

}
