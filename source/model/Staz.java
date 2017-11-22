package model;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author xjurp20
 */
public class Staz implements Comparable<Staz> {

    public enum Atribut {

        NAZEV, //String
        OBOR, //Staz.Obor
        DATNASTUPU, //Date
        DELKA, //int jako pocet dni
        KAPACITA, //int
        LOKALITA, //Staz.Lokalita
        POPIS; //String

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public enum Obor {

        Administrativa,
        Bankovnictví,
        Potravinářství,
        Farmacie,
        ICT,
        Kultura,
        Logistika,
        Marketing,
        Právo,
        Stavebnictví,
        Strojírenství,
        Výzkum,
        Školství,
        Management,
        VšechnyObory;
    }

    public enum Lokalita {

        Praha,
        JižníČechy,
        SeverníČechy,
        VýchodníČechy,
        ZápadníČechy,
        Morava,
        Slezsko,
        Zahraničí,
        VšechnyLokality;
    }

    public enum StavStaze {

        BUDOUCI,
        PROBIHAJICI,
        UPLYNULE,
        VSECHNY;
    }
    
    private Map<Staz.Atribut, Object> atributy;
    private Set<Student> studenti;

    public Staz(
            String nazev,
            Staz.Obor obor,
            Date datNastupu,
            int delka,
            int kapacita,
            Staz.Lokalita lokalita,
            String popis) {

        atributy = new HashMap<>();
        studenti = new HashSet<>();

        setAtribut(Staz.Atribut.NAZEV, nazev);
        setAtribut(Staz.Atribut.OBOR, obor);
        setAtribut(Staz.Atribut.DATNASTUPU, datNastupu);
        setAtribut(Staz.Atribut.DELKA, delka);
        setAtribut(Staz.Atribut.KAPACITA, kapacita);
        setAtribut(Staz.Atribut.LOKALITA, lokalita);
        setAtribut(Staz.Atribut.POPIS, popis);
    }

    /**
     * Vrat hodnotu atributu
     *
     * @param nazev atributu
     * @return hodnota atributu
     */
    public Object getAtribut(Staz.Atribut atribut) {
        return atributy.get(atribut);
    }

    /**
     * Nastav atributu novou hodnotu
     *
     * @return true, pokud se hodnota atributu zmenila
     */
    public final boolean setAtribut(Staz.Atribut atribut, Object hodnota) {
        if (atribut == Staz.Atribut.LOKALITA
                && !(hodnota instanceof Staz.Lokalita)) {
            return false;
        }
        if (atribut == Staz.Atribut.OBOR
                && !(hodnota instanceof Staz.Obor)) {
            return false;
        }
        if (atribut == Staz.Atribut.DATNASTUPU) {
            if (hodnota instanceof Date) {
                Date dat = (Date) hodnota;
                if (dat.before(new Date())) {
                    hodnota = new Date();
                }
            } else {
                return false;
            }
        }
        if ((atribut == Staz.Atribut.DELKA || atribut == Staz.Atribut.KAPACITA)) {
            if (hodnota instanceof Integer) {
                hodnota = Math.abs((int) hodnota);
                //nelze snizit kapacitu pod prave prihlaseny pocet studentu
                if ((int) hodnota < studenti.size()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        atributy.put(atribut, hodnota);
        return true;
    }

    /**
     * Prihlasit studenta na stazi
     *
     * @param student
     * @return false, pokud by uz staz prevysila svoji kapacitu
     */
    public boolean prihlasStud(Student... studenti) {
        if (null == studenti) {
            return false;
        }

        int vel = this.studenti.size();
        for (Student s : studenti) {
            if (volnaMista() > 0) {
                this.studenti.add(s);
            }
        }
        if (vel == this.studenti.size()) {
            return false;
        }

        return true;
    }

    public void odhlasStud(String... xnames) {
        if (null == xnames) {
            return;
        }
        for (String x : xnames) {
            for (Student s : studenti) {
                if (x.equalsIgnoreCase(s.toString())) {
                    studenti.remove(s);
                }
            }
        }
    }

    public Set<Student> getStudenty() {
        return Collections.unmodifiableSet(studenti);
    }

    public int volnaMista() {
        return ((int) atributy.get(Staz.Atribut.KAPACITA)
                - studenti.size());
    }
    
    public Staz.StavStaze getStavStaze() {
        Date datNastupu = (Date)atributy.get(Staz.Atribut.DATNASTUPU);
        //datum konce staze
        Calendar cal = Calendar.getInstance();
        cal.setTime(datNastupu);
        cal.add(Calendar.DAY_OF_MONTH, (int)atributy.get(Staz.Atribut.DELKA));
        
        Date now = new Date();
        if (now.before(datNastupu)) {
            return Staz.StavStaze.BUDOUCI;
        } else if (now.after(cal.getTime())) {
            return Staz.StavStaze.UPLYNULE;
        } else {
            return Staz.StavStaze.PROBIHAJICI;
        }
    }

    /**
     * Metoda equals pro porovnání s další instancí třídy Staz (obecně s
     * jakýmkoliv objektem). Podrobný popis této metody je u třídy Object. Dvě
     * instance třídy Staz jsou stejné, pokud mají stejnou zkratku (tj. nezáleží
     * na názvu útvaru).
     *
     * @param o druhý útvar/Object, se kterým se má aktuální instance porovnat
     * @return true, pokud jsou instance stejné - pokud obsahují stejnou zkratku
     * (název se neporovnává).
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Staz) {
            Staz druha = (Staz) o;
            Date _this = (Date) atributy.get(Staz.Atribut.DATNASTUPU);
            Date _that = (Date) druha.getAtribut(Staz.Atribut.DATNASTUPU);
            return _this.equals(_that);
        }

        return false;
    }

    /**
     * Metoda vrací číselnou hodnotu vyjadřující konkrétní instanci. Používá se
     * pro optimalizaci v dynamických datových strukturách. Bližší popis viz
     * třída Object.
     *
     * @return číselný kód vyjadřující instanci
     */
    @Override
    public int hashCode() {
        return atributy.get(Staz.Atribut.DATNASTUPU).hashCode();
    }

    /**
     * Compare to a next object in a TreeSet //uses String.compareTo()
     *
     * @param dalsi_staz a next object in a TreeSet
     *
     * @return integer as a result of comparing defined in a Comparable class
     */
    @Override
    public int compareTo(Staz dalsi_staz) {
        Date _this = (Date) atributy.get(Staz.Atribut.DATNASTUPU);
        Date _that = (Date) dalsi_staz.getAtribut(Staz.Atribut.DATNASTUPU);

        return _this.compareTo(_that);
    }

    @Override
    public String toString() {
        return (String) atributy.get(Staz.Atribut.NAZEV);
    }
}
