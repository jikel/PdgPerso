package modele;

public enum EtatCmd {
    EN_COURS(1), PAYEE(3), ANNULEE(2), PERTE(2);

    private int valeur;

    public int getValeur() {
        return valeur;
    }

    private EtatCmd(int i) {
        this.valeur = i;
    }

}