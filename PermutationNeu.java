/*
 * Liefert eine Permutation nach der anderen.
 * Christian Semrau, [URL]http://chsemrau.de[/URL]
 */
public class PermutationNeu{
/**
 * Liefert die lexikalisch naechste Permutation zu <code>curr</code>.
 * Wenn das bereits die letzte war, wird <code>null</code> geliefert.
 * Funktioniert auch, wenn die Elemente des Feldes nicht paarweise
 * verschieden sind.
 */
 
public  int[] nextPerm(int[] curr) {
	if (curr == null || curr.length<=0) return null;
	int[] next = (int[]) curr.clone();

	//Ende auswaehlen
	int marker = curr.length - 1;

	//solange nach vorn wandern, bis der Vorgaenger kleiner ist
	while (marker > 0 && curr[marker - 1] >= curr[marker]) marker--;

	//wenn keiner mehr da ist, dann war das die letzte Permutation
	if (marker <= 0)
		return null;

	//diesen kleineren Vorgaenger auswaehlen
	marker--;

	//aus allen Nachfolgern den kleinsten auswaehlen, der groesser ist
	int neu = marker + 1; // garantiert groesser
	for (int h = next.length - 1; h > marker; h--)
		if (next[h] > next[marker] && next[h] < next[neu])
			neu = h;

	//tauschen
	int tmp = next[neu];
	next[neu] = next[marker];
	next[marker] = tmp;

	//die Nachfolger aufsteigend sortieren:
	//Indizes einschliesslich marker+1 und next.length-1
	sort(next, marker + 1, next.length - 1);

	//fertig
	return next;
}

/**
 * Sortiert die Elemente <code>le</code> bis <code>re</code>
 * (beide einschliesslich) von <code>feld</code>.
 * (Insertion-Sort, fuer diesen Zweck aufgrund kleiner Feldgroessen
 * ausreichend)
 */
public void sort(int[] feld, int le, int re) {
  if (feld==null) return;
  if (le<0) le=0;
  if (re>=feld.length) re=feld.length-1;
  if (le>=re) return;

  for (int i = le+1; i <= re; i++){
	int marker = i;
	int temp = feld[i];
	while (marker > le && feld[marker - 1]>temp) {
	  feld[marker] = feld[marker - 1];
	  marker--;
	}
	if (marker!=i) {
	  feld[marker] = temp;
	}
  }
}
}

