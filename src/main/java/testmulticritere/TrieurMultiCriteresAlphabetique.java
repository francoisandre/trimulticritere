package testmulticritere;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TrieurMultiCriteresAlphabetique {

	public final static String SEPARATEUR = "@@@";

	public static List<? extends TriableMultiCriteresAlphabetiques> trie(List<? extends TriableMultiCriteresAlphabetiques> entrants, CriteresTri criteresTri) {

		if (entrants == null) {
			return null;
		}
		
		if ((criteresTri == null) || (criteresTri.isEmpty())) {
			return entrants;
		}
		else {
			Map<String, TriableMultiCriteresAlphabetiques> tableIntermediaire = new HashMap<String, TriableMultiCriteresAlphabetiques>();
			for (TriableMultiCriteresAlphabetiques entrant : entrants) {
				tableIntermediaire.put(calculeCle(entrant, criteresTri), entrant);
			}

			List<String> cles = new ArrayList<String>();
			cles.addAll(tableIntermediaire.keySet());
			Collections.sort(cles);

			List<TriableMultiCriteresAlphabetiques> resultat = new ArrayList<TriableMultiCriteresAlphabetiques>(cles.size());
			for (String cle : cles) {
				resultat.add(tableIntermediaire.get(cle));
			}
			return resultat;
		}
	}


	private static String calculeCle(TriableMultiCriteresAlphabetiques entrant, CriteresTri criteresTri) {
		List<String> morceauxCles = new ArrayList<String>(); 
		for (String critere : criteresTri) {
			morceauxCles.add(""+entrant.calculeClePourCritere(critere));
		}
		
		//On rajoute une chaine aléatoire pour gérer les doublons
		morceauxCles.add(UUID.randomUUID().toString());
		
		return String.join(SEPARATEUR, morceauxCles);
	}

}
