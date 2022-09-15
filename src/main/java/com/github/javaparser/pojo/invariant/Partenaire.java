package fr.gouv.justice.cassiopee.invariant.eurojust.model.partenaire;

import java.util.Date;

import fr.gouv.justice.cassiopee.common.util.HashCodeEqualsHelper;

public abstract class Partenaire implements Comparable<Partenaire>{
    public static final String PAYS = "Pays";

    public static final String ORGANISME = "Organisme";

    private static final HashCodeEqualsHelper<Partenaire> HE_HELPER = HashCodeEqualsHelper.of(Partenaire.class, Partenaire::getDateEntree,
            Partenaire::getId, Partenaire::getRefNationale, Partenaire::getResponsableECE, Partenaire::getName);


    private Long id;
    private String responsableECE;
    private String refNationale;
    private Date dateEntree;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResponsableECE() {
        return responsableECE;
    }

    public void setResponsableECE(String responsableECE) {
        this.responsableECE = responsableECE;
    }

    public String getRefNationale() {
        return refNationale;
    }

    public void setRefNationale(String refNationale) {
        this.refNationale = refNationale;
    }

    public Date getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }

    public abstract String getName();

    @Override
    public int hashCode() {
        return HE_HELPER.getHashCode(this);
    }

    @Override
    public boolean equals(final Object other) {
        return HE_HELPER.areEqual(this, other);
    }

    @Override
    public int compareTo(Partenaire p) {
        return getName().compareTo(p.getName());
    }

}
