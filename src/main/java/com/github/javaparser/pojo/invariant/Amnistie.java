/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.infraction.model;

/** Données concernant l'amnistie attachées à l'infraction */
public class Amnistie implements java.io.Serializable, Cloneable {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = -3217604199043321142L;

    /**
     * Cet attribut permet d'associer une information au niveau de l'infraction concernant l'amnistie:
     * "Exclue de l'amnistie", "Amnistiable au quantum"...
     */
    private InfoAmnistie infoAmnistie;

    /** Contient la nature del'amnistie: au quantum, par nature ... */
    private TypeAmnistie typeAmnistie;

    /**
     * Contient la date et les références du texte de loi à l'origine de l'amnistie.
     */
    private String referenceLoi;

    /**
     * Retourne les informations complémentaires concernant l'amnistie
     *
     * @return les informations complémentaires concernant l'amnistie
     */
    public InfoAmnistie getInfoAmnistie() {
        return infoAmnistie;
    }

    /**
     * Renseigne les informations complémentaires concernant l'amnistie
     *
     * @param infoAmnistie
     *            informations complémentaires
     */
    public void setInfoAmnistie(InfoAmnistie infoAmnistie) {
        this.infoAmnistie = infoAmnistie;
    }

    /**
     * Indique la référence du texte de loi d'amnistie
     *
     * @return la référence du texte de loi d'amnistie
     */
    public String getReferenceLoi() {
        return referenceLoi;
    }

    /**
     * Renseigne la réference du texte de loi d'amnistie
     *
     * @param referenceLoi
     *            la réference du texte de loi d'amnistie
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
        // On peut partager toutes références: OK
        return (Amnistie) super.clone();
    }

}
