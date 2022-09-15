/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.infraction.model;

import fr.gouv.justice.cassiopee.commons.codification.model.Codification;

/**
 * unit� taux alcool�mie : grammes/litre d'air expir� ou milligrammes/litre de sang.
 */
public class UniteTauxAlcoolemie extends Codification {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = -4054442703326795495L;

    /** Code grammes/litre */
    public static final String CODE_GRAMMES_PAR_LITRE = "1";

    /** Code milligrammes/litre */
    public static final String CODE_MILLIGRAMMES_PAR_LITRE = "2";

}