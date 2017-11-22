package model;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Konstruktor tridy ExportDat okamzite danou mnozinu z parametru prevede do
 * formatu XML a <b>ulozi do souboru "kits.xml"</b>
 *
 * @author xjurp20
 */
public class ExportDat {

    public ExportDat(Set<Firma> firmy) {
        export(firmy);
    }

    /**
     * Ulozi danou mnozinu firem do souboru "kits.xml" ve formatu XML <i>uklada
     * vzdy do aktualniho adresare!</i>
     *
     * @param firmy mnozina firem
     */
    private void export(Set<Firma> firmy) {
        try {
            //pro spravne formatovani z Date objektu pri ukladani do XML
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("firmy");
            doc.appendChild(rootElement);

            int i = 0;
            for (Firma f : firmy) {
                //firma
                Element firma = doc.createElement("firma");
                rootElement.appendChild(firma);
                firma.setAttribute("id", "" + (i++));

                //firma nazev
                Element firmaNazev = doc.createElement(""
                        + Firma.Atribut.NAZEV);
                firmaNazev.appendChild(doc.createTextNode(""
                        + f.getAtribut(Firma.Atribut.NAZEV)));
                firma.appendChild(firmaNazev);

                //firma ico
                Element firmaICO = doc.createElement(""
                        + Firma.Atribut.ICO);
                firmaICO.appendChild(doc.createTextNode(""
                        + f.getAtribut(Firma.Atribut.ICO)));
                firma.appendChild(firmaICO);

                //firma adresa
                Element firmaAdresa = doc.createElement(""
                        + Firma.Atribut.ADRESA);
                firma.appendChild(firmaAdresa);

                Element firmaALokalita = doc.createElement(""
                        + Adresa.Atribut.LOKALITA);
                Element firmaAMesto = doc.createElement(""
                        + Adresa.Atribut.MESTO);
                Element firmaAUlice = doc.createElement(""
                        + Adresa.Atribut.ULICE);
                Element firmaACp = doc.createElement(""
                        + Adresa.Atribut.CP);
                Element firmaAPsc = doc.createElement(""
                        + Adresa.Atribut.PSC);

                Adresa adresaF = (Adresa) f.getAtribut(Firma.Atribut.ADRESA);
                firmaALokalita.appendChild(doc.createTextNode(""
                        + adresaF.getAtribut(Adresa.Atribut.LOKALITA)));
                firmaAMesto.appendChild(doc.createTextNode(""
                        + adresaF.getAtribut(Adresa.Atribut.MESTO)));
                firmaAUlice.appendChild(doc.createTextNode(""
                        + adresaF.getAtribut(Adresa.Atribut.ULICE)));
                firmaACp.appendChild(doc.createTextNode(""
                        + adresaF.getAtribut(Adresa.Atribut.CP)));
                firmaAPsc.appendChild(doc.createTextNode(""
                        + adresaF.getAtribut(Adresa.Atribut.PSC)));

                firmaAdresa.appendChild(firmaALokalita);
                firmaAdresa.appendChild(firmaAMesto);
                firmaAdresa.appendChild(firmaAUlice);
                firmaAdresa.appendChild(firmaACp);
                firmaAdresa.appendChild(firmaAPsc);

                //firma kontakt
                Element firmaKontakt = doc.createElement(""
                        + Firma.Atribut.KONTAKT);
                firma.appendChild(firmaKontakt);

                Element firmaKJmeno = doc.createElement(""
                        + Osoba.Atribut.JMENO);
                Element firmaKEmail = doc.createElement(""
                        + Osoba.Atribut.EMAIL);
                Element firmaKTel = doc.createElement(""
                        + Osoba.Atribut.TELEFON);

                Osoba osobaF = (Osoba) f.getAtribut(Firma.Atribut.KONTAKT);
                firmaKJmeno.appendChild(doc.createTextNode(""
                        + osobaF.getAtribut(Osoba.Atribut.JMENO)));
                firmaKEmail.appendChild(doc.createTextNode(""
                        + osobaF.getAtribut(Osoba.Atribut.EMAIL)));
                firmaKTel.appendChild(doc.createTextNode(""
                        + osobaF.getAtribut(Osoba.Atribut.TELEFON)));

                firmaKontakt.appendChild(firmaKJmeno);
                firmaKontakt.appendChild(firmaKEmail);
                firmaKontakt.appendChild(firmaKTel);

                //firma STAZE
                Element stazeRoot = doc.createElement("staze");
                firma.appendChild(stazeRoot);
                int j = 0;
                for (Staz s : f.getStaze()) {
                    Element staz = doc.createElement("staz");
                    stazeRoot.appendChild(staz);
                    staz.setAttribute("id", "" + (j++));

                    //staz nazev
                    Element stazNazev = doc.createElement(""
                            + Staz.Atribut.NAZEV);
                    stazNazev.appendChild(doc.createTextNode(""
                            + s.getAtribut(Staz.Atribut.NAZEV)));
                    staz.appendChild(stazNazev);

                    //staz obor
                    Element stazObor = doc.createElement(""
                            + Staz.Atribut.OBOR);
                    stazObor.appendChild(doc.createTextNode(""
                            + s.getAtribut(Staz.Atribut.OBOR)));
                    staz.appendChild(stazObor);

                    //staz datum nastupu
                    Element stazDatNast = doc.createElement(""
                            + Staz.Atribut.DATNASTUPU);
                    stazDatNast.appendChild(doc.createTextNode(""
                            + dateFormat.format(s.getAtribut(Staz.Atribut.DATNASTUPU))));
                    staz.appendChild(stazDatNast);

                    //staz delka (dny)
                    Element stazDelka = doc.createElement(""
                            + Staz.Atribut.DELKA);
                    stazDelka.appendChild(doc.createTextNode(""
                            + s.getAtribut(Staz.Atribut.DELKA)));
                    staz.appendChild(stazDelka);

                    //staz kapacita
                    Element stazKapacita = doc.createElement(""
                            + Staz.Atribut.KAPACITA);
                    stazKapacita.appendChild(doc.createTextNode(""
                            + s.getAtribut(Staz.Atribut.KAPACITA)));
                    staz.appendChild(stazKapacita);

                    //staz lokalita
                    Element stazLokalita = doc.createElement(""
                            + Staz.Atribut.DELKA);
                    stazLokalita.appendChild(doc.createTextNode(""
                            + s.getAtribut(Staz.Atribut.DELKA)));
                    staz.appendChild(stazLokalita);

                    //staz popis
                    Element stazPopis = doc.createElement(""
                            + Staz.Atribut.POPIS);
                    stazPopis.appendChild(doc.createTextNode(""
                            + s.getAtribut(Staz.Atribut.POPIS)));
                    staz.appendChild(stazPopis);

                    //staz STUDENTI
                    Element studentRoot = doc.createElement("studenti");
                    staz.appendChild(studentRoot);
                    int k = 0;
                    for (Student stud : s.getStudenty()) {
                        Element student = doc.createElement("student");
                        studentRoot.appendChild(student);
                        student.setAttribute("id", "" + (k++));

                        //student jmeno
                        Element studentJmeno = doc.createElement(""
                                + Student.Atribut.JMENO);
                        studentJmeno.appendChild(doc.createTextNode(""
                                + stud.getAtribut(Student.Atribut.JMENO)));
                        student.appendChild(studentJmeno);

                        //student email
                        Element studentEmail = doc.createElement(""
                                + Student.Atribut.EMAIL);
                        studentEmail.appendChild(doc.createTextNode(""
                                + stud.getAtribut(Student.Atribut.EMAIL)));
                        student.appendChild(studentEmail);

                        //student telefon
                        Element studentTel = doc.createElement(""
                                + Student.Atribut.TELEFON);
                        studentTel.appendChild(doc.createTextNode(""
                                + stud.getAtribut(Student.Atribut.TELEFON)));
                        student.appendChild(studentTel);

                        //student xname
                        Element studentXname = doc.createElement(""
                                + Student.Atribut.XNAME);
                        studentXname.appendChild(doc.createTextNode(""
                                + stud.getAtribut(Student.Atribut.XNAME)));
                        student.appendChild(studentXname);

                        //student datum narozeni
                        Element studentDatNar = doc.createElement(""
                                + Student.Atribut.DATNAR);
                        studentDatNar.appendChild(doc.createTextNode(""
                                + dateFormat.format(stud.getAtribut(Student.Atribut.DATNAR))));
                        student.appendChild(studentDatNar);

                        //student fakulta
                        Element studentFak = doc.createElement(""
                                + Student.Atribut.FAKULTA);
                        studentFak.appendChild(doc.createTextNode(""
                                + stud.getAtribut(Student.Atribut.FAKULTA)));
                        student.appendChild(studentFak);

                        //student semestr
                        Element studentSem = doc.createElement(""
                                + Student.Atribut.SEMESTR);
                        studentSem.appendChild(doc.createTextNode(""
                                + stud.getAtribut(Student.Atribut.SEMESTR)));
                        student.appendChild(studentSem);
                    }
                }
            }

            // samotny zapis do XML souboru
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("kits.xml"));

            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

            // HOTOVO !! soubor kits.xml by uz mel obsahovat aktualni data
        } catch (ParserConfigurationException | TransformerException ex) {
            Model.logger.log(Level.SEVERE, "Udaje nebyly ulozeny!", ex);
        }
    }
}
