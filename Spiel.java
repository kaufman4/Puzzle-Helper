
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Markus
 */
public class Spiel {

    private static int anzObjekte;
    private static int[] moeglichkeiten;

    public void setAnzObjekte(int i) {
        anzObjekte = i;
        initMoeglichkeiten();
    }

    public void initMoeglichkeiten() {
        moeglichkeiten = new int[anzObjekte];
        for (int i = 0; i < anzObjekte; i++) {
            moeglichkeiten[i] = i;

        }

    }

    public void eingabeAnzObjekte() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            do {
                System.out.print("Anzahl der Figuren: ");
                anzObjekte = Integer.parseInt(reader.readLine());
            } while (anzObjekte <= 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        initMoeglichkeiten();
    }

    public int getAnzObjekte() {
        return anzObjekte;
    }

    public static void main(String[] Args) {
        SpielFeld spielfeld = new SpielFeld();
        spielfeld.setleerZeichen(" nix ");
        spielfeld.eingabeAbmase();
        spielfeld.initFeld();
        spielfeld.ausgabeSpielfeld();
        Spiel spiel = new Spiel();
        spiel.eingabeAnzObjekte();

        Figur[] figuren = new Figur[anzObjekte];
        String[][] temp;
        int eingabe;
        String korrektErfasst = "N";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < anzObjekte; i++) {
            figuren[i] = new Figur();
            figuren[i].eingabeFarbe();
            figuren[i].eingabeAbmase(spielfeld);
            temp = new String[figuren[i].getAnzZeilen()][figuren[i].getAnzSpalten()];
            for (int k = 0; k < figuren[i].getAnzZeilen(); k++) {
                for (int q = 0; q < figuren[i].getAnzSpalten(); q++) {
                    System.out.println("koordinaten: \n" + "Zeile: " + k + " Spalte: " + q);
                    try {
                        eingabe = Integer.parseInt(reader.readLine());
                        if (eingabe == 0) {
                            temp[k][q] = spielfeld.getleerZeichen();
                        }
                        if (eingabe == 1) {
                            temp[k][q] = figuren[i].getFarbe();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            figuren[i].setForm(temp);
            figuren[i].ausgabeForm();
            System.out.print("Form korrekt? (J/N): ");
            try {
                korrektErfasst = reader.readLine();
                korrektErfasst = korrektErfasst.toUpperCase();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (korrektErfasst.equals("N")) {
                i--;
            }
        }


        SpieleHelfer helper = new SpieleHelfer();

        for (int i = 0; i < anzObjekte; i++) {
            figuren[i].ausgabeForm();
            System.out.println();
        }

        PermutationNeu permutation = new PermutationNeu();
        int[] aktuelleReihenfolge = permutation.nextPerm(moeglichkeiten);
        boolean ging = false, geschafft = false;
        int versuche = 0;
        int erfolgreichGesetzt = 0;
        while (aktuelleReihenfolge != null && !geschafft) {
            aktuelleReihenfolge = permutation.nextPerm(aktuelleReihenfolge);

            for (int i = 0; i < anzObjekte; i++) {
//              
                versuche++;
                ging = helper.findePosition(spielfeld, figuren[aktuelleReihenfolge[i]]);
//               
                if (!ging) {
                    spielfeld.resetFeld();
                    erfolgreichGesetzt = 0;
                    break;
                } else {
                    spielfeld.uebernehmeFigur(figuren[aktuelleReihenfolge[i]]);
                    erfolgreichGesetzt++;

                    if (erfolgreichGesetzt == anzObjekte) {
                        geschafft = true;
                        System.out.println("Spiel gelöst in " + versuche + " Versuchen!");
                        spielfeld.ausgabeSpielfeld();
                    }

                }
            }
        }
        if (!geschafft) {
            System.out.println("Spiel konnte nicht gelöst werden");
        }
        try {
            eingabe = Integer.parseInt(reader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
