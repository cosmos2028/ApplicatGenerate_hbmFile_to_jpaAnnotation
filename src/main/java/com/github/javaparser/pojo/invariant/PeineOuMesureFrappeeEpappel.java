package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gouv.justice.cassiopee.invariant.infraction.helper.InfractionUtils;
import fr.gouv.justice.cassiopee.invariant.infraction.model.EtatInfractionEnum;
import fr.gouv.justice.cassiopee.invariant.infraction.model.Infraction;
import fr.gouv.justice.cassiopee.invariant.infraction.model.LienPersonneInfraction;
import fr.gouv.justice.cassiopee.invariant.personneevenement.model.DecisionFrappeeEpappel;

/**
 * Voir {@link DecisionFrappeeEpappel}.
 */
public class PeineOuMesureFrappeeEpappel extends DecisionFrappeeEpappel<PeineOuMesure> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeineOuMesureFrappeeEpappel.class);

    /**
     * Retourne l'infraction si elle est en cours.
     * Retourne l'infraction en cours liée à l'infraction donnée si elle est infirmée, et si on est sur un lien auteur infraction.
     */
    private static final Function<Infraction, Infraction> ENCOURS_OU_INFIRMEE_FILTER = (Infraction t) -> {
            if (t == null) {
                return null;
            }
            if (t.isEnCours()) {
                return t;
            }
            if (t.getEtatCourant().is(EtatInfractionEnum.INFIRMEE) || t.getEtatCourant().is(EtatInfractionEnum.DISQUALIFIEE)) {
                return InfractionUtils.getInfractionEnCoursLiee(t);
            }
            return null;
        };

    private static final Function<Infraction, Infraction> DISQUALIFIEE_FILTER = (Infraction t) -> t != null && t.isDisqualifiee()? t : null;

    private PeineOuMesure peineOuMesure;
    private String codeCaracteristique;
    private String codeMotif;
    public String getCodeCaracteristique() {
		return codeCaracteristique;
	}

	public void setCodeCaracteristique(String codeCaracteristique) {
		this.codeCaracteristique = codeCaracteristique;
	}

	public String getCodeMotif() {
		return codeMotif;
	}

	public void setCodeMotif(String codeMotif) {
		this.codeMotif = codeMotif;
	}

	public PeineOuMesure getPeineOuMesure() {
        return peineOuMesure;
    }

    public void setPeineOuMesure(PeineOuMesure peineOuMesure) {
        this.peineOuMesure = peineOuMesure;
    }


    @Override
    public List<Infraction> getInfractions() {
        List<Infraction> list = new ArrayList<>();
        Optional<Infraction> opt = getMatchingInfraction(ENCOURS_OU_INFIRMEE_FILTER);
        if (opt.isPresent()) {
            list.add(opt.get());
        } else {
            getMatchingInfractions(ENCOURS_OU_INFIRMEE_FILTER).forEach(list::add);
        }
        return list;
    }

    @Override
    public List<Infraction> getInfractionsRequalifiees() {
        Optional<Infraction> opt = getMatchingInfraction(DISQUALIFIEE_FILTER);
        if (opt.isPresent()) {
            return opt.get().getLiensPersonneInfractionRequalifies().stream().map(LienPersonneInfraction::getInfraction).collect(Collectors.toList());
        } else {
            return getMatchingInfractions(DISQUALIFIEE_FILTER).filter(Objects::nonNull).flatMap(infraction -> infraction.getLiensPersonneInfractionRequalifies().stream())
                    .map(LienPersonneInfraction::getInfraction).collect(Collectors.toList());
        }
    }

    /**
     * Mappe l'infraction du PersonneEvenementInfraction via la fonction donnée, si elle n'est pas nulle.
     * @param condition
     * @return
     */
    private Optional<Infraction> getMatchingInfraction(Function<Infraction, Infraction> mapper) {
        PersonneEvenementInfraction pei = peineOuMesure.getPersonneEvenementInfraction();
        Infraction inf = pei == null ? null : pei.getInfraction();
        return Optional.ofNullable(mapper.apply(inf));
    }

    private Stream<Infraction> getMatchingInfractions(Function<Infraction, Infraction> mapper) {
        return peineOuMesure.getGroupes().stream().flatMap(groupe -> groupe.getInfractions().stream()).distinct().map(mapper).filter(Objects::nonNull);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((peineOuMesure == null) ? 0 : peineOuMesure.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (obj == null || getClass() != obj.getClass())
            return false;
        PeineOuMesureFrappeeEpappel other = (PeineOuMesureFrappeeEpappel) obj;
        if (peineOuMesure == null) {
            if (other.peineOuMesure != null)
                return false;
        } else if (!peineOuMesure.equals(other.peineOuMesure))
            return false;
        return true;
    }

    @Override
    public PeineOuMesure getDecision() {
        return peineOuMesure;
    }

    @Override
    public void setDecision(PeineOuMesure decision) {
        this.peineOuMesure = decision;
    }

    @Override
    public Long getDecisionId() {
        return peineOuMesure.getId();
    }

}
