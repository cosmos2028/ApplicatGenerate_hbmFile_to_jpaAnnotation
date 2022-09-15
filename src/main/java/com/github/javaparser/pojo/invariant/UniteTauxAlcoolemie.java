/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.infraction.model;

import fr.gouv.justice.cassiopee.commons.codification.model.Codification;

/**
 * unité taux alcoolémie : grammes/litre d'air expiré ou milligrammes/litre de sang.
 */
public class UniteTauxAlcoolemie extends Codification {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = -4054442703326795495L;

    /** Code grammes/litre */
    public static final String CODE_GRAMMES_PAR_LITRE = "1";

    /** Code milligrammes/litre */
    public static final String CODE_MILLIGRAMMES_PAR_LITRE = "2";

}