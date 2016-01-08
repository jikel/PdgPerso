package modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import modele.Article.Groupe;

public class Facade extends Observable {
    /**
     * Actions possibles
     *
     * @author Didier
     *
     */
    public enum ElementsResto {
        ARTICLE, COMMANDE, LIGNECMD, SERVEUR, TABLE
    }

    public enum ActionsResto {
        AJOUT_ARTICLE, SUPPRESSION_ARTICLE, MODIFICATION_ARTICLE, SELECTION_ARTICLE, AJOUT_COMMANDE, SUPPRESSION_COMMANDE, MODIFICATION_COMMANDE, SELECTION_COMMANDE, AJOUT_LIGNECMD, SUPPRESSION_LIGNECMD, MODIFICATION_LIGNECMD, SELECTION_LIGNECMD, AJOUT_SERVEUR, SUPPRESSION_SERVEUR, MODIFICATION_SERVEUR, SELECTION_SERVEUR, AJOUT_TABLE, SUPPRESSION_TABLE, MODIFICATION_TABLE, SELECTION_TABLE
    };

    /***************************************************************************************************************************/
    // Type et Informations envoyés aux vues
    /**
     * Objet envoyé au vues pour spécifier des informations sur l'évènement
     *
     * @author Didier
     */
    public static class InfoVue {// Rem.: static ne signifie pas ici une classe
                                    // statique comme Math
        private final Object objet;
        private final ActionsResto action;
        private final ElementsResto elem;

        public InfoVue(Object objet, ActionsResto action, ElementsResto elem) {
            super();
            this.objet = objet;
            this.action = action;
            this.elem = elem;
        }

        /**
         * @return the objet
         */
        public Object getObjet() {
            return objet;
        }

        /**
         * @return the action
         */
        public ActionsResto getAction() {
            return action;
        }

        /**
         * @return the element
         */
        public ElementsResto getElement() {
            return elem;
        }
    }

    /***************************************************************************************************************************/
    // pour l'instant une salle
    private Salle salle;

    /* ***************************************** */
    // Maintient les éléments sélectionnés (sur lesquels les actions se feront)
    private Serveur serveurSelect;

    private Article articleSelect;

    private Table tableSelect;

    /* ***************************************** */
    // Maintient les collections par une clé
    // Serveurs dans un dictionnaire trié
    private SortedMap<String, Serveur> serveurs = new TreeMap<String, Serveur>();
    private SortedMap<String, Article> articles = new TreeMap<String, Article>();

    // Liste des catégories
    private Map<String, Categorie> categories = new TreeMap<>();

    /**
     * constructeur Facade
     */
    public Facade() {
/**
 * Charge les données de test avec les catégories et les articles, la salle et ses tables
 */
        chargeDonnees();

    }

    private void chargeDonnees() {
        // Création d'une salle avec des tables
        List<Table> tables = new ArrayList<>();
        Table t1 = new Table(100, 2, 2, 2, false);
        tables.add(t1);
        t1 = new Table(101, 2, 2, 4, true);
        tables.add(t1);
        t1 = new Table(102, 4, 5, 2, true);
        tables.add(t1);
        t1 = new Table(103, 4, 8, 2, true);
        tables.add(t1);
        t1 = new Table(104, 6, 2, 7, false);
        tables.add(t1);
        t1 = new Table(105, 6, 5, 7, false);
        tables.add(t1);
        t1 = new Table(106, 6, 8, 7, false);
        tables.add(t1);
        salle = new Salle("SalleA", 15, 10, tables);

        // Création des catégories principales
        Categorie cat1, cat2; // var temp

        categories.put("B", new Categorie("B", "Boissons", 1));
        categories.put("D", new Categorie("D", "Dessert", 1));
        categories.put("F", new Categorie("F", "Fromage", 1));
        categories.put("P", new Categorie("P", "Poisson", 1));
        categories.put("S", new Categorie("S", "Sauce", 1));
        categories.put("V", new Categorie("V", "Viande", 1));

        // Boissons
        cat1 = categories.get("B");// Boissons
        cat2 = cat1.addCategorieEnfant("BV", "Vins");
        categories.put("BV", cat2);
        categories.put("BVB", cat2.addCategorieEnfant("BVB", "VinBlanc"));
        categories.put("BVR", cat2.addCategorieEnfant("BVR", "VinRouge"));
        categories.put("BVROS", cat2.addCategorieEnfant("BVROS", "VinRosé"));

        categories.put("BS", cat1.addCategorieEnfant("BS", "Boissons froides"));
        categories.put("BC", cat1.addCategorieEnfant("BC", "Boissons chaudes"));
        // Viandes
        cat1 = categories.get("V");
        categories.put("VR", cat1.addCategorieEnfant("VR", "Viande Rouge"));
        categories.put("VB", cat1.addCategorieEnfant("VB", "Viande Blanche"));
        // Dessert
        cat1 = categories.get("D");
        categories.put("DC", cat1.addCategorieEnfant("DC", "Dessert chaud"));
        categories.put("DG", cat1.addCategorieEnfant("DG", "Dessert glacé"));

        // Création d'articles

        try {
            articles.put("JAMSER",
                    new Article("JAMSER", "Jambon serrano", 11.75, Article.Groupe.ENTREE, categories.get("V")));
            articles.put("AVOC", new Article("AVOC", "Avocat aux crevettes grises", 12.25, Article.Groupe.ENTREE,
                    categories.get("P")));
            articles.put("CARPPES", new Article("CARPPES", "Carpaccio de boeuf pesto", 12.25, Article.Groupe.ENTREE,
                    categories.get("V")));
            articles.put("SAUMFUM",
                    new Article("SAUMFUM", "Saumon fumé", 14.25, Article.Groupe.ENTREE, categories.get("P")));
            articles.put("FOIEGRAS",
                    new Article("FOIEGRAS", "Fois gras", 18.25, Article.Groupe.ENTREE, categories.get("V")));
            articles.put("SOUPPOIS",
                    new Article("SOUPPOIS", "Soupe du pêcheur", 8.95, Article.Groupe.ENTREE, categories.get("P")));
            articles.put("CROUFRO", new Article("CROUFRO", "Croustillant au chèvre", 10.75, Article.Groupe.ENTREE,
                    categories.get("F")));
            articles.put("CROQCREV", new Article("CROQCREV", "Croquettes de crevettes", 12.25, Article.Groupe.ENTREE,
                    categories.get("P")));
            articles.put("WOKGAM",
                    new Article("WOKGAM", "Wok de gambas", 16.50, Article.Groupe.ENTREE, categories.get("P")));
            articles.put("LANGOU",
                    new Article("LANGOU", "Langoustines au thym", 16.95, Article.Groupe.ENTREE, categories.get("P")));
            articles.put("PAVEB",
                    new Article("PAVEB", "Pavé de boeuf BMH", 18.25, Article.Groupe.PLAT, categories.get("VR")));
            articles.put("BROCHM",
                    new Article("BROCHM", "Brochette maison", 18.95, Article.Groupe.PLAT, categories.get("VR")));
            articles.put("CONTRFIL",
                    new Article("CONTRFIL", "Contrefilet angus Irl", 24.95, Article.Groupe.PLAT, categories.get("VR")));
            articles.put("CUBEROLL",
                    new Article("CUBEROLL", "Cuberoll d'angus", 25.95, Article.Groupe.PLAT, categories.get("VR")));
            articles.put("COTEOS",
                    new Article("COTEOS", "Côte à l'os", 41.50, Article.Groupe.PLAT, categories.get("VR")));
            articles.put("ENTREC",
                    new Article("ENTREC", "Entrecôte Irl", 47.50, Article.Groupe.PLAT, categories.get("VR")));
            articles.put("COTEAGN",
                    new Article("COTEAGN", "Côtes d'agneau", 22.95, Article.Groupe.PLAT, categories.get("VR")));
            articles.put("ROGNON",
                    new Article("ROGNON", "ris et rognon", 23.75, Article.Groupe.PLAT, categories.get("V")));
            articles.put("FILAM",
                    new Article("FILAM", "Filet américain", 14.95, Article.Groupe.PLAT, categories.get("VR")));
            articles.put("SOLMEUN",
                    new Article("SOLMEUN", "Solettes", 17.95, Article.Groupe.PLAT, categories.get("P")));
            articles.put("SOLRAG",
                    new Article("SOLRAG", "Solettes ragoût", 19.50, Article.Groupe.PLAT, categories.get("P")));
            articles.put("PAVSAUM",
                    new Article("PAVSAUM", "Pavé de saumon", 19.75, Article.Groupe.PLAT, categories.get("P")));
            articles.put("FILSANDR",
                    new Article("FILSANDR", "Filet sandre", 19.75, Article.Groupe.PLAT, categories.get("P")));
            articles.put("CABVAP",
                    new Article("CABVAP", "Cabillaud vapeur", 22.50, Article.Groupe.PLAT, categories.get("P")));
            articles.put("MUSC75",
                    new Article("MUSC75", "Muscadet 75cl", 23.75, Article.Groupe.BOISSONS, categories.get("BVB")));
            articles.put("MUSC50",
                    new Article("MUSC50", "Muscadet 50cl", 14.75, Article.Groupe.BOISSONS, categories.get("BVB")));
            articles.put("SANCB75",
                    new Article("SANCB75", "Sancerre", 34.25, Article.Groupe.BOISSONS, categories.get("BVB")));
            articles.put("CHABL75", new Article("CHABL75", "Petit Chablis 75cl", 35.95, Article.Groupe.BOISSONS,
                    categories.get("BVB")));
            articles.put("POUIL75", new Article("POUIL75", "Pouilly Fuissé 75cl", 48.50, Article.Groupe.BOISSONS,
                    categories.get("BVB")));
            articles.put("POUIL50", new Article("POUIL50", "Pouilly Fuissé 50cl", 35.25, Article.Groupe.BOISSONS,
                    categories.get("BVB")));
            articles.put("BOURG75",
                    new Article("BOURG75", "Bourgogne MC 75cl", 34.95, Article.Groupe.BOISSONS, categories.get("BVR")));
            articles.put("STEM75", new Article("STEM75", "Saint Emilion CL 75cl", 36.95, Article.Groupe.BOISSONS,
                    categories.get("BVR")));
            articles.put("STEM50", new Article("STEM50", "Saint Emilion CL 50cl", 26.25, Article.Groupe.BOISSONS,
                    categories.get("BVR")));
            articles.put("HAUTM75",
                    new Article("HAUTM75", "HautMédoc CP 75cl", 68.50, Article.Groupe.BOISSONS, categories.get("BVR")));
            articles.put("COTPRO75", new Article("COTPRO75", "Côtes Provence CM 75cl", 29.25, Article.Groupe.BOISSONS,
                    categories.get("BVROS")));
            articles.put("SANCR50", new Article("SANCR50", "Sancerre Rosé CM 50cl", 18.95, Article.Groupe.BOISSONS,
                    categories.get("BVROS")));
            articles.put("SAUC_PV",
                    new Article("SAUC_PV", "Sauce au poivre vert", 2.50, Article.Groupe.DIVERS, categories.get("S")));
            articles.put("SAUC_RQ",
                    new Article("SAUC_RQ", "Sauce au roquefort", 2.50, Article.Groupe.DIVERS, categories.get("S")));
            articles.put("SAUC_BE",
                    new Article("SAUC_BE", "Sauce béarnaise", 2.50, Article.Groupe.DIVERS, categories.get("S")));
            articles.put("BCOCA",
                    new Article("BCOCA", "Cocacola", 3.00, Article.Groupe.BOISSONS, categories.get("BS")));
            articles.put("BCOCAL",
                    new Article("BCOCAL", "Coca ligth", 3.00, Article.Groupe.BOISSONS, categories.get("BS")));
            articles.put("BCOCAZ",
                    new Article("BCOCAZ", "Coca zéro", 3.00, Article.Groupe.BOISSONS, categories.get("BS")));
            articles.put("DDB", new Article("DDB", "Dame Blanche", 9.50, Article.Groupe.DESSERT, categories.get("DG")));
            articles.put("DMM",
                    new Article("DMM", "Moëlleux à la Mangue", 7.50, Article.Groupe.DESSERT, categories.get("DC")));
            articles.put("DPP", new Article("DPP", "Pain Perdu", 8.50, Article.Groupe.DESSERT, categories.get("DC")));
            articles.put("EAUPL25",
                    new Article("EAUPL25", "Eau plate SPA 1/4", 3.00, Article.Groupe.BOISSONS, categories.get("BS")));
            articles.put("EAUPL50",
                    new Article("EAUPL50", "Eau plate SPA 1/2", 3.00, Article.Groupe.BOISSONS, categories.get("BS")));
            articles.put("EAUPL100",
                    new Article("EAUPL100", "Eau plate SPA", 3.00, Article.Groupe.BOISSONS, categories.get("BS")));
            articles.put("EAUPE25", new Article("EAUPE25", "Eau pétillante BRU 1/4", 3.00, Article.Groupe.BOISSONS,
                    categories.get("BS")));
            articles.put("EAUPE50", new Article("EAUPE50", "Eau pétillante BRU 1/2", 3.00, Article.Groupe.BOISSONS,
                    categories.get("BS")));
            articles.put("EAUPE75", new Article("EAUPE75", "Eau pétillante BRU 3/4", 3.00, Article.Groupe.BOISSONS,
                    categories.get("BS")));

        } catch (ExceptionResto e) {

            e.printStackTrace();
        }




    }

    /**
     *
     * @return la salle du resto avec ses tables
     */
    public Salle getSalle() {

        return salle;
    }

    // Setter et Getter des éléments sélectionnés

    /**
     * @return the tableSelect
     */
    public Table getTableSelect() {
        return tableSelect;
    }

    /**
     * @param tableSelect
     *            the tableSelect to set
     */
    public void setTableSelect(Table table) {
        this.tableSelect = table;
        avertirVues(new InfoVue(table, ActionsResto.SELECTION_TABLE, ElementsResto.TABLE));
    }

    /**
     * sélectionne une table à partir d'une coordonnée x,y
     *
     * @param x
     * @param y
     */
    public void setTableSelect(float x, float y) {
        Table table = null;
        try {// version avec stream Java8
            // recherche le premier élément qui répond au critère, s'il n'existe pas lance l'exception
            table=salle.getTables().stream().filter((t)->t.isCoordInTable(x, y)).findFirst().get();
            setTableSelect(table);
            // juste pour les tests
            JOptionPane.showMessageDialog(null, table);
        } catch (NoSuchElementException e) {

        }

    }

    /**
     * @return the serveurSelect
     */
    public Serveur getServeurSelect() {
        return serveurSelect;
    }

    /**
     * @param serveurSelect
     *            the serveurSelect to set
     */
    public void setServeurSelect(Serveur serveurSelect) {
        this.serveurSelect = serveurSelect;
    }

    /**
     * @return the articleSelect
     */
    public Article getArticleSelect() {
        return articleSelect;
    }

    /**
     * @param articleSelect
     *            Le code de l'article sélectionné ou null
     * @throws ExceptionResto
     */
    public void setArticleSelect(String codeArticle) throws ExceptionResto {
        Article a = null;
        // même article ?
        if (articleSelect != null && articleSelect.getCodeArticle().equals(codeArticle))
            return;
        if (codeArticle == null && articleSelect == null)
            return;

        if (codeArticle == null)
            a = null;
        else {
            a = articles.get(codeArticle);
            if (a == null)
                throw new ExceptionResto("L'article n'existe pas: " + codeArticle);
        }
        articleSelect = a;
        avertirVues(new InfoVue(a, ActionsResto.SELECTION_ARTICLE, ElementsResto.ARTICLE));

        this.articleSelect = a;

    }



    // ******************* Gestion des Serveurs **********************
    /*
     * Ajoute un Serveur. Le code doit être unique
     */
    public void addServeur(String id, String nom, String prenom) throws ExceptionResto {
        if (serveurs.containsKey(id)) {
            throw new ExceptionResto("Le serveur" + id + " existe déjà");
        }

        serveurs.put(id, new Serveur(id, nom, prenom));

    }

    /**
     *
     * @return un vecteur de Serveur
     */
    public Serveur[] getListeServeur() {
        return serveurs.values().toArray(new Serveur[0]);
    }

    /**
     *
     * @return une liste triée des codes des serveurs
     */
    public String[] getIdsServeur() {
        return serveurs.keySet().toArray(new String[0]);
    }

    /**
     * Permet d'avoir l'objet serveur à partir de son id
     *
     * @param id
     * @return l'objet serveur
     * @throws ExceptionResto
     */
    public Serveur getServeurFromId(String id) throws ExceptionResto {
        Serveur serveur = serveurs.get(id);
        if (serveur == null) {
            throw new ExceptionResto(" Serveur inconnu : " + id);
        }
        return serveur;

    }

    // ******************* Gestion des Articles **********************
    /*
     * Ajoute un Article. Le code doit être unique
     */

    public void addArticle(String id, String nom, double prix, Groupe groupe, Categorie cat) throws ExceptionResto {
        if (articles.containsKey(id)) {
            throw new ExceptionResto("L'article" + id + " existe déjà");
        }

        Article a = new Article(id, nom, prix, groupe, cat);
        articles.put(id, a);
        avertirVues(new InfoVue(a, ActionsResto.AJOUT_ARTICLE, ElementsResto.ARTICLE));

    }

    /*
     * Ajoute un Article. Le code doit être unique
     */
    public void addArticle(Article a) throws ExceptionResto {
        String id = a.getCodeArticle();
        if (articles.containsKey(id)) {
            throw new ExceptionResto("L'article" + id + " existe déjà");
        }

        articles.put(id, a);
        avertirVues(new InfoVue(a, ActionsResto.AJOUT_ARTICLE, ElementsResto.ARTICLE));

    }

    /**
     *
     * @param grp
     *            précise le groupe ou null si pas de groupe précisé
     * @param cat
     *            précise la catégorie ou null si pas de catégorie précisée
     * @return un vecteur d'articles répondant au critère
     */
    public List<Article> getListeArticles(Groupe grp, Categorie cat) {
        if (grp == null && cat == null)
            return Collections.unmodifiableList(new ArrayList<Article>(articles.values()));
        return Collections.unmodifiableList(articles.values().stream()
                .filter((a) -> (grp == null || a.getGroupe() == grp) && (cat == null || a.getCategorie().equals(cat)))
                .collect(Collectors.toList()));

    }

    /**
     *
     * @return une liste triée des codes article
     */
    public String[] getIdsArticles() {
        return articles.keySet().toArray(new String[0]);
    }

    /**
     * Permet d'avoir l'objet article à partir de son id
     *
     * @param id
     * @return l'objet article
     * @throws ExceptionResto
     */
    public Article getArticleFromId(String id) throws ExceptionResto {
        Article article = articles.get(id);
        if (article == null) {
            throw new ExceptionResto(" Article inconnu : " + id);
        }
        return article;

    }

    /**
     *
     * @param id
     *            de l'article à supprimer
     * @throws ExceptionResto
     *             si l'id n'existe pas
     */
    public void delArticle(String id) throws ExceptionResto {
        Article a = articles.remove(id);
        if (a == null) {
            throw new ExceptionResto(" Article inconnu : " + id);
        }

        avertirVues(new InfoVue(a, ActionsResto.SUPPRESSION_ARTICLE, ElementsResto.ARTICLE));
    }

    /******************************************************************************************/
    // Catégories
    /**
     *
     * @return la liste des catégories principales
     */
    public List<Categorie> getCategoriesPrincipales() {

        return categories.values().stream().filter((c) -> c.getNiv() == 1).collect(Collectors.toList());

    }

    /**
     *
     * @return la liste des catégories feuilles
     */
    public List<Categorie> getCategoriesFeuilles() {

        return categories.values().stream().filter((c) -> c.isFeuille()).collect(Collectors.toList());

    }
/**
 *
 * @param id d'une catégorie
 * @return la catégorie si elle existe
 * @throws ExceptionResto si la catégorie n'existe pas
 */
    public Categorie getCategorieFromId(String id) throws ExceptionResto {
        Categorie c = categories.get(id);
        if (c == null)
            throw new ExceptionResto(" Catégorie inconnue : " + id);
        return c;

    }

    /**
     * Permet d'avertir les vues
     *
     * @param infoVue
     */

    private void avertirVues(InfoVue infoVue) {
        setChanged();
        if (infoVue == null)
            notifyObservers();
        else
            notifyObservers(infoVue);

    }
}