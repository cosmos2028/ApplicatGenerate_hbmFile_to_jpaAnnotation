/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.infraction.model;

import fr.gouv.justice.cassiopee.commons.metier.model.DateIncompleteAvecPrefixe;
import fr.gouv.justice.cassiopee.processus.execution.model.EtatRequete;
import fr.gouv.justice.cassiopee.processus.execution.service.enumeration.EtatRequeteEnum;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Commune;

/**
 * Requ�te en rectification : La requ�te peut �tre en attente de rectification ou accord�e.
 * <ul>
 * <li>Pour une requ�te en attente de validation, on conserve les valeurs corrig�es d'une infraction en attendant la
 * validation d'une requ�te en rectification en erreur mat�rielle. Cette informations sont gard�es jusqu'� ce que la
 * rectification soit accord�e.</li>
 * <li>Pour une requ�te accord�e, on conserve les valeurs initiales suite � une requ�te en rectification sur erreur
 * mat�rielle pour une infraction. Les attributs sont renseign�s avec les valeurs obsol�tes de l'infraction une fois la
 * rectification accord�e.</li>
 * </ul>
 */
public class InfractionRequete implements java.io.Serializable, Cloneable {

    private static final long serialVersionUID = 4787394435174609411L;

    private Long id;

    private Infraction infraction;

    private String codeEtatRequete;

    private EtatRequete etatRequete;

    private Commune commune;

    private DateIncompleteAvecPrefixe dateDebut;

    private DateIncompleteAvecPrefixe dateFin;

    private String libelleLieuInfraction;

    /**
     *
     */
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     */
    public Infraction getInfraction() {
        return this.infraction;
    }

    public void setInfraction(Infraction infraction) {
        this.infraction = infraction;
    }

    /**
     *
     */
    public EtatRequete getEtatRequete() {
        return this.etatRequete;
    }

    public void setEtatRequete(EtatRequete etatRequete) {
        this.etatRequete = etatRequete;
    }

    public void setEtat(EtatRequeteEnum etatRequeteEnum) {
        this.codeEtatRequete = etatRequeteEnum.getCode();
    }

    public EtatRequeteEnum getEtat() {
        return EtatRequeteEnum.resolve(this.codeEtatRequete);
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    /**
     *
     */
    public String getLibelleLieuInfraction() {
        return this.libelleLieuInfraction;
    }

    public void setLibelleLieuInfraction(String libelleLieuInfraction) {
        this.libelleLieuInfraction = libelleLieuInfraction;
    }

    public DateIncompleteAvecPrefixe getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(DateIncompleteAvecPrefixe dateDebut) {
        this.dateDebut = dateDebut;
    }

    public DateIncompleteAvecPrefixe getDateFin() {
        return dateFin;
    }

    public void setDateFin(DateIncompleteAvecPrefixe dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Cr�e un clone de cette Requ�te, non encore enregistr� en base. L'identifier est null et la r�f�rence vers
     * l'infraction est supprim�e.
     */
    @Override
    public InfractionRequete clone() throws CloneNotSupportedException {
        InfractionRequete clone = (InfractionRequete) super.clone();
        clone.setId(null);
        if (this.dateDebut != null) {
            clone.setDateDebut(this.dateDebut.clone());
        }
        if (this.dateFin != null) {
            clone.setDateFin(this.dateFin.clone());
        }
        clone.setInfraction(null);
        return clone;
    }

}
