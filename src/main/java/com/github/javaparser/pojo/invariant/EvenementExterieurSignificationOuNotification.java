/*
 * Ce document est la propriété d'Atos Origin et du Ministère de la Justice.
 * Il ne peut être utilisé, reproduit ou divulgué sans leur autorisation écrite préalable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

/**
 * Evénement ayant rendu la décision définitive. Il peut s'agir soit : d'une signification si la décision est un
 * jugement ou d'une notification si la décision est une ordonnance pénale. Note d'implémentation: on enregistrera un
 * tel événement soit avec le type SIGNIF 70058, soit avec le type NOTIF 70139, et ce quel que soit le type "réel" de
 * cet événement dans l'affaire d'origine. Cela permettra d'afficher les caractéristiques appropriées.
 */
public class EvenementExterieurSignificationOuNotification extends EvenementExterieur implements java.io.Serializable {

    /** Identifiant version pour la sérialisation */
    private static final long serialVersionUID = 8303032768447473047L;

}
