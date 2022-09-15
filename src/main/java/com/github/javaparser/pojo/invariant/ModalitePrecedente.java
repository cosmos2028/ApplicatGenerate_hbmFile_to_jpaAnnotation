/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.infraction.model;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;
import fr.gouv.justice.cassiopee.referentiel.infraction.codification.model.ModaliteParticipation;

/**
 * La relation qu’e les personnes à l’infraction est modulée par les modalités de participation à l’infraction :
 * <ul>
 * <li>une personne peut être complice d’une infraction commise par l’auteur principal,</li>
 * <li>une personne peut être auteur d’une tentative d’infraction,</li>
 * <li>une infraction est commise en récidive par rapport à une autre infraction sanctionnée par une condamnation
 * définitive.</li>
 * </ul>
 * Les trois modalités de participation peuvent se combiner et seules ces combinaisons existent : <li>Complicité de
 * tentative de NATINF</li> <li>Tentative de NATINF en récidive</li> <li>Complicité de NATINF en récidive</li> <li>
 * Complicité de tentative de NATINF en récidive</li> </ul> Ces modalités sont historisées. Les modalités sont dans un
 * ordre déterminé par le texte de la QD.
 */
public class ModalitePrecedente implements java.io.Serializable {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = -3770399414773636160L;

    private static final HashCodeEqualsHelper<ModalitePrecedente> HE_HELPER = HashCodeEqualsHelper.of(ModalitePrecedente.class, ModalitePrecedente::getModaliteParticipation, ModalitePrecedente::getQualificationDeveloppee);

    private ModaliteParticipation modaliteParticipation;

    /**
     * Qualification développée définie pour cet auteur, cette infraction et les modalités de participation
     */
    private QualificationDeveloppee qualificationDeveloppee;

    public ModaliteParticipation getModaliteParticipation() {
        return modaliteParticipation;
    }

    public void setModaliteParticipation(ModaliteParticipation modaliteParticipation) {
        this.modaliteParticipation = modaliteParticipation;
    }

    /**
     * Indique la Qualification développée définie pour cet auteur, cette infraction et les modalités de participation
     *
     * @return la qualification développée
     */
    public QualificationDeveloppee getQualificationDeveloppee() {
        return qualificationDeveloppee;
    }

    /**
     * Renseigne la Qualification développée définie pour cet auteur, cette infraction et les modalités de participation
     *
     * @param qualificationDeveloppee
     *            la qualification développée
     */
    public void setQualificationDeveloppee(QualificationDeveloppee qualificationDeveloppee) {
        this.qualificationDeveloppee = qualificationDeveloppee;
    }


    @Override
    public boolean equals(Object obj){
        return HE_HELPER.areEqual(this, obj);
    }

    @Override
    public int hashCode() {
        return HE_HELPER.getHashCode(this);
    }
}
