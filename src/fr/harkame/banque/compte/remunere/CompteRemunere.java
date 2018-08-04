package fr.harkame.banque.compte.remunere;

import fr.harkame.banque.compte.Compte;
import fr.harkame.banque.exception.OperationBancaireException;

public abstract class CompteRemunere extends Compte
{
	private double	taux_remuneration;
	private double	solde_plafond;

	/**
	 * Constructeur - Compte remunere
	 * 
	 * @param libelle
	 *             Nom du compte
	 * @param solde
	 *             Montant d'argent sur le comte
	 * @param taux_remuneration
	 *             Taux de reumuneration du compte
	 * @param solde_plafond
	 *             Solde maximum que le comte peut posseder
	 * @throws OperationBancaireException
	 *              Le solde du compte depasse le solde plafond
	 */
	public CompteRemunere(String libelle, double solde, double taux_remuneration, double solde_plafond)
		throws OperationBancaireException
	{
		super(libelle, solde);
		this.taux_remuneration = taux_remuneration;
		this.solde_plafond = solde_plafond;
		if(this.getSolde() > this.getSoldePlafond())
		{
			throw new OperationBancaireException("Le montant indique fait depasser le solde plafond");
		}
	}

	/**
	 * Getter - Solde plafond d'un compte (this)
	 * 
	 * @return this.solde_plafond
	 */

	public double getSoldePlafond()
	{
		return this.solde_plafond;
	}

	/**
	 * 
	 * @param montant
	 *             Le montant a crediter sur un compte (this)
	 * @throws OperationBancaireException
	 *              Plafond du compte depasse
	 */
	public void crediter(double montant) throws OperationBancaireException
	{
		if((this.getSolde() + montant) > this.solde_plafond)
		{
			throw new OperationBancaireException("Solde plafond depasse");
		}

		super.Crediter(montant);
	}

	/**
	 * Getter - Taux de remuneration d'un compte remunere(this)
	 * 
	 * @return this.taux_remuneration
	 */
	public double getTaux_Remuneration()
	{
		return this.taux_remuneration;
	}

}
