package model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author xjurp20
 */
public class Osoba {

    public enum Atribut {

        JMENO, //String
        EMAIL, //String
        TELEFON; //int

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }
    private Map<Osoba.Atribut, Object> atributy;

    public Osoba(String jmeno, String email, int telefon) {
        atributy = new HashMap<>();
        atributy.put(Osoba.Atribut.JMENO, jmeno);
        atributy.put(Osoba.Atribut.EMAIL, email);
        atributy.put(Osoba.Atribut.TELEFON, telefon);
    }

    /**
     * Vrat hodnotu atributu
     *
     * @param nazev atributu
     * @return hodnota atributu
     */
    public Object getAtribut(Osoba.Atribut atribut) {
        return atributy.get(atribut);
    }

    /**
     * Nastav atributu novou hodnotu
     *
     * @return true, pokud se hodnota atributu zmenila
     */
    public boolean setAtribut(Osoba.Atribut atribut, Object hodnota) {
        if (atribut == Osoba.Atribut.TELEFON && !(hodnota instanceof Integer)) {
            return false;
        }
        atributy.put(atribut, hodnota);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Osoba) {
            Osoba student = (Osoba) o;
            String _this = (String) atributy.get(Osoba.Atribut.JMENO);
            String _that = (String) student.getAtribut(Osoba.Atribut.JMENO);
            return _this.equalsIgnoreCase(_that);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return atributy.get(Osoba.Atribut.JMENO).hashCode();
    }

    @Override
    public String toString() {
        return (String) atributy.get(Osoba.Atribut.JMENO);
    }
}
