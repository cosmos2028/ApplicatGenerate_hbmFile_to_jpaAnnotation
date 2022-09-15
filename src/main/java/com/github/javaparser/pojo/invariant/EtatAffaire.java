/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.affaire.model;

import fr.gouv.justice.cassiopee.commons.codification.model.Codification;

/**
 * La classe EtatAffaire permet la Gestion de l'état d'une affaire.<br>
 * NB: Le cycle de vie d'une affaire et les différents états associés à celle-ci sont décrits au niveau du diagramme
 * d'état: CV 01 - état affaire.
 */
public class EtatAffaire extends Codification {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = 6995632849675997766L;

    /**
     * Constructor for EtatAffaire
     */
    public EtatAffaire() {
        super();
    }

    /**
     * Constructor for EtatAffaire
     *
     * @param code
     *            le code de la codification souhaitée
     * @param libelle
     *            le libellé de la codification souhaitée
     */
    public EtatAffaire(String code, String libelle) {
        setCode(code);
        setLibelle(libelle);
    }

}
