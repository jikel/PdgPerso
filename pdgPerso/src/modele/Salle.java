package modele;

import java.util.ArrayList;
import java.util.List;

public class Salle {
//ID
private final String nom;
/**
 * @return the tables
 */
public List<Table> getTables() {
    return tables;
}
/**
 * @param tables the tables to set
 */
public void setTables(List<Table> tables) {
    this.tables = tables;
}
/**
 * @return the nom
 */
public String getNom() {
    return nom;
}
/**
 * @return the largeur
 */
public int getLargeur() {
    return largeur;
}
/**
 * @return the hauteur
 */
public int getHauteur() {
    return hauteur;
}
public Salle(String nom, int largeur, int hauteur, List<Table> tables) {
    super();
    this.nom = nom;
    this.largeur = largeur;
    this.hauteur = hauteur;
    this.tables = tables;
}
//Version salle rectangulaire (mètres)
private final int largeur;
private final int hauteur;
private List<Table> tables= new ArrayList<>();
}
