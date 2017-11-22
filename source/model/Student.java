package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author xjurp20
 */
public class Student {

    public enum Atribut {

        JMENO, //String
        EMAIL, //String
        TELEFON, //int
        XNAME, //String
        DATNAR, //Date
        FAKULTA, //Student.Fakulta
        SEMESTR; //int

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public enum Fakulta {

        FFU,
        FMV,
        FPH,
        FIS,
        NF,
        FM;
    }
    private Map<Student.Atribut, Object> atributy;

    public Student(
            String jmeno,
            String email,
            int telefon,
            String xname,
            Date datNar,
            Student.Fakulta fakulta,
            int semestr) {

        atributy = new HashMap<>();
        setAtribut(Student.Atribut.JMENO, jmeno);
        setAtribut(Student.Atribut.EMAIL, email);
        setAtribut(Student.Atribut.TELEFON, telefon);
        setAtribut(Student.Atribut.XNAME, xname);
        setAtribut(Student.Atribut.DATNAR, datNar);
        setAtribut(Student.Atribut.FAKULTA, fakulta);
        setAtribut(Student.Atribut.SEMESTR, semestr);
    }

    /**
     * Vrat hodnotu atributu
     *
     * @param nazev atributu
     * @return hodnota atributu
     */
    public Object getAtribut(Student.Atribut atribut) {
        return atributy.get(atribut);
    }

    /**
     * Nastav atributu novou hodnotu
     *
     * @return true, pokud se hodnota atributu zmenila
     */
    public final boolean setAtribut(Student.Atribut atribut, Object hodnota) {
        if ((atribut == Student.Atribut.TELEFON || atribut == Student.Atribut.SEMESTR)
                && !(hodnota instanceof Integer)) {
            return false;
        }
        if (atribut == Student.Atribut.DATNAR && !(hodnota instanceof Date)) {
            return false;
        }
        if (atribut == Student.Atribut.FAKULTA && !(hodnota instanceof Fakulta)) {
            return false;
        }

        atributy.put(atribut, hodnota);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Student) {
            Student student = (Student) o;
            String _this = (String) atributy.get(Student.Atribut.XNAME);
            String _that = (String) student.getAtribut(Student.Atribut.XNAME);
            return _this.equalsIgnoreCase(_that);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return atributy.get(Student.Atribut.XNAME).hashCode();
    }

    @Override
    public String toString() {
        return (String) atributy.get(Student.Atribut.XNAME);
    }
}
