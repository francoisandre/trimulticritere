package testmulticritere;

public class Personne implements TriableMultiCriteresAlphabetiques{

	public final static String NOM = "nom";
	public final static String PRENOM = "prenom";
	public final static String GENRE = "genre";
	public final static String VILLE = "ville";

	private String nom;
	private String prenom;
	private String genre;
	private String ville;

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}

	public String calculeClePourCritere(String nomCritere) {
		if (nomCritere.equals(NOM)) {
			return getNom();
		}
		if (nomCritere.equals(PRENOM)) {
			return getPrenom();
		}
		if (nomCritere.equals(GENRE)) {
			return getGenre();
		}
		if (nomCritere.equals(VILLE)) {
			return getVille();
		}
		return null;
	}
	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", prenom=" + prenom + ", genre=" + genre + ", ville=" + ville + "]";
	}





}
