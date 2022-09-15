package fr.gouv.justice.cassiopee.invariant.evenement.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import fr.gouv.justice.cassiopee.invariant.evenement.model.caracteristique.CaracteristiqueEvenement;
import fr.gouv.justice.cassiopee.invariant.evenement.model.interfaces.IEvenementCaracs;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.CaracteristiqueType;
import fr.gouv.justice.cassiopee.referentiel.evenement.model.LienEvenementCaracteristiqueType;

/**
 * Composant g�n�rique permettant de prendre en compte toutes les caract�ristiques d'un Evenement (ou d'un
 * EvenementExterieur). Permet d'acc�der � toutes les caract�ristiques possibles indiqu�es pour l'�v�nement-type dans le
 * SR, qu'elles soient valoris�es pour cet �v�nement ou non.
 * <p>
 * Ce map a la m�me interface que java.util.Map mais a un comportement diff�rent. La liste mininale des cl�s de ce map
 * est la liste des codes de caracteristique-type associ�s � l'evenement-type. En fonction du String (code
 * caract�ristique) pass� en argument, get(String) renvoie :
 * <ul>
 * <li>s'il s'agit d'une caract�ristique de l'�v�nement enregistr�e en base : on obtient un objet Caracteristique
 * encapsulant le format de la caract�ristique (date, montant...) et la valeur.
 * <li>s'il s'agit d'une caract�ristique li�e � l'�v�nement-type dans le SR, mais n'ayant pas de valeur en base pour cet
 * �v�nement : on obtient un objet Caracteristique qui encapsule le format de la caract�ristique et une valeur null.</li>
 * <li>s'il ne s'agit pas d'une caract�ristique possible pour cet �v�nement-type : on obtient null.</li> </li>
 * </ul>
 *
 * @param <E>
 *            une classe d'�v�nement (Evenement affaire ou EvenementExterieur)
 * @param <C>
 *            la classe de caract�ristique correspondante (Caracteristique pour un �v�nement affaire ou
 *            ValeurCaracteristiqueEvenementExterieur)
 */
public abstract class GenericCaracteristiquesUpdater<E extends IEvenementCaracs<C>, C extends CaracteristiqueEvenement>
        implements Map<String, C> {

    private static final long serialVersionUID = -1270917848206447156L;

    /**
     * Constructeur de classe
     *
     * @param evenement
     */
    public GenericCaracteristiquesUpdater(E evenement) {
        this.evenement = evenement;
    }

    private E evenement;

    /*
     * flag d'activation/desactivation des updates des caracs de l'evenement � positionner a false lors de la l'appel
     * depuis la partie edition
     */
    protected boolean updateEvenementCaracs = true;

    public void clear() {
        evenement.getCaracteristiquesPersistante().clear();
    }

    /**
     * Retourne <tt>true</tt> si le map de caracteristique contient une valeur pour le code donn� ou si l'evenement type
     * associ�e contient ce code <b>sans valeur associ� dans le map de caracteristique</b>. ce comportement est
     * diff�rent aux implementation de map.
     *
     * @param code
     *            code � tester dans ce map
     * @return <tt>true</tt> si ce map contient une valeur pour le code donn�
     * @see Map#containsKey(java.lang.Object)
     */
    public boolean containsKey(Object code) {
        // recherche l'existance du code dans le map des caracteristique en base
        if (evenement.getCaracteristiquesPersistante().containsKey(code))
            return true;

        // recherche l'existance de ce code pour l'evenement type associ� �
        // l'evenement
        for (LienEvenementCaracteristiqueType caracteristiqueType : evenement.getEvenementTypePourCaracs()
                .getLienEvenementCaracteristiqueTypes()) {
            if (caracteristiqueType.getPk().getCaracteristiqueType().getCode().equals(code)) {
                return true;
            }
        }

        // si rien n'a �t� trouv� retourne false
        return false;

    }

    /**
     * retourne <tt>true</tt> si pour ce map un code � la caracteristique sp�cifi�e.
     *
     * @param caracteristique
     *            caracteristique � rechercher
     * @return <tt>true</tt> si la caractistique a �t� trouv�e.
     */
    public boolean containsValue(Object caracteristique) {
        // on ne recherche que dans les caracteristiques en base
        return evenement.getCaracteristiquesPersistante().containsValue(caracteristique);
    }

    /*
     * classe pour retourner des entry
     */
    private class KEntry implements Entry<String, C> {
        private String code;

        private C caracteristique;

        KEntry(String code) {
            this.code = code;
        }

        public String getKey() {
            return this.code;
        }

        public C getValue() {
            return this.caracteristique;
        }

        public C setValue(C value) {
            C old = this.caracteristique;
            this.caracteristique = value;
            return old;
        }
    }

    /**
     * retourne un ensemble representant les associations code caracteristique. le comportement different de
     * l'implementation de map.
     *
     * @return un ensemble representant les associations code caracteristique.
     */
    public Set<Entry<String, C>> entrySet() {
        Set<Entry<String, C>> set = new LinkedHashSet<>();
        Set<String> codeSRs = new HashSet<>();

        // On it�re suivant l'ordre des types de caracteristique pr�sents dans le SR
        for (LienEvenementCaracteristiqueType lienCaracteristiqueType : evenement.getEvenementTypePourCaracs()
                .getLienEvenementCaracteristiqueTypes()) {
            // Si la carac type n'a pas �t� ferm�
            if ((lienCaracteristiqueType.getDateFermeture() == null)
                    || (lienCaracteristiqueType.getDateFermeture().after(evenement.getDateEvenement()))) {
                String code = lienCaracteristiqueType.getPk().getCaracteristiqueType().getCode();
                codeSRs.add(code);

                Entry<String, C> entry = new KEntry(code);

                // Si le type de caract�ristique est pr�sent en base alors on recopie la valeur
                // Sinon on cr�e une nouvelle caract�ristique
                C carac = evenement.getCaracteristiquesPersistante().get(code);
                if (carac == null) {
                    carac = constructCaracteristique(lienCaracteristiqueType.getPk().getCaracteristiqueType(), evenement);
                }
                carac.setRang(lienCaracteristiqueType.getRang());
                entry.setValue(carac);
                set.add(entry);
            }
        }

        // Si un type de caract�ristique est pr�sent en base mais n'est pas pr�sent dans le SR
        // On recopie la caract�ristique � la fin
        for (String code : this.evenement.getCaracteristiquesPersistante().keySet()) {
            if (!codeSRs.contains(code)) {
                Entry<String, C> entry = new KEntry(code);
                entry.setValue(evenement.getCaracteristiquesPersistante().get(code));
                set.add(entry);
            }
        }

        return set;
    }

    protected abstract C constructCaracteristique(CaracteristiqueType caracteristiqueType, E evenement);

    /**
     * retourne la caracteristique associ�e au code donn�.
     *
     * @return la caracteristique associ�e au code donn�
     */
    public C get(Object code) {

        // On v�rifie que le code du type de caract�ristique est pr�sent les
        // caract�ristiques persistantes
        if (evenement.getCaracteristiquesPersistante().containsKey(code)) {
            return evenement.getCaracteristiquesPersistante().get(code);
        }

        // On v�rifie que le code du type de caract�ristique est pr�sent les
        // caract�ristiques du SR
        for (LienEvenementCaracteristiqueType caracteristiqueType : evenement.getEvenementTypePourCaracs()
                .getLienEvenementCaracteristiqueTypes()) {

            if (caracteristiqueType.getPk().getCaracteristiqueType().getCode().equals(code)) {
                C caracteristique = constructCaracteristique(caracteristiqueType.getPk().getCaracteristiqueType(),
                        evenement);
                // Si la caracteristique est pr�sente dans le SR on la recopie
                // dans les caract�ristiques persistantes
                if (isUpdateEvenementCaracs())
                    this.evenement.getCaracteristiquesPersistante().put((String) code, caracteristique);

                caracteristique.setRang(caracteristiqueType.getRang());
                return caracteristique;
            }
        }

        return null;
    }

    public boolean isEmpty() {
        return (evenement.getCaracteristiquesPersistante().isEmpty() && evenement.getEvenementTypePourCaracs()
                .getLienEvenementCaracteristiqueTypes().isEmpty());
    }

    public Set<String> keySet() {
        return evenement.getCaracteristiquesPersistante().keySet();
    }

    public C put(String code, C caracteristique) {
        return evenement.getCaracteristiquesPersistante().put(code, caracteristique);
    }

    // On ne s'en sert pas
    public void putAll(Map<? extends String, ? extends C> caracteristiques) {
        evenement.getCaracteristiquesPersistante().putAll(caracteristiques);
    }

    public C remove(Object arg0) {
        // Contournement BUG HIBERNATE : https://hibernate.onjira.com/browse/HHH-2112
        // Sera certainement corrig� dans les futures versions d'Hibernate
        evenement.getCaracteristiquesPersistante().containsKey(arg0); // Ici on "r�veille" le lazy loading
        // Fin contournement bug hibernate
        return evenement.getCaracteristiquesPersistante().remove(arg0);
    }

    /**
     * Retourne le nombre des caract�ristiques
     *
     * @see #entrySet()
     */
    public int size() {
        return entrySet().size();
    }

    /**
     * Retourne les Caract�ristiques associ�es aux cl�s dans la table de hachage
     *
     * @see #entrySet()
     */
    public Collection<C> values() {

        Collection<C> values = new LinkedList<>();

        Set<Entry<String, C>> entrees = entrySet();

        Iterator<Entry<String, C>> iterator = entrees.iterator();

        while (iterator.hasNext()) {

            Entry<String, C> entree = iterator.next();
            values.add(entree.getValue());
        }

        return values;
    }

    /**
     * @return Returns the updateEvenementCaracs.
     */
    protected boolean isUpdateEvenementCaracs() {
        return updateEvenementCaracs;
    }

    /**
     * Permet de updater/ne pas updater les Caracs evenement selon qu'on est en mode consultation ou en mode creation
     *
     * @param updateEvenementCaracs
     *            The updateEvenementCaracs to set.
     */
    protected void setUpdateEvenementCaracs(boolean updateEvenementCaracs) {
        this.updateEvenementCaracs = updateEvenementCaracs;
    }
}
