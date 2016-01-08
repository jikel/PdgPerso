package modele;

public class Article {
    public enum Groupe {
        ENTREE, PLAT, DESSERT, BOISSONS, DIVERS
    }

    private final String codeArticle; // code unique identifiant l'article
    private final String nom; // son nom complet
    private double prix; // son prix
    private final Groupe groupe;
    private final Categorie cat;

    public Article(String codeArticle, String nom, double prix, Groupe groupe, Categorie cat) throws ExceptionResto {

        if (codeArticle == null || codeArticle.trim().equals("") || nom == null || nom.trim().equals("")) {
            throw new ExceptionResto("Code Article et ou Nom article sont à vide");
        }
        this.codeArticle = codeArticle;
        this.nom = nom;
        this.prix = prix;
        this.groupe = groupe;
        this.cat = cat;

    }

    public String getCodeArticle() {
        return codeArticle;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    /**
     * @param prix
     *            the prix to set
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * @return the groupe
     */
    public Groupe getGroupe() {
        return groupe;
    }

    /**
     * @return the cat
     */
    public Categorie getCategorie() {
        return cat;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codeArticle == null) ? 0 : codeArticle.hashCode());
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        long temp;
        temp = Double.doubleToLongBits(prix);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Article other = (Article) obj;
        if (codeArticle == null) {
            if (other.codeArticle != null)
                return false;
        } else if (!codeArticle.equals(other.codeArticle))
            return false;
        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        if (Double.doubleToLongBits(prix) != Double.doubleToLongBits(other.prix))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Article [codeArticle=" + codeArticle + ", nom=" + nom + ", prix=" + prix + "]";
    }

}