package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

import fr.gouv.justice.cassiopee.referentiel.evenement.model.EnumerationValue;

/**
 * Evénement portant le dispositif pénal dans l'affaire dite "externe" à l'affaire courante. Il peut s'agir par exemple
 * d'un événement de type jugement ou ordonnance pénale.
 */
public class EvenementExterieurPortantDispo extends EvenementExterieur implements java.io.Serializable {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = 8303032768447473047L;

    /**
     * Nature du jugement (dans le cas où la décision est un jugement). La nature du jugement peut être contradictoire,
     * etc.
     */
    private EnumerationValue natureJugement;

    /**
     * Numéro de l'affaire à laquelle se rapporte le dispositif extérieur. Composé de l'identifiant du TGI extérieur (8
     * car.) + numéro de parquet dans le TGI (13 car.)
     */
    private String numeroParquetExterieur;

    /**
     * Libellé permettant de saisir le service (ou la chambre) de la décision. Le service est saisi librement, il ne
     * constitue pas un critère pour rechercher automatiquement un dispositif pénal dans l'affaire extérieure
     */
    private String service;

    private EvenementExterieur evenementNotification;

    public EnumerationValue getNatureJugement() {
        return natureJugement;
    }

    public void setNatureJugement(EnumerationValue natureJugement) {
        this.natureJugement = natureJugement;
    }

    public String getNumeroParquetExterieur() {
        return numeroParquetExterieur;
    }

    public void setNumeroParquetExterieur(String numeroParquetExterieur) {
        this.numeroParquetExterieur = numeroParquetExterieur;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public EvenementExterieur getEvenementNotification() {
        return evenementNotification;
    }

    public void setEvenementNotification(EvenementExterieur evenementNotification) {
        this.evenementNotification = evenementNotification;
    }

}
