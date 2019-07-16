package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Produit.
 */
@Entity
@Table(name = "produit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 250)
    @Column(name = "libelle", length = 250, nullable = false)
    private String libelle;

    @NotNull
    @Size(max = 50)
    @Column(name = "reference", length = 50, nullable = false)
    private String reference;

    @NotNull
    @Column(name = "numeroordre", nullable = false)
    private Integer numeroordre;

    @NotNull
    @Size(max = 50)
    @Column(name = "typeproduit", length = 50, nullable = false)
    private String typeproduit;

    @NotNull
    @Size(max = 50)
    @Column(name = "familleproduit", length = 50, nullable = false)
    private String familleproduit;

    @NotNull
    @Column(name = "produitadditionnel", nullable = false)
    private Boolean produitadditionnel;

    @NotNull
    @Column(name = "obligatoirepourentreprise", nullable = false)
    private Boolean obligatoirepourentreprise;

    @Column(name = "effectifmax")
    private Integer effectifmax;

    @NotNull
    @Size(max = 15)
    @Column(name = "chartegraphique", length = 15, nullable = false)
    private String chartegraphique;

    @Size(max = 255)
    @Column(name = "alertetarificationexterne", length = 255)
    private String alertetarificationexterne;

    @NotNull
    @Column(name = "questionnairerecexige", nullable = false)
    private Boolean questionnairerecexige;

    @NotNull
    @Size(max = 50)
    @Column(name = "libellemodule", length = 50, nullable = false)
    private String libellemodule;

    @Size(max = 20)
    @Column(name = "nomchampbadh", length = 20)
    private String nomchampbadh;

    @Size(max = 50)
    @Column(name = "typequestionnairerec", length = 50)
    private String typequestionnairerec;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "module_definition_id")
    @JsonIgnoreProperties("produits")
    private Moduledefinition moduledefinition;

    @OneToMany(mappedBy = "produit")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Garantie> garanties = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public Produit libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getReference() {
        return reference;
    }

    public Produit reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getNumeroordre() {
        return numeroordre;
    }

    public Produit numeroordre(Integer numeroordre) {
        this.numeroordre = numeroordre;
        return this;
    }

    public void setNumeroordre(Integer numeroordre) {
        this.numeroordre = numeroordre;
    }

    public String getTypeproduit() {
        return typeproduit;
    }

    public Produit typeproduit(String typeproduit) {
        this.typeproduit = typeproduit;
        return this;
    }

    public void setTypeproduit(String typeproduit) {
        this.typeproduit = typeproduit;
    }

    public String getFamilleproduit() {
        return familleproduit;
    }

    public Produit familleproduit(String familleproduit) {
        this.familleproduit = familleproduit;
        return this;
    }

    public void setFamilleproduit(String familleproduit) {
        this.familleproduit = familleproduit;
    }

    public Boolean isProduitadditionnel() {
        return produitadditionnel;
    }

    public Produit produitadditionnel(Boolean produitadditionnel) {
        this.produitadditionnel = produitadditionnel;
        return this;
    }

    public void setProduitadditionnel(Boolean produitadditionnel) {
        this.produitadditionnel = produitadditionnel;
    }

    public Boolean isObligatoirepourentreprise() {
        return obligatoirepourentreprise;
    }

    public Produit obligatoirepourentreprise(Boolean obligatoirepourentreprise) {
        this.obligatoirepourentreprise = obligatoirepourentreprise;
        return this;
    }

    public void setObligatoirepourentreprise(Boolean obligatoirepourentreprise) {
        this.obligatoirepourentreprise = obligatoirepourentreprise;
    }

    public Integer getEffectifmax() {
        return effectifmax;
    }

    public Produit effectifmax(Integer effectifmax) {
        this.effectifmax = effectifmax;
        return this;
    }

    public void setEffectifmax(Integer effectifmax) {
        this.effectifmax = effectifmax;
    }

    public String getChartegraphique() {
        return chartegraphique;
    }

    public Produit chartegraphique(String chartegraphique) {
        this.chartegraphique = chartegraphique;
        return this;
    }

    public void setChartegraphique(String chartegraphique) {
        this.chartegraphique = chartegraphique;
    }

    public String getAlertetarificationexterne() {
        return alertetarificationexterne;
    }

    public Produit alertetarificationexterne(String alertetarificationexterne) {
        this.alertetarificationexterne = alertetarificationexterne;
        return this;
    }

    public void setAlertetarificationexterne(String alertetarificationexterne) {
        this.alertetarificationexterne = alertetarificationexterne;
    }

    public Boolean isQuestionnairerecexige() {
        return questionnairerecexige;
    }

    public Produit questionnairerecexige(Boolean questionnairerecexige) {
        this.questionnairerecexige = questionnairerecexige;
        return this;
    }

    public void setQuestionnairerecexige(Boolean questionnairerecexige) {
        this.questionnairerecexige = questionnairerecexige;
    }

    public String getLibellemodule() {
        return libellemodule;
    }

    public Produit libellemodule(String libellemodule) {
        this.libellemodule = libellemodule;
        return this;
    }

    public void setLibellemodule(String libellemodule) {
        this.libellemodule = libellemodule;
    }

    public String getNomchampbadh() {
        return nomchampbadh;
    }

    public Produit nomchampbadh(String nomchampbadh) {
        this.nomchampbadh = nomchampbadh;
        return this;
    }

    public void setNomchampbadh(String nomchampbadh) {
        this.nomchampbadh = nomchampbadh;
    }

    public String getTypequestionnairerec() {
        return typequestionnairerec;
    }

    public Produit typequestionnairerec(String typequestionnairerec) {
        this.typequestionnairerec = typequestionnairerec;
        return this;
    }

    public void setTypequestionnairerec(String typequestionnairerec) {
        this.typequestionnairerec = typequestionnairerec;
    }

    public Moduledefinition getModuledefinition() {
        return moduledefinition;
    }

    public Produit moduledefinition(Moduledefinition moduledefinition) {
        this.moduledefinition = moduledefinition;
        return this;
    }

    public void setModuledefinition(Moduledefinition moduledefinition) {
        this.moduledefinition = moduledefinition;
    }

    public Set<Garantie> getGaranties() {
        return garanties;
    }

    public Produit garanties(Set<Garantie> garanties) {
        this.garanties = garanties;
        return this;
    }

    public Produit addGarantie(Garantie garantie) {
        this.garanties.add(garantie);
        garantie.setProduit(this);
        return this;
    }

    public Produit removeGarantie(Garantie garantie) {
        this.garanties.remove(garantie);
        garantie.setProduit(null);
        return this;
    }

    public void setGaranties(Set<Garantie> garanties) {
        this.garanties = garanties;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Produit)) {
            return false;
        }
        return id != null && id.equals(((Produit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Produit{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", reference='" + getReference() + "'" +
            ", numeroordre=" + getNumeroordre() +
            ", typeproduit='" + getTypeproduit() + "'" +
            ", familleproduit='" + getFamilleproduit() + "'" +
            ", produitadditionnel='" + isProduitadditionnel() + "'" +
            ", obligatoirepourentreprise='" + isObligatoirepourentreprise() + "'" +
            ", effectifmax=" + getEffectifmax() +
            ", chartegraphique='" + getChartegraphique() + "'" +
            ", alertetarificationexterne='" + getAlertetarificationexterne() + "'" +
            ", questionnairerecexige='" + isQuestionnairerecexige() + "'" +
            ", libellemodule='" + getLibellemodule() + "'" +
            ", nomchampbadh='" + getNomchampbadh() + "'" +
            ", typequestionnairerec='" + getTypequestionnairerec() + "'" +
            "}";
    }
}
