package modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Categorie {
    private final String id; // code unique d'une catégorie
    private Categorie catPere;


    private final String nom;
    private final int niv; // >0
    private List<Categorie> catEnfants;

    public Categorie(String id, String nom, int niv) {
        super();
        this.id = id;
        this.nom = nom;
        this.niv = niv;
        catEnfants = null; // création lors de l'ajout d'une catégorie enfant
    }

    /**
     * Ajoute une catégorie et calcul le niveau
     *
     * @param nom
     * @return la catégorie créée
     */
    public Categorie addCategorieEnfant(String id, String nom) {
        Categorie cat = new Categorie(id, nom, niv + 1);
        cat.setCatPere(this);// ajuste le lien bidirectionnel
        if (catEnfants == null)
            catEnfants = new ArrayList<>();
        catEnfants.add(cat);
        return cat;
    }
    /**
     * @return the catPere
     */
    public Categorie getCatPere() {
        return catPere;
    }

    /**
     * @param catPere the catPere to set
     */
    protected void setCatPere(Categorie catPere) {
        this.catPere = catPere;
    }
    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return the niv
     */
    public int getNiv() {
        return niv;
    }

    /**
     * @return une liste non modifiable des catégories enfants
     */
    public List<Categorie> getCatEnfants() {

        return (List<Categorie>) Collections.unmodifiableCollection(catEnfants);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + niv;
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Categorie other = (Categorie) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (niv != other.niv)
            return false;
        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return id+" : "+ nom ;
    }

    /**
     * Permet de savoir si la catégorie possède des catégories enfants
     *
     * @return vrai si elle n'en possède pas
     */
    public Boolean isFeuille() {

        return catEnfants == null;
    }
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * Permet de savoir si la catégorie précisée en paramètre est égale à la catégorie en cours ou une catégorie parent
     * @param cat
     * @return
     */
    public boolean isCategorie(Categorie cat){
        if (cat==null)return false;
        return cat.equals(this)||catPere!=null && catPere.isCategorie(cat);
    }

}