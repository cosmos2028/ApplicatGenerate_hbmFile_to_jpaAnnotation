/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.affaire.model;

import fr.gouv.justice.cassiopee.commons.codification.model.Codification;

/**
 * La classe EtatAffaire permet la Gestion de l'�tat d'une affaire.<br>
 * NB: Le cycle de vie d'une affaire et les diff�rents �tats associ�s � celle-ci sont d�crits au niveau du diagramme
 * d'�tat: CV 01 - �tat affaire.
 */
public class EtatAffaire extends Codification {

    /** Identifiant version pour la s�rialisation */
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
     *            le code de la codification souhait�e
     * @param libelle
     *            le libell� de la codification souhait�e
     */
    public EtatAffaire(String code, String libelle) {
        setCode(code);
        setLibelle(libelle);
    }

}
