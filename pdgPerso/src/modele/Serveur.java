package modele;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

/**
 *
 * @author Didier
 *
 */
public class Serveur {
    private final String id;
    private final String nom;
    private final String prenom;

    /**
     *
     * @param id
     *            : code unique du serveur
     * @param nom
     * @param prenom
     */
    public Serveur(String id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    @Override
    public String toString() {
        return "Serveur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
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
        Serveur other = (Serveur) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        if (prenom == null) {
            if (other.prenom != null)
                return false;
        } else if (!prenom.equals(other.prenom))
            return false;
        return true;
    }

    public static void main(String[] args) {
        Serveur s1 = new Serveur("dvo", "VO", "Didier");
        Serveur s2 = new Serveur("dvo", "VO", "Didier");
        // String nom= JOptionPane.showInputDialog("Entrez un nom: ");

        EtatCmd etat = EtatCmd.EN_COURS;

        JOptionPane.showMessageDialog(null, etat.getValeur() + etat.name());

        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date d1 = sdf.parse("22/09/2015");

            JOptionPane.showMessageDialog(null, sdf.format(d1));



        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Date erreur");
        }

    }

}