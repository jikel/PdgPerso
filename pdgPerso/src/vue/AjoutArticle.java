package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import modele.Article;
import modele.Categorie;
import modele.ExceptionResto;
import modele.Facade;

public class AjoutArticle extends JDialog {
    private JButton btAjout;
    private JButton btAnnuler;
    private boolean ok = false; // ok = true si l'on sélectionne le bouton OK
    private PanelArticle vueArticle;
    private Article article = null;
    private Facade facade;

    /**
     * Création d'une vue pour ajouter des articles
     *
     * @param title
     * @param categorie
     * @throws HeadlessException
     */

    private AjoutArticle(String title, Facade facade) throws HeadlessException {
        super();
        setTitle(title);
        this.facade = facade;
        initGui();
        pack();

    }

    private void initGui() {
        Container cp = getContentPane();
        vueArticle = new PanelArticle(facade.getCategoriesFeuilles());
        // VueArticle vueA=new VueArticle(); // ancien JPanel fait au cours
        // (remplacé par PanelArticle)
        cp.add(vueArticle, BorderLayout.CENTER);
        // ajout des boutons
        JPanel pBoutons = new JPanel();
        FlowLayout flowLayout = (FlowLayout) pBoutons.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        btAjout = new JButton("Ajout");
        btAnnuler = new JButton("Annuler");
        pBoutons.add(btAjout);
        pBoutons.add(btAnnuler);
        cp.add(pBoutons, BorderLayout.SOUTH);

        btAjout.addActionListener((e) -> {
            // ajout d'un Article
            try {
                article = new Article(vueArticle.getZtCode().getText(), vueArticle.getZtNom().getText(),
                        (Double) vueArticle.getZtPrix().getValue(),
                        Article.Groupe.valueOf(vueArticle.getCbGroupe().getSelectedItem().toString()),
                        vueArticle.getCbCategorie());

                ok = true;
                this.setVisible(false);
            } catch (ExceptionResto e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), " ERREUR", JOptionPane.ERROR_MESSAGE);
            }

        });

        btAnnuler.addActionListener((e) -> this.setVisible(false));

    }

    public static void ajoutArticle(Facade facade) {
        boolean sortie;
        AjoutArticle vue = new AjoutArticle("Ajout d'un article", facade);
        vue.setModal(true);
        do {
            vue.setVisible(true);

            // Attente du retour

            sortie = !vue.ok; // on sort si pas d'appui sur le bouton ok
            if (vue.ok)

                try {// essaye l'ajout d'un article
                    facade.addArticle(vue.article);
                    sortie = true;
                } catch (ExceptionResto e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), " ERREUR", JOptionPane.ERROR_MESSAGE);
                    vue.ok = false;
                    sortie = false;

                }
        } while (!sortie);
        vue.dispose();

    }

}
