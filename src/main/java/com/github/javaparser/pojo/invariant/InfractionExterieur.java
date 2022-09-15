/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model.infraction;

import fr.gouv.justice.cassiopee.commons.metier.model.DateIncompleteAvecPrefixe;
import fr.gouv.justice.cassiopee.invariant.exterieur.model.DispositifExterieur;
import fr.gouv.justice.cassiopee.referentiel.infraction.codification.model.ModaliteParticipation;
import fr.gouv.justice.cassiopee.referentiel.infraction.type.model.NatureInfractionType;
import fr.gouv.justice.cassiopee.referentiel.infraction.type.model.VersionNatureInfraction;

/**
 * Infractions (uniquement p�nales) commises par l'auteur dans le cadre de l'affaire ext�rieure et pour lesquelles
 * l'auteur a �t� d�clar� coupable. NB : Les autres infractions pour lesquelles l?auteur a eu la mesure � relaxe � ou �
 * exon�ration de responsabilit� � n?int�ressent pas DIE.
 */
public class InfractionExterieur implements java.io.Serializable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 1948264925177502322L;

    /** Identifiant de l'objet Infraction */
    private Long id;

    /** nature infraction type */
    private NatureInfractionType natureInfractionType;

    /** Version de la nature de l'infraction associ�e � la date de l'infraction */
    private VersionNatureInfraction versionNatureInfraction;

    private DispositifExterieur dispositifExterieur;

    /** Date de d�but de l'infraction */
    private DateIncompleteAvecPrefixe dateDebut;

    /** Date de fin de l'infraction */
    private DateIncompleteAvecPrefixe dateFin;

    /** modalit� de Participation */
    private ModaliteParticipation modaliteParticipation;

    /**
     * Retourne le dispositif ext�rieur
     *
     * @return le dispositif ext�rieur
     */
    public DispositifExterieur getDispositifExterieur() {
        return dispositifExterieur;
    }

    /**
     * Renseigne le dispositif ext�rieur
     *
     * @param dispositifExterieur
     *            le dispositif ext�rieur
     */
    public void setDispositifExterieur(DispositifExterieur dispositifExterieur) {
        this.dispositifExterieur = dispositifExterieur;
    }

    /**
     * Retourne l'identifiant de l'objet Infraction
     *
     * @return l'identifiant de l'objet Infraction
     */
    public Long getId() {
        return id;
    }

    /**
     * Renseigne l'identifiant de l'objet Infraction
     *
     * @param id
     *            l'identifiant de l'objet Infraction
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Indique la date de d�but de l'infraction
     *
     * @return la date de d�but de l'infraction
     */
    public DateIncompleteAvecPrefixe getDateDebut() {
        return dateDebut;
    }

    /**
     * Renseigne la date d�but de l'infraction
     *
     * @param dateDebut
     *            la date de d�but de l'infraction
     */
    public void setDateDebut(DateIncompleteAvecPrefixe dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Indique la date de fin de l'infraction
     *
     * @return la date de fin de l'infraction
     */
    public DateIncompleteAvecPrefixe getDateFin() {
        return dateFin;
    }

    /**
     * Renseigne la date de fin de l'infraction
     *
     * @param dateFin
     *            la date de fin de l'infraction
     */
    public void setDateFin(DateIncompleteAvecPrefixe dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Indique la version de la nature de l'infraction associ�e � la date de l'infraction
     */
    public VersionNatureInfraction getVersionNatureInfraction() {
        return versionNatureInfraction;
    }

    /**
     * Renseigne la version de la nature de l'infraction associ�e � la date de l'infraction
     *
     * @param versionNatureInfraction
     *            la version de la nature de l'infraction associ�e � la date de l'infraction
     */
    public void setVersionNatureInfraction(VersionNatureInfraction versionNatureInfraction) {
        this.versionNatureInfraction = versionNatureInfraction;
    }

    /**
     * Indique la nature infraction type
     *
     * @return la nature infraction type
     */
    public NatureInfractionType getNatureInfractionType() {
        return natureInfractionType;
    }

    /**
     * Renseigne la nature infraction type
     *
     * @param natureInfractionType
     *            � renseigner
     */
    public void setNatureInfractionType(NatureInfractionType natureInfractionType) {
        this.natureInfractionType = natureInfractionType;
    }

    /**
     * Indique modalite de Participation
     *
     * @return modalite de Participation
     */
    public ModaliteParticipation getModaliteParticipation() {
        return modaliteParticipation;
    }

    /**
     * Renseigne la modalite de Participation
     *
     * @param modaliteParticipation
     *            � renseigner
     */
    public void setModaliteParticipation(ModaliteParticipation modaliteParticipation) {
        this.modaliteParticipation = modaliteParticipation;
    }

}
