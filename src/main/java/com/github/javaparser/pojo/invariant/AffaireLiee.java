package fr.gouv.justice.cassiopee.invariant.affaire.model;

import java.io.Serializable;

import fr.gouv.justice.cassiopee.administration.structure.model.Tgi;
import fr.gouv.justice.cassiopee.processus.parquet.model.IdentificationParquet;
import fr.gouv.justice.cassiopee.processus.parquet.service.enumeration.OrigineCreationEnum;

/**
 * La notion d'affaire li�e permet de g�rer les liens entre affaires. Les affaires li�es peuvent ne pas exister dans la
 * base CASSIOPEE mais on garde la trace des num�ros de parquet.<br>
 * Elle permet par exemple :<br>
 * - de conna�tre les affaires jointes sur une affaire d'accueil,<br>
 * - de conna�tre les affaires requ�te d'une affaire d'origine associ�e,<br>
 * - de conna�tre les affaires cr��es par duplication d'une affaire d'origine dans le cadre d'une instance modificative.<br>
 * <br>
 * Attention : en cr�ation d'affaire courrier ext�rieur, il n'y a pas cr�ation d'affaire mais juste cr�ation d'un
 * Identification Parquet, avec la m�me affaire utilis�e. Donc l'AffaireLiee devrait pointer sur elle-m�me, ce qui est
 * inutile car les liens origine/destination ne signifient plus rien. Il a donc �t� d�cid� de n'utiliser qu'un seul lien
 * : AffaireLiee.affaireDestination car c'est selui qui est obligatoire et assure la persistence, et d'y enregistrer les
 * affaires d'origine, car elles peuvent �tre plusieurs. <br>
 * Contenu de la table AFF_AFFAIRE_LIEE en fonction du type de liaison :<br>
 * Jonction Affaire requ�te Courrier ext�r Duplication<br>
 * ID_AFFAIRE_DEST accueil aff requ�te affaire dupliqu�e<br>
 * ID_AFFAIRE_ORIGINE jointe origine nul origine<br>
 * CODE_TGI_AFF_LIEE nul aff requ�te tgi courr ext nul<br>
 * TGI_ORIGN_COUR_EXT nul nul tgi origine nul<br>
 * NUM_PQT_ORIGINE jointe aff origine origine origine<br>
 * NUM_PQT_DEST accueil aff requ�te aff courrier ext dupliqu�e<br>
 * ORIGN_CREA_AFF_DEST nul R C nul<br>
 */
public class AffaireLiee implements Serializable {

    /**
     * Identifiant de s�rialisation
     */
    private static final long serialVersionUID = 843361216705004916L;

    private Long id;
    private Affaire affaireDestination;
    private Affaire affaireOrigine;
    private String origineCreationAffaire;
    private String numeroParquetOrigine;
    private String numeroParquetDestination;
    private Boolean lienRequeteAJour = false;
    private Tgi tgiAffaireDistante;
    private Tgi tgiAffaireOrigineCourrierExt;
    private Boolean existanceAffaireDansKcop = false;
    /**
     * TGI de l'affaire d'accueil (lors d'une jonction)
     */
    private Tgi tgiAffaireAccueil;
    /**
     * TGI de l'affaire jointe (lors d'une jonction)
     */
    private Tgi tgiAffaireJointe;

    /**
     * @return True si l'affaire existe dans Cassiopee, False sinon.
     */
    public Boolean getExistanceAffaireDansKcop() {
        return existanceAffaireDansKcop;
    }

    /**
     * @param existanceAffaireDansKcop
     *            True si l'affaire existe dans Cassiopee, False sinon.
     */
    public void setExistanceAffaireDansKcop(Boolean existanceAffaireDansKcop) {
        this.existanceAffaireDansKcop = existanceAffaireDansKcop;
    }

    /**
     * Selon le cas :<br>
     * - jonction : retourne nul.<br>
     * - requ�te : retourne le TGI de l'affaire distante.<br>
     * - courrier ext : retourne le TGI de l'affaire courrier ext�rieur.
     *
     * @return le TGI de l'affaire li�e.
     */
    public Tgi getTgiAffaireDistante() {
        return tgiAffaireDistante;
    }

    /**
     * @param tgiAffaireDistante
     *            le TGI de l'affaire li�e.
     */
    public void setTgiAffaireDistante(Tgi tgiAffaireDistante) {
        this.tgiAffaireDistante = tgiAffaireDistante;
    }

    /**
     * Dans le cas de l'affaire courrier ext�rieur, retourne le TGI de l'affaire d'origine.<br>
     * Retourne null dans les autres cas.
     *
     * @return le TGI de l'affaire d'origine pour les affaire courrier ext�rieur.
     */
    public Tgi getTgiAffaireOrigineCourrierExt() {
        return tgiAffaireOrigineCourrierExt;
    }

    /**
     * @param tgiAffaireOrigine
     *            le TGI de l'affaire d'origine.
     */
    public void setTgiAffaireOrigineCourrierExt(Tgi tgiAffaireOrigine) {
        this.tgiAffaireOrigineCourrierExt = tgiAffaireOrigine;
    }

    /**
     * Dans le cas d'une jonction, retourne le TGI de l'affaire d'accueil.<br>
     * Retourne null dans les autres cas.
     *
     * @return le TGI de l'affaire d'origine pour les affaires qui ont fait l'objet d'une jonction.
     */
    public Tgi getTgiAffaireAccueil() {
        return tgiAffaireAccueil;
    }

    /**
     * @param tgiAffaireAccueil
     *            le TGI de l'affaire d'accueil
     */
    public void setTgiAffaireAccueil(Tgi tgiAffaireAccueil) {
        this.tgiAffaireAccueil = tgiAffaireAccueil;
    }

    /**
     * Dans le cas d'une jonction, retourne le TGI de l'affaire jointe.<br>
     * Retourne null dans les autres cas.
     *
     * @return le TGI de l'affaire d'origine pour les affaires qui ont fait l'objet d'une jonction.
     */
    public Tgi getTgiAffaireJointe() {
        return tgiAffaireJointe;
    }

    /**
     * @param tgiAffaireJointe
     *            le TGI de l'affaire jointe
     */
    public void setTgiAffaireJointe(final Tgi tgiAffaireJointe) {
        this.tgiAffaireJointe = tgiAffaireJointe;
    }

    /**
     * @return le num�ro de parquet de l'affaire de destination (affaire d'accueil ou affaire cr��e).
     */
    public String getNumeroParquetDestination() {
        return numeroParquetDestination;
    }

    /**
     * @param numeroParquetDestination
     *            le num�ro de parquet de l'affaire de destination (affaire d'accueil ou affaire cr��e).
     */
    public void setNumeroParquetDestination(String numeroParquetDestination) {
        this.numeroParquetDestination = numeroParquetDestination;
    }

    /**
     * Retourne : <br>
     * - en cas de jonction : l'affaire d'accueil,<br>
     * - en cas d'affaire requ�te : l'affaire requ�te,<br>
     * - en cas d'affaire courrier ext�rieur : l'affaire d'origine (qui est la m�me que l'affaire de destination),<br>
     * - en cas de duplication : l'affaire dupliqu�e.<br>
     * Cet attribut est obligatoire, car c'est celui qui permet la relation � l'affaire.
     *
     * @return l'affaire de destination
     */
    public Affaire getAffaireDestination() {
        return affaireDestination;
    }

    /**
     * Positionne : <br>
     * - en cas de jonction : l'affaire d'accueil,<br>
     * - en cas d'affaire requ�te : l'affaire requ�te,<br>
     * - en cas d'affaire courrier ext�rieur : l'affaire d'origine (qui est la m�me que l'affaire de destination),<br>
     * - en cas de duplication : l'affaire dupliqu�e.<br>
     * Cet attribut est obligatoire, car c'est celui qui permet la relation � l'affaire.
     *
     * @param affairePrimaire
     *            l'affaire de destination.
     */
    public void setAffaireDestination(Affaire affairePrimaire) {
        this.affaireDestination = affairePrimaire;
    }

    /**
     * Retourne : <br>
     * - en cas de jonction : l'affaire jointe,<br>
     * - en cas d'affaire requ�te : null car l'affaire n'appartient pas forc�ment � Cassiop�e,<br>
     * - en cas d'affaire courrier ext�rieur : null,<br>
     * - en cas de duplication : l'affaire d'origine.<br>
     *
     * @return l'affaire d'origine.
     */
    public Affaire getAffaireOrigine() {
        return affaireOrigine;
    }

    /**
     * Positionne : <br>
     * - en cas de jonction : l'affaire jointe,<br>
     * - en cas d'affaire requ�te : null car l'affaire n'appartient pas forc�ment � Cassiop�e,<br>
     * - en cas d'affaire courrier ext�rieur : null,<br>
     * - en cas de duplication : l'affaire d'origine.<br>
     *
     * @param affaireSecondaire
     *            l'affaire d'origine.
     */
    public void setAffaireOrigine(Affaire affaireSecondaire) {
        this.affaireOrigine = affaireSecondaire;
    }

    /**
     * @return l'identifiant de AffaireLiee (de la table de jointure).
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            l'identifiant de AffaireLiee (de la table de jointure).
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Il s'agit du num�ro de parquet complet (comprenant l'identifiant du TGI : code origine+n� �l�ment de structure)
     * de l'affaire avec lequel on �tablit le lien depuis une autre affaire. <br>
     * Dans le cas de l'affaire requ�te, ce num�ro est renseign� par l'utilisateur et non contr�l�. De tout fa�on,
     * l'affaire n'existe sans doute pas dans Cassiop�e.<br>
     * Dans le cas de la jonction, le num�ro r�duit est utilis� (sur 11 caract�res) car la jonction est intra-TGI.
     *
     * @return Le num�ro de parquet complet de l'affaire d'origine.
     */
    public String getNumeroParquetOrigine() {
        return numeroParquetOrigine;
    }

    /**
     * Il s'agit du num�ro de parquet complet (comprenant l'identifiant du TGI : code origine+n� �l�ment de structure)
     * de l'affaire avec lequel on �tablit le lien depuis une autre affaire. <br>
     * Dans le cas de l'affaire requ�te, ce num�ro est renseign� par l'utilisateur et non contr�l�. De tout fa�on,
     * l'affaire n'existe sans doute pas dans Cassiop�e.<br>
     * Dans le cas de la jonction, le num�ro r�duit est utilis� (sur 11 caract�res) car la jonction est intra-TGI.
     *
     * @param numeroParquetOrigine
     *            Le num�ro de parquet complet de l'affaire d'origine.
     */
    public void setNumeroParquetOrigine(String numeroParquetOrigine) {
        this.numeroParquetOrigine = numeroParquetOrigine;
    }

    /**
     * Cette information permet de savoir de quel type est le num�ro de parquet li� et notamment s'il s'agit:<br>
     * - d'une affaire "requ�te" (origine renseign�e � R)<br>
     * - d�une affaire � courrier ext�rieure � (origine renseign�e � C).<br>
     * Dans les autres cas d'affaire li�e, l'origine de cr�ation ne sera pas renseign�e (affaires jointes).<br>
     * Correspond aux valeurs de l'Enum OrigineCreationEnum.
     *
     * @return l'origine de cr�ation du lien.
     */
    public String getOrigineCreationAffaire() {
        return origineCreationAffaire;
    }

    /**
     * Cette information permet de savoir de quel type est le num�ro de parquet li�.
     *
     * @return l'origine de cr�ation du lien.
     */
    public OrigineCreationEnum getOrigineCreationAffaireEnum() {
        return OrigineCreationEnum.resolve(origineCreationAffaire);
    }

    /**
     * M�thode rendue private (utilis�e par hibernate uniquement) pour contr�ler les valeurs rentr�es avec l'�num, sinon
     * plantage contrainte de la base.<br>
     *
     * @param origineCreationAffaire
     *            l'origine de cr�ation du lien.
     */
    @SuppressWarnings("unused")
    private void setOrigineCreationAffaire(String origineCreationAffaire) {
        this.origineCreationAffaire = origineCreationAffaire;
    }

    /**
     * Cette information permet de savoir de quel type est le num�ro de parquet li� et notamment s'il s'agit:<br>
     * - d'une affaire "requ�te" (origine renseign�e � R)<br>
     * - d�une affaire � courrier ext�rieure � (origine renseign�e � C).<br>
     * Dans les autres cas d'affaire li�e, l'origine de cr�ation ne sera pas renseign�e (affaires jointes).
     *
     * @param origineCreationAffaire
     *            l'origine de cr�ation du lien.
     */
    public void setOrigineCreationAffaire(OrigineCreationEnum origineCreationAffaire) {
        if (origineCreationAffaire == null) {
            this.origineCreationAffaire = null;
        } else {
            this.origineCreationAffaire = origineCreationAffaire.getCode();
        }
    }

    /**
     * Dans le cas de cr�ation d'affaire requ�te en reconnaissance d'identit�, on �tablit un lien entre l'affaire
     * requ�te et les affaires d'origine. Lors du jugement de l'affaire requ�te, l'utilisateur peut mettre � jour les
     * affaires d'origine. Lorsque la mise � jour a �t� effectu�e sur une affaire d'origine, on conserve cette
     * information sous forme de bool�en sur le lien.
     *
     * @return Vrai si le lien a �t� mis � jour, faux sinon.
     */
    public Boolean getLienRequeteAJour() {
        return lienRequeteAJour;
    }

    /**
     * Dans le cas de cr�ation d'affaire requ�te en reconnaissance d'identit�, on �tablit un lien entre l'affaire
     * requ�te et les affaires d'origine. Lors du jugement de l'affaire requ�te, l'utilisateur peut mettre � jour les
     * affaires d'origine. Lorsque la mise � jour a �t� effectu�e sur une affaire d'origine, on conserve cette
     * information sous forme de bool�en sur le lien.
     *
     * @param lienRequeteAJour
     *            Vrai si le lien a �t� mis � jour, faux sinon.
     */
    public void setLienRequeteAJour(Boolean lienRequeteAJour) {
        this.lienRequeteAJour = lienRequeteAJour;
    }

    /**
     * Retourne l'identification parquet de l'affaire d'origine
     *
     * @return
     */
    public IdentificationParquet getIdentificationParquetOrigine() {
        return affaireOrigine.getIdentificationParquet(tgiAffaireJointe.getCode(), numeroParquetOrigine);
    }

    /**
     * Retourne l'identification parquet de l'affaire de destination
     *
     * @return
     */
    public IdentificationParquet getIdentificationParquetDestination() {
        return affaireDestination.getIdentificationParquet(tgiAffaireAccueil.getCode(), numeroParquetDestination);
    }
}
