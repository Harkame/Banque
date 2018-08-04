package fr.harkame.banque.compte.remunere;

import java.util.Date;

import fr.harkame.banque.exception.OperationBancaireException;

/**
 * 
 * @author Louis Daviaud
 *
 */
public class PlanEpargneBloque extends CompteRemunere {

	private Date date;

	/**
	 * Constructeur - Plan d'epargne bloque
	 *
	 * @param libelle
	 *            Nom du compte
	 * @param solde
	 *            Somme d'argent du compte
	 * @param taux_remuneration
	 *            Taux de remuneration du compte
	 * @param solde_plafond
	 *            Solde plafon du compte
	 * 
	 * @param DateAutorisation
	 *            Date a laquel les operation seront autorise
	 * @throws OperationBancaireException
	 *             Le solde du compte depasse le solde plafond
	 */
	public PlanEpargneBloque(String libelle, double solde, double taux_remuneration, double solde_plafond, Date DateAutorisation) throws OperationBancaireException {
		super(libelle, solde, taux_remuneration, solde_plafond);
		this.date = DateAutorisation;
		System.out.println("Creation du plan epargne bloque ");
		System.out.println("                              - Identifiant : " + this.getidCompte());
		System.out.println("                              - Libelle : " + this.getLibelle());
		System.out.println("                              - Solde : " + this.getSolde());
		System.out.println("                              - Taux de remuneration : " + this.getTaux_Remuneration());
		System.out.println("                              - Solde plafond : " + this.getSoldePlafond());
		System.out.println("                              - Date autorisation : " + this.getDate());
		System.out.println("");
	}

	/**
	 * Getter - Date d'autorisation d'un plan d'epargne bloque (this)
	 * 
	 * @return this.date
	 */
	public Date getDate() {
		return this.date;
	}

}
