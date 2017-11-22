package model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author xjurp20
 */
public class Adresa {

    public enum Atribut {

        LOKALITA,
        MESTO,
        ULICE,
        CP,
        PSC;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
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
        NotApplicable;
    }
    
    private Map<Adresa.Atribut, Object> atributy;

    public Adresa(Lokalita lokalita,
            String mesto,
            String ulice,
            int cp,
            int psc) {

        atributy = new HashMap<>();
        setAtribut(Adresa.Atribut.LOKALITA, lokalita);
        setAtribut(Adresa.Atribut.MESTO, mesto);
        setAtribut(Adresa.Atribut.ULICE, ulice);
        setAtribut(Adresa.Atribut.CP, cp);
        setAtribut(Adresa.Atribut.PSC, psc);
    }

    /**
     * Vrat hodnotu atributu
     *
     * @param nazev atributu
     * @return hodnota atributu
     */
    public Object getAtribut(Adresa.Atribut atribut) {
        return atributy.get(atribut);
    }

    /**
     * Nastav atributu novou hodnotu
     *
     * @return true, pokud se hodnota atributu zmenila
     */
    public final boolean setAtribut(Adresa.Atribut atribut, Object hodnota) {
        if (atribut == Adresa.Atribut.LOKALITA
                && !(hodnota instanceof Adresa.Lokalita)) {
            return false;
        }
        if ((atribut == Adresa.Atribut.CP || atribut == Adresa.Atribut.PSC)
                && !(hodnota instanceof Integer)) {
            return false;
        }
        atributy.put(atribut, hodnota);
        return true;
    }
}
