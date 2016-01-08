package exemples;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class VueBoutons extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VueBoutons frame = new VueBoutons();
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
    public VueBoutons() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        byte nbArticles=12;
        String[] noms={"Artcicle1","Article2","Article3","Artcicle4","Article5","Article6","Artcicle7","Article8","Article9","Artcicle10","Article11","Article12"};
        JPanel grille=new JPanel(new GridLayout(5,10,2,2));
        grille.setBorder(new LineBorder(new Color(72, 209, 204), 2));
        JButton[] boutons= new JButton[nbArticles];
        for (int i=0;i<nbArticles;i++){
            boutons[i]=new JButton(noms[i]);
            grille.add(boutons[i]);
        }
        contentPane.add(grille, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(Color.BLUE, 2));
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(1, 4, 2, 2));

        JToggleButton toggleButton = new JToggleButton("Entr\u00E9es");
        panel.add(toggleButton);

        JToggleButton toggleButton_1 = new JToggleButton("Plats");
        panel.add(toggleButton_1);

        JToggleButton toggleButton_2 = new JToggleButton("Desserts");
        panel.add(toggleButton_2);

        JToggleButton toggleButton_3 = new JToggleButton("Boissons");
        panel.add(toggleButton_3);

    }

}
