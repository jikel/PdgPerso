package processus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

//import com.sun.swing.internal.plaf.synth.resources.synth;

public class VueCompteur extends JFrame {

    // MonAction
    class MonAction implements ActionListener {

        private volatile boolean stop; // permet d'interrompre le processus
        private volatile boolean start;// évite de lancer 2 fois le processus
        private volatile boolean pause;

        @Override
        public void actionPerformed(ActionEvent arg0) {
            System.err.println(SwingUtilities.isEventDispatchThread());
            if (start)
                return;
            start = true;
            stop = false;
            pause = false;
            // **************************************************
            // Version 1
            // int cpt=0;
            // while (cpt < 100 && !stop) {
            //
            // digit2.setCar(cpt % 10);
            // digit1.setCar(cpt / 10);
            //
            //
            // try {
            // Thread.sleep(30);
            // } catch (InterruptedException e) {
            //
            // }
            // cpt++;
            // }
            // start = false;
            // ******************************************************
            // ******************************************************
            // **Version 2

            // Lance la tâche dans un autre thread
            // new Thread(new Runnable() {
            // int cpt = 0;
            //
            // @Override
            // public void run() {
            // System.err.println(SwingUtilities.isEventDispatchThread());
            // while (cpt < 100 && !stop) {
            // // Lance le traitement dans l'Event Dispatch Thread
            //
            // digit2.setCar(cpt % 10);
            // digit1.setCar(cpt / 10);
            //
            // try {
            // Thread.sleep(120);
            // } catch (InterruptedException e) {
            //
            // }
            // cpt++;
            // }
            // start = false;
            // }
            // }).start();
            //
            // //******************************************************
            // //**Version 3
            //
            // // Lance la tâche dans un autre thread
            new Thread(new Runnable() {
                int cpt = 0;

                @Override
                public void run() {

                    while (cpt < 100 && !stop) {
                        // Lance le traitement dans l'Event Dispatch Thread
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                digit2.setCar(cpt % 10);
                                digit1.setCar(cpt / 10);
                            }
                        });
                        ;

                        try {
                            do {

                                Thread.sleep(100);

                            } while (pause && !stop);
                        } catch (InterruptedException e) {

                        }

                        cpt++;

                    }
                    start = false;
                }
            }).start();

        }

        public boolean isStop() {
            return stop;
        }

        public void stop() {
            this.stop = true;
        }

        public void start() {
            if (start)
                return;

        }

        public void pause() {
            if (this.pause) {
                this.pause = false;
            } else {
                this.pause = true;
            }
        }

        public boolean isPause() {
            return pause;
        }

    }

    Digit digit1, digit2;
    private JButton btStart;
    private JButton btStop;
    private JButton btPause;
    private MonAction action;

    public VueCompteur(String title) throws HeadlessException {
        super(title);
        initGui();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
    }

    private void initGui() {
        Container cp = getContentPane();

        JPanel panneauCentre = new JPanel();
        digit1 = new Digit();
        panneauCentre.add(digit1);
        digit2 = new Digit();
        panneauCentre.add(digit2);
        cp.add(panneauCentre, BorderLayout.CENTER);

        JPanel panneauSud = new JPanel();

        btStart = new JButton("Start");
        // création de l'action
        action = new MonAction();
        btStart.addActionListener(action);

        btPause = new JButton("Pause");
        // pause de l'action
        btPause.addActionListener((ActionEvent e) -> action.pause());

        btStop = new JButton("Stop");

        // Arrêt de l'action
        btStop.addActionListener((ActionEvent e) -> action.stop());

        panneauSud.add(btStart);
        panneauSud.add(btPause);
        panneauSud.add(btStop);

        cp.add(panneauSud, BorderLayout.SOUTH);
        cp.add(new JTextField(), BorderLayout.NORTH);

    }

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                VueCompteur frame = new VueCompteur("Exemple Thread");

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
