/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice. Il ne peut être utilisé, reproduit ou
 * divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.personneevenement.model;

import fr.gouv.justice.cassiopee.commons.codification.model.Codification;

/**
 * Le mode de saisie est une donnée contextuelle à la vue guidée Jugement. Par défaut, la valeur est « Normal », si
 * cette donnée est passée à « Rapide » par l?utilisateur, c?est seulement dans le cadre de la vue guidée. En cas de
 * fermeture puis réouverture de la vue guidée, la valeur repasse à « Normal ».
 */
public class ModeSaisie extends Codification {

    /**
     * Identifiant pour la serialisation.
     */
    private static final long serialVersionUID = 3989955166093168454L;

}
