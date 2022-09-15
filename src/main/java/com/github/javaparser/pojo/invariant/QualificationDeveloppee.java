package fr.gouv.justice.cassiopee.invariant.infraction.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gouv.justice.cassiopee.administration.infraction.model.ParagrapheDescriptif;
import fr.gouv.justice.cassiopee.administration.infraction.model.QualificationDeveloppeeType;
import fr.gouv.justice.cassiopee.administration.infraction.model.QualificationDeveloppeeTypeLocale;
import fr.gouv.justice.cassiopee.administration.infraction.model.QualificationDeveloppeeTypeNationale;
import fr.gouv.justice.cassiopee.invariant.infraction.service.exception.VariableInvalideException;

/**
 * La Qualification développée (QD) vient préciser la ou les infractions constatées. Elle décrit les éléments moral
 * (intention) non systématique et matériel (accomplissement des faits). La QD est fonction de l'infraction, de l’auteur
 * et des modalités de participation, celles-ci étant dans un ordre déterminé (ordre d’affichage) dans le texte de la
 * QD. Par extension, les paragraphes faisant référence aux textes qui prévoient et répriment l’infraction (références
 * issues du SR) pourront être construits dans la QD puisque systématiquement appelés à sa suite dans les éditions. La
 * QD est constuite à partir d'une QD type soit locale soit nationale
 */
public class QualificationDeveloppee implements java.io.Serializable, Cloneable {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = -3832641736660229004L;

    private static final Logger LOGGER = LoggerFactory.getLogger(QualificationDeveloppee.class);

    /***************************************************************************
     * Il s'agit du texte légal de la qualification de l’infraction, fusionné avec les éléments propres au cas d’espèce
     * issus de la base de données (*) ou de formulaires de saisie de données volatiles (#) non stockées. ex: D’avoir à
     * (Paris*) le (1er juin 2002 *) frauduleusement soustrait (une montre #) au préjudice de (MAGASIN *), faits prévus
     * et réprimés par les articles….du code pénal
     **************************************************************************/
    private String qdTexte;

    /**
     * Indique si la QD a été créée "identique pour tous auteurs".
     */
    private boolean creeIdentique;

    /** Qualification développée type locale */
    private QualificationDeveloppeeTypeNationale qualificationDeveloppeeTypeNationale;

    /** Qualification développée type nationale */
    private QualificationDeveloppeeTypeLocale qualificationDeveloppeeTypeLocale;

    /** descriptif du paragraphe non persistant */
    private ParagrapheDescriptif paragrapheDescriptif;

    /**
     * Retourne le texte légal de l'infraction
     *
     * @return le texte légal de l'infraction
     */
    public String getQdTexte() {
        return qdTexte;
    }

    /**
     * Renseigne le texte légal de l'infraction
     *
     * @param qdTexte
     *            le texte légal
     */
    public void setQdTexte(String qdTexte) {
        this.qdTexte = qdTexte;
    }

    /**
     * Retourne la qualification développée type
     *
     * @return la qualification développée type
     */
    public QualificationDeveloppeeTypeLocale getQualificationDeveloppeeTypeLocale() {
        return qualificationDeveloppeeTypeLocale;
    }

    /**
     * Renseigne la qualification développée type
     *
     * @param qualificationDeveloppeeTypeLocale
     *            la qualification développée type
     */
    public void setQualificationDeveloppeeTypeLocale(QualificationDeveloppeeTypeLocale qualificationDeveloppeeTypeLocale) {
        this.qualificationDeveloppeeTypeLocale = qualificationDeveloppeeTypeLocale;
    }

    /**
     * Retourne la qualification développée type
     *
     * @return la qualification développée type
     */
    public QualificationDeveloppeeTypeNationale getQualificationDeveloppeeTypeNationale() {
        return qualificationDeveloppeeTypeNationale;
    }

    /**
     * Renseigne la qualification développée type
     *
     * @param qualificationDeveloppeeTypeNationale
     *            la qualification développée type
     */
    public void setQualificationDeveloppeeTypeNationale(
            QualificationDeveloppeeTypeNationale qualificationDeveloppeeTypeNationale) {
        this.qualificationDeveloppeeTypeNationale = qualificationDeveloppeeTypeNationale;
    }

    /**
     * Retourne le descriptif du paragraphe associé à la qualification développée type Cet attribut n'est persistant.
     *
     * @return le descriptif du paragraphe associé à la qualification développée type
     */
    public ParagrapheDescriptif getParagrapheDescriptif() {
        return paragrapheDescriptif;
    }

    /**
     * Renseigne le descriptif du paragraphe associé à la qualification développée type Cet attribut n'est persistant.
     *
     * @param paragrapheDescriptif
     *            le descriptif du paragraphe associé à la qualification développée type
     */
    public void setParagrapheDescriptif(ParagrapheDescriptif paragrapheDescriptif) {
        this.paragrapheDescriptif = paragrapheDescriptif;
    }

    /**
     * Retourne une qualification développee type. La qualification développée type est soit nationale, soit locale.
     *
     * @return un objet QualificationDeveloppeeType
     */
    public QualificationDeveloppeeType getQualificationDeveloppeeType() {
        QualificationDeveloppeeType qualificationDeveloppeeType;
        if (this.qualificationDeveloppeeTypeLocale != null) {
            qualificationDeveloppeeType = qualificationDeveloppeeTypeLocale;
        } else {
            qualificationDeveloppeeType = qualificationDeveloppeeTypeNationale;
        }
        return qualificationDeveloppeeType;
    }

    /**
     * Renseigne la qualification développee type. La qualification développée type est soit nationale, soit locale.
     *
     * @param qualificationDeveloppeeType
     *            qualification développee type.
     */
    public void setQualificationDeveloppeeType(QualificationDeveloppeeType qualificationDeveloppeeType) {
        if (qualificationDeveloppeeType instanceof QualificationDeveloppeeTypeNationale) {
            qualificationDeveloppeeTypeNationale = (QualificationDeveloppeeTypeNationale) qualificationDeveloppeeType;
        } else {
            qualificationDeveloppeeTypeLocale = (QualificationDeveloppeeTypeLocale) qualificationDeveloppeeType;
        }
    }

    /**
     * construit un descriptif du paragraphe associé à la qualification développée
     *
     * @return un descriptif du paragraphe associé à la qualification développée t
     * @throws VariableInvalideException
     */
    public ParagrapheDescriptif buildParagrapheDescriptif() throws VariableInvalideException {
        return ParagrapheDescriptif.parseParagraphe(getQdTexte());
    }

    /**
     * Crée un clone de la qualification développée afin de pouvoir l'appliquer à une autre infraction ou autre lien
     * auteur-infraction.
     *
     * @throws CloneNotSupportedException
     */
    @Override
    public QualificationDeveloppee clone() {
        QualificationDeveloppee clone = null;
        try {
            clone = (QualificationDeveloppee) super.clone();
            clone.setQdTexte(this.qdTexte);
            clone.setQualificationDeveloppeeType(this.getQualificationDeveloppeeType());
        } catch (CloneNotSupportedException e) {
            LOGGER.error("Clone non supporté", e);
        }
        return clone;
    }

    /**
     * Retourne <code>true</code> si la QD a été créée "identique pour tous auteurs".
     *
     * @return
     */
    public boolean isCreeIdentique() {
        return creeIdentique;
    }

    /**
     * Renseigne si la QD a été créée "identique pour tous auteurs".
     *
     * @param creeIdentique
     */
    public void setCreeIdentique(final boolean creeIdentique) {
        this.creeIdentique = creeIdentique;
    }

}
