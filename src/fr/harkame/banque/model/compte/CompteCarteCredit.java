package fr.harkame.banque.model.compte;

public class CompteCarteCredit extends Compte
{
	/**
	 * Constructeur - Compte carte de credit
	 * 
	 * @param libelle
	 *             Nom du compte
	 * @param solde
	 *             Somme d'argent sur le compte
	 */
	public CompteCarteCredit(String libelle, int solde)
	{
		super(libelle, solde);
		System.out.println("Creation du compte carte de credit ");
		System.out.println("                                 - Identifiant : " + this.getidCompte());
		System.out.println("                                 - Libelle : " + this.getLibelle());
		System.out.println("                                 - Solde : " + this.getSolde());
		System.out.println("");
	}
}
