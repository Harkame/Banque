package fr.harkame.banque.compte.remunere;

import fr.harkame.banque.exception.OperationBancaireException;

public class CompteEpargne extends CompteRemunere
{

	/**
	 * Constrcteur - Compte epargne
	 * 
	 * @param libelle
	 *             Nom du compte
	 * @param solde
	 *             Montant d'argent sur le compte
	 * @param taux_remuneration
	 *             Taux de remuneration du compte
	 * @param solde_plafond
	 *             Solde plafond du compte
	 * @throws OperationBancaireException
	 *              Le solde du compte depasse le solde plafond
	 */
	public CompteEpargne(String libelle, double solde, double taux_remuneration, double solde_plafond)
		throws OperationBancaireException
	{
		super(libelle, solde, taux_remuneration, solde_plafond);
		System.out.println("Creation du compte epargne ");
		System.out.println("                         - Identifiant : " + this.getidCompte());
		System.out.println("                         - Libelle : " + this.getLibelle());
		System.out.println("                         - Solde : " + this.getSolde());
		System.out.println("                         - Taux de remuneration : " + this.getTaux_Remuneration());
		System.out.println("                         - Solde plafond : " + this.getSoldePlafond());
		System.out.println("");
	}
}
