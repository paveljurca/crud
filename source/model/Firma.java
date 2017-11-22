package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author xjurp20
 */
public class Firma implements Comparable<Firma> {

    public enum Atribut {

        NAZEV, //String
        ICO, //int
        ADRESA, //Adresa
        KONTAKT; //Osoba

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }
    private Map<Firma.Atribut, Object> atributy;
    private Set<Staz> staze;

    public Firma(
            String nazev,
            int ico,
            Adresa adresa,
            Osoba kontakt) {

        atributy = new HashMap<>();
        staze = new TreeSet<>();
        setAtribut(Firma.Atribut.NAZEV, nazev);
        setAtribut(Firma.Atribut.ICO, ico);
        setAtribut(Firma.Atribut.ADRESA, adresa);
        setAtribut(Firma.Atribut.KONTAKT, kontakt);
    }

    /**
     * Vrat hodnotu atributu
     *
     * @param nazev atributu
     * @return hodnota atributu
     */
    public Object getAtribut(Firma.Atribut atribut) {
        return atributy.get(atribut);
    }

    /**
     * Nastav atributu novou hodnotu
     *
     * @return true, pokud se hodnota atributu zmenila
     */
    public final boolean setAtribut(Firma.Atribut atribut, Object hodnota) {
        if (atribut == Firma.Atribut.ADRESA
                && !(hodnota instanceof Adresa)) {
            return false;
        }
        if (atribut == Firma.Atribut.KONTAKT
                && !(hodnota instanceof Osoba)) {
            return false;
        }
        if (atribut == Firma.Atribut.ICO
                && !(hodnota instanceof Integer)) {
            return false;
        }
        atributy.put(atribut, hodnota);
        return true;
    }

    public Set<Staz> getStaze() {
        return staze;
    }

    public void pridejStaze(Staz... staze) {
        if (null == staze) {
            return;
        }
        this.staze.addAll(Arrays.asList(staze));
    }

    public void odeberStaze(Staz... staze) {
        throw new java.lang.UnsupportedOperationException();
//        if (null == firmy) {return;}        
//        this.staze.removeAll(Arrays.asList(staze));
    }

    /**
     * Metoda equals pro porovnání s další instancí třídy Firma (obecně s
     * jakýmkoliv objektem). Podrobný popis této metody je u třídy Object. Dvě
     * instance třídy Firma jsou stejné, pokud mají stejnou zkratku (tj.
     * nezáleží na názvu útvaru).
     *
     * @param o druhý útvar/Object, se kterým se má aktuální instance porovnat
     * @return true, pokud jsou instance stejné - pokud obsahují stejnou zkratku
     * (název se neporovnává).
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Firma) {
            Firma druha = (Firma) o;
            Integer _this = Integer.valueOf(staze.size());
            Integer _that = Integer.valueOf(druha.getStaze().size());

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
        return Integer.valueOf(staze.size()).hashCode();
    }

    /**
     * Compare to a next object in a TreeSet //uses String.compareTo()
     *
     * @param dalsi_firma a next object in a TreeSet
     *
     * @return integer as a result of comparing defined in a Comparable class
     */
    @Override
    public int compareTo(Firma dalsi_firma) {
        return Integer.valueOf(staze.size()).compareTo(dalsi_firma.getStaze().size());
    }

    @Override
    public String toString() {
        return (String) atributy.get(Firma.Atribut.NAZEV);
    }
}
