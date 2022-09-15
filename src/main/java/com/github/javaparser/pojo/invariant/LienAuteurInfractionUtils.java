package fr.gouv.justice.cassiopee.invariant.infraction.model;

import java.util.List;

import fr.gouv.justice.cassiopee.invariant.infraction.helper.InfractionUtils;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.DetailDommageInteret;

public class LienAuteurInfractionUtils {

    private LienAuteurInfractionUtils() {

    }

    public static boolean hasPEMFrappeeAppel(LienAuteurInfraction lien, List<Long> appelIds) {
        if (InfractionUtils.hasPEMFrappeeAppel(lien.getInfraction(), appelIds)) {
            return true;
        }
        // Peut être qu'il y a une infraction d'origine à l'état infirmée ou disqualifiee
        if (lien.getLienAuteurInfractionOrigine() != null) {
            return hasPEMFrappeeAppel(lien.getLienAuteurInfractionOrigine(), appelIds);
        }
        
        return false;
    }
    
    /**
     * Retourne {@code true} si le {@link LienAuteurInfraction} possède un {@link DetailDommageInteret} frappé par un des appels.
     * 
     * @param lien
     * @param appelIds
     * @return
     */
    public static boolean hasDIFrappeAppel(LienAuteurInfraction lien, List<Long> appelIds) {
        boolean result = false;
        
        if (lien == null) {
            return result;
        }
        
        for (DetailDommageInteret detailDommageInteret : lien.getLienDiPersonneInfraction()) {
            for (Long appelId : appelIds) {
                result =  result || detailDommageInteret.getDommagesInteretsFrappesAppel().stream()
                    .anyMatch(di -> di.getEvenementAppel().getId().equals(appelId));
            }
        }
        
        result =  result || hasDIFrappeAppel(lien.getLienAuteurInfractionOrigine(), appelIds);
        
        return result;
    }
    
    /**
     * Retourne {@code true} si le {@link LienAuteurInfraction} possède un événement PPL lié et frappé par un des appels.
     * 
     * @param lien
     * @param appelIds
     * @return
     */
    public static boolean hasEvenementPPLFrappeeAppel(LienAuteurInfraction lien, List<Long> appelIds) {
        if (InfractionUtils.hasEvenementPPLFrappeAppel(lien.getInfraction(), appelIds)) {
            return true;
        }
        // Peut être qu'il y a une infraction d'origine à l'état infirmée?
        if (lien.getLienAuteurInfractionOrigine() != null) {
            return  hasEvenementPPLFrappeeAppel(lien.getLienAuteurInfractionOrigine(), appelIds);
        }
        
        return false;
    }

}
