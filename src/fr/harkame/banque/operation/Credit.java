package fr.harkame.banque.operation;

import fr.harkame.banque.compte.*;
import fr.harkame.banque.compte.remunere.CompteRemunere;
import fr.harkame.banque.compte.remunere.PlanEpargneBloque;
import fr.harkame.banque.exception.OperationBancaireException;

public class Credit extends Operation
{
	/**
	 * Constructeur - Credit
	 * 
	 * @param libelle
	 *             Nom du compte
	 * @param compte
	 *             Compte a crediter
	 * @param montant
	 *             montant a crediter
	 */
	public Credit(String libelle, Compte compte, double montant)
	{
		super(libelle, compte, montant);
		System.out.println("Creation Credit");
		System.out.println("              - Libelle : " + this.getLibelle());
		System.out.println("              - Compte : " + compte.getidCompte());
		System.out.println("              - Montant : " + this.getMontant());
		System.out.println("");
	}

	/**
	 * Fait passer le statut du Credit (this) a OK, si le credit est possible,
	 * sinon leve une exception
	 * 
	 * @throws OperationBancaireException
	 *              Le montant est negatif | Le solde plafon est depasse
	 */
	public void CreditPossible() throws OperationBancaireException
	{
		if(this.statut == Statut.Attente)
		{
			if(0 > this.getMontant())
			{
				this.statut = Statut.KO;
				throw new OperationBancaireException("Montant negatif");
			}
			if(this.getCompte() instanceof CompteRemunere)
			{ // Si on a affaire
				// a
				// un compte
				// avec un solde plafond
				if(Depasse(this.getCompte(), this.getMontant()))
				{
					this.statut = Statut.KO;
					throw new OperationBancaireException(
						"Solde plafond " + ((CompteRemunere) this.getCompte()).getSoldePlafond() + " depasse");
				}

			}
			if(this.getCompte() instanceof PlanEpargneBloque)
			{
				if(this.getDate() != ((PlanEpargneBloque) this.getCompte()).getDate())
				{
					this.statut = Statut.KO;
					throw new OperationBancaireException("Date non autorise");
				}
			}
			this.statut = Statut.OK;
		}
		else if(this.statut != Statut.Attente)
		{
		}
	}

	/**
	 * Active le Credit (this)
	 * 
	 * @throws OperationBancaireException
	 *              Montant negatif | Solde plafond depasse
	 */

	public void passerCredit() throws OperationBancaireException
	{
		this.CreditPossible();
		if(this.statut == Statut.OK)
		{
			this.getCompte().Crediter(this.getMontant());
		}
		else
			throw new OperationBancaireException("");

	}

	/**
	 * Permet de savoir si le solde du compte depassera celui de son solde
	 * plafond
	 * 
	 * @param compte
	 *             Compte a crediter
	 * @param montant
	 *             Montant a crediter
	 * @return boolean Vrai si le montant crediter fait depasser le solde au
	 *         dessus du solde plafond, faux sinon
	 */
	public boolean Depasse(Compte compte, double montant)
	{
		double tamp = compte.getSolde() + montant;
		if(tamp > ((CompteRemunere) compte).getSoldePlafond())
		{
			return true;
		}
		else
			return false;
	}

}
