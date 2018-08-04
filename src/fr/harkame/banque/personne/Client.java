package fr.harkame.banque.personne;

import java.util.ArrayList;

import fr.harkame.banque.compte.*;
import fr.harkame.banque.compte.Compte.Statut;

public class Client extends Personne
{
	private final int			idClient;
	private static int			incr;
	private ArrayList<Compte>	tabCompte;

	/**
	 * Constructeur - Client
	 * 
	 * @param nom
	 *             Nom du client
	 */
	public Client(String nom)
	{
		super(nom);
		this.idClient = incr++;
		this.tabCompte = new ArrayList<Compte>();
		System.out.println("Creation client");
		System.out.println("              - Nom : " + this.getNom());
		System.out.println("              - Identifiant :" + this.getidClient());
		System.out.println("");
	}

	/**
	 * Getter - nom du Client (this)
	 * 
	 * @return this.getNom();
	 */

	public String getNomClient()
	{
		return this.getNom();
	}

	/**
	 * permet d'attribuer un compte, a un client (this)
	 * 
	 * @param compte
	 *             Compte a attribuer
	 */
	public void AttribuerCompte(Compte compte)
	{
		if(compte.getStatut().equals(Statut.Attente))
		{
			this.tabCompte.add(compte);
			System.out.println("Attribution Compte");
			System.out.println("                 - Compte : " + compte.getidCompte());
			System.out.println("                 - Client : " + this.getidClient());
			compte.setStatut(Statut.Attribue);
		}
		else
			System.out.println("Compte deja attribue");
		System.out.println("");
	}

	/**
	 * Permet de savoir si un compte appartient a un Client (this)
	 * 
	 * @param compte
	 *             Compte a verifier
	 * @return true Si le compte appartient au Client 'this)
	 */
	public boolean appartient(Compte compte)
	{
		for(int i = 0; i < this.gettabCompte().size(); i++)
		{
			if(this.gettabCompte().get(i) == compte)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Getter - Liste de compte d'un client
	 * 
	 * @return this.tabCompte;
	 */

	public ArrayList<Compte> gettabCompte()
	{
		return this.tabCompte;
	}

	/**
	 * Getter - id d'un client this
	 * 
	 * @return this.idClient
	 */
	public int getidClient()
	{
		return this.idClient;
	}

}
