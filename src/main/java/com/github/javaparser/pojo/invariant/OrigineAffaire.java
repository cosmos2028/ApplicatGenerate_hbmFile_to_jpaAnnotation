/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.affaire.model;

import fr.gouv.justice.cassiopee.commons.codification.model.DateCodification;

/**
 * La classe OrigineAffaire permet la gestion de l'application d'origine d'une affaire. Les valeurs possibles sont
 * Cassiop�e ou Reprise de donn�es.
 */
public class OrigineAffaire extends DateCodification {

    /** Identifiant version pour la s�rialisation */
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
     *            le code de la codification souhait�
     */
    public OrigineAffaire(String code) {
        setCode(code);
    }

}
