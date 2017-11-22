package model;

import java.util.Set;

/**
 *
 * @author xjurp20
 */
public interface IModel {

    Set<Firma> hledejFirmy(String nazev, Adresa.Lokalita lokalita);

    Set<Staz> hledejStaze(
            String klicoveSlovo,
            Staz.Obor obor,
            Staz.Lokalita lokalita,
            Staz.StavStaze stavStaze);

    Set<Staz> getStaze();

    Set<Student> getStudenty();

    void odeberFirmy(Firma... firmy);

    void pridejFirmy(Firma... firmy);

    Set<Firma> getFirmy();
}
