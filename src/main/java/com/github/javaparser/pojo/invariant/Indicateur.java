/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation
 * �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.affaire.model;

import java.io.Serializable;

import org.apache.commons.lang.BooleanUtils;

public class Indicateur implements Serializable {

    /**
     * Identifiant pour le contr�le de version
     */
    private static final long serialVersionUID = 4214772341883014338L;

    /**
     * Cet indicateur pr�cise que l'affaire pr�sente un int�r�t particulier (soit en fonction des personnes concern�e,
     * affaire sensible ...). La valorisation de cet indicateur est laiss�e � l'initiative de l'utilisateur. Ne
     * d�clenche aucune confidentialit� particuli�re, m�me si valoris�e � Vrai. Saisi par l'utilisateur.
     */
    private Boolean affaireSignalee;

    /**
     * Cet indicateur (saisi par l'utilisateur) pr�cise si une affaire est cach�e c'est-�-dire confidentielle. S'il est
     * valoris� � Vrai, il d�clenche une confidentialit� transversale sp�cifique sur cette affaire.
     */
    private Boolean affaireCachee;

    /**
     * Cet indicateur pr�cise si l'affaire pr�sente un int�r�t historique. Saisi par l'utilisateur, il est � destination
     * des chercheurs des archives d�partementales.
     */
    private Boolean interetHistorique;

    /**
     * Cet indicateur (saisi par l'utilisateur) signale la pr�sence d'objets volumineux ou d'une nature particuli�re
     * (v�hicule, bateau, collection de tableaux...) dans cette affaire.
     */
    private Boolean gardiennage;

    /**
     * C'est un indicateur saisi par l'utilisateur, pr�cisant que des objets ou autres ont �t� saisis et plac�s sous
     * scell�s au cours d'une proc�dure sans avoir de d�p�t associ� (pas de lien avec indicateur objet dans affaire de
     * AFFAIRE)
     */

    private Boolean scellePv;

    /**
     * indicateur S'il y a des scell�s dans le module Scell�s ou dans l'application SPARK
     */
    private Integer scellesDansModuleScellesOuSpark;

    /**
     * Cet indicateur pr�cise si l'affaire est forafaitis�e ou pas.
     */
    private Boolean affaireForfaitisee;
    
    //[DEI-041] EIA-Spark : � Scell�s dans l'affaire �
    /**
     * indicateur S'il y a des scell�s dans SPARK Archives
     */
    private boolean presenceScellesDansAffaire;
    // Debut : 0136640: [AMI_090] Ajouts indicateurs - Affaires PNAT et Affaires PNF (1/2)
    /**
     * Cet indicateur pr�cise si l'affaire est li�e au Parquet National Antiterroriste
     */
    private boolean suivieParLePNAT;
    
    /**
     * Cet indicateur pr�cise si l'affaire est li�e au Parquet National Financier
     */
    private boolean suivieParLePNF;
    // Fin : 0136640: [AMI_090] Ajouts indicateurs - Affaires PNAT et Affaires PNF (1/2)
    //DEBUT:  0146104: [DEI_1006] (LPJ) Cr�ation des services autonomes de type parquet PNAT et PNF dans l'applicatif m�tier Cassiop�e
    /**
     * Cet indicateur pr�cise si l'affaire PNAT EST accessible ou non
     */
    private boolean accesAffairePNAT;
    
    /**
     * Cet indicateur pr�cise si l'affaire PNF EST accessible ou non
     */
    private boolean accesAffairePNF;
    //FIN: 0146104: [DEI_1006] (LPJ) Cr�ation des services autonomes de type parquet PNAT et PNF dans l'applicatif m�tier Cassiop�e
    

    /**
    * Cet indicateur pr�cise si l'affaire est CSS ou pas.
    */
    private String affaireTraitementCSS;

    /**
    * Evo 1014 : Cet indicateur pr�cise si l'affaire fait l'objet d'un MAEE en tant que source
    */
    private Boolean affaireMAEE_S;

    /**
    * Evo 1014 : Cet indicateur pr�cise si l'affaire fait l'objet d'un MAEE en tant que extension
    */
    private Boolean affaireMAEE_E;

 	public String getAffaireTraitementCSS() {
		return affaireTraitementCSS;
	}

	public void setAffaireTraitementCSS(String affaireTraitementCSS) {
		this.affaireTraitementCSS = affaireTraitementCSS;
	}

	//[DEI-041] EIA-Spark : � Scell�s dans l'affaire �
    public boolean getPresenceScellesDansAffaire() {
		return presenceScellesDansAffaire;
	}

    //[DEI-041] EIA-Spark : � Scell�s dans l'affaire �
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
	// Debut : 0146104: [DEI_1006] (LPJ) Cr�ation des services autonomes de type parquet PNAT et PNF dans l'applicatif m�tier Cassiop�e
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
	//FIN: 0146104: [DEI_1006] (LPJ) Cr�ation des services autonomes de type parquet PNAT et PNF dans l'applicatif m�tier Cassiop�e

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
