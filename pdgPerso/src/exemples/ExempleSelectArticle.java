package exemples;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class ExempleSelectArticle extends JFrame {

    private JPanel contentPane;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ExempleSelectArticle frame = new ExempleSelectArticle();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ExempleSelectArticle() {
        setTitle("S\u00E9lection article");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 531, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(1, 4, 2, 2));

        JToggleButton tglbtnEntres = new JToggleButton("Entr\u00E9es");
        panel.add(tglbtnEntres);

        JToggleButton tglbtnPlats = new JToggleButton("Plats");
        panel.add(tglbtnPlats);

        JToggleButton tglbtnDesserts = new JToggleButton("Desserts");
        panel.add(tglbtnDesserts);

        JToggleButton tglbtnBoissons = new JToggleButton("Boissons");
        panel.add(tglbtnBoissons);
        ButtonGroup group = new ButtonGroup();
        group.add(tglbtnEntres);
        group.add(tglbtnPlats);
        group.add(tglbtnDesserts);
        group.add(tglbtnBoissons);
        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.CENTER);



        table = new JTable();

        table.setModel(new DefaultTableModel(
            new Object[][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
            },
            new String[] {
                "Code", "Nom", "Prix", "Cat\u00E9gorie"
            }
        ));
        JScrollPane scrollPane = new JScrollPane(table);
        panel_1.add(scrollPane);

        JPanel panel_2 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        contentPane.add(panel_2, BorderLayout.SOUTH);

        JButton btnAjoutArticle = new JButton("Ajout Article");
        panel_2.add(btnAjoutArticle);

        JButton btnAnnuler = new JButton("Annuler");
        panel_2.add(btnAnnuler);
    }

}