/*
 * Ce document est la propri�t� d'Atos Origin et du Minist�re de la Justice.
 * Il ne peut �tre utilis�, reproduit ou divulgu� sans leur autorisation �crite pr�alable.
 */
package fr.gouv.justice.cassiopee.invariant.evenement.model;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.invariant.evenement.service.enumeration.TypeEvenementEnum;

/**
 * Ev�nement juridique sur scell�s. Permet de tracer une d�cision juridique concernant les scell�s sans lien avec un
 * objet du domaine SCELLES. Il s'agit notamment d'une d�cision de restitution de scell�s prononc�e lors du jugement.<br>
 * RG.EVTM002 6>
 */
@RegleDeGestion(RG.EVTM002)
public class EvenementJuridiqueScelle extends EvenementGestionScelle {

    private static final long serialVersionUID = 6436460293586716294L;

    @Override
    public TypeEvenementEnum getTypeEvenementEnum() {
        return TypeEvenementEnum.JURIDIQUE_SCELLE;
    }

}
