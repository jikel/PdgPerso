package modele;

import java.awt.geom.Rectangle2D;

public class Table {
    public final static float larg = 0.8f;
    // id de la table
    private final int id;
    private final int nbPlaces;
    // position relative par rapport à la salle point sup/gauche
    private final int posX;
    private final int posY;
    // table placée horizontalement (false) ou verticalement (true)
    private final boolean sens;

    private boolean libre;
    // permet de savoir si une position est dans la table lors d'une sélection
    private final Rectangle2D.Float rect;

    public Table(int id, int nbPlaces, int posX, int posY, boolean sens) {
        super();
        this.id = id;
        this.nbPlaces = nbPlaces;
        this.posX = posX;
        this.posY = posY;
        this.sens = sens;
        if (sens)
            this.rect = new Rectangle2D.Float(posX, posY, larg, larg * nbPlaces / 2.0f);
        else
            this.rect = new Rectangle2D.Float(posX, posY, larg * nbPlaces / 2.0f, larg);
        libre = true;
    }

    /**
     * @return the nbPlaces
     */
    public int getNbPlaces() {
        return nbPlaces;
    }

    /**
     * @return the posX
     */
    public int getPosX() {
        return posX;
    }

    /**
     * @return the posY
     */
    public int getPosY() {
        return posY;
    }

    /**
     * @return the sens
     */
    public boolean isSens() {
        return sens;
    }

    /**
     * permet de savoir si la position appartient à la table
     *
     * @param x
     *            de gauche à droite
     * @param y
     *            de haut en bas
     * @return vrai si le point est dans le rectangle
     */
    public boolean isCoordInTable(float x, float y) {
        return rect.contains(x, y);
    }

    /**
     * Permet de savoir si la table est occupée ou non
     *
     * @return
     */
    public boolean isLibre() {

        return libre;
    }

    /**
     * Indique si la table est libre ou non
     *
     * @param libre
     */
    public void setLibre(boolean libre) {
        this.libre = libre;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Table [id=" + id + ", nbPlaces=" + nbPlaces + "]";
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
        result = prime * result + id;
        result = prime * result + nbPlaces;
        result = prime * result + posX;
        result = prime * result + posY;
        result = prime * result + (sens ? 1231 : 1237);
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
        Table other = (Table) obj;
        if (id != other.id)
            return false;
        if (nbPlaces != other.nbPlaces)
            return false;
        if (posX != other.posX)
            return false;
        if (posY != other.posY)
            return false;
        if (sens != other.sens)
            return false;
        return true;
    }

}
