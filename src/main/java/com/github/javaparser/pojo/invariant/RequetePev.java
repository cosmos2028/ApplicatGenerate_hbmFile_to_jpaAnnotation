/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.personneevenement.model;

import fr.gouv.justice.cassiopee.invariant.evenement.model.EvenementRequete;
import fr.gouv.justice.cassiopee.processus.execution.model.EtatRequete;
import fr.gouv.justice.cassiopee.processus.execution.model.RequetePersonneEvenementId;
import fr.gouv.justice.cassiopee.processus.execution.service.enumeration.EtatRequeteEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.EnumerationValue;

/**
 * Requ�te en rectification : La requ�te peut �tre en attente de rectification ou accord�e. 1) Pour une requ�te en
 * attente de validation, on conserve les valeurs corrig�es d'un p.ev. en attendant la validation d'une requ�te en
 * rectification en erreur mat�rielle. Cette informations sont gard�es jusqu'� ce que la rectification soit accord�e. 2)
 * Pour une requ�te accord�e, on conserve les valeurs initiales suite � une requ�te en rectification sur erreur
 * mat�rielle pour un �v�nement. Les attributs sont renseign�s avec les valeurs obsol�tes de l'infraction une fois la
 * rectification accord�e.
 */
public class RequetePev implements java.io.Serializable {

    private static final long serialVersionUID = -826111050749064988L;

    private RequetePersonneEvenementId id;

    private PersonneEvenement personneEvenement;

    private EvenementRequete evenementRequete;

    private EnumerationValue modeComparution;

    private EnumerationValue natureJugement;

    private String codeEtatRequete;

    private EtatRequete etatRequete;

    public RequetePersonneEvenementId getId() {
        return this.id;
    }

    public void setId(RequetePersonneEvenementId id) {
        this.id = id;
    }

    /**
     * @return retourne evenementRequete.
     */
    public EvenementRequete getEvenementRequete() {
        return evenementRequete;
    }

    /**
     * @param evenementRequete
     *            affecte evenementRequete
     */
    public void setEvenementRequete(EvenementRequete evenementRequete) {
        this.evenementRequete = evenementRequete;
    }

    public PersonneEvenement getPersonneEvenement() {
        return this.personneEvenement;
    }

    public void setPersonneEvenement(PersonneEvenement personneEvenement) {
        this.personneEvenement = personneEvenement;
    }

    /**
     *
     */
    public EnumerationValue getModeComparution() {
        return this.modeComparution;
    }

    public void setModeComparution(EnumerationValue modeComparution) {
        this.modeComparution = modeComparution;
    }

    /**
     *
     */
    public EnumerationValue getNatureJugement() {
        return this.natureJugement;
    }

    public void setNatureJugement(EnumerationValue natureJugement) {
        this.natureJugement = natureJugement;
    }

    /**
     * @return retourne etatRequete.
     */
    public EtatRequete getEtatRequete() {
        return etatRequete;
    }

    /**
     * @param etatRequete
     *            affecte etatRequete
     */
    public void setEtatRequete(EtatRequete etatRequete) {
        this.etatRequete = etatRequete;
    }

    public void setEtat(EtatRequeteEnum etatRequeteEnum) {
        this.codeEtatRequete = etatRequeteEnum.getCode();
    }

    public EtatRequeteEnum getEtat() {
        return EtatRequeteEnum.resolve(this.codeEtatRequete);
    }

}
