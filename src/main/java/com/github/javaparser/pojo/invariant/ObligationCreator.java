package fr.gouv.justice.cassiopee.invariant.peineoumesure.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import fr.gouv.justice.cassiopee.RegleDeGestion;
import fr.gouv.justice.cassiopee.RegleDeGestion.RG;
import fr.gouv.justice.cassiopee.invariant.peineoumesure.service.enumeration.TypePeineOuMesureEnum;
import fr.gouv.justice.cassiopee.invariant.personne.model.Personne;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.helper.ObligationsHelper;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.PeineOuMesureLienUnitaire;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.model.PeineOuMesureType;
import fr.gouv.justice.cassiopee.referentiel.peineoumesure.service.enumeration.TypeLienMesureTypeEnum;
import fr.gouv.justice.cassiopee.referentiel.structure.model.TypeElementStructure;

/**
 * classe de creation des obligations
 */
public class ObligationCreator implements Serializable  {

	// l'héritage avec la classe AbstractPeineOuMesurePEMDecisionServiceImpl est
	// nécessaire pour utiliser la méthode getPeineOuMesure

	/**
	 *
	 */
	private static final long serialVersionUID = 3911206490910593300L;

	private PeineOuMesureType peineOuMesureType;

	private PeineOuMesure peineOuMesure;

	private Personne personne;

	// Modif CRT 27/04/06
	private Date dateEvenement;

	// Fin Modif CRT 27/04/06

	private ObligationCreator() {
	}

	/**
	 * @param peineOuMesureType
	 * @param peineOuMesure
	 * @param personne
	 * @param dateEvenement
	 */
	public ObligationCreator(PeineOuMesureType peineOuMesureType, PeineOuMesure peineOuMesure, Personne personne,
			Date dateEvenement) {
		this.peineOuMesure = peineOuMesure;
		this.peineOuMesureType = peineOuMesureType;
		this.personne = personne;
		this.dateEvenement = dateEvenement;
	}

	public List<PeineOuMesureBase> publierObligationsPossiblesouPrononceesMesureCreationCJ(
			final TypePeineOuMesureEnum typePeineOuMesureEnum, final boolean pourConsultation, final boolean isMineur,
			final boolean isMineurAuMomentDesFaits) {
		List<PeineOuMesureBase> listeObligations = new ArrayList<>();

		/*
		 * Si l'indicateur pourConsultation est à faux, il faut rechercher les possibles
		 * + prononcées
		 */
		// les obligations Type
		List<PeineOuMesureLienUnitaire> listeObligationsTypeLienUnitaire = ObligationsHelper
				.getListeObligationMineurOuNonMineur(this.personne, this.peineOuMesureType, this.dateEvenement,
						this.peineOuMesure, isMineur, isMineurAuMomentDesFaits, true);

		if (!pourConsultation) {

			/*
			 * Verifie s'il existe au moin Une peine fille prononcée pour la peine en cours
			 */
			boolean existeUneObligation = existeUneObligationPrononcee(typePeineOuMesureEnum, this.peineOuMesure);

			for (PeineOuMesureLienUnitaire peineOuMesureTypeObligation : listeObligationsTypeLienUnitaire) {

				PeineOuMesure obligationPrononce = null;
				if (this.peineOuMesure != null) {
					// rechercher l'obligation prononcée
					obligationPrononce = recherchePeineOuMesurePrononceeByCodeMesureEtType(
							peineOuMesureTypeObligation.getMesureLiee().getCodeMesure(),
							this.peineOuMesure.getPeinesOuMesuresFilles(), typePeineOuMesureEnum);
				}

				boolean indPrecochagePeineObligatoire = false;
				/*
				 * Si aucune obligation n'est prononcée pour cette peine en cours, alors
				 * appliquer la règle indicateur obligatoire Sinon (une obligation prononcée
				 * existe), ne pas précocher les peines obligatoires (l'utilisateur a
				 * volontairement décoché ces peines)
				 */
				if (!existeUneObligation) {
					/*
					 * précochage = Vrai OBL_O(2) : obligations générales liées à une décision ou à
					 * une mesure (obligations de plein droit)
					 */
					if (peineOuMesureTypeObligation.getTypeLien().getCode()
							.equals(TypeLienMesureTypeEnum.OBL_O.getCode())) {
						indPrecochagePeineObligatoire = true;
					}
					/*
					 * précochage = Vrai si CodeMesure()==42990 sauf dans un cas particulier pour le
					 * lien avec la mesure 30045
					 */
					if (peineOuMesureTypeObligation.getMesureLiee().getCodeMesure() == 42990) {
						if (peineOuMesureTypeObligation.getTypeLien().getCode()
								.equals(TypeLienMesureTypeEnum.OBL_F.getCode())
								&& peineOuMesureTypeObligation.getMesure().getCodeMesure() == 30045)

							indPrecochagePeineObligatoire = false;
						else
							indPrecochagePeineObligatoire = true;
					}

				}

				// creation d'un objet PeineOuMesureBase Obligation
				PeineOuMesureBase obligation = createObligation(peineOuMesureTypeObligation.getMesureLiee(),
						obligationPrononce, indPrecochagePeineObligatoire, typePeineOuMesureEnum);
				obligation.setForMineur(peineOuMesureTypeObligation.isForMineur());
				// ajouter dans la liste des obligations
				listeObligations.add(obligation);
			}
		} else {
			/*
			 * Si l'indicateur pourConsultation est à vrai, il faut rechercher seulement les
			 * obligations prononcées
			 */

			PeineOuMesure peineRecherche = this.peineOuMesure;
			while ((listeObligationsTypeLienUnitaire == null || listeObligationsTypeLienUnitaire.isEmpty())
					&& peineRecherche != null) {
				listeObligationsTypeLienUnitaire = ObligationsHelper.getListeObligationMineurOuNonMineur(this.personne,
						peineRecherche.getPeineOuMesureType(), this.dateEvenement, this.peineOuMesure, true, false,
						true);
				peineRecherche = peineRecherche.getPeineOuMesurePrecedente();
			}

			// Rechercher les obligations prononcées
			// Parcours de toutes les filles de la peine prononcee ou requise
			for (PeineOuMesure peineOuMesureFille : this.peineOuMesure.getPeinesOuMesuresFilles()) {
				// Seule les peines filles de type OBLIGATION sont prises en
				// compte
				if ((peineOuMesureFille.getTypePeineOuMesureEnum()
						.equals(TypePeineOuMesureEnum.OBLIGATION_DECISION_SANCTION))
						|| (peineOuMesureFille.getTypePeineOuMesureEnum()
								.equals(TypePeineOuMesureEnum.OBLIGATION_MESURE_MINEUR))
						|| (peineOuMesureFille.getTypePeineOuMesureEnum().equals(
								TypePeineOuMesureEnum.OBLIGATION_SUR_MODALITE_EXECUTION_OU_APPLICATION_OU_PERSONNALISATION))
						|| (peineOuMesureFille.getTypePeineOuMesureEnum()
								.equals(TypePeineOuMesureEnum.OBLIGATION_SUR_OBLIGATION))
						|| (peineOuMesureFille.getTypePeineOuMesureEnum()
								.equals(TypePeineOuMesureEnum.OBLIGATION_SUR_PEINE))) {

					// creation d'un objet PeineOuMesureBase Obligation
					PeineOuMesureBase obligation = createObligation(peineOuMesureFille.getPeineOuMesureType(),
							peineOuMesureFille, true, typePeineOuMesureEnum);

					if (mesureApplicableMineur(listeObligationsTypeLienUnitaire,
							obligation.getPeineOuMesureType().getCodeMesure())) {
						obligation.setForMineur(true);
					}

					listeObligations.add(obligation);
				}
			}

		}

		return listeObligations;
	}

	/**
	 * creer une liste d'obligations (ou sous obligations) en fonction du
	 * typePeineOuMesureEnum passé en paramètre fait appel au tpm
	 * P01_publierObligationsPossiblesMesure
	 *
	 * @param typePeineOuMesureEnum
	 * @param pourConsultation
	 *            : Si à VRAI : on est en consultation seule on ne recherche que les
	 *            mesures prononcées. Si à FAUX : on est en saisie, on recherche les
	 *            possibles + prononcées
	 * @return List<ObligationDIA>
	 */
	public List<PeineOuMesureBase> publierObligationsPossiblesouPrononceesMesure(
			TypePeineOuMesureEnum typePeineOuMesureEnum, boolean pourConsultation, boolean isDDSE) {
		List<PeineOuMesureBase> listeObligations = new ArrayList<>();

		// AMI_1018
        boolean isJuridictionDeJugument = false;
        if(PeineOuMesurePEMouDecision.typeEls!=null && 
        		(PeineOuMesurePEMouDecision.typeEls.getCode().equals("1-39") 
        				|| PeineOuMesurePEMouDecision.typeEls.getCode().equals("1-42")
        				|| PeineOuMesurePEMouDecision.typeEls.getCode().equals("1-43")
        				|| PeineOuMesurePEMouDecision.typeEls.getCode().equals("9-1951")
        				|| PeineOuMesurePEMouDecision.typeEls.getCode().equals("9-873")
        				|| PeineOuMesurePEMouDecision.typeEls.getCode().equals("9-753"))) {
        	isJuridictionDeJugument = true;
        }
		/*
		 * Si l'indicateur pourConsultation est à faux, il faut rechercher les possibles
		 * + prononcées
		 */
		// les obligations Type
		List<PeineOuMesureLienUnitaire> listeObligationsTypeLienUnitaire = ObligationsHelper
				.getListeObligationLienUnitairePossible(this.personne, this.peineOuMesureType, this.dateEvenement,
						this.peineOuMesure);
		if (!pourConsultation) {

			/*
			 * Verifie s'il existe au moin Une peine fille prononcée pour la peine en cours
			 */
			boolean existeUneObligation = existeUneObligationPrononcee(typePeineOuMesureEnum, this.peineOuMesure);

			for (PeineOuMesureLienUnitaire peineOuMesureTypeObligation : listeObligationsTypeLienUnitaire) {

				PeineOuMesure obligationPrononce = null;
				if (this.peineOuMesure != null) {
					// rechercher l'obligation prononcée
					obligationPrononce = recherchePeineOuMesurePrononceeByCodeMesureEtType(
							peineOuMesureTypeObligation.getMesureLiee().getCodeMesure(),
							this.peineOuMesure.getPeinesOuMesuresFilles(), typePeineOuMesureEnum);
				}

				boolean indPrecochagePeineObligatoire = false;
				/*
				 * Si aucune obligation n'est prononcée pour cette peine en cours, alors
				 * appliquer la règle indicateur obligatoire Sinon (une obligation prononcée
				 * existe), ne pas précocher les peines obligatoires (l'utilisateur a
				 * volontairement décoché ces peines)
				 */
				if (!existeUneObligation) {
					/*
					 * précochage = Vrai OBL_O(2) : obligations générales liées à une décision ou à
					 * une mesure (obligations de plein droit)
					 */
					if (peineOuMesureTypeObligation.getTypeLien().getCode()
							.equals(TypeLienMesureTypeEnum.OBL_O.getCode())) {
						if (isJuridictionDeJugument == true && isDDSE == true && peineOuMesureTypeObligation.getMesure().getCodeMesure() == 42990) {
							indPrecochagePeineObligatoire = false;
						}else
						indPrecochagePeineObligatoire = true;
					}
					/*
					 * précochage = Vrai si CodeMesure()==42990 sauf dans un cas particulier pour le
					 * lien avec la mesure 30045
					 */
					if (peineOuMesureTypeObligation.getMesureLiee().getCodeMesure() == 42990) {
						if (peineOuMesureTypeObligation.getTypeLien().getCode()
								.equals(TypeLienMesureTypeEnum.OBL_F.getCode())
								&& peineOuMesureTypeObligation.getMesure().getCodeMesure() == 30045)

							indPrecochagePeineObligatoire = false;
						else
							indPrecochagePeineObligatoire = true;
					}

				}

				// creation d'un objet PeineOuMesureBase Obligation
				PeineOuMesureBase obligation = createObligation(peineOuMesureTypeObligation.getMesureLiee(),
						obligationPrononce, indPrecochagePeineObligatoire, typePeineOuMesureEnum);

				// ajouter dans la liste des obligations
				listeObligations.add(obligation);
			}
		} else {
			/*
			 * Si l'indicateur pourConsultation est à vrai, il faut rechercher seulement les
			 * obligations prononcées
			 */

			PeineOuMesure peineRecherche = this.peineOuMesure;
			while ((listeObligationsTypeLienUnitaire == null || listeObligationsTypeLienUnitaire.isEmpty())
					&& peineRecherche != null) {
				listeObligationsTypeLienUnitaire = ObligationsHelper.getListeObligationMineurOuNonMineur(this.personne,
						peineRecherche.getPeineOuMesureType(), this.dateEvenement, this.peineOuMesure, true, false,
						false);
				peineRecherche = peineRecherche.getPeineOuMesurePrecedente();
			}

			// Rechercher les obligations prononcées
			// Parcours de toutes les filles de la peine prononcee ou requise
			for (PeineOuMesure peineOuMesureFille : this.peineOuMesure.getPeinesOuMesuresFilles()) {
				// Seule les peines filles de type OBLIGATION sont prises en
				// compte
				if ((peineOuMesureFille.getTypePeineOuMesureEnum()
						.equals(TypePeineOuMesureEnum.OBLIGATION_DECISION_SANCTION))
						|| (peineOuMesureFille.getTypePeineOuMesureEnum()
								.equals(TypePeineOuMesureEnum.OBLIGATION_MESURE_MINEUR))
						|| (peineOuMesureFille.getTypePeineOuMesureEnum().equals(
								TypePeineOuMesureEnum.OBLIGATION_SUR_MODALITE_EXECUTION_OU_APPLICATION_OU_PERSONNALISATION))
						|| (peineOuMesureFille.getTypePeineOuMesureEnum()
								.equals(TypePeineOuMesureEnum.OBLIGATION_SUR_OBLIGATION))
						|| (peineOuMesureFille.getTypePeineOuMesureEnum()
								.equals(TypePeineOuMesureEnum.OBLIGATION_SUR_PEINE))) {

					// creation d'un objet PeineOuMesureBase Obligation
					PeineOuMesureBase obligation = createObligation(peineOuMesureFille.getPeineOuMesureType(),
							peineOuMesureFille, true, typePeineOuMesureEnum);

					if (mesureApplicableMineur(listeObligationsTypeLienUnitaire,
							obligation.getPeineOuMesureType().getCodeMesure())) {
						obligation.setForMineur(true);
					}

					listeObligations.add(obligation);
				}
			}

		}

		return listeObligations;
	}

	/**
	 * Publie les obligations possibles pour une peine de modification d'obligations
	 * CJ en mode création donc avec les obligations prononcées de la peine
	 * précédente précochées
	 *
	 * @param typePeineOuMesureEnum
	 * @param peinePrecedente
	 * @return la liste des obligations
	 */
	@RegleDeGestion({ RG.PEMM008, RG.PEMI003 })
	public List<PeineOuMesureBase> publierObligationsPossiblesModifObligCJCreation(
			TypePeineOuMesureEnum typePeineOuMesureEnum, PeineOuMesure peinePrecedente, final boolean isMineur,
			final boolean isMineurAuMomentDesFaits) {
		List<PeineOuMesureBase> listeObligations = new ArrayList<>();

		/*
		 * on recherche les obligations possibles de la peine précédente, si celle-ci
		 * n'en a pas on recherche celles de sa peine précédente et ainsi de suite
		 */
		List<PeineOuMesureLienUnitaire> listeObligationsTypeLienUnitaire = null;
		PeineOuMesure peineRecherche = peinePrecedente;
		while ((listeObligationsTypeLienUnitaire == null || listeObligationsTypeLienUnitaire.isEmpty())
				&& peineRecherche != null) {
			listeObligationsTypeLienUnitaire = ObligationsHelper.getListeObligationMineurOuNonMineur(this.personne,
					peineRecherche.getPeineOuMesureType(), this.dateEvenement, this.peineOuMesure, isMineur,
					isMineurAuMomentDesFaits, false);
			peineRecherche = peineRecherche.getPeineOuMesurePrecedente();
		}

		if (listeObligationsTypeLienUnitaire == null || listeObligationsTypeLienUnitaire.isEmpty()) {
			// ce cas ne devrait en théorie jamais se produire
			return listeObligations;
		}

		// on se trouve en création donc on doit précocher les obligations
		// instanciées de la peine précédente
		for (PeineOuMesureLienUnitaire peineOuMesureTypeObligation : listeObligationsTypeLienUnitaire) {

			PeineOuMesure obligationPrononcePeinePrecedente = null;
			// on se trouve en création donc il n'y a aucune obligation
			// prononcée pour la peine
			boolean indPrecochagePeineObligatoire = false;
			if (peinePrecedente != null) {
				// rechercher l'obligation prononcée
				obligationPrononcePeinePrecedente = recherchePeineOuMesurePrononceeByCodeMesureEtType(
						peineOuMesureTypeObligation.getMesureLiee().getCodeMesure(),
						peinePrecedente.getPeinesOuMesuresFilles(), typePeineOuMesureEnum);
				if (obligationPrononcePeinePrecedente != null) {
					indPrecochagePeineObligatoire = true;
				}
			}

			// creation d'un objet PeineOuMesureBase Obligation
			PeineOuMesureBase obligation = createObligation(peineOuMesureTypeObligation.getMesureLiee(),
					obligationPrononcePeinePrecedente, indPrecochagePeineObligatoire, typePeineOuMesureEnum);
			obligation.setForMineur(peineOuMesureTypeObligation.isForMineur());
			// ajouter dans la liste des obligations
			listeObligations.add(obligation);
		}

		return listeObligations;
	}

	/**
	 * Publie les obligations possibles pour une peine de modification d'obligations
	 * CJ en mode modification donc avec les obligations prononcées de la peine
	 * cochées
	 *
	 * @param typePeineOuMesureEnum
	 * @param peinePrecedente
	 * @return la liste des obligations
	 */
	@RegleDeGestion({ RG.PEMM008, RG.PEMI003 })
	public List<PeineOuMesureBase> publierObligationsPossiblesModifObligCJModification(
			TypePeineOuMesureEnum typePeineOuMesureEnum, PeineOuMesure peinePrecedente, final boolean isMineur,
			final boolean isMineurAuMomentDesFaits) {
		List<PeineOuMesureBase> listeObligations = new ArrayList<>();

		/*
		 * on recherche les obligations possibles de la peine précédente, si celle-ci
		 * n'en a pas on recherche celles de sa peine précédente et ainsi de suite
		 */
		List<PeineOuMesureLienUnitaire> listeObligationsTypeLienUnitaire = null;
		PeineOuMesure peineRecherche = peinePrecedente;
		while ((listeObligationsTypeLienUnitaire == null || listeObligationsTypeLienUnitaire.isEmpty())
				&& peineRecherche != null) {
			listeObligationsTypeLienUnitaire = ObligationsHelper.getListeObligationMineurOuNonMineur(this.personne,
					peineRecherche.getPeineOuMesureType(), this.dateEvenement, this.peineOuMesure, isMineur,
					isMineurAuMomentDesFaits, false);
			peineRecherche = peineRecherche.getPeineOuMesurePrecedente();
		}

		if (listeObligationsTypeLienUnitaire.isEmpty()) {
			// ce cas ne devrait en théorie jamais se produire
			return listeObligations;
		}

		// on se trouve en modification donc on doit cocher les obligations
		// instanciées de la peine
		for (PeineOuMesureLienUnitaire peineOuMesureTypeObligation : listeObligationsTypeLienUnitaire) {

			PeineOuMesure obligationPrononce = null;
			boolean indPrecochagePeineObligatoire = false;
			if (this.peineOuMesure != null) {
				// rechercher l'obligation prononcée
				obligationPrononce = recherchePeineOuMesurePrononceeByCodeMesureEtType(
						peineOuMesureTypeObligation.getMesureLiee().getCodeMesure(),
						this.peineOuMesure.getPeinesOuMesuresFilles(), typePeineOuMesureEnum);
			}

			// creation d'un objet PeineOuMesureBase Obligation
			PeineOuMesureBase obligation = createObligation(peineOuMesureTypeObligation.getMesureLiee(),
					obligationPrononce, indPrecochagePeineObligatoire, typePeineOuMesureEnum);

			obligation.setForMineur(peineOuMesureTypeObligation.isForMineur());
			// ajouter dans la liste des obligations
			listeObligations.add(obligation);
		}

		return listeObligations;
	}

	/**
	 * Methode permettant de creer une obligation de type PeineOuMesureBase a partir
	 * d'un objet peine ou mesure
	 *
	 * @param peineOuMesureType
	 *            : type de la peine ou mesure
	 * @param obligationPrononce
	 * @param indPrecochagePeineObligatoire
	 * @return PeineOuMesureBase : l'objet simplifié correspondant a l'obligation
	 */
	private PeineOuMesureBase createObligation(final PeineOuMesureType peineOuMesureType,
			final PeineOuMesure obligationPrononce, final boolean indPrecochagePeineObligatoire,
			final TypePeineOuMesureEnum typePeineOuMesureEnum) {
		/* creation d'une obligation PeineOuMesureBase */
		return new PeineOuMesureBase(peineOuMesureType, obligationPrononce, indPrecochagePeineObligatoire,
				typePeineOuMesureEnum);
	}

	/**
	 * rechercher une peineOuMesure prononcee parmi la liste passée en paramètre
	 *
	 * @param codeMesureRecherchee
	 * @param peineOuMesureFille
	 * @param typePeineOuMesureEnumRecherche
	 * @return PeineOuMesure
	 */
	protected PeineOuMesure recherchePeineOuMesurePrononceeByCodeMesureEtType(Long codeMesureRecherchee,
			Set<PeineOuMesure> peineOuMesureFille, TypePeineOuMesureEnum typePeineOuMesureEnumRecherche) {
		for (PeineOuMesure peineOuMesure : peineOuMesureFille) {
			if (peineOuMesure.getTypePeineOuMesureEnum().getType().equals(typePeineOuMesureEnumRecherche.getType())
					&& peineOuMesure.getPeineOuMesureType().getCodeMesure().equals(codeMesureRecherchee)) {
				return peineOuMesure;
			}
		}
		return null;
	}

	/**
	 * verifie si parmi les filles de la peine prononcées passées en paramètre, il
	 * existe au moins une peine de type typePeineOuMesureEnumRecherche
	 *
	 * @param typePeineOuMesureEnumRecherche
	 * @param peineOuMesurePrononcee
	 * @return boolean
	 */
	protected boolean existeUneObligationPrononcee(TypePeineOuMesureEnum typePeineOuMesureEnumRecherche,
			PeineOuMesure peineOuMesurePrononcee) {
		if (peineOuMesurePrononcee != null) {
			for (PeineOuMesure peineOuMesure : peineOuMesurePrononcee.getPeinesOuMesuresFilles()) {
				if (peineOuMesure.getTypePeineOuMesureEnum().getType()
						.equals(typePeineOuMesureEnumRecherche.getType())) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean mesureApplicableMineur(List<PeineOuMesureLienUnitaire> listeObligationsTypeLienUnitaire,
			Long codeMesure) {
		for (Iterator<PeineOuMesureLienUnitaire> iterPEMLien = listeObligationsTypeLienUnitaire.iterator(); iterPEMLien
				.hasNext();) {
			PeineOuMesureLienUnitaire pemLienUnit = iterPEMLien.next();
			if (pemLienUnit.getMesureLiee() != null && pemLienUnit.getMesureLiee().getCodeMesure().equals(codeMesure)) {
				return pemLienUnit.getTypeLien() != null
						? pemLienUnit.getTypeLien().getCode().equals(TypeLienMesureTypeEnum.OBL_M.getCode())
						: false;
			}
		}
		return false;
	}
}
