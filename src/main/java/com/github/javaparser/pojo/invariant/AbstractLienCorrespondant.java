/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
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
 * mutualise des propri�t�s comme entre lien emetteur et lien destinataire.<br/>
 *
 * @param <C>
 *            emetteur ou destinataire
 */
@SuppressWarnings("nls")
public abstract class AbstractLienCorrespondant<C extends Correspondant> implements Serializable {

    /**
     * typeElementStructure represent� par ce lien, information toujours renseign�.<br/>
     * si les autres attribut ne sont pas renseign�, ce lien est un typeElementStructure.<br/>
     */
    private TypeElementStructure typeElementStructure;

    /**
     * si le lien represente un elementStructure ce champ est renseign�, exclusif.<br/>
     */
    private ElementStructure elementStructure;

    /**
     * si le lien represente une personneQualifiee ce champ est renseign�, exclusif.<br/>
     */
    private PersonneQualifiee personneQualifiee;

    /**
     * si le lien represente une agent ce champ est renseign�, exclusif.<br/>
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
     * Retourne le typeElementStructure, qui est toujours renseign�. Il s'agit du TES du correspondant � la cr�ation de
     * l'�v�nement.
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
     * Indique si le correspondant est un Element de Structure. Cette m�thode est nomm�e avec un article afin d'�viter
     * toute confusion avec un getter.
     *
     * @return true si le LienEmetteur pointe vers un Element de Structure, false sinon.
     */
    public boolean estUnElementStructure() {
        return (this.getElementStructure() != null);
    }

    /**
     * Indique si le correspondant est une personne qualifi�e (de type ActeurExterne). Cette m�thode est nomm�e avec un
     * article afin d'�viter toute confusion avec un getter.
     *
     * @return true si le LienEmetteur pointe vers une personne qualifi�e, false sinon.
     */
    public boolean estUnePersonneQualifiee() {
        return (this.getPersonneQualifiee() != null);
    }

    /**
     * Indique si l'�metteur est un agent (de type UtilisateurService). Cette m�thode est nomm�e avec un article afin
     * d'�viter toute confusion avec un getter.
     *
     * @return true si le LienEmetteur pointe vers une personne qualifi�e, false sinon.
     */
    public boolean estUnAgent() {
        return (this.getAgent() != null && this.getAgent().getUtilisateur() != null);
    }

    /**
     * Indique si l'�metteur est un type de pesonne de type TypeElementStructure pour les affaires contre X. Cette
     * m�thode est nomm�e avec un article afin d'�viter toute confusion avec un getter.
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
     * retourne l'objet correspondant (m�thode de convenance g�n�rique)
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
     * affecte l'objet correspondat (m�thode de convenance g�n�rique)
     *
     * @param correspondant
     */
    public void setCorrespondant(C correspondant) {
        // mise � blanc de tous les champs, sauf typeElementStructure
        // cette action est n�cessaire pour gerer le changement de type de
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
        // �cras�)
        if (correspondant instanceof TypeElementStructure) {
            this.typeElementStructure = (TypeElementStructure) correspondant;
            return;
        }

        // exeception technique si le type n'est reconnu
        throw new TechnicalException("ce type de correspondant n'est pas valide : " + this.toString());
    }

    protected void miseABlanc() {
        // mise � blanc de tous les champs, sauf typeElementStructure
        // cette action est n�cessaire pour gerer le changement de type de
        // correspondant
        this.agent = null;
        this.elementStructure = null;
        this.personneQualifiee = null;
    }

}
