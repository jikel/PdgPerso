package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import controleur.CtrlPrincipal;
import modele.Article;
import modele.ExceptionResto;
import modele.Facade.ActionsResto;
import modele.Facade.InfoVue;

public class VuePrincipale extends JFrame implements Observer {
    /**
     * Modèle de ma JTable pour les articles
     *
     * @author Didier
     *
     */
    class ModeleArticle extends AbstractTableModel {
        /*
         * (non-Javadoc)
         *
         * @see javax.swing.table.AbstractTableModel#getColumnName(int)
         */
        @Override
        public String getColumnName(int c) {
            switch (c) {
            case 0:
                return "Code Article";
            case 1:
                return " Nom Article";
            case 2:
                return " Prix Article";
            }
            return "????";
        }

        private List<Article> articles;
        private Article a;

        @Override
        public int getColumnCount() {

            return 3;
        }

        @Override
        public int getRowCount() {
            return articles == null ? 0 : articles.size();
        }

        @Override
        public Object getValueAt(int l, int c) {
            a = articles.get(l);
            if (a == null)
                return ("null");
            switch (c) {
            case 0:
                return a.getCodeArticle();
            case 1:
                return a.getNom();
            case 2:
                return a.getPrix();
            }

            return "????";
        }

        /*
         * (non-Javadoc)
         *
         * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
         */
        @Override
        public boolean isCellEditable(int i, int j) {

            return j == 2;
        }

        /*
         * (non-Javadoc)
         *
         * @see
         * javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object,
         * int, int)
         */
        @Override
        public void setValueAt(Object valeur, int i, int c) {
            a = articles.get(i);
            switch (c) {
            case 2:
                a.setPrix((Double) valeur);
                break;

            }

        }

        /**
         * @return the articles
         */
        public List<Article> getArticles() {
            return articles;
        }

        /*
         * (non-Javadoc)
         *
         * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
         */
        @Override
        public Class<?> getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /**
         * @param articles
         *            the articles to set
         */
        public void setArticles(List<Article> articles) {
            this.articles = articles;
            fireTableDataChanged();
        }

        public Article getArticle(int i) {

            return articles.get(i);
        }

    }

    private JTextField ztNom;
    private JButton btAjoutArticle;
    private JButton btServeur;
    private CtrlPrincipal controleur;

    // La liste des actions
    private ActionMap actions;
    private JTable tblArticle;
    private ModeleArticle monModeleArticle;

    public VuePrincipale() throws HeadlessException {
        this(null);
    }

    public VuePrincipale(CtrlPrincipal ctrlPrincipal) {
        super("Projet 2015/2016");
        this.controleur = ctrlPrincipal;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGui();
        pack();
        setVisible(true);
    }

    private void initGui() {
        // Création de la liste d'actions
        creerActionMap();
        Container cp = getContentPane(); // JPanel avec BorderLayout
        // Label nord
        JLabel lblTitre = new JLabel("Projet PDG 2015-2016");
        lblTitre.setOpaque(true);
        lblTitre.setBackground(new Color(135, 206, 250));
        lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitre.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        cp.add(lblTitre, BorderLayout.NORTH);
        // Texte input
        ztNom = new JTextField();
        ztNom.setBackground(new Color(176, 196, 222));

        cp.add(ztNom, BorderLayout.SOUTH);
        JPanel pBoutons = new JPanel();

        // Boutons
        btAjoutArticle = new JButton();
        btAjoutArticle.setAction(actions.get(ActionsResto.AJOUT_ARTICLE));
        pBoutons.add(btAjoutArticle);

        // Bt Serveur
        btServeur = new JButton();
        btServeur.setAction(actions.get(ActionsResto.AJOUT_SERVEUR));
        pBoutons.add(btServeur);

        cp.add(pBoutons, BorderLayout.SOUTH);

        monModeleArticle = new ModeleArticle();
        monModeleArticle.setArticles(controleur.getArticles());
        tblArticle = new JTable(monModeleArticle);
        tblArticle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblArticle.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent a) {
                if (a.getValueIsAdjusting())
                    return;

                // prend la ligne sélectionné
                int i = tblArticle.getSelectedRow();
                if (i >= 0) {
                    // convertion num ligne vue vers modèle ( cas du tri )
                    i = tblArticle.convertRowIndexToModel(i);
                    Article art = monModeleArticle.getArticle(i);
                    try {
                        // supprime l'article sélectionné
                        controleur.selectionArticle(art.getCodeArticle());
                    } catch (ExceptionResto e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }

                }
            }
        });
        JPanel centrePanel = new JPanel(); // Le panel de la JTable
        centrePanel.add(new JScrollPane(tblArticle));

        cp.add(centrePanel, BorderLayout.CENTER);

    }

    /**
     * Création de la liste des actions
     *
     */
    private void creerActionMap() {

        if (actions != null)
            return;
        // Création d'une actionMap
        actions = new ActionMap();

        /******************************
         * Action pour AjoutServeur
         ******************************/

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    VuePrincipale.this.controleur.addServeur("VO", "VAN OUDENHOVE", "Didier");
                    JOptionPane.showMessageDialog(null, "YES");
                } catch (ExceptionResto e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                }

            }
        };
        setParamsAction(action, "Ajout Serveur", "Ajout d'un nouveau serveur", KeyEvent.VK_F10, null); // "images/AjoutServeur.png");
        actions.put(ActionsResto.AJOUT_SERVEUR, action);

        /******************************
         * Action pour AjoutArticle
         ******************************/

        action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.ajoutArticle();
            }
        };
        setParamsAction(action, "Ajout Article", "Ajout d'un article", KeyEvent.VK_F8, null); // "images/AjoutServeur.png");
        actions.put(ActionsResto.AJOUT_ARTICLE, action);

    }

    /**
     * Spécifie les paramètres d'une action: Le nom une description courte une
     * touche rapide dans le menu un icon
     *
     * @param action
     * @param nom
     * @param description
     * @param touche
     * @param image
     */
    private void setParamsAction(Action action, String nom, String description, Integer touche, String image) {
        action.putValue(Action.NAME, nom);
        action.putValue(Action.SHORT_DESCRIPTION, description);
        if (touche != null)
            action.putValue(Action.MNEMONIC_KEY, touche);

        if (image != null) {
            URL url = VuePrincipale.class.getResource(image);
            if (url != null)
                action.putValue(Action.SMALL_ICON, new ImageIcon(url));
        }

    }

    public static void main(String[] args) {
        VuePrincipale princ = new VuePrincipale();

    }

    // MAJ par l'observable
    @Override
    public void update(Observable o, Object obj) {
        if (obj instanceof InfoVue) {
            InfoVue info = (InfoVue) obj;
            switch (info.getAction()) {
            case AJOUT_ARTICLE:
            case MODIFICATION_ARTICLE:
            case SUPPRESSION_ARTICLE:
                monModeleArticle.setArticles(controleur.getArticles());
                break;

            }

        }

    }

}