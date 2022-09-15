/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model.infraction;

import fr.gouv.justice.cassiopee.commons.metier.model.DateIncompleteAvecPrefixe;
import fr.gouv.justice.cassiopee.invariant.exterieur.model.DispositifExterieur;
import fr.gouv.justice.cassiopee.referentiel.infraction.codification.model.ModaliteParticipation;
import fr.gouv.justice.cassiopee.referentiel.infraction.type.model.NatureInfractionType;
import fr.gouv.justice.cassiopee.referentiel.infraction.type.model.VersionNatureInfraction;

/**
 * Infractions (uniquement pénales) commises par l'auteur dans le cadre de l'affaire extérieure et pour lesquelles
 * l'auteur a été déclaré coupable. NB : Les autres infractions pour lesquelles l?auteur a eu la mesure « relaxe » ou «
 * exonération de responsabilité » n?intéressent pas DIE.
 */
public class InfractionExterieur implements java.io.Serializable {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = 1948264925177502322L;

    /** Identifiant de l'objet Infraction */
    private Long id;

    /** nature infraction type */
    private NatureInfractionType natureInfractionType;

    /** Version de la nature de l'infraction associée à la date de l'infraction */
    private VersionNatureInfraction versionNatureInfraction;

    private DispositifExterieur dispositifExterieur;

    /** Date de début de l'infraction */
    private DateIncompleteAvecPrefixe dateDebut;

    /** Date de fin de l'infraction */
    private DateIncompleteAvecPrefixe dateFin;

    /** modalité de Participation */
    private ModaliteParticipation modaliteParticipation;

    /**
     * Retourne le dispositif extérieur
     *
     * @return le dispositif extérieur
     */
    public DispositifExterieur getDispositifExterieur() {
        return dispositifExterieur;
    }

    /**
     * Renseigne le dispositif extérieur
     *
     * @param dispositifExterieur
     *            le dispositif extérieur
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
     * Indique la date de début de l'infraction
     *
     * @return la date de début de l'infraction
     */
    public DateIncompleteAvecPrefixe getDateDebut() {
        return dateDebut;
    }

    /**
     * Renseigne la date début de l'infraction
     *
     * @param dateDebut
     *            la date de début de l'infraction
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
     * Indique la version de la nature de l'infraction associée à la date de l'infraction
     */
    public VersionNatureInfraction getVersionNatureInfraction() {
        return versionNatureInfraction;
    }

    /**
     * Renseigne la version de la nature de l'infraction associée à la date de l'infraction
     *
     * @param versionNatureInfraction
     *            la version de la nature de l'infraction associée à la date de l'infraction
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
     *            à renseigner
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
     *            à renseigner
     */
    public void setModaliteParticipation(ModaliteParticipation modaliteParticipation) {
        this.modaliteParticipation = modaliteParticipation;
    }

}
