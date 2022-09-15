/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.infraction.model;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;
import fr.gouv.justice.cassiopee.referentiel.infraction.codification.model.ModaliteParticipation;

/**
 * La relation qu�e les personnes � l�infraction est modul�e par les modalit�s de participation � l�infraction :
 * <ul>
 * <li>une personne peut �tre complice d�une infraction commise par l�auteur principal,</li>
 * <li>une personne peut �tre auteur d�une tentative d�infraction,</li>
 * <li>une infraction est commise en r�cidive par rapport � une autre infraction sanctionn�e par une condamnation
 * d�finitive.</li>
 * </ul>
 * Les trois modalit�s de participation peuvent se combiner et seules ces combinaisons existent : <li>Complicit� de
 * tentative de NATINF</li> <li>Tentative de NATINF en r�cidive</li> <li>Complicit� de NATINF en r�cidive</li> <li>
 * Complicit� de tentative de NATINF en r�cidive</li> </ul> Ces modalit�s sont historis�es. Les modalit�s sont dans un
 * ordre d�termin� par le texte de la QD.
 */
public class ModalitePrecedente implements java.io.Serializable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -3770399414773636160L;

    private static final HashCodeEqualsHelper<ModalitePrecedente> HE_HELPER = HashCodeEqualsHelper.of(ModalitePrecedente.class, ModalitePrecedente::getModaliteParticipation, ModalitePrecedente::getQualificationDeveloppee);

    private ModaliteParticipation modaliteParticipation;

    /**
     * Qualification d�velopp�e d�finie pour cet auteur, cette infraction et les modalit�s de participation
     */
    private QualificationDeveloppee qualificationDeveloppee;

    public ModaliteParticipation getModaliteParticipation() {
        return modaliteParticipation;
    }

    public void setModaliteParticipation(ModaliteParticipation modaliteParticipation) {
        this.modaliteParticipation = modaliteParticipation;
    }

    /**
     * Indique la Qualification d�velopp�e d�finie pour cet auteur, cette infraction et les modalit�s de participation
     *
     * @return la qualification d�velopp�e
     */
    public QualificationDeveloppee getQualificationDeveloppee() {
        return qualificationDeveloppee;
    }

    /**
     * Renseigne la Qualification d�velopp�e d�finie pour cet auteur, cette infraction et les modalit�s de participation
     *
     * @param qualificationDeveloppee
     *            la qualification d�velopp�e
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
