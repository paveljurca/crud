package model;

import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author xjurp20
 */
public class StazTest {

    private Staz staz;
    private Student student;
    private Student student2;

    public StazTest() {
    }

    @Before
    public void setUp() {
        staz = new Staz(
                "admin", Staz.Obor.ICT, new Date(),
                30, 1, Staz.Lokalita.Praha, "linux");
        student = new Student("pavel jurca", "xjurp20@vse.cz",
                123456789, "xjurp20", new Date(), Student.Fakulta.FIS, 2);
        student2 = new Student("franta vopicka", "xvopf20@vse.cz",
                123456789, "xvopf20", new Date(), Student.Fakulta.FM, 4);
    }

    @Test
    public void testVolnaMista() {
        assertSame(1, staz.volnaMista());
        staz.prihlasStud(student);
        assertSame(0, staz.volnaMista());
        assertFalse(staz.prihlasStud(student));
    }

    @Test
    public void testPrihlasitStudenta() {
        assertTrue(staz.prihlasStud(student));
        assertSame(1, staz.getStudenty().size());
        assertEquals(student, staz.getStudenty().iterator().next());
    }

    @Test
    public void testOdhlasitStudenta() {
        staz.prihlasStud(student);
        assertSame(0, staz.volnaMista());
        staz.odhlasStud(student.toString());
        assertSame(1, staz.volnaMista());
    }
    
    @Test
    public void testZmenaKapacity() {
        assertTrue(staz.prihlasStud(student));
        assertFalse(staz.setAtribut(Staz.Atribut.KAPACITA, 0));
        assertSame(1, staz.getAtribut(Staz.Atribut.KAPACITA));        
        
        staz.odhlasStud(student.toString());
        assertTrue(staz.setAtribut(Staz.Atribut.KAPACITA, 1));
        assertSame(1, staz.getAtribut(Staz.Atribut.KAPACITA));
    }
}
