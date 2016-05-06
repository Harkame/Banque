package compte.remunere;

import exception.OperationBancaireException;

/**
 * 
 * @author Louis Daviaud
 *
 */
public class CompteEpargne extends CompteRemunere {

	/**
	 * Constrcteur - Compte epargne
	 * 
	 * @param libellé
	 *            Nom du compte
	 * @param solde
	 *            Montant d'argent sur le compte
	 * @param taux_rémunération
	 *            Taux de remuneration du compte
	 * @param solde_plafond
	 *            Solde plafond du compte
	 * @throws OperationBancaireException
	 *             Le solde du compte depasse le solde plafond
	 */
	public CompteEpargne(String libellé, double solde, double taux_rémunération, double solde_plafond) throws OperationBancaireException {
		super(libellé, solde, taux_rémunération, solde_plafond);
		System.out.println("Creation du compte epargne ");
		System.out.println("                         - Identifiant : " + this.getidCompte());
		System.out.println("                         - Libelle : " + this.getLibelle());
		System.out.println("                         - Solde : " + this.getSolde());
		System.out.println("                         - Taux de remuneration : " + this.getTaux_Remuneration());
		System.out.println("                         - Solde plafond : " + this.getSoldePlafond());
		System.out.println("");
	}
}
