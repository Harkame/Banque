package personne;

public abstract class Personne
{
	private String nom;

	/**
	 * Constructeur - Personne
	 * 
	 * @param nom
	 *             Nom de la personne
	 */
	public Personne(String nom)
	{
		this.nom = nom;
	}

	/**
	 * Getter - Le nom d'une personne (this)
	 * 
	 * @return this.nom
	 */
	public String getNom()
	{
		return this.nom;
	}
}
