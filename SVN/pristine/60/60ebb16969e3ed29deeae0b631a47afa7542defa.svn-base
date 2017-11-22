package model;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author xjurp20
 */
public class ImportDat {

    private final Set<Student> studenti; //externi databaze studentu
    private final Set<Firma> firmy;
    private DateFormat dateFormat;

    public ImportDat() {
        studenti = new HashSet<>();
        firmy = new HashSet<>();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        loadFirmy(new File("kits.xml"));
        loadStudenty(new File("studenti.xml"));
    }

    private void loadFirmy(File file) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            //FIRMY
            NodeList nListFirma = doc.getElementsByTagName("firma");
            for (int j = 0; j < nListFirma.getLength(); j++) {
                Node nNodeFirma = nListFirma.item(j);
                if (nNodeFirma.getNodeType() == Node.ELEMENT_NODE) {
                    Set<Staz> staze = new HashSet<>();
                    Element eFirma = (Element) nNodeFirma;
                    //STAZE
                    NodeList nListStaz = eFirma.getElementsByTagName("staz");
                    for (int i = 0; i < nListStaz.getLength(); i++) {
                        Node nNodeStaz = nListStaz.item(i);
                        if (nNodeStaz.getNodeType() == Node.ELEMENT_NODE) {
                            Set<Student> studenti = new HashSet<>();
                            Element eStaz = (Element) nNodeStaz;
                            Staz staz = new Staz(
                                    eStaz.getElementsByTagName(""
                                    + Staz.Atribut.NAZEV).item(0).getTextContent(),
                                    Staz.Obor.valueOf(eStaz.getElementsByTagName(""
                                    + Staz.Atribut.OBOR).item(0).getTextContent()),
                                    dateFormat.parse(eStaz.getElementsByTagName(""
                                    + Staz.Atribut.DATNASTUPU).item(0).getTextContent()),
                                    Integer.parseInt(eStaz.getElementsByTagName(""
                                    + Staz.Atribut.DELKA).item(0).getTextContent()),
                                    Integer.parseInt(eStaz.getElementsByTagName(""
                                    + Staz.Atribut.KAPACITA).item(0).getTextContent()),
                                    Staz.Lokalita.valueOf(eStaz.getElementsByTagName(""
                                    + Staz.Atribut.LOKALITA).item(0).getTextContent()),
                                    eStaz.getElementsByTagName(""
                                    + Staz.Atribut.POPIS).item(0).getTextContent());
                            //STUDENTI
                            NodeList nListStudent = eStaz.getElementsByTagName("student");
                            for (int k = 0; k < nListStudent.getLength(); k++) {
                                Node nNodeStudent = nListStudent.item(k);
                                if (nNodeStudent.getNodeType() == Node.ELEMENT_NODE) {
                                    Element eStudent = (Element) nNodeStudent;
                                    studenti.add(
                                            new Student(
                                            eStudent.getElementsByTagName(""
                                            + Student.Atribut.JMENO).item(0).getTextContent(),
                                            eStudent.getElementsByTagName(""
                                            + Student.Atribut.EMAIL).item(0).getTextContent(),
                                            Integer.parseInt(eStudent.getElementsByTagName(""
                                            + Student.Atribut.TELEFON).item(0).getTextContent()),
                                            eStudent.getElementsByTagName(""
                                            + Student.Atribut.XNAME).item(0).getTextContent(),
                                            dateFormat.parse(eStudent.getElementsByTagName(""
                                            + Student.Atribut.DATNAR).item(0).getTextContent()),
                                            Student.Fakulta.valueOf(eStudent.getElementsByTagName(""
                                            + Student.Atribut.FAKULTA).item(0).getTextContent()),
                                            Integer.parseInt(eStudent.getElementsByTagName(""
                                            + Student.Atribut.SEMESTR).item(0).getTextContent())));
                                }
                            }
                            staz.prihlasStud(studenti.toArray(new Student[studenti.size()]));
                            staze.add(staz);
                        }
                    }
                    //ADRESA
                    Element eAdresa = (Element) eFirma.getElementsByTagName("adresa").item(0);
                    Adresa adresa = new Adresa(
                            Adresa.Lokalita.valueOf(eAdresa.getElementsByTagName(""
                            + Adresa.Atribut.LOKALITA).item(0).getTextContent()),
                            eAdresa.getElementsByTagName(""
                            + Adresa.Atribut.MESTO).item(0).getTextContent(),
                            eAdresa.getElementsByTagName(""
                            + Adresa.Atribut.ULICE).item(0).getTextContent(),
                            Integer.parseInt(eAdresa.getElementsByTagName(""
                            + Adresa.Atribut.CP).item(0).getTextContent()),
                            Integer.parseInt(eAdresa.getElementsByTagName(""
                            + Adresa.Atribut.PSC).item(0).getTextContent()));
                    //KONTAKT
                    Element eOsoba = (Element) eFirma.getElementsByTagName("kontakt").item(0);
                    Osoba kontakt = new Osoba(
                            eOsoba.getElementsByTagName(""
                            + Osoba.Atribut.JMENO).item(0).getTextContent(),
                            eOsoba.getElementsByTagName(""
                            + Osoba.Atribut.EMAIL).item(0).getTextContent(),
                            Integer.parseInt(eOsoba.getElementsByTagName(""
                            + Osoba.Atribut.TELEFON).item(0).getTextContent()));
                    //FIRMA
                    Firma firma = new Firma(
                            eFirma.getElementsByTagName(""
                            + Firma.Atribut.NAZEV).item(0).getTextContent(),
                            Integer.parseInt(eFirma.getElementsByTagName(""
                            + Firma.Atribut.ICO).item(0).getTextContent()),
                            adresa,
                            kontakt);

                    Iterator<Staz> iterA = staze.iterator();
                    while (iterA.hasNext()) {
                        firma.pridejStaze(iterA.next());
                    }

                    firmy.add(firma);
                }
            }
        } catch (Exception ex) {
            Model.logger.log(Level.SEVERE, "Data nebyly nacteny!", ex);
        }
    }

    private void loadStudenty(File file) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("student");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    studenti.add(
                            new Student(
                            eElement.getElementsByTagName(""
                            + Student.Atribut.JMENO).item(0).getTextContent(),
                            eElement.getElementsByTagName(""
                            + Student.Atribut.EMAIL).item(0).getTextContent(),
                            Integer.parseInt(eElement.getElementsByTagName(""
                            + Student.Atribut.TELEFON).item(0).getTextContent()),
                            eElement.getElementsByTagName(""
                            + Student.Atribut.XNAME).item(0).getTextContent(),
                            dateFormat.parse(eElement.getElementsByTagName(""
                            + Student.Atribut.DATNAR).item(0).getTextContent()),
                            Student.Fakulta.valueOf(eElement.getElementsByTagName(""
                            + Student.Atribut.FAKULTA).item(0).getTextContent()),
                            Integer.parseInt(eElement.getElementsByTagName(""
                            + Student.Atribut.SEMESTR).item(0).getTextContent())));
                }
            }
        } catch (Exception ex) {
            Model.logger.log(Level.SEVERE, "Data nebyly nacteny!", ex);
        }
    }

    public Set<Firma> getFirmy() {
        return firmy;
    }

    public Set<Student> getStudenty() {
        return studenti;
    }
}
