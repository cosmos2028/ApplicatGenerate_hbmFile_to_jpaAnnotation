/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe Englobante intervenant dans la Gestion des Relances d'événement
 */
public class EvenementRelance implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7765433103438986180L;

    private Evenement evenement;

    private Date dateRetourSouhaite;

    private int rang;

    private String libelleTGI ;

    /**
     * Constructeur de classe
     *
     * @param evenement
     * @param dateRetourSouhaite
     */
    public EvenementRelance(Evenement evenement, Date dateRetourSouhaite) {
        super();
        this.evenement = evenement;
        this.dateRetourSouhaite = dateRetourSouhaite;
    }

    /**
     * Constructeur de classe
     *
     * @param evenement
     * @param dateRetourSouhaite
     * @param rang
     */
    public EvenementRelance(final Evenement evenement, final Date dateRetourSouhaite, final int rang,String libelleTGi) {
        super();
        this.evenement = evenement;
        this.dateRetourSouhaite = dateRetourSouhaite;
        this.rang = rang;
        this.setLibelleTGI(libelleTGi) ;
    }


    /**
     * @return retourne dateRetourSouhaite.
     */
    public Date getDateRetourSouhaite() {
        return dateRetourSouhaite;
    }

    /**
     * @param dateRetourSouhaite
     *            affecte dateRetourSouhaite.
     */
    public void setDateRetourSouhaite(Date dateRetourSouhaite) {
        this.dateRetourSouhaite = dateRetourSouhaite;
    }

    /**
     * @return retourne evenement.
     */
    public Evenement getEvenement() {
        return evenement;
    }

    /**
     * @param evenement
     *            affecte evenement.
     */
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public int getRang() {
        return rang;
    }

    public String getLibelleTGI() {
        return libelleTGI;
    }

    public void setLibelleTGI(String libelleTGI) {
        this.libelleTGI = libelleTGI;
    }

}
