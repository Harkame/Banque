package fr.harkame.banque.model.operation;

import fr.harkame.banque.model.compte.*;
import fr.harkame.banque.model.compte.remunere.PlanEpargneBloque;
import fr.harkame.banque.model.exception.OperationBancaireException;

public class Debit extends Operation
{

	/**
	 * Constructeur - Debit
	 * 
	 * @param libelle
	 *             Nom du compte
	 * @param compte
	 *             Compte a debiter
	 * @param montant
	 *             Montant a debiter
	 */
	public Debit(String libelle, Compte compte, double montant)
	{
		super(libelle, compte, montant);
		System.out.println("Creation Debit");
		System.out.println("              - Libelle : " + this.getLibelle());
		System.out.println("              - Compte : " + compte.getidCompte());
		System.out.println("              - Montant : " + this.getMontant());
		System.out.println("");
	}

	/**
	 * Fait passer le statut du Debit (this) a OK, si le credit est possible,
	 * sinon leve une exception
	 * 
	 * @throws OperationBancaireException
	 *              Montant negatif | Solde insuffisant
	 */

	public void DebitPossible() throws OperationBancaireException
	{
		if(this.statut == Statut.Attente)
		{
			if(this.getMontant() < 0)
			{
				this.statut = Statut.KO;
				throw new OperationBancaireException("Montant negatif");
			}
			if(this.getCompte().getSolde() < this.getMontant())
			{
				this.statut = Statut.KO;
				throw new OperationBancaireException("Solde insuffisant");
			}
			this.setStatut(Statut.OK);
			if(this.getCompte() instanceof PlanEpargneBloque)
			{
				if(this.getDate() != ((PlanEpargneBloque) this.getCompte()).getDate())
				{ // Je
					// ne
					// savais
					// pas
					// comment
					// dire
					// que
					// la
					// date
					// de
					// l'operation
					// devait
					// être
					// <
					// celle
					// autorise
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
	 * Active le debit (this)
	 * 
	 * @throws OperationBancaireException
	 *              Montant negatif | Solde insuffisant
	 */
	public void passerDebit() throws OperationBancaireException
	{
		this.DebitPossible();
		if(this.statut == Statut.OK)
		{
			this.getCompte().Debiter(this.getMontant());
		}
		else
			throw new OperationBancaireException("");

	}
}
