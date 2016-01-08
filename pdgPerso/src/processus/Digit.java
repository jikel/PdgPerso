package processus;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;

public class Digit extends JPanel implements IDigit {

    private static final double distBord = 0.1;
    private static final Color coulFond_Defaut = new Color(0xe0, 0xf0, 0xff);
    private static final Color coulOn_Defaut = new Color(0, 0, 0x80);
    private static final Color coulOff_Defaut = new Color(0xe0, 0xff, 0xd0);

    private final Color coulFond;
    private final Color coulLigneOn;
    private final Color coulLigneOff;
    // variable pour les segments
    final int e1 = 1;

    final int e2 = 5;
    final int e3 = 3;
    GeneralPath path;

    // Points des segments
    private Point[] p = new Point[7];
    // finition segments
    private Stroke stroke;

// code pour indiquer les segments à allumer ou éteindre
    private byte code = 0b01111110;
    private Graphics2D g2;
    //Le car à afficher
    private char car = '0';
/**
 * Constructeur à défaut
 */
    public Digit() {
        this(coulFond_Defaut, coulOn_Defaut, coulOff_Defaut);
        setPreferredSize(new Dimension(40, 80));
    }
/**
 * Constructeur avec possibilité de spécifier les couleurs
 * @param coulFond
 * @param coulLigneOn
 * @param coulLigneOff
 */
    private Digit(Color coulFond, Color coulLigneOn, Color coulLigneOff) {
        super();
        this.coulFond = coulFond;
        this.coulLigneOn = coulLigneOn;
        this.coulLigneOff = coulLigneOff;
        stroke = new BasicStroke(1f);
        setCar(' ');
    }

    /**
     * Ajuste le code en fonction du chiffre (car) à afficher
     */
    @Override
    public void setCar(char car) {
        if (this.car == car)
            return;
        this.car = car;

        switch (car) {
        case ' ':
            code = (byte) 0b00000000;
            break;
        case '0':
            code = (byte) 0b01111110;
            break;
        case '1':
            code = (byte) 0b00110000;
            break;
        case '2':
            code = (byte) 0b01101101;
            break;
        case '3':
            code = (byte) 0b01111001;
            break;
        case '4':
            code = (byte) 0b00110011;
            break;
        case '5':
            code = (byte) 0b01011011;
            break;
        case '6':
            code = (byte) 0b00011111;
            break;
        case '7':
            code = (byte) 0b01110000;
            break;
        case '8':
            code = (byte) 0b01111111;
            break;
        case '9':
            code = (byte) 0b01110011;
            break;
        case 'A':
            code = (byte) 0b01110111;
            break;
        case 'F':
            code = (byte) 0b01000111;
            break;
        default:
            break;
        }

    }

    /**
     * Dessine un segment entre 2 points avec une finition pointue param
     * e1,e2,e3
     *
     * @param pa
     * @param pb
     */
    private void segment(Point pa, Point pb) {

        int x, y;
        path = new GeneralPath(GeneralPath.WIND_NON_ZERO, 7);

        if (Math.abs(pb.y - pa.y) < 2) {
            if (pa.x > pb.x) {
                Point tmp = pa;
                pa = pb;
                pb = tmp;
            }
            x = pa.x;
            y = pa.y;

            x = pa.x + e1;
            path.moveTo(x, y);
            x += e2;
            y -= e3;
            path.lineTo(x, y);
            x = pb.x - e1 - e2;
            path.lineTo(x, y);
            x += e2;
            y += e3;
            path.lineTo(x, y);
            x -= e2;
            y += e3;
            path.lineTo(x, y);
            x = pa.x + e1 + e2;
            path.lineTo(x, y);

        } else {
            if (pa.y > pb.y) {
                Point tmp = pa;
                pa = pb;
                pb = tmp;
            }
            x = pa.x;
            y = pa.y;
            y = pa.y + e1;
            path.moveTo(x, y);
            y += e2;
            x -= e3;
            path.lineTo(x, y);
            y = pb.y - e1 - e2;
            path.lineTo(x, y);
            x += e3;
            y += e2;
            path.lineTo(x, y);
            x += e3;
            y -= e2;
            path.lineTo(x, y);
            y = pa.y + e1 + e2;
            path.lineTo(x, y);

        }
        path.closePath();
        // g2.draw(path); // contours
        g2.fill(path);// remplissage

    }
/**
 *
 * Dessin du digit
 */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        g2.setStroke(stroke);

        // bordutre du digit
        Rectangle r = g.getClipBounds();
        int w = r.width;
        int h = r.height;
        // définit une marge extérieure et une intérieure
        int bord = (int) (Math.round(w) * distBord);// bordure
        int pad = (int) (bord * 0.5);// padding= marge intérieure
        int ecart = pad + bord;
        int distM = (int) ((h - ecart - ecart) / 2.0);

        // calcul des 6 points aux extrémités des segments
        p[0] = new Point(ecart, ecart);
        p[1] = new Point(w - ecart, ecart);
        p[2] = new Point(w - ecart, distM + ecart);
        p[3] = new Point(w - ecart, h - ecart);
        p[4] = new Point(ecart, h - ecart);
        p[5] = new Point(ecart, distM + ecart);
        p[6] = p[0];

        setBackground(coulFond);
       // Dessine les 7 segments: parcourt les points et dessine entre les segments
        byte masque = 0b01000000;
        for (int i = 0; i < 6; i++) {
            // allume ou non un segment en fonction du code
            g2.setPaint((this.code & masque) != 0 ? coulLigneOn : coulLigneOff);
            // dessine le segment
            segment(p[i], p[i + 1]);
            // avance dans le masque pour passer au segment suivant
            masque = (byte) (masque >>> 1);
        }

        // reste le segment du milieu
        g2.setPaint((this.code & masque) != 0 ? coulLigneOn : coulLigneOff);
        segment(p[5], p[2]);
        // provoque le rafraîchissement du digit
        repaint();
    }

    /* (non-Javadoc)
     * @see ihm.IDigit#setCar(int)
     */
    @Override
    public void setCar(int car) {

        setCar(Integer.toString(car).charAt(0));
    }
}