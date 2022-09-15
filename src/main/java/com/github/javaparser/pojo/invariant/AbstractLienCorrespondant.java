/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.io.Serializable;

import fr.gouv.justice.cassiopee.administration.acteur.externe.model.PersonneQualifiee;
import fr.gouv.justice.cassiopee.administration.acteur.interne.model.Agent;
import fr.gouv.justice.cassiopee.common.exception.TechnicalException;
import fr.gouv.justice.cassiopee.referentiel.structure.model.ElementStructure;
import fr.gouv.justice.cassiopee.referentiel.structure.model.TypeElementStructure;

/**
 * base des liens destinataires et liens emetteurs.<br/>
 * mutualise des propriètés comme entre lien emetteur et lien destinataire.<br/>
 *
 * @param <C>
 *            emetteur ou destinataire
 */
@SuppressWarnings("nls")
public abstract class AbstractLienCorrespondant<C extends Correspondant> implements Serializable {

    /**
     * typeElementStructure representé par ce lien, information toujours renseigné.<br/>
     * si les autres attribut ne sont pas renseigné, ce lien est un typeElementStructure.<br/>
     */
    private TypeElementStructure typeElementStructure;

    /**
     * si le lien represente un elementStructure ce champ est renseigné, exclusif.<br/>
     */
    private ElementStructure elementStructure;

    /**
     * si le lien represente une personneQualifiee ce champ est renseigné, exclusif.<br/>
     */
    private PersonneQualifiee personneQualifiee;

    /**
     * si le lien represente une agent ce champ est renseigné, exclusif.<br/>
     */
    private Agent agent;

    /**
     * @return Returns the agent.
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * @param agent
     *            The agent to set.
     */
    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    /**
     * Retourne le typeElementStructure, qui est toujours renseigné. Il s'agit du TES du correspondant à la création de
     * l'événement.
     *
     * @return retourne typeElementStructure.
     */
    public TypeElementStructure getTypeElementStructure() {
        return typeElementStructure;
    }

    /**
     * @param typeElementStructure
     *            affecte typeElementStructure
     */
    public void setTypeElementStructure(TypeElementStructure typeElementStructure) {
        this.typeElementStructure = typeElementStructure;
    }

    /**
     * @return retourne elementStructure.
     */
    public ElementStructure getElementStructure() {
        return elementStructure;
    }

    /**
     * @param elementStructure
     *            affecte elementStructure
     */
    public void setElementStructure(ElementStructure elementStructure) {
        this.elementStructure = elementStructure;
    }

    /**
     * @return retourne personneQualifiee.
     */
    public PersonneQualifiee getPersonneQualifiee() {
        return personneQualifiee;
    }

    /**
     * @param personneQualifiee
     *            affecte personneQualifiee
     */
    public void setPersonneQualifiee(PersonneQualifiee personneQualifiee) {
        this.personneQualifiee = personneQualifiee;
    }

    /**
     * Indique si le correspondant est un Element de Structure. Cette méthode est nommée avec un article afin d'éviter
     * toute confusion avec un getter.
     *
     * @return true si le LienEmetteur pointe vers un Element de Structure, false sinon.
     */
    public boolean estUnElementStructure() {
        return (this.getElementStructure() != null);
    }

    /**
     * Indique si le correspondant est une personne qualifiée (de type ActeurExterne). Cette méthode est nommée avec un
     * article afin d'éviter toute confusion avec un getter.
     *
     * @return true si le LienEmetteur pointe vers une personne qualifiée, false sinon.
     */
    public boolean estUnePersonneQualifiee() {
        return (this.getPersonneQualifiee() != null);
    }

    /**
     * Indique si l'émetteur est un agent (de type UtilisateurService). Cette méthode est nommée avec un article afin
     * d'éviter toute confusion avec un getter.
     *
     * @return true si le LienEmetteur pointe vers une personne qualifiée, false sinon.
     */
    public boolean estUnAgent() {
        return (this.getAgent() != null && this.getAgent().getUtilisateur() != null);
    }

    /**
     * Indique si l'émetteur est un type de pesonne de type TypeElementStructure pour les affaires contre X. Cette
     * méthode est nommée avec un article afin d'éviter toute confusion avec un getter.
     *
     * @return true si il s'agit d'un typeElementStructure : affaire contre X
     */
    public boolean estUnTypeElementStructure() {
        if (estUnAgent())
            return false;
        if (estUnElementStructure())
            return false;
        if (estUnePersonneQualifiee())
            return false;

        return true;
    }

    /**
     * retourne l'objet correspondant (méthode de convenance générique)
     *
     * @return correspondant
     */
    @SuppressWarnings("unchecked")
    public C getCorrespondant() {
        if (estUnAgent())
            // retourne agent
            return (C) this.getAgent();

        if (estUnElementStructure())
            // retourne elementStructure
            return (C) this.getElementStructure();

        if (estUnePersonneQualifiee())
            // retourne personneQualifiee
            return (C) this.getPersonneQualifiee();

        if (estUnTypeElementStructure())
            // retourne typeElementStructure
            return (C) this.getTypeElementStructure();

        // exeception technique si le type n'est reconnu
        throw new IllegalStateException("ce type de correspondant n'est pas valide : " + this.toString());
    }

    /**
     * affecte l'objet correspondat (méthode de convenance générique)
     *
     * @param correspondant
     */
    public void setCorrespondant(C correspondant) {
        // mise à blanc de tous les champs, sauf typeElementStructure
        // cette action est nécessaire pour gerer le changement de type de
        // correspondant
        miseABlanc();

        // si le correspondant est un Agent, on renseigne agent
        if (correspondant instanceof Agent) {
            this.agent = (Agent) correspondant;
            return;
        }

        // si le correspondant est un ElementStructure, on renseigne
        // elementStructure
        if (correspondant instanceof ElementStructure) {
            this.elementStructure = (ElementStructure) correspondant;
            return;
        }

        // si le correspondant est une PersonneQualifiee, on renseigne
        // personneQualifiee
        if (correspondant instanceof PersonneQualifiee) {
            this.personneQualifiee = (PersonneQualifiee) correspondant;
            return;
        }

        // si le correspondant est un TypeElementStructure, on renseigne
        // typeElementStructure (information sur le typeElementStructure est
        // écrasé)
        if (correspondant instanceof TypeElementStructure) {
            this.typeElementStructure = (TypeElementStructure) correspondant;
            return;
        }

        // exeception technique si le type n'est reconnu
        throw new TechnicalException("ce type de correspondant n'est pas valide : " + this.toString());
    }

    protected void miseABlanc() {
        // mise à blanc de tous les champs, sauf typeElementStructure
        // cette action est nécessaire pour gerer le changement de type de
        // correspondant
        this.agent = null;
        this.elementStructure = null;
        this.personneQualifiee = null;
    }

}
