/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
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
 * Requête en rectification : La requête peut être en attente de rectification ou accordée. 1) Pour une requête en
 * attente de validation, on conserve les valeurs corrigées d'un événement en attendant la validation d'une requête en
 * rectification en erreur matérielle. Cette informations sont gardées jusqu'à ce que la rectification soit accordée. 2)
 * Pour une requête accordée, on conserve les valeurs initiales suite à une requête en rectification sur erreur
 * matérielle pour un événement. Les attributs sont renseignés avec les valeurs obsolètes de l'infraction une fois la
 * rectification accordée.
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
     * @return un Long représentant l'identifiant de cet événement.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @param id
     *            l'identifiant à valoriser. Ne devrait être utilisé que par Hibernate.
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
     * @return la Date à laquelle la requête en modification a été faite ou accordée.
     */
    public Date getDateRequete() {
        return this.dateRequete;
    }

    /**
     * Valorise la Date à laquelle la requête en modificaiton a été faite ou accordée.
     *
     * @param dateRequete
     *            la Date voulue.
     */
    public void setDateRequete(Date dateRequete) {
        this.dateRequete = dateRequete;
    }

    /**
     * Renvoie la Date de l'événement: ancienne valeur remplacée ou nouvelle valeur proposée.
     *
     * @return la Date en question.
     */
    public Date getDateEvenement() {
        return this.dateEvenement;
    }

    /**
     * Valorise la Date de l'événement: ancienne valeur remplacée ou nouvelle valeur proposée.
     *
     * @param dateEvenement
     *            la Date en question.
     */
    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    /**
     * Renvoie la composition de l'événement: ancienne valeur remplacée ou nouvelle valeur proposée.
     *
     * @return un String représentant la composition corrigée ou proposée.
     */
    public String getComposition() {
        return this.composition;
    }

    /**
     * @param composition
     *            un String représentant la composition corrigée ou proposée.
     */
    public void setComposition(String composition) {
        this.composition = composition;
    }

    /**
     * @return le code correspondant à l'état de la requête.
     */
    public EtatRequete getEtatRequete() {
        return etatRequete;
    }

    /**
     * Valorise le code correspondant à l'état de la requête.
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
     * Crée une copie de cette Requete, sans Id et sans événement lié.
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
