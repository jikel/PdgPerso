package vue;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import modele.Article;
import modele.Categorie;

import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;

public class PanelArticle extends JPanel {
    private JTextField ztCode;
    private JTextField ztNom;
    private JFormattedTextField ztPrix;
    private JComboBox<Categorie> cbCategorie;
    private JComboBox<Article.Groupe> cbGroupe;

    /**
     * Create the panel.
     */
    public PanelArticle(){
        this(new ArrayList<Categorie>());
    }

    /**
     *
     * @param categories
     */
    public PanelArticle(List<Categorie> categories) {

        JLabel lblArticle = new JLabel("ARTICLE");
        lblArticle.setHorizontalAlignment(SwingConstants.CENTER);
        lblArticle.setOpaque(true);
        lblArticle.setBackground(new Color(192, 192, 192));
        lblArticle.setForeground(new Color(0, 0, 0));
        lblArticle.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JLabel lblCodeArticle = new JLabel("Code Article");

        ztCode=new JTextField();
        getZtCode().setColumns(10);

        JLabel lblNomArticle = new JLabel("Nom Article");

        ztNom=new JTextField();
        ztNom.setColumns(10);

        JLabel lblPrixArticle = new JLabel("Prix Article");

        ztPrix=new JFormattedTextField(new Double(0.0));
        getZtPrix().setColumns(10);
        getZtPrix().setInputVerifier(new InputVerifier() {

            @Override
            public boolean verify(JComponent input) {

                return getZtPrix().getText().matches("[0-9]*.?[0-9]{0,2}");
            }
        });

        JLabel lblGroupe = new JLabel("Groupe");

        cbGroupe = new JComboBox(Article.Groupe.values());
        cbGroupe.setEditable(true);

        JLabel lblCatgorie = new JLabel("Catégorie");

        cbCategorie = new JComboBox<>();
        for (Categorie c:categories){
            cbCategorie.addItem(c);
        }

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
                .createSequentialGroup().addGap(50)
                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblArticle, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup().addComponent(lblCodeArticle)
                                                .addGap(18).addComponent(getZtCode()).addGap(90))
                                .addGroup(groupLayout.createSequentialGroup()
                                        .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                .addComponent(lblNomArticle)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblGroupe).addComponent(lblPrixArticle)
                                                        .addComponent(lblCatgorie)))
                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                .addGroup(groupLayout.createSequentialGroup().addGap(18)
                                                        .addComponent(cbCategorie, 0, 180, Short.MAX_VALUE))
                                                .addGroup(groupLayout.createSequentialGroup().addGap(18)
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                                .addComponent(getZtNom(), GroupLayout.DEFAULT_SIZE, 180,
                                                                        Short.MAX_VALUE)
                                                                .addGroup(Alignment.TRAILING, groupLayout
                                                                        .createSequentialGroup()
                                                                        .addGroup(groupLayout
                                                                                .createParallelGroup(Alignment.TRAILING)
                                                                                .addComponent(cbGroupe,
                                                                                        Alignment.LEADING, 0, 86,
                                                                                        Short.MAX_VALUE)
                                                                                .addComponent(getZtPrix()))
                                                                        .addGap(94)))))))
                                .addGap(128)))
                .addGap(20)));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
                .createSequentialGroup().addContainerGap().addComponent(lblArticle).addGap(27)
                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblCodeArticle).addComponent(
                        getZtCode(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18)
                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNomArticle).addComponent(
                        getZtNom(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18)
                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblPrixArticle).addComponent(
                        getZtPrix(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18)
                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblGroupe).addComponent(
                        cbGroupe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18)
                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblCatgorie).addComponent(
                        cbCategorie, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE)));
        setLayout(groupLayout);

    }

    public JTextField getZtCode() {
        return ztCode;
    }

    public JTextField getZtNom() {
        return ztNom;
    }

    public JFormattedTextField getZtPrix() {
        return ztPrix;
    }

    /**
     * @return the cbCategorie
     */
    public Categorie getCbCategorie() {
        return (Categorie) cbCategorie.getSelectedItem();
    }

    /**
     * @return the cbGroupe
     */
    public JComboBox<Article.Groupe> getCbGroupe() {
        return cbGroupe;
    }
}