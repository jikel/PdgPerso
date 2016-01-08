package processus;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class ExempleVuePrincipale extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JTable table_1;
    private JTable table_3;
    private JTable table_4;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ExempleVuePrincipale frame = new ExempleVuePrincipale();
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
    public ExempleVuePrincipale() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 685, 549);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel lblMenuPrincipal = new JLabel("MENU PRINCIPAL");
        lblMenuPrincipal.setBorder(new LineBorder(new Color(186, 85, 211), 2));
        lblMenuPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblMenuPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblMenuPrincipal, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.WEST);
        panel.setLayout(new GridLayout(6, 1, 2, 2));

        JButton btnNewButton = new JButton("AjoutLocal");
        panel.add(btnNewButton);

        JButton btnDellocal = new JButton("SuppLocal");
        panel.add(btnDellocal);

        JButton btnAjoutcours = new JButton("AjoutCours");
        panel.add(btnAjoutcours);

        JButton btnSuppcours = new JButton("SuppCours");
        panel.add(btnSuppcours);

        JButton btnAjoutmodule = new JButton("AjoutModule");
        panel.add(btnAjoutmodule);

        JButton btnSuppmodule = new JButton("SuppModule");
        panel.add(btnSuppmodule);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new GridLayout(2, 2, 4, 4));

        JPanel panel_2 = new JPanel();
        panel_1.add(panel_2);
        panel_2.setLayout(new BorderLayout(0, 0));

        JLabel lblLocaux = new JLabel("Locaux");
        lblLocaux.setBorder(new LineBorder(new Color(0, 0, 255), 2));
        lblLocaux.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblLocaux.setHorizontalAlignment(SwingConstants.CENTER);
        panel_2.add(lblLocaux, BorderLayout.NORTH);



        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "New column", "New column", "New column"
            }
        ));
        JScrollPane scrollPane = new JScrollPane(table);
        panel_2.add(scrollPane, BorderLayout.CENTER);

        JPanel panel_3 = new JPanel();
        panel_1.add(panel_3, BorderLayout.SOUTH);
        panel_3.setLayout(new BorderLayout(0, 0));

        JLabel lblCours = new JLabel("Cours");
        lblCours.setBorder(new LineBorder(Color.ORANGE, 2));
        lblCours.setHorizontalAlignment(SwingConstants.CENTER);
        lblCours.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_3.add(lblCours, BorderLayout.NORTH);

        table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "New column", "New column", "New column"
            }
        ));
        JScrollPane scrollPane_1 = new JScrollPane(table_1);
        panel_3.add(scrollPane_1, BorderLayout.CENTER);

        JPanel panel_4 = new JPanel();
        panel_1.add(panel_4);
        panel_4.setLayout(new BorderLayout(0, 0));

        JLabel lblProfesseurs = new JLabel("Professeurs");
        lblProfesseurs.setBorder(new LineBorder(new Color(0, 206, 209), 2));
        lblProfesseurs.setHorizontalAlignment(SwingConstants.CENTER);
        lblProfesseurs.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_4.add(lblProfesseurs, BorderLayout.NORTH);

        table_3 = new JTable();
        table_3.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "New column", "New column", "New column"
            }
        ));
        JScrollPane scrollPane_3 = new JScrollPane(table_3);
        panel_4.add(scrollPane_3, BorderLayout.CENTER);



        JPanel panel_5 = new JPanel();
        panel_1.add(panel_5);
        panel_5.setLayout(new BorderLayout(0, 0));

        JLabel lblModules = new JLabel("Modules");
        lblModules.setBorder(new LineBorder(new Color(255, 140, 0), 2));
        lblModules.setHorizontalAlignment(SwingConstants.CENTER);
        lblModules.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_5.add(lblModules, BorderLayout.NORTH);

        table_4 = new JTable();
        table_4.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "New column", "New column", "New column"
            }
        ));
        JScrollPane scrollPane_4 = new JScrollPane(table_4);
        panel_5.add(scrollPane_4, BorderLayout.CENTER);



    }

}