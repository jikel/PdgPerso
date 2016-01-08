package vue;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.InputVerifier;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
/**
 *
 * @author Didier
 *
 *@deprecated Exemple vu au cours mais plus utilisé dans le projet.
 *@see PanelArticle AjoutArticle
 *
 */
public class VueArticle extends JPanel implements ActionListener {
    private JTextField ztCode;
    private JTextField ztNom;
    private JTextField ztPrix;
    private JButton btnAjout;
    private JButton btnAnnuler;

    /**
     * Create the panel.
     */
    public VueArticle() {

        JLabel lblArticle = new JLabel("ARTICLE");
        lblArticle.setHorizontalAlignment(SwingConstants.CENTER);
        lblArticle.setOpaque(true);
        lblArticle.setBackground(new Color(192, 192, 192));
        lblArticle.setForeground(new Color(0, 0, 0));
        lblArticle.setFont(new Font("Tahoma", Font.PLAIN, 16));

        JLabel lblCodeArticle = new JLabel("Code Article");

        ztCode = new JTextField();
        ztCode.setColumns(10);

        JLabel lblNomArticle = new JLabel("Nom Article");

        ztNom = new JTextField();
        ztNom.setColumns(10);

        JLabel lblPrixArticle = new JLabel("Prix Article");

        ztPrix = new JTextField();
        ztPrix.setColumns(10);
        ztPrix.setInputVerifier(new InputVerifier() {

            @Override
            public boolean verify(JComponent input) {

                return ztPrix.getText().matches("[0-9]*.?[0-9]{0,2}");
            }
        });

        btnAjout = new JButton("Ajout");
        // Version1 classe anonyme
//        btnAjout.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "AJOUT ACRTICLE OK");
//
//            }
//        });
//
//        // version 1 Lambda
//        btnAjout.addActionListener((e) -> JOptionPane.showMessageDialog(null, "AJOUT ACRTICLE OK Lambda"));

        // version 2 JPanel comme écouteur
        btnAjout.addActionListener(this);

        btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(this);

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
                groupLayout.createSequentialGroup().addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup().addGap(50)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblArticle, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 380,
                                                Short.MAX_VALUE)
                                .addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
                                        .createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup().addComponent(lblCodeArticle)
                                                .addGap(18).addComponent(ztCode).addGap(90))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(lblPrixArticle).addComponent(lblNomArticle))
                                                .addGap(18)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(ztPrix).addGap(94))
                                                        .addComponent(ztNom, GroupLayout.DEFAULT_SIZE, 180,
                                                                Short.MAX_VALUE))))
                                        .addGap(128))))
                        .addGroup(groupLayout.createSequentialGroup().addContainerGap(355, Short.MAX_VALUE)
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                        .addComponent(btnAnnuler, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnAjout, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(20)));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
                .createSequentialGroup().addContainerGap().addComponent(lblArticle).addGap(27)
                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblCodeArticle).addComponent(
                        ztCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18)
                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNomArticle).addComponent(
                        ztNom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18)
                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblPrixArticle).addComponent(
                        ztPrix, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAjout).addPreferredGap(ComponentPlacement.RELATED).addComponent(btnAnnuler)
                .addGap(12)));
        setLayout(groupLayout);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAjout) {
            SwingUtilities.getWindowAncestor(this).setVisible(false);
        } else if (e.getSource() == btnAnnuler) {
            JOptionPane.showMessageDialog(this, "COUCOU Annuler");
        }

    }
}
