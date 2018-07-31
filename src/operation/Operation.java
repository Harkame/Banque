package operation;

import java.util.*;

import compte.*;

public abstract class Operation
{
	private final int	numero_operation;
	private static int	incr;
	private String		libelle;
	private Date		date;
	private Compte		compte;
	private double		montant;

	protected enum Statut
	{
		OK, KO, Attente
	}

	protected Statut statut;

	/**
	 * Constructeur - Operation
	 * 
	 * @param libelle
	 *             Nom du compte
	 * @param compte
	 *             Compte sur lequel faire une operation
	 * @param montant
	 *             Montant a debiter/crediter
	 */
	public Operation(String libelle, Compte compte, double montant)
	{
		this.numero_operation = incr++;
		this.libelle = libelle;
		this.compte = compte;
		this.montant = montant;
		this.date = Calendar.getInstance().getTime();
		statut = Statut.Attente;
		compte.getHistorique().add(this);
	}

	public int getidOperation()
	{
		return this.numero_operation;
	}

	/**
	 * Getter_Operation - Libelle
	 * 
	 * @return this.libelle
	 */
	public String getLibelle()
	{
		return this.libelle;
	}

	/**
	 * Getter - Statut d'une operation (this)
	 * 
	 * @return this.statut
	 */
	public Statut getStatut()
	{
		return this.statut;
	}

	/**
	 * Setter - Statut d'une operation (this)
	 * 
	 * @param statut
	 *             Nouveau statut
	 */
	public void setStatut(Statut statut)
	{
		this.statut = statut;
	}

	/**
	 * Getter - Date d'une operation (this)
	 * 
	 * @return this.date
	 */
	public Date getDate()
	{
		return this.date;
	}

	/**
	 * Getter - Montant d'une operation (this)
	 * 
	 * @return this.montant
	 */
	public double getMontant()
	{
		return this.montant;
	}

	/**
	 * Getter - Compte sur lequel porte une operation (this)
	 * 
	 * @return this.compte
	 */
	public Compte getCompte()
	{
		return this.compte;
	}

	/**
	 * Permet de faire apsser le statut d'une operation en Attente
	 */
	public void miseAttente()
	{
		this.statut = Statut.Attente;
	}

	/**
	 * Permet de faire passer le statut d'une operation a KO
	 */
	public void miseEchec()
	{
		this.statut = Statut.KO;
	}

}
