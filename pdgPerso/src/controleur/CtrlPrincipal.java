package controleur;

import java.util.List;

import javax.swing.SwingUtilities;

import modele.Article;
import modele.Categorie;
import modele.ExceptionResto;
import modele.Facade;
import vue.VuePrincipale;

public class CtrlPrincipal {

    private CtrlArticle ctrlArticle;
    private VuePrincipale vuePrincipale;
    private Facade facade;

    public CtrlPrincipal() {

        facade = new Facade();

        ctrlArticle = new CtrlArticle(facade);

        vuePrincipale = new VuePrincipale(this);

        // enregistre la Vue principale sur la façade
        facade.addObserver(vuePrincipale);

    }

    public void ajoutArticle() {
        ctrlArticle.getView();

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new CtrlPrincipal());

    }

    public void addServeur(String id, String nom, String prenom) throws ExceptionResto {
        /*Categorie cat=facade.getCategorieFromId("P");
        System.out.println(cat);
        List<Article> a = facade.getListeArticles(Article.Groupe.PLAT, cat);
        a.stream().forEach((e) -> System.out.println(e));*/
        facade.addServeur(id, nom, prenom);
    }

    public List<Article> getArticles() {

        return facade.getListeArticles(null, null);
    }

    public void supprimeArticle(String codeArticle) throws ExceptionResto {
        facade.delArticle(codeArticle);

    }

    public void selectionArticle(String codeArticle) throws ExceptionResto {
        facade.setArticleSelect(codeArticle);

    }

}