
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
public class Figur {

    private String farbe;
    private String[][] form;
    private int[] obenLinks;
    private int i = 0;
    private int j = 0;
    private int spalten;
    private int zeilen;

    public void eingabeAbmase(SpielFeld spielfeld) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Eingabe der Figurgroese");

        try {
            do {
                System.out.print("Spalten: ");
                spalten = Integer.parseInt(reader.readLine());
            } while (spalten > spielfeld.getAnzSpalten() || spalten<=0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            do {
                System.out.print("Zeilen: ");
                zeilen = Integer.parseInt(reader.readLine());
            } while (zeilen > spielfeld.getAnzZeilen() || zeilen<=0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eingabeFarbe() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Farbe: ");
        try {
            farbe = " " + reader.readLine() + " ";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setObenLinks(int[] ecke) {
        obenLinks = ecke.clone();
    }

    public int getAnzSpalten() {
        return spalten;
    }

    public int getAnzZeilen() {
        return zeilen;
    }

    public int[] getObenLinks() {
        return obenLinks;
    }

    public int getI() {
        return i;
    }

    public void setI(int p) {
        i = p;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int p) {
        j = p;
    }

    public void setFarbe(String color) {
        farbe = color;
    }

    public String getFarbe() {
        return farbe;
    }

    public void setForm(String[][] f) {

        form = f.clone();
        spalten = form[0].length;
        zeilen = form.length;
    }

    public String[][] getForm() {

        return form;
    }

    public void ausgabeForm() {
        for (int i = 0; i < zeilen; i++) {
            for (int j = 0; j < spalten; j++) {
                System.out.print(form[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
