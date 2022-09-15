/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice. Il ne peut �tre utilis�, reproduit ou
 * divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.personneevenement.model;

import fr.gouv.justice.cassiopee.commons.codification.model.Codification;

/**
 * Le mode de saisie est une donn�e contextuelle � la vue guid�e Jugement. Par d�faut, la valeur est � Normal �, si
 * cette donn�e est pass�e � � Rapide � par l?utilisateur, c?est seulement dans le cadre de la vue guid�e. En cas de
 * fermeture puis r�ouverture de la vue guid�e, la valeur repasse � � Normal �.
 */
public class ModeSaisie extends Codification {

    /**
     * Identifiant pour la serialisation.
     */
    private static final long serialVersionUID = 3989955166093168454L;

}
