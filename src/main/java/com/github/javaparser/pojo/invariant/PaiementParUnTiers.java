package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;

/**
 * Correspond � la mesure PMTIERS - Paiement par un tiers. Cette mesure a la particularit� dans DIA d'�tre compl�t�e par
 * les tiers payeurs.
 */
public class PaiementParUnTiers extends AutreModaliteExecution {

    /**
     *
     */
    private static final long serialVersionUID = -601978362152814194L;

    @Override
    public TypePeineOuMesureEnum getTypePeineOuMesureEnum() {
        return TypePeineOuMesureEnum.PAIEMENT_PAR_UN_TIERS;
    }
}
