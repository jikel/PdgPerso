package processus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;
import java.nio.file.Path;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Dessin extends JFrame {
    // Panel de dessin
    class Papier extends JPanel{

        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = ( Graphics2D ) g;
            super.paint(g2);
            g2.setColor(Color.GREEN);
            Rectangle borne=g2.getClipBounds();
            g2.drawRoundRect(10, 30, 100, 150, 5, 5);
            g2.setColor(Color.CYAN);
            g2.fillRect(120, 10,50,30);
            g2.drawOval(100, 100, 70, 90);

            GeneralPath p = new GeneralPath();
            p.moveTo(10.0,10.0);
            p.lineTo(20.0, 10.0);
            p.lineTo(30.0, 40.0);
            p.lineTo(20.0,50.0);
            g2.setColor(Color.MAGENTA);

            p.closePath();
            g2.draw(p);


        }



    }

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Dessin frame = new Dessin();
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
    public Dessin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel lblExempleDeDessin = new JLabel("Exemple de dessin");
        lblExempleDeDessin.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblExempleDeDessin, BorderLayout.NORTH);

        JPanel papier = new Papier();
        papier.setBorder(new LineBorder(Color.BLUE, 2));
        contentPane.add(papier, BorderLayout.CENTER);
    }

}
