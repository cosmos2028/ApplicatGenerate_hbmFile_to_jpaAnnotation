package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

import fr.gouv.justice.cassiopee.referentiel.evenement.model.EnumerationValue;

/**
 * Ev�nement portant le dispositif p�nal dans l'affaire dite "externe" � l'affaire courante. Il peut s'agir par exemple
 * d'un �v�nement de type jugement ou ordonnance p�nale.
 */
public class EvenementExterieurPortantDispo extends EvenementExterieur implements java.io.Serializable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 8303032768447473047L;

    /**
     * Nature du jugement (dans le cas o� la d�cision est un jugement). La nature du jugement peut �tre contradictoire,
     * etc.
     */
    private EnumerationValue natureJugement;

    /**
     * Num�ro de l'affaire � laquelle se rapporte le dispositif ext�rieur. Compos� de l'identifiant du TGI ext�rieur (8
     * car.) + num�ro de parquet dans le TGI (13 car.)
     */
    private String numeroParquetExterieur;

    /**
     * Libell� permettant de saisir le service (ou la chambre) de la d�cision. Le service est saisi librement, il ne
     * constitue pas un crit�re pour rechercher automatiquement un dispositif p�nal dans l'affaire ext�rieure
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
