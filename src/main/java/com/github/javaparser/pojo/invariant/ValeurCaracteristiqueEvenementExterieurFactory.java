package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

import fr.gouv.justice.cassiopee.common.exception.TechnicalException;
import fr.gouv.justice.cassiopee.referentiel.evenement.service.enumeration.FormatCaracteristiqueEnum;

/**
 * Fabrique de valeur caractéristique d'évenement.
 */
public class ValeurCaracteristiqueEvenementExterieurFactory {

    /**
     * crée une valeur de caractéristique d'évenement.
     *
     * @param <E>
     *            type de caractéristique
     * @param formatCaracteristiqueEnum
     *            format qui détermine le type de caractéristique
     * @return une valeur de caractéristique d'évenement non initialisé
     */
    @SuppressWarnings("unchecked")
    public static <E> ValeurCaracteristiqueEvenementExterieur<E> createValeurCaracteristiqueEvenementExterieur(
            FormatCaracteristiqueEnum formatCaracteristiqueEnum) {

        ValeurCaracteristiqueEvenementExterieur valeurCaracteristiqueEvenementExterieur;

        switch (formatCaracteristiqueEnum) {
            case NOMBRE:
                valeurCaracteristiqueEvenementExterieur = new ValeurCaracteristiqueEvenementNombre();
                break;

            case DUREE:
                valeurCaracteristiqueEvenementExterieur = new ValeurCaracteristiqueEvenementDuree();
                break;

            case DATE:
                valeurCaracteristiqueEvenementExterieur = new ValeurCaracteristiqueEvenementDate();
                break;

            case HEURE:
                valeurCaracteristiqueEvenementExterieur = new ValeurCaracteristiqueEvenementHeure();
                break;

            case TEXTE:
                valeurCaracteristiqueEvenementExterieur = new ValeurCaracteristiqueEvenementTexte();
                break;

            case ENUMERATION:
                valeurCaracteristiqueEvenementExterieur = new ValeurCaracteristiqueEvenementEnumeration();
                break;

            case SERVICE:
                valeurCaracteristiqueEvenementExterieur = new ValeurCaracteristiqueEvenementService();
                break;
                
            case MONTANT:
                valeurCaracteristiqueEvenementExterieur = new ValeurCaracteristiqueEvenementMontant();
                break;
                
             // Mantis 125782 : ADF - Exception Technique sur édition AVISAUD (Andromed) sur une affaire contenant une recidive
            case PAYS:
            	 valeurCaracteristiqueEvenementExterieur = new ValeurCaracteristiqueEvenementPays();
            	break;
            	
            //Mantis : 144405 & 153245 & 153252 & 153267	
            case ELEMENT_STRUCTURE:
            	 valeurCaracteristiqueEvenementExterieur = new ValeurCaracteristiqueEvenementElementStructure();
             	break;
             	
            default:
                throw new TechnicalException("cas de la caracteristique : " + formatCaracteristiqueEnum.toString()
                        + " non prise en charge");
        }
        return valeurCaracteristiqueEvenementExterieur;
    }

}
