package fr.harkame.banque.compte;

public class CompteCourant extends Compte
{
	/**
	 * Constructeur - Compte courant
	 * 
	 * @param libelle
	 *             Nom du compte
	 * @param solde
	 *             Montant d'argent sur le compte
	 */
	public CompteCourant(String libelle, double solde)
	{
		super(libelle, solde);
		System.out.println("Creation du compte courant ");
		System.out.println("                         - Identifiant : " + this.getidCompte());
		System.out.println("                         - Libelle : " + this.getLibelle());
		System.out.println("                         - Solde : " + this.getSolde());
		System.out.println("");
	}
}
