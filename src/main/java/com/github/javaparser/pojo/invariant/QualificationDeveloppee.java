package fr.gouv.justice.cassiopee.invariant.infraction.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gouv.justice.cassiopee.administration.infraction.model.ParagrapheDescriptif;
import fr.gouv.justice.cassiopee.administration.infraction.model.QualificationDeveloppeeType;
import fr.gouv.justice.cassiopee.administration.infraction.model.QualificationDeveloppeeTypeLocale;
import fr.gouv.justice.cassiopee.administration.infraction.model.QualificationDeveloppeeTypeNationale;
import fr.gouv.justice.cassiopee.invariant.infraction.service.exception.VariableInvalideException;

/**
 * La Qualification d�velopp�e (QD) vient pr�ciser la ou les infractions constat�es. Elle d�crit les �l�ments moral
 * (intention) non syst�matique et mat�riel (accomplissement des faits). La QD est fonction de l'infraction, de l�auteur
 * et des modalit�s de participation, celles-ci �tant dans un ordre d�termin� (ordre d�affichage) dans le texte de la
 * QD. Par extension, les paragraphes faisant r�f�rence aux textes qui pr�voient et r�priment l�infraction (r�f�rences
 * issues du SR) pourront �tre construits dans la QD puisque syst�matiquement appel�s � sa suite dans les �ditions. La
 * QD est constuite � partir d'une QD type soit locale soit nationale
 */
public class QualificationDeveloppee implements java.io.Serializable, Cloneable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -3832641736660229004L;

    private static final Logger LOGGER = LoggerFactory.getLogger(QualificationDeveloppee.class);

    /***************************************************************************
     * Il s'agit du texte l�gal de la qualification de l�infraction, fusionn� avec les �l�ments propres au cas d�esp�ce
     * issus de la base de donn�es (*) ou de formulaires de saisie de donn�es volatiles (#) non stock�es. ex: D�avoir �
     * (Paris*) le (1er juin 2002 *) frauduleusement soustrait (une montre #) au pr�judice de (MAGASIN *), faits pr�vus
     * et r�prim�s par les articles�.du code p�nal
     **************************************************************************/
    private String qdTexte;

    /**
     * Indique si la QD a �t� cr��e "identique pour tous auteurs".
     */
    private boolean creeIdentique;

    /** Qualification d�velopp�e type locale */
    private QualificationDeveloppeeTypeNationale qualificationDeveloppeeTypeNationale;

    /** Qualification d�velopp�e type nationale */
    private QualificationDeveloppeeTypeLocale qualificationDeveloppeeTypeLocale;

    /** descriptif du paragraphe non persistant */
    private ParagrapheDescriptif paragrapheDescriptif;

    /**
     * Retourne le texte l�gal de l'infraction
     *
     * @return le texte l�gal de l'infraction
     */
    public String getQdTexte() {
        return qdTexte;
    }

    /**
     * Renseigne le texte l�gal de l'infraction
     *
     * @param qdTexte
     *            le texte l�gal
     */
    public void setQdTexte(String qdTexte) {
        this.qdTexte = qdTexte;
    }

    /**
     * Retourne la qualification d�velopp�e type
     *
     * @return la qualification d�velopp�e type
     */
    public QualificationDeveloppeeTypeLocale getQualificationDeveloppeeTypeLocale() {
        return qualificationDeveloppeeTypeLocale;
    }

    /**
     * Renseigne la qualification d�velopp�e type
     *
     * @param qualificationDeveloppeeTypeLocale
     *            la qualification d�velopp�e type
     */
    public void setQualificationDeveloppeeTypeLocale(QualificationDeveloppeeTypeLocale qualificationDeveloppeeTypeLocale) {
        this.qualificationDeveloppeeTypeLocale = qualificationDeveloppeeTypeLocale;
    }

    /**
     * Retourne la qualification d�velopp�e type
     *
     * @return la qualification d�velopp�e type
     */
    public QualificationDeveloppeeTypeNationale getQualificationDeveloppeeTypeNationale() {
        return qualificationDeveloppeeTypeNationale;
    }

    /**
     * Renseigne la qualification d�velopp�e type
     *
     * @param qualificationDeveloppeeTypeNationale
     *            la qualification d�velopp�e type
     */
    public void setQualificationDeveloppeeTypeNationale(
            QualificationDeveloppeeTypeNationale qualificationDeveloppeeTypeNationale) {
        this.qualificationDeveloppeeTypeNationale = qualificationDeveloppeeTypeNationale;
    }

    /**
     * Retourne le descriptif du paragraphe associ� � la qualification d�velopp�e type Cet attribut n'est persistant.
     *
     * @return le descriptif du paragraphe associ� � la qualification d�velopp�e type
     */
    public ParagrapheDescriptif getParagrapheDescriptif() {
        return paragrapheDescriptif;
    }

    /**
     * Renseigne le descriptif du paragraphe associ� � la qualification d�velopp�e type Cet attribut n'est persistant.
     *
     * @param paragrapheDescriptif
     *            le descriptif du paragraphe associ� � la qualification d�velopp�e type
     */
    public void setParagrapheDescriptif(ParagrapheDescriptif paragrapheDescriptif) {
        this.paragrapheDescriptif = paragrapheDescriptif;
    }

    /**
     * Retourne une qualification d�veloppee type. La qualification d�velopp�e type est soit nationale, soit locale.
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
     * Renseigne la qualification d�veloppee type. La qualification d�velopp�e type est soit nationale, soit locale.
     *
     * @param qualificationDeveloppeeType
     *            qualification d�veloppee type.
     */
    public void setQualificationDeveloppeeType(QualificationDeveloppeeType qualificationDeveloppeeType) {
        if (qualificationDeveloppeeType instanceof QualificationDeveloppeeTypeNationale) {
            qualificationDeveloppeeTypeNationale = (QualificationDeveloppeeTypeNationale) qualificationDeveloppeeType;
        } else {
            qualificationDeveloppeeTypeLocale = (QualificationDeveloppeeTypeLocale) qualificationDeveloppeeType;
        }
    }

    /**
     * construit un descriptif du paragraphe associ� � la qualification d�velopp�e
     *
     * @return un descriptif du paragraphe associ� � la qualification d�velopp�e t
     * @throws VariableInvalideException
     */
    public ParagrapheDescriptif buildParagrapheDescriptif() throws VariableInvalideException {
        return ParagrapheDescriptif.parseParagraphe(getQdTexte());
    }

    /**
     * Cr�e un clone de la qualification d�velopp�e afin de pouvoir l'appliquer � une autre infraction ou autre lien
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
            LOGGER.error("Clone non support�", e);
        }
        return clone;
    }

    /**
     * Retourne <code>true</code> si la QD a �t� cr��e "identique pour tous auteurs".
     *
     * @return
     */
    public boolean isCreeIdentique() {
        return creeIdentique;
    }

    /**
     * Renseigne si la QD a �t� cr��e "identique pour tous auteurs".
     *
     * @param creeIdentique
     */
    public void setCreeIdentique(final boolean creeIdentique) {
        this.creeIdentique = creeIdentique;
    }

}
