/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.exterieur.model.evenement;

/**
 * Ev�nement ayant rendu la d�cision d�finitive. Il peut s'agir soit : d'une signification si la d�cision est un
 * jugement ou d'une notification si la d�cision est une ordonnance p�nale. Note d'impl�mentation: on enregistrera un
 * tel �v�nement soit avec le type SIGNIF 70058, soit avec le type NOTIF 70139, et ce quel que soit le type "r�el" de
 * cet �v�nement dans l'affaire d'origine. Cela permettra d'afficher les caract�ristiques appropri�es.
 */
public class EvenementExterieurSignificationOuNotification extends EvenementExterieur implements java.io.Serializable {

    /** Identifiant version pour la s�rialisation */
    private static final long serialVersionUID = 8303032768447473047L;

}
