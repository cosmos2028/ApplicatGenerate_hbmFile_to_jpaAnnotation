/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.affaire.model;

import fr.gouv.justice.cassiopee.commons.codification.model.DateCodification;

/**
 * La classe OrigineAffaire permet la gestion de l'application d'origine d'une affaire. Les valeurs possibles sont
 * Cassiopée ou Reprise de données.
 */
public class OrigineAffaire extends DateCodification {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = 7850281388250990148L;

    /**
     * Constructor for OrigineAffaire
     */
    public OrigineAffaire() {
        super();
    }

    /**
     * Constructor for OrigineAffaire
     *
     * @param code
     *            le code de la codification souhaité
     */
    public OrigineAffaire(String code) {
        setCode(code);
    }

}
