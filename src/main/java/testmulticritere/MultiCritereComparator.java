package testmulticritere;

import java.util.Comparator;

public class MultiCritereComparator implements Comparator<TriableMultiCriteres>{
	
	private CriteresTri criteres;

	public MultiCritereComparator(CriteresTri criteres) {
		this.criteres = criteres;
		
	}

	public int compare(TriableMultiCriteres o1, TriableMultiCriteres o2) {
		if ((o1 == null) && (o2 == null)) {
			return 0;
		}
		
		if (o1 == null) {
			return -1;
		}
		if (o2 == null) {
			return 1;
		}
		
		for (String critere : criteres) {
			Comparable v1 = o1.calculeClePourCritere(critere);
			Comparable v2 = o2.calculeClePourCritere(critere);
			
			if ((v1 == null) && (v2 == null)) {
				continue;
			}
			
			if (v1 == null) {
				return -1;
			}
			if (v2 == null) {
				return 1;
			}
			
			int aux = v1.compareTo(v2);
			
			if (aux == 0) {
				continue;
			}
			else {
				return aux; 
			}
			
		}
		
		// Les objets sont égaux au sens du comparateur
		return 0;
		
	}

}
