/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.infraction.model;

/** Donn�es concernant l'amnistie attach�es � l'infraction */
public class Amnistie implements java.io.Serializable, Cloneable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -3217604199043321142L;

    /**
     * Cet attribut permet d'associer une information au niveau de l'infraction concernant l'amnistie:
     * "Exclue de l'amnistie", "Amnistiable au quantum"...
     */
    private InfoAmnistie infoAmnistie;

    /** Contient la nature del'amnistie: au quantum, par nature ... */
    private TypeAmnistie typeAmnistie;

    /**
     * Contient la date et les r�f�rences du texte de loi � l'origine de l'amnistie.
     */
    private String referenceLoi;

    /**
     * Retourne les informations compl�mentaires concernant l'amnistie
     *
     * @return les informations compl�mentaires concernant l'amnistie
     */
    public InfoAmnistie getInfoAmnistie() {
        return infoAmnistie;
    }

    /**
     * Renseigne les informations compl�mentaires concernant l'amnistie
     *
     * @param infoAmnistie
     *            informations compl�mentaires
     */
    public void setInfoAmnistie(InfoAmnistie infoAmnistie) {
        this.infoAmnistie = infoAmnistie;
    }

    /**
     * Indique la r�f�rence du texte de loi d'amnistie
     *
     * @return la r�f�rence du texte de loi d'amnistie
     */
    public String getReferenceLoi() {
        return referenceLoi;
    }

    /**
     * Renseigne la r�ference du texte de loi d'amnistie
     *
     * @param referenceLoi
     *            la r�ference du texte de loi d'amnistie
     */
    public void setReferenceLoi(String referenceLoi) {
        this.referenceLoi = referenceLoi;
    }

    /**
     * Indique le type d'amnistie
     *
     * @return le type d'amnistie
     */
    public TypeAmnistie getTypeAmnistie() {
        return typeAmnistie;
    }

    /**
     * Renseigne le type d'amnistie
     *
     * @param typeAmnistie
     *            le type d'amnistie
     */
    public void setTypeAmnistie(TypeAmnistie typeAmnistie) {
        this.typeAmnistie = typeAmnistie;
    }

    @Override
    public Amnistie clone() throws CloneNotSupportedException {
        // On peut partager toutes r�f�rences: OK
        return (Amnistie) super.clone();
    }

}
