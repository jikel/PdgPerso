package processus;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.text.html.BlockView;

public class VueWorker extends JFrame {
    class Travail extends SwingWorker<List<Integer>, Integer> {
        private  boolean pause;
        List<Integer> nps;
        int max;

        public Travail(int max) {
            this.max = max;
            this.pause = false;
            jpb.setValue(0);
            jpb.setVisible(true);
            addPropertyChangeListener(new PropertyChangeListener() {

                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if ("progress".equals(evt.getPropertyName())) {
                        jpb.setValue((Integer) evt.getNewValue());
                    }
                }
            });
        }

        public boolean isPause() {
            return pause;
        }

        public void setPause(boolean pause) {

            if (!isDone() && !this.isCancelled()) {
                this.pause = !this.isPause();

            }
        }

        @Override
        protected void process(List<Integer> l) {
            for (int number : l) {
                taResult.insert( number + "\n",1);

            }

        }

        @Override
        protected void done() {
            try {
                if (!isCancelled()) {
                    List<Integer> l = get();

                }
            } catch (InterruptedException e) {

            } catch (ExecutionException e) {

            }
            setProgress(100);
            taResult.append("FIN" + String.valueOf(nps.size()));
            pause = false;
            super.done();
        }

        @Override
        protected List<Integer> doInBackground() throws Exception {
            nps = new ArrayList<>();
            calculNbPremiers(max);
            return nps;
        }

        private void calculNbPremiers(int nb) {
            int n = 1;
            int i;
            nps.add(n);
            int cpt = 1;

            while (cpt < nb && !isCancelled()) {
                n++;
                i = 1;
                while (i < nps.size() && n % nps.get(i) != 0)
                    i++;
                if (i >= nps.size()) {
                    nps.add(n);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {

                    }
                    ;
                    publish(n);
                    cpt++;

                    setProgress((Integer)(100*cpt/nb));
                    while (pause) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                        }
                        if (isCancelled())
                            continue;

                    }
                }

            }

        }

    }

    private JButton btCalcul;
    private JButton btCancel;
    private Travail travail;
    private JTextArea taResult;
    private JButton btPause;
    private JProgressBar jpb;

    public VueWorker(String arg0) throws HeadlessException {
        super(arg0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = getContentPane();
        btCalcul = new JButton("Calcul");
        cp.add(btCalcul, BorderLayout.NORTH);
        taResult =new JTextArea();
        cp.add( new JScrollPane(taResult), BorderLayout.CENTER);
        btCancel = new JButton("Cancel");
        cp.add(btCancel, BorderLayout.WEST);
        btCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                travail.cancel(true);

            }
        });
        btCalcul.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (travail == null || travail.isDone()) {

                    taResult.setText(null);
                    travail = new Travail(1000);
                    travail.execute();
                /*Thread tache=new Thread(new Runnable() {

                    private int i;

                    @Override
                    public void run() {
                     // Faire une long traitement
                        for (i=0;i<1000;i++){

                            SwingUtilities.invokeLater( new Runnable() {
                                public void run() {
                                    taResult.insert(String.valueOf(i), 1);
                                }
                            });
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                });
                tache.start();*/
                }



            }
        });
        btPause = new JButton("Pause/Resume");
        add(btPause, BorderLayout.EAST);
        btPause.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                travail.setPause(!travail.isPause());

            }
        });
        jpb=new JProgressBar(0,100);
        add(jpb,BorderLayout.SOUTH);


        setSize(600, 400);
        setVisible(true);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new VueWorker("TEST SWINGWORKER");

    }

}