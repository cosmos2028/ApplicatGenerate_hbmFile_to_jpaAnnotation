/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation
 * écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.affaire.model;

import java.io.Serializable;

import org.apache.commons.lang.BooleanUtils;

public class Indicateur implements Serializable {

    /**
     * Identifiant pour le contrôle de version
     */
    private static final long serialVersionUID = 4214772341883014338L;

    /**
     * Cet indicateur précise que l'affaire présente un intérêt particulier (soit en fonction des personnes concernée,
     * affaire sensible ...). La valorisation de cet indicateur est laissée à l'initiative de l'utilisateur. Ne
     * déclenche aucune confidentialité particulière, même si valorisée à Vrai. Saisi par l'utilisateur.
     */
    private Boolean affaireSignalee;

    /**
     * Cet indicateur (saisi par l'utilisateur) précise si une affaire est cachée c'est-à-dire confidentielle. S'il est
     * valorisé à Vrai, il déclenche une confidentialité transversale spécifique sur cette affaire.
     */
    private Boolean affaireCachee;

    /**
     * Cet indicateur précise si l'affaire présente un intérêt historique. Saisi par l'utilisateur, il est à destination
     * des chercheurs des archives départementales.
     */
    private Boolean interetHistorique;

    /**
     * Cet indicateur (saisi par l'utilisateur) signale la présence d'objets volumineux ou d'une nature particulière
     * (véhicule, bateau, collection de tableaux...) dans cette affaire.
     */
    private Boolean gardiennage;

    /**
     * C'est un indicateur saisi par l'utilisateur, précisant que des objets ou autres ont été saisis et placés sous
     * scellés au cours d'une procédure sans avoir de dépôt associé (pas de lien avec indicateur objet dans affaire de
     * AFFAIRE)
     */

    private Boolean scellePv;

    /**
     * indicateur S'il y a des scellés dans le module Scellés ou dans l'application SPARK
     */
    private Integer scellesDansModuleScellesOuSpark;

    /**
     * Cet indicateur précise si l'affaire est forafaitisée ou pas.
     */
    private Boolean affaireForfaitisee;
    
    //[DEI-041] EIA-Spark : « Scellés dans l'affaire »
    /**
     * indicateur S'il y a des scellés dans SPARK Archives
     */
    private boolean presenceScellesDansAffaire;
    // Debut : 0136640: [AMI_090] Ajouts indicateurs - Affaires PNAT et Affaires PNF (1/2)
    /**
     * Cet indicateur précise si l'affaire est liée au Parquet National Antiterroriste
     */
    private boolean suivieParLePNAT;
    
    /**
     * Cet indicateur précise si l'affaire est liée au Parquet National Financier
     */
    private boolean suivieParLePNF;
    // Fin : 0136640: [AMI_090] Ajouts indicateurs - Affaires PNAT et Affaires PNF (1/2)
    //DEBUT:  0146104: [DEI_1006] (LPJ) Création des services autonomes de type parquet PNAT et PNF dans l'applicatif métier Cassiopée
    /**
     * Cet indicateur précise si l'affaire PNAT EST accessible ou non
     */
    private boolean accesAffairePNAT;
    
    /**
     * Cet indicateur précise si l'affaire PNF EST accessible ou non
     */
    private boolean accesAffairePNF;
    //FIN: 0146104: [DEI_1006] (LPJ) Création des services autonomes de type parquet PNAT et PNF dans l'applicatif métier Cassiopée
    

    /**
    * Cet indicateur précise si l'affaire est CSS ou pas.
    */
    private String affaireTraitementCSS;

    /**
    * Evo 1014 : Cet indicateur précise si l'affaire fait l'objet d'un MAEE en tant que source
    */
    private Boolean affaireMAEE_S;

    /**
    * Evo 1014 : Cet indicateur précise si l'affaire fait l'objet d'un MAEE en tant que extension
    */
    private Boolean affaireMAEE_E;

 	public String getAffaireTraitementCSS() {
		return affaireTraitementCSS;
	}

	public void setAffaireTraitementCSS(String affaireTraitementCSS) {
		this.affaireTraitementCSS = affaireTraitementCSS;
	}

	//[DEI-041] EIA-Spark : « Scellés dans l'affaire »
    public boolean getPresenceScellesDansAffaire() {
		return presenceScellesDansAffaire;
	}

    //[DEI-041] EIA-Spark : « Scellés dans l'affaire »
	public void setPresenceScellesDansAffaire(boolean presenceScellesDansAffaire) {
		this.presenceScellesDansAffaire = presenceScellesDansAffaire;
	}

	public Boolean getAffaireForfaitisee() {
		return affaireForfaitisee;
	}

	public void setAffaireForfaitisee(Boolean affaireForfaitisee) {
		this.affaireForfaitisee = affaireForfaitisee;
	}
	
    public Boolean getAffaireCachee() {
        return affaireCachee;
    }

    public void setAffaireCachee(Boolean affaireCachee) {
        this.affaireCachee = affaireCachee;
    }

    public Boolean getAffaireSignalee() {
        return affaireSignalee;
    }

    public void setAffaireSignalee(Boolean affaireSignalee) {
        this.affaireSignalee = affaireSignalee;
    }

    public Boolean getGardiennage() {
        return gardiennage;
    }

    public void setGardiennage(Boolean gardiennage) {
        this.gardiennage = gardiennage;
    }

    public Boolean getInteretHistorique() {
        return interetHistorique;
    }

    public void setInteretHistorique(Boolean interetHistorique) {
        this.interetHistorique = interetHistorique;
    }

    public Boolean getScellePv() {
        return scellePv;
    }

    public void setScellePv(Boolean scellePv) {
        this.scellePv = scellePv;
    }

    public Integer getScellesDansModuleScellesOuSpark() {
        return scellesDansModuleScellesOuSpark;
    }

    public void setScellesDansModuleScellesOuSpark(Integer scellesDansModuleScellesOuSpark) {
        this.scellesDansModuleScellesOuSpark = scellesDansModuleScellesOuSpark;
    }
	// Debut : 0136640: [AMI_090] Ajouts indicateurs - Affaires PNAT et Affaires PNF (1/2)
	/**
	 * @return the suivieParLePNAT
	 */
	public boolean getSuivieParLePNAT() {
		return suivieParLePNAT;
	}

	/**
	 * @param suivieParLePNAT the suivieParLePNAT to set
	 */
	public void setSuivieParLePNAT(boolean suivieParLePNAT) {
		this.suivieParLePNAT = suivieParLePNAT;
	}

	/**
	 * @return the suivieParLePNF
	 */
	public boolean getSuivieParLePNF() {
		return suivieParLePNF;
	}

	/**
	 * @param suivieParLePNF the suivieParLePNF to set
	 */
	public void setSuivieParLePNF(boolean suivieParLePNF) {
		this.suivieParLePNF = suivieParLePNF;
	}
	// Fin : 0136640: [AMI_090] Ajouts indicateurs - Affaires PNAT et Affaires PNF (1/2)
	// Debut : 0146104: [DEI_1006] (LPJ) Création des services autonomes de type parquet PNAT et PNF dans l'applicatif métier Cassiopée
	public boolean getAccesAffairePNAT() {
		return accesAffairePNAT;
	}

	public void setAccesAffairePNAT(boolean accesAffairePNAT) {
		this.accesAffairePNAT = accesAffairePNAT;
	}

	public boolean getAccesAffairePNF() {
		return accesAffairePNF;
	}

	public void setAccesAffairePNF(boolean accesAffairePNF) {
		this.accesAffairePNF = accesAffairePNF;
	}
	//FIN: 0146104: [DEI_1006] (LPJ) Création des services autonomes de type parquet PNAT et PNF dans l'applicatif métier Cassiopée

	public Boolean getAffaireMAEE_S() {
		return BooleanUtils.toBoolean(affaireMAEE_S);
	}

	public void setAffaireMAEE_S(Boolean affaireMAEE_S) {
		this.affaireMAEE_S = affaireMAEE_S;
	}

	public Boolean getAffaireMAEE_E() {
		return BooleanUtils.toBoolean(affaireMAEE_E);
	}

	public void setAffaireMAEE_E(Boolean affaireMAEE_E) {
		this.affaireMAEE_E = affaireMAEE_E;
	}

}
