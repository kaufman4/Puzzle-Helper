
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Markus
 */
public class SpielFeld {

    private String[][] feld;
    private String leerZeichen;
    private int spalten;
    private int zeilen;

    public boolean spielFeldVoll() {
        return false;
    }

    public void eingabeAbmase() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Eingabe der Spielfeldgroese");

        try {
            do {
                System.out.print("Spalten: ");
                spalten = Integer.parseInt(reader.readLine());
            } while (spalten <= 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            do {
                System.out.print("Zeilen: ");
                zeilen = Integer.parseInt(reader.readLine());
            } while (zeilen <= 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setleerZeichen(String z) {
        leerZeichen = z;
    }

    public String getleerZeichen() {
        return leerZeichen;
    }

    public String[][] getFeld() {
        return feld;
    }

    public void initFeld() {
        feld = new String[zeilen][spalten];
        for (int i = 0; i < zeilen; i++) {
            for (int j = 0; j < spalten; j++) {
                feld[i][j] = leerZeichen;
                //   feld[i][j] = " # ";
            }
        }
        spalten = feld[0].length;
        zeilen = feld.length;
    }

    public int getAnzSpalten() {
        return spalten;
    }

    public int getAnzZeilen() {
        return zeilen;
    }

    public void resetFeld() {
        for (int i = 0; i < zeilen; i++) {
            for (int j = 0; j < spalten; j++) {
                feld[i][j] = leerZeichen;
            }
        }
    }

    public void uebernehmeFigur(Figur Form) {
        int x = Form.getObenLinks()[0];
        int y = Form.getObenLinks()[1];
        for (int i = x; i < x + Form.getAnzZeilen(); i++) {
            for (int j = y; j < y + Form.getAnzSpalten(); j++) {
                if (!Form.getForm()[i - x][j - y].equals(leerZeichen)) {
                    feld[i][j] = Form.getForm()[i - x][j - y];
                }
            }
        }
    }

    public void ausgabeSpielfeld() {
        for (int i = 0; i < zeilen; i++) {
            for (int j = 0; j < spalten; j++) {
                System.out.print(feld[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
