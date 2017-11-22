package model;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.AbstractTableModel;
import view.GUI;

/**
 *
 * @author xjurp20
 */
public class Model implements IModel {

    public class TabulkaStaz extends AbstractTableModel {

        private ArrayList<Staz> staze;
        String[] zahlaviSloupcu = {"Datum nástupu", "Název", "Obor", "Lokalita", "Volná místa"};

        public TabulkaStaz() {
            this.staze = new ArrayList<>();
            staze.addAll(getStaze());
        }

        @Override
        public String getColumnName(int col) {
            return zahlaviSloupcu[col];
        }

        @Override
        public int getRowCount() {
            return staze.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public Object getValueAt(int row, int col) {
            Staz staz = staze.get(row);
            switch (col) {
                case 0:
                    return (staz.getAtribut(Staz.Atribut.DATNASTUPU));
                case 1:
                    return (staz.getAtribut(Staz.Atribut.NAZEV));
                case 2:
                    return (staz.getAtribut(Staz.Atribut.OBOR));
                case 3:
                    return (staz.getAtribut(Staz.Atribut.LOKALITA));
                case 4:
                    return (staz.volnaMista());
                default:
                    return null;
            }
        }
    }
    
    public class TabulkaFirma extends AbstractTableModel {

        private ArrayList<Firma> firmy;
        String[] zahlaviSloupcu = {"Název", "Lokalita", "Stáže"};

        public TabulkaFirma() {
            this.firmy = new ArrayList<>();
            firmy.addAll(getFirmy());
        }

        @Override
        public String getColumnName(int col) {
            return zahlaviSloupcu[col];
        }

        @Override
        public int getRowCount() {
            return firmy.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public Object getValueAt(int row, int col) {
            Firma firma = firmy.get(row);
            switch (col) {
                case 0:
                    return (firma.getAtribut(Firma.Atribut.NAZEV));
                case 1:
                    Adresa adresa = (Adresa)firma.getAtribut(Firma.Atribut.ADRESA);
                    return (adresa.getAtribut(Adresa.Atribut.LOKALITA));
                case 2:
                    return (firma.getStaze().size());
                default:
                    return null;
            }
        }
    }
    
    private static final String PATH_LOGS_FILE =
            System.getProperty("java.io.tmpdir") + File.separator
            + "kits.log";
    private Set<Firma> firmy;
    private Set<Student> studenti;
    protected static final Logger logger = Logger.getLogger(Model.class.getName());
    public final URL napovedaURL =
            GUI.class.getResource("/model/zdroje/napoveda.html");

    public Model() {
        firmy = new TreeSet<>();
        studenti = new HashSet<>();
        initLogger();
        init();
    }

    private void init() {
        try {
            ImportDat load = new ImportDat();
            firmy = load.getFirmy();
            studenti = load.getStudenty();

            if (null == napovedaURL) {
                logger.log(Level.WARNING, "Soubor s napovedou nebyl nalezen!");
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "FATAL ERROR V DATOVE CASTI PROGRAMU!", ex);
        }
    }

    private void initLogger() {
        try {
            FileHandler fh = new FileHandler(PATH_LOGS_FILE);
            fh.setFormatter(new SimpleFormatter());
            fh.setLevel(Level.ALL);

            logger.setUseParentHandlers(false);
            logger.addHandler(fh);
        } catch (IOException | SecurityException ex) {
            //ex.printStackTrace(System.err);
        }
    }

    @Override
    public Set<Firma> getFirmy() {
        return firmy;
    }

    @Override
    public void pridejFirmy(Firma... firmy) {
        if (null == firmy) {
            return;
        }
        this.firmy.addAll(Arrays.asList(firmy));
    }

    @Override
    public void odeberFirmy(Firma... firmy) {
        throw new java.lang.UnsupportedOperationException();
//        if (null == firmy) {return;}
//        this.firmy.removeAll(Arrays.asList(firmy));
    }

    @Override
    public Set<Student> getStudenty() {
        return studenti;
    }

    @Override
    public Set<Staz> getStaze() {
        TreeSet<Staz> staze = new TreeSet<>();
        for (Firma f : firmy) {
            staze.addAll(f.getStaze());
        }

        return staze;
    }

    @Override
    public Set<Staz> hledejStaze(
            String klicoveSlovo,
            Staz.Obor obor,
            Staz.Lokalita lokalita,
            Staz.StavStaze stavStaze) {

        Set<Staz> staze = new TreeSet<>(getStaze());
        Iterator<Staz> iter = staze.iterator();
        search:
        while (iter.hasNext()) {
            Staz staz = iter.next();

            Staz.Lokalita lok = (Staz.Lokalita) staz.getAtribut(Staz.Atribut.LOKALITA);
            if (lokalita != lok && lokalita != Staz.Lokalita.VšechnyLokality) {
                iter.remove();
                continue search;
            }

            Staz.Obor ob = (Staz.Obor) staz.getAtribut(Staz.Atribut.OBOR);
            if (obor != ob && obor != Staz.Obor.VšechnyObory) {
                iter.remove();
                continue search;
            }

            Staz.StavStaze stav = staz.getStavStaze();
            if (stavStaze != stav && stavStaze != Staz.StavStaze.VSECHNY) {
                iter.remove();
                continue search;
            }

            klicoveSlovo = klicoveSlovo.trim();
            if (!klicoveSlovo.equals("")) {
                String fulltext = (String) staz.getAtribut(Staz.Atribut.NAZEV)
                        + (String) staz.getAtribut(Staz.Atribut.POPIS);
                Pattern p = Pattern.compile("(?siu)" + klicoveSlovo);
                Matcher m = p.matcher(fulltext);
                if (!m.find()) {
                    iter.remove();
                    continue search;
                }
            }

        }

        return staze;
    }

    @Override
    public Set<Firma> hledejFirmy(String nazev, Adresa.Lokalita lokalita) {

        Set<Firma> firm = new TreeSet<>(getFirmy());
        Iterator<Firma> iter = firm.iterator();
        search:
        while (iter.hasNext()) {
            Firma firma = iter.next();
            Adresa adr = (Adresa) firma.getAtribut(Firma.Atribut.ADRESA);
            Adresa.Lokalita lok = (Adresa.Lokalita) adr.getAtribut(Adresa.Atribut.LOKALITA);
            if (lokalita != lok && lokalita != Adresa.Lokalita.NotApplicable) {
                iter.remove();
                continue search;
            }

            nazev = nazev.trim();
            if (!nazev.equals("")) {
                String fulltext = (String) firma.getAtribut(Firma.Atribut.NAZEV);
                Pattern p = Pattern.compile("(?siu)" + nazev);
                Matcher m = p.matcher(fulltext);
                if (!m.find()) {
                    iter.remove();
                    continue search;
                }
            }

        }

        return firm;
    }

    @Override
    public String toString() {
        return "KITs";
    }
}
