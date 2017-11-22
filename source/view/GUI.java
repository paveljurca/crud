package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import model.Model;

/**
 * Graficke okno programu
 *
 * @author xjurp20
 */
public class GUI {

    private Model model;
    private TableModel stazeTableModel;
    private JFrame frame;
    private JPanel panel;
    private JTable stazeTable;
    private OknoNapoveda oknoNapovedy;
    private JMenuBar menuBar;
    private JMenu mFirma;
    private JMenu mNapoveda;

    /**
     * Zobrazi informacni dialog "O programu"
     */
    private class OProgramu implements ActionListener {

        private JLabel lbl;

        public OProgramu() {
            lbl = new JLabel(
                    "<html>"
                    + "&nbsp;&nbsp;_&nbsp;&nbsp;&nbsp;&nbsp;______&nbsp;____<br />"
                    + "<code>| |/ /_ _|_   _|__  </code><br />"
                    + "<code>| ' / | |  | |/ __| </code><br />"
                    + "<code>| . \\ | |  | |\\__ \\ </code><br />"
                    + "<code>|_|\\_\\___||_||___/ </code><br />"
                    + "<center>"
                    + "<h3>Evidence stáží pro studenty KIT</h3>"
                    + "<br />.<br />"
                    + "Copyleft 2013<br />"
                    + "<i>xjurp20, xradd02, xhalt04, xsedl17</i><br />"
                    + "4IT115, FIS, VŠE, 2013<br /></center></html>",
                    JLabel.CENTER);
            lbl.setBorder(BorderFactory.createEmptyBorder(0, 24, 24, 24));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(frame, lbl, "O Programu",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    public GUI(Model model) {
        this.model = model;
        stazeTableModel = model.new TabulkaStaz();
        init();
    }

    private void init() {
        frame = new JFrame();
        panel = new JPanel(new GridLayout(1, 1, 0, 0));

        oknoNapovedy = new OknoNapoveda(frame, "Pomoc",
                model.napovedaURL);

        panel.setPreferredSize(new Dimension(600, 400));

        initMenuBar();
        frame.setJMenuBar(menuBar);
        frame.setTitle(model.toString());

        stazeTable = new JTable(stazeTableModel);
        stazeTable.setPreferredScrollableViewportSize(new Dimension(560, 250));

        JScrollPane scpRoluj = new JScrollPane(stazeTable);
        panel.add(scpRoluj);

        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    private void initMenuBar() {
        menuBar = new JMenuBar();
        menuBar.setFocusable(false);
        menuBar.setBackground(new Color(244, 244, 244));

        mFirma = new JMenu("Firma");
        mNapoveda = new JMenu("Nápověda");

        mFirma.setMnemonic(KeyEvent.VK_F);
        mFirma.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        mNapoveda.setMnemonic(KeyEvent.VK_N);
        mNapoveda.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        JMenuItem zalozitIt = new JMenuItem("  Založit");
        JMenuItem pomocIt = new JMenuItem("  Pomoc");
        JMenuItem oProgramuIt = new JMenuItem("  O Programu");

        zalozitIt.setBorder(mFirma.getBorder());
        pomocIt.setBorder(mNapoveda.getBorder());
        oProgramuIt.setBorder(mNapoveda.getBorder());

        //zalozitIt.addActionListener();
        pomocIt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oknoNapovedy.zobraz();
            }
        });
        oProgramuIt.addActionListener(new OProgramu());

        mFirma.add(zalozitIt);
        mNapoveda.add(pomocIt);
        mNapoveda.addSeparator();
        mNapoveda.add(oProgramuIt);
        menuBar.add(mFirma);
        menuBar.add(mNapoveda);
    }

    public void zobraz() {
        frame.setVisible(true);
    }

    public void ukonci() {
        System.exit(0);
    }
}
