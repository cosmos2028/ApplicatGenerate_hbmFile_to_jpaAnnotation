package com.github.javaparser.pojoAnnote;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

/*
 * la class actualite represente dans la BDD une actualite dans le monde reel
 */
@Entity
@Table(name = "PAR_ACTUALITE", schema = "SCH_ACTUALITE", catalog = "CAT_ACTUALITE")
public class Actualite extends Object implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /*
	 * id qui va servir de clé primaire dans la BDD
	 */
    @Id
    @Column(name = "ACTUALITE_ID", scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    private Long id;

    // Titre de l'actualite
    @Column(name = "TITRE", length = 100)
    private String titre;

    @Column(name = "TEXTE", length = 700)
    private String texte;

    @Column(name = "DATE_DEBUT")
    private LocalDateTime dateDebut;

    @Column(name = "DATE_FIN")
    private LocalDateTime dateFin;

    @Column(name = "FLASH", precision = 1, scale = 0)
    private boolean flash;

    @Column(name = "NOMBRE_REPETITION", precision = 1, scale = 0)
    private Integer nombreRepetition;

    @Column(name = "DOC_LIE_TITRE", length = 100)
    private String documentLieTitre;

    @Column(name = "DOC_LIE_URL", length = 200)
    private String documentLieUrl;

    public Actualite() {
    }

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
