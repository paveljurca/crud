package view;

import java.io.IOException;
import java.net.URL;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.text.Document;

/**
 * Dialogovo okno pro napovedu
 *
 * @author xjurp20
 */
public class OknoNapoveda {

    private JDialog dialogOkno;
    private JEditorPane edtPaneObsah;
    private URL obsahURL;

    public OknoNapoveda(JFrame parent, String title, URL obsahURL) {
        this.obsahURL = obsahURL;
        
        dialogOkno = new JDialog();
        edtPaneObsah = new JEditorPane();

        dialogOkno.setTitle(title);
        edtPaneObsah.setEditable(false);

        dialogOkno.setSize(500, 600);
        dialogOkno.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        dialogOkno.setLocationRelativeTo(parent);

        dialogOkno.add(new JScrollPane(edtPaneObsah));
    }

    public void zobraz() {
        try {
            Document doc = edtPaneObsah.getDocument();
            doc.putProperty(Document.StreamDescriptionProperty, null);
            edtPaneObsah.setPage(obsahURL);
            
            dialogOkno.setVisible(true);
        } catch (IOException e) {
        }
    }
}
