package testmulticritere;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Cette classe utilise le comparateur plus générique 
 * @author fandre5
 *
 */

public class TestTriMulticritereComparator {
	
	private static final int TAILLE_DONNEES = 300;
	private List<Personne> personnes;
	
	@Test
	public void testTri1() {
		Assert.assertEquals(personnes.size(), TAILLE_DONNEES);
		CriteresTri criteres = new CriteresTri();
		criteres.add(Personne.NOM);
		criteres.add(Personne.PRENOM);
		Collections.sort(personnes, new MultiCritereComparator(criteres));
		Assert.assertEquals(((Personne) personnes.get(0)).getNom(), "aaserud");
	}

	
	@Test
	public void testTri2() {
		Assert.assertEquals(personnes.size(), TAILLE_DONNEES);
		CriteresTri criteres = new CriteresTri();
		criteres.add(Personne.PRENOM);
		criteres.add(Personne.NOM);
		Collections.sort(personnes, new MultiCritereComparator(criteres));
		Assert.assertEquals(((Personne) personnes.get(0)).getPrenom(), "aaliyah");
	}
	
	@Test
	public void testTri3() {
		Assert.assertEquals(personnes.size(), TAILLE_DONNEES);
		CriteresTri criteres = new CriteresTri();
		criteres.add(Personne.VILLE);
		criteres.add(Personne.NOM);
		Collections.sort(personnes, new MultiCritereComparator(criteres));
		Assert.assertTrue(((Personne) personnes.get(0)).getVille().startsWith("aalborg"));
	}
	
	
	@Test
	public void testTri4() {
		Assert.assertEquals(personnes.size(), TAILLE_DONNEES);
		CriteresTri criteres = new CriteresTri();
		criteres.add(Personne.GENRE);
		criteres.add(Personne.NOM);
		Collections.sort(personnes, new MultiCritereComparator(criteres));
		
		Assert.assertTrue(((Personne) personnes.get(0)).getNom().startsWith("aaserud"));
		Assert.assertTrue(((Personne) personnes.get(0)).getGenre().startsWith("female"));
		Assert.assertTrue(((Personne) personnes.get(1)).getNom().startsWith("abanuz"));
		Assert.assertTrue(((Personne) personnes.get(1)).getGenre().startsWith("female"));
		Assert.assertTrue(((Personne) personnes.get(TAILLE_DONNEES-1)).getGenre().startsWith("male"));
	}

	
	@Before
	public  void chargeDonnees() {
		personnes = new ArrayList<Personne>();
		String contenu = chargeJson();
		JSONObject json = new JSONObject(contenu);
		 JSONArray tableauPersonnes = (JSONArray) json.get("results");
		 for (Object object : tableauPersonnes) {
			JSONObject personne = (JSONObject) object;
			Personne nouvellePersonne = new Personne();
			nouvellePersonne.setGenre(personne.getString("gender"));
			
			JSONObject name = personne.getJSONObject("name");
			JSONObject location = personne.getJSONObject("location");
			
			nouvellePersonne.setNom(name.getString("last"));
			nouvellePersonne.setPrenom(name.getString("first"));
			nouvellePersonne.setVille(location.getString("city"));
			personnes.add(nouvellePersonne);
		}
		
	}
	
	

	private static String chargeJson() {

		String result = "";
			
		ClassLoader classLoader = TestTriMulticritereComparator.class.getClassLoader();
		try {
		    result = IOUtils.toString(classLoader.getResourceAsStream("testmulticritere/data.json"), StandardCharsets.UTF_8.name());
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return result;
	  }
	

}
