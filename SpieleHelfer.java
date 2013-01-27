/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Markus
 */
public class SpieleHelfer {

    public boolean findePosition(SpielFeld spiel, Figur Form) {
        String[][] feldArr = spiel.getFeld().clone();
        String[][] formArr = Form.getForm().clone();
        int[] temp = new int[2];

        
        temp[0] = -1;
        temp[1] = -1;
        int rechts = 0;
        int unten = 0;
        boolean geht;


        int ok = 0;
        //Zeile
        for (int i = 0; i < spiel.getAnzZeilen(); i++) {
            //Spalten der JEWEILIGEN ZEILE 
            for (int j=0; j < spiel.getAnzSpalten(); j++) {
                
                rechts = spiel.getAnzSpalten() - (j + Form.getAnzSpalten());
            
                unten = spiel.getAnzZeilen() - (i + Form.getAnzZeilen());
    

                if (rechts >= 0 && unten >= 0) {

                    //Platz reicht schon mal -> 
                    // Testen ob bereits andere Farbe überschrieben werden würde
                    geht = true;
                    ok = 0;

                    for (int k = i; k < i + Form.getAnzZeilen() && geht; k++) {
                        for (int t = j; t < j + Form.getAnzSpalten() && geht; t++) {
                            
                            //Wenn in der Form ein Feld nicht belegt ist, ändert sich im Spielfeld nix
                            //Ist an der Stelle der Form eine Farbe eingetragen muss geprüft werden, ob diese Stelle in der Figur noch leer ist
                            //Es muss zunächst für alle Stellen geprüft werden und nur wenn alle Felder ok sind, darf die Figur eingetragen werden

                            //  System.out.println(formArr[k - i][t - j]);

                            //Leerzeichen ändert nix

                            if (formArr[k - i][t - j].equals(spiel.getleerZeichen())) {
                                ok++;
                            } else if (!(formArr[k - i][t - j].equals(spiel.getleerZeichen())) && feldArr[k][t].equals(spiel.getleerZeichen())) {
                                ok++;
                            } else {
                                geht = false;
                            }

                        }

                    }

                    if (ok == (Form.getAnzSpalten() * Form.getAnzZeilen())) {
                        temp[0] = i;
                        temp[1] = j;
                        Form.setObenLinks(temp);
                        return true;
                    }
                }
            }
        }
        Form.setObenLinks(temp);
        return false;
    }
}
