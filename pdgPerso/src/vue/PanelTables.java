package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modele.Facade;
import modele.Facade.InfoVue;
import modele.Salle;
import modele.Table;

public class PanelTables extends JPanel implements Observer {

    private final Color COULEUR_TABLE_LIBRE = Color.magenta;
    private final Color COULEUR_TABLE_OCCUPEE = Color.cyan;
    private final Color COULEUR_SALLE = Color.blue;
    private Salle salle;
    private float rap = 1;
    private float rapX = 1;
    private float rapY = 1;
    private float px, py;
    private Shape s;

    private Facade facade;

    public PanelTables(Facade facade) {
        super();
        this.facade = facade;
        // s'enregistre sur la facade
        facade.addObserver(this);

        salle = this.facade.getSalle();
        ajusteRapport();// calcul les rapports pour l'affichage
        setOpaque(true);
        // gestion d'un clic de souris pour sélectionner une table
        addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    // demande la sélection de la table en x,y
                    PanelTables.this.facade.setTableSelect(e.getX() / rap, e.getY() / rap);
                }

            }
        });
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.Container#paintComponents(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics g) {

        super.paint(g);
        ajusteRapport();// ajuste champ rap

        g.setColor(COULEUR_SALLE);
        g.drawRect(0, 0, Math.round(salle.getLargeur() * rap), Math.round(salle.getHauteur() * rap));

        for (Table t : salle.getTables()) {
            px = t.getPosX() * rap;
            py = t.getPosY() * rap;

            if (t.isLibre())
                g.setColor(COULEUR_TABLE_LIBRE);
            else
                g.setColor(COULEUR_TABLE_OCCUPEE);
            // table horizontale ou verticale
            if (t.isSens())
                g.fillRect(Math.round(px), Math.round(py), Math.round(Table.larg * rap),
                        Math.round(t.getNbPlaces() * Table.larg * rap / 2.0f));

            else
                g.fillRect(Math.round(px), Math.round(py), Math.round(t.getNbPlaces() * Table.larg * rap / 2.0f),
                        Math.round(Table.larg * rap));

        }

    }

    /**
     * Ajuste le rapport en fonction des dimensions de la fenêtre et de la salle
     */
    private void ajusteRapport() {
        rapX = (float) (getSize().getWidth() / salle.getLargeur()) - 1;
        rapY = (float) (getSize().getHeight() / salle.getHauteur()) - 1;
        rap = Math.min(rapX, rapY);
    }

    /**
     * Permet de préciser la salle à afficher
     *
     * @param salle
     */
    public void setSalle(Salle salle) {
        this.salle = salle;
        repaint();
    }

    /**
     * Permet d'être averti des changements les tables
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object obj) {
        if (obj instanceof InfoVue) {
            InfoVue info = (InfoVue) obj;
            switch (info.getElement()) {
            case TABLE:
                // JOptionPane.showMessageDialog(this, info.getObjet().toString());
                salle = facade.getSalle();
                repaint();
            }

        }

    }

}
