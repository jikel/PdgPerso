package controleur;

import modele.Facade;
import vue.AjoutArticle;

public class CtrlArticle {
    private Facade facade;
    private AjoutArticle vue;

    public CtrlArticle(Facade facade) {
        super();
        this.facade = facade;
    }


    public void getView() {
      AjoutArticle.ajoutArticle(facade);

    }
}