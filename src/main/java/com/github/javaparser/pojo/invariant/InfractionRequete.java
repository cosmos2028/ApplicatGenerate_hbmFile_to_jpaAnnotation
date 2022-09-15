/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.infraction.model;

import fr.gouv.justice.cassiopee.commons.metier.model.DateIncompleteAvecPrefixe;
import fr.gouv.justice.cassiopee.processus.execution.model.EtatRequete;
import fr.gouv.justice.cassiopee.processus.execution.service.enumeration.EtatRequeteEnum;
import fr.gouv.justice.cassiopee.referentiel.territoire.model.Commune;

/**
 * Requête en rectification : La requête peut être en attente de rectification ou accordée.
 * <ul>
 * <li>Pour une requête en attente de validation, on conserve les valeurs corrigées d'une infraction en attendant la
 * validation d'une requête en rectification en erreur matérielle. Cette informations sont gardées jusqu'à ce que la
 * rectification soit accordée.</li>
 * <li>Pour une requête accordée, on conserve les valeurs initiales suite à une requête en rectification sur erreur
 * matérielle pour une infraction. Les attributs sont renseignés avec les valeurs obsolètes de l'infraction une fois la
 * rectification accordée.</li>
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
     * Crée un clone de cette Requête, non encore enregistré en base. L'identifier est null et la référence vers
     * l'infraction est supprimée.
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
