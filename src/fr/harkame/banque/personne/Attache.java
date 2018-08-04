package fr.harkame.banque.personne;

import java.util.ArrayList;

import fr.harkame.banque.compte.Compte;
import fr.harkame.banque.exception.OperationBancaireException;
import fr.harkame.banque.exception.PersonnelNonAutoriseException;
import fr.harkame.banque.operation.*;

public class Attache extends Personne
{
	private final int				idAttache;
	private static int				incr	= 0;
	private static ArrayList<Client>	tabClient;

	/**
	 * Constructeur - Attache, gerant des clients
	 * 
	 * @param nom
	 *             Nom de l'attache
	 */
	public Attache(String nom)
	{
		super(nom);
		this.idAttache = Attache.incr++;
		Attache.tabClient = new ArrayList<Client>();
		System.out.println("Creation de l'attache ");
		System.out.println("                    - Nom : " + this.getNom());
		System.out.println("                    - identifiant " + this.getidAttache());
		System.out.println("");
	}

	/**
	 * Getter - Nom d'un l'Attache (this)
	 * 
	 * @return this.getNom()
	 */
	public String getNomAttache()
	{
		return this.getNom();
	}

	/**
	 * Getter - id d'un Attache (this)
	 * 
	 * @return this.idAttache
	 */
	public int getidAttache()
	{
		return this.idAttache;
	}

	/**
	 * Attribut un client a un Attache (this) en le rajoutant dans son
	 * ArrayList de client
	 * 
	 * @param client
	 *             Client a associer
	 */
	public void SuivreClient(Client client)
	{
		this.gettabClient().add(client);
		System.out.println("Association Attache - Client");
		System.out.println("                           - Attache : " + this.getidAttache());
		System.out.println("                           - Client : " + client.getidClient());
		System.out.println("");
	}

	/**
	 * Permet de savoir si un compte appartient a un client suivit par un
	 * attache (this)
	 * 
	 * @param compte
	 *             Le compte a verifier
	 * @return true Si le compte appartient a un client suivit par un attache
	 *         this
	 */

	public boolean estAssocie(Compte compte)
	{
		for(int i = 0; i < this.gettabClient().size(); i++)
		{
			if(this.gettabClient().get(i).appartient(compte))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Permet de recuperer tous les client suivit par l'attache (this)
	 * 
	 * @return tabClient La liste des client suivit par l'attache (this)
	 */
	public ArrayList<Client> gettabClient()
	{
		return Attache.tabClient;
	}

	/**
	 * Permet a un attache (this) de forcer le debit
	 * 
	 * @param debit
	 *             Operation de debit a forcer
	 * @return debit.getCompte().getSolde() Le nouveau solde du compte
	 * @throws PersonnelNonAutoriseException
	 *              Le compte n'appartient pas a un client suivit par cette
	 *              attache
	 * @throws OperationBancaireException
	 *              Montant negatif
	 */
	public double forcerDebit(Debit debit) throws PersonnelNonAutoriseException, OperationBancaireException
	{
		if(!(this.estAssocie(debit.getCompte())))
		{
			throw new PersonnelNonAutoriseException("Cette attache ne suit pas le client proprietaire de ce compte");
		}
		try
		{
			debit.DebitPossible();
			debit.passerDebit();
		}
		catch(OperationBancaireException e)
		{
			debit.getCompte().Debiter(debit.getMontant());
		}
		return debit.getCompte().getSolde();
	}

	/**
	 * Permet a un attache (this) de forcer le credit
	 * 
	 * @param credit
	 *             Operation de credit a forcer
	 * @return credit.getCompte().getSolde() Le nouveau solde du compte
	 * @throws PersonnelNonAutoriseException
	 *              compte n'appartient pas a un client suivit par cette
	 *              attache
	 * @throws OperationBancaireException
	 *              Montant negatif
	 */
	public double forcerCredit(Credit credit) throws PersonnelNonAutoriseException, OperationBancaireException
	{
		if(!(this.estAssocie(credit.getCompte())))
		{
			throw new PersonnelNonAutoriseException("Cette attache ne suit pas le client proprietaire de ce compte");
		}
		try
		{
			credit.CreditPossible();
			credit.passerCredit();
		}
		catch(OperationBancaireException e)
		{
			credit.getCompte().Crediter(credit.getMontant());
		}
		return credit.getCompte().getSolde();
	}
}
