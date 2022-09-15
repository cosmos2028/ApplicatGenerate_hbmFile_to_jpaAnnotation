/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import fr.gouv.justice.cassiopee.invariant.personneevenement.model.RequetePev;
import fr.gouv.justice.cassiopee.processus.execution.model.EtatRequete;
import fr.gouv.justice.cassiopee.processus.execution.service.enumeration.EtatRequeteEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.EnumerationValue;

/**
 * Requ�te en rectification : La requ�te peut �tre en attente de rectification ou accord�e. 1) Pour une requ�te en
 * attente de validation, on conserve les valeurs corrig�es d'un �v�nement en attendant la validation d'une requ�te en
 * rectification en erreur mat�rielle. Cette informations sont gard�es jusqu'� ce que la rectification soit accord�e. 2)
 * Pour une requ�te accord�e, on conserve les valeurs initiales suite � une requ�te en rectification sur erreur
 * mat�rielle pour un �v�nement. Les attributs sont renseign�s avec les valeurs obsol�tes de l'infraction une fois la
 * rectification accord�e.
 */
public class EvenementRequete implements java.io.Serializable {

    private static final long serialVersionUID = 1679293333844228630L;

    private Long id;

    private Evenement evenement;

    protected String codeEtatRequete;

    private EtatRequete etatRequete;

    private Date dateRequete;

    private Date dateEvenement;

    private EnumerationValue publiciteDebats;

    private EnumerationValue formation;

    private EnumerationValue ressortJugement;

    private String composition;

    private Set<RequetePev> personneEvenementRequetes = new HashSet<RequetePev>();

    /**
     * Constructeur sans argument.
     */
    public EvenementRequete() {
        super();
    }

    /**
     * @return un Long repr�sentant l'identifiant de cet �v�nement.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @param id
     *            l'identifiant � valoriser. Ne devrait �tre utilis� que par Hibernate.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param etat
     */
    public void setEtat(EtatRequeteEnum etat) {
        this.codeEtatRequete = etat.getCode();
    }

    /**
     * @return etat
     */
    public EtatRequeteEnum getEtat() {
        return EtatRequeteEnum.resolve(this.codeEtatRequete);
    }

    /**
     * @return la Date � laquelle la requ�te en modification a �t� faite ou accord�e.
     */
    public Date getDateRequete() {
        return this.dateRequete;
    }

    /**
     * Valorise la Date � laquelle la requ�te en modificaiton a �t� faite ou accord�e.
     *
     * @param dateRequete
     *            la Date voulue.
     */
    public void setDateRequete(Date dateRequete) {
        this.dateRequete = dateRequete;
    }

    /**
     * Renvoie la Date de l'�v�nement: ancienne valeur remplac�e ou nouvelle valeur propos�e.
     *
     * @return la Date en question.
     */
    public Date getDateEvenement() {
        return this.dateEvenement;
    }

    /**
     * Valorise la Date de l'�v�nement: ancienne valeur remplac�e ou nouvelle valeur propos�e.
     *
     * @param dateEvenement
     *            la Date en question.
     */
    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    /**
     * Renvoie la composition de l'�v�nement: ancienne valeur remplac�e ou nouvelle valeur propos�e.
     *
     * @return un String repr�sentant la composition corrig�e ou propos�e.
     */
    public String getComposition() {
        return this.composition;
    }

    /**
     * @param composition
     *            un String repr�sentant la composition corrig�e ou propos�e.
     */
    public void setComposition(String composition) {
        this.composition = composition;
    }

    /**
     * @return le code correspondant � l'�tat de la requ�te.
     */
    public EtatRequete getEtatRequete() {
        return etatRequete;
    }

    /**
     * Valorise le code correspondant � l'�tat de la requ�te.
     *
     * @param etatRequete
     */
    public void setEtatRequete(EtatRequete etatRequete) {
        this.etatRequete = etatRequete;
    }

    public EnumerationValue getFormation() {
        return formation;
    }

    public void setFormation(EnumerationValue formation) {
        this.formation = formation;
    }

    public EnumerationValue getPubliciteDebats() {
        return publiciteDebats;
    }

    public void setPubliciteDebats(EnumerationValue publiciteDebats) {
        this.publiciteDebats = publiciteDebats;
    }

    public EnumerationValue getRessortJugement() {
        return ressortJugement;
    }

    public void setRessortJugement(EnumerationValue ressortJugement) {
        this.ressortJugement = ressortJugement;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    /**
     * @return retourne personneEvenementRequetes.
     */
    public Set<RequetePev> getPersonneEvenementRequetes() {
        return personneEvenementRequetes;
    }

    /**
     * @param personneEvenementRequetes
     *            affecte personneEvenementRequetes
     */
    public void setPersonneEvenementRequetes(Set<RequetePev> personneEvenementRequetes) {
        this.personneEvenementRequetes = personneEvenementRequetes;
    }

    /**
     * Cr�e une copie de cette Requete, sans Id et sans �v�nement li�.
     *
     * @throws CloneNotSupportedException
     */
    public EvenementRequete clone() throws CloneNotSupportedException {
        EvenementRequete cloneRequete = (EvenementRequete) super.clone();
        cloneRequete.setEtatRequete(this.etatRequete);
        cloneRequete.setFormation(this.formation);
        cloneRequete.setPubliciteDebats(this.publiciteDebats);
        cloneRequete.setRessortJugement(this.ressortJugement);
        cloneRequete.setComposition(this.composition);
        cloneRequete.setDateEvenement(this.dateEvenement);
        cloneRequete.setDateRequete(this.dateRequete);
        return cloneRequete;
    }
}
