/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.personneevenement.model;

import fr.gouv.justice.cassiopee.invariant.evenement.model.EvenementRequete;
import fr.gouv.justice.cassiopee.processus.execution.model.EtatRequete;
import fr.gouv.justice.cassiopee.processus.execution.model.RequetePersonneEvenementId;
import fr.gouv.justice.cassiopee.processus.execution.service.enumeration.EtatRequeteEnum;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.EnumerationValue;

/**
 * Requête en rectification : La requête peut être en attente de rectification ou accordée. 1) Pour une requête en
 * attente de validation, on conserve les valeurs corrigées d'un p.ev. en attendant la validation d'une requête en
 * rectification en erreur matérielle. Cette informations sont gardées jusqu'à ce que la rectification soit accordée. 2)
 * Pour une requête accordée, on conserve les valeurs initiales suite à une requête en rectification sur erreur
 * matérielle pour un événement. Les attributs sont renseignés avec les valeurs obsolètes de l'infraction une fois la
 * rectification accordée.
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
