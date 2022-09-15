package fr.gouv.justice.cassiopee.invariant.edition.model;

import java.util.Date;

/**
 * La classe demande d'edition represente une demande d'edition d'une liste d'ecran. Cet objet est persistant.
 *
 * @author afe
 */

public class EditionDemandeDiffereeListesEcrans extends EditionDemandeDifferee {

    /** debut intervalle de dates qui definit la date de debuts des audiences, echeancier,.. */
    private Date intervalleDateDebut;

    /** Fin intervalle de dates qui definit la date de fin d'echeancier,.. */
    private Date intervalleDateFin;

    /** echelle pour les audiences a editer */
    private Integer nbreEchelle;

    /** libelle echelle pour les audiences a editer */
    private String libelleEchelle;

    /** code specialite pour les audiences a editer */
    private String codeSpecialite;

    /** code de la juridiction pour les audiences a editer */
    private String codeJuridiction;

    /** l'id des services pour les audiences a editer */
    private String listServiceId;

    /** type de formation pour les audiences a editer */
    private String typeFormation;

    /** filtre pour les audiences a editer */
    private Boolean audienceNonPleine;

    /** ensemble de criteres utlises pour certains demandes (echeancier, recherche,...) */
    private String criteres;

    /** ensemble des resultats de la recherche utlises pour certains demandes (recherche personne, mineur, decision...) */
    private String resultatsRecherche;

    /**
     * retourne le debut de l'intervalle de dates qui definit les objets a editer
     *
     * @return le debut de l'intervalle de dates qui definit les objets a editer
     */
    public Date getIntervalleDateDebut() {
        return intervalleDateDebut;
    }

    /**
     * renseigne le debut de l'intervalle de dates qui definit les objets a editer
     *
     * @param intervalleDateDebut
     *            le debut de l'intervalle de dates qui definit les objets a editer
     */
    public void setIntervalleDateDebut(Date intervalleDateDebut) {
        this.intervalleDateDebut = intervalleDateDebut;
    }

    /**
     * renseigne la fin de l'intervalle de dates qui definit les objets a editer
     *
     * @param intervalleDateDebut
     *            la fin de l'intervalle de dates qui definit les objets a editer
     */
    public void setIntervalleDateFin(Date intervalleDateFin) {
        this.intervalleDateFin = intervalleDateFin;
    }

    /**
     * retourne la fin de l'intervalle de dates qui definit les objets a editer
     *
     * @return la fin de l'intervalle de dates qui definit les objets a editer
     */
    public Date getIntervalleDateFin() {
        return intervalleDateFin;
    }

    /**
     * @return Returns the nbreEchelle.
     */
    public Integer getNbreEchelle() {
        return nbreEchelle;
    }

    /**
     * @param nbreEchelle
     *            The nbreEchelle to set.
     */
    public void setNbreEchelle(Integer nbreEchelle) {
        this.nbreEchelle = nbreEchelle;
    }

    /**
     * @return Returns the libelleEchelle.
     */
    public String getLibelleEchelle() {
        return libelleEchelle;
    }

    /**
     * @param string
     *            The libelleEchelle to set.
     */
    public void setLibelleEchelle(String libelleEchelle) {
        this.libelleEchelle = libelleEchelle;
    }

    /**
     * @return Returns the audienceNonPleine.
     */
    public Boolean getAudienceNonPleine() {
        return audienceNonPleine;
    }

    /**
     * @param audienceNonPleine
     *            The audienceNonPleine to set.
     */
    public void setAudienceNonPleine(Boolean audienceNonPleine) {
        this.audienceNonPleine = audienceNonPleine;
    }

    /**
     * @return Returns the codeSpecialite.
     */
    public String getCodeSpecialite() {
        return codeSpecialite;
    }

    /**
     * @param codeSpecialite
     *            The codeSpecialite to set.
     */
    public void setCodeSpecialite(String codeSpecialite) {
        this.codeSpecialite = codeSpecialite;
    }

    /**
     * @return Returns the typeFormation.
     */
    public String getTypeFormation() {
        return typeFormation;
    }

    /**
     * @param typeFormation
     *            The typeFormation to set.
     */
    public void setTypeFormation(String typeFormation) {
        this.typeFormation = typeFormation;
    }

    /**
     * @return Returns the chambreCabinet.
     */
    public String getListServiceId() {
        return listServiceId;
    }

    /**
     * @param listServiceId
     *            The serviceId to set.
     */
    public void setListServiceId(String listServiceId) {
        this.listServiceId = listServiceId;
    }

    /**
     * @return Returns the codeJuridiction.
     */
    public String getCodeJuridiction() {
        return codeJuridiction;
    }

    /**
     * @param codeJuridiction
     *            The codeJuridiction to set.
     */
    public void setCodeJuridiction(String codeJuridiction) {
        this.codeJuridiction = codeJuridiction;
    }

    /**
     * @return Returns the criteres.
     */
    public String getCriteres() {
        return criteres;
    }

    /**
     * @param criteres
     *            The criteres to set.
     */
    public void setCriteres(String criteres) {
        this.criteres = criteres;
    }

    /**
     * @return Returns the resultatsRecherche.
     */
    public String getResultatsRecherche() {
        return resultatsRecherche;
    }

    /**
     * @param resultatsRecherche
     *            The resultatsRecherche to set.
     */
    public void setResultatsRecherche(String resultatsRecherche) {
        this.resultatsRecherche = resultatsRecherche;
    }

    @Override
    public EditionDemandeDiffereeEnum getEditionDemandeDiffereeEnum() {
        return EditionDemandeDiffereeEnum.EDITION_DEMANDE_DIFFEREE_LISTES_ECRANS;
    }

}
