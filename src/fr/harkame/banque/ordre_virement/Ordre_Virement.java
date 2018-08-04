package fr.harkame.banque.ordre_virement;

import fr.harkame.banque.compte.*;
import fr.harkame.banque.exception.OperationBancaireException;
import fr.harkame.banque.operation.*;
import fr.harkame.banque.personne.Client;

public class Ordre_Virement
{
	private Client	donneur_ordre;
	private Compte	compte_origine;
	private Compte	compte_destinataire;
	private double	montant;
	private Debit	debit;
	private Credit	credit;

	/**
	 * Constructeur - Ordre de virement
	 * 
	 * @param client
	 *             Client demandeur de l'ordre de virement
	 * @param compte_origine
	 *             Compte qui va etre debiter
	 * @param compte_destinataire
	 *             Compte qui va etre crediter
	 * @param montant
	 *             montant d'argent a virer
	 * @throws OperationBancaireException
	 *              Exception de credit et debit
	 */
	public Ordre_Virement(Client client, Compte compte_origine, Compte compte_destinataire, double montant)
		throws OperationBancaireException
	{
		this.donneur_ordre = client;
		this.compte_origine = compte_origine;
		this.compte_destinataire = compte_destinataire;
		System.out.println("");
		System.out.println("Creation O_V");
		this.montant = montant;

		this.debit = new Debit("dO_V", compte_origine, montant);
		this.credit = new Credit("co_V", compte_destinataire, montant);
		System.out.println("Detail de l'odre de virement");
		System.out
			.println("                           - Compte d'origine : " + this.getCompte_Origine().getidCompte());
		System.out.println(
			"                           - Compte destinataire : " + this.getCompte_Destinataire().getidCompte());
		System.out.println("                           - Montant : " + this.getMontant());
		System.out.println("");
	}

	public void PasserOrdre_Virement() throws OperationBancaireException
	{
		System.out.println("Passer ordre de virement");
		System.out.println("                       - Client : " + this.getDonneur_Ordre().getidClient());
		System.out.println("                       - Compte origine : " + this.getCompte_Origine().getidCompte());
		System.out.println(
			"                       - Compte destinataire : " + this.getCompte_Destinataire().getidCompte());
		System.out.println("                       - Montant : " + this.getMontant());
		this.debit.passerDebit();
		this.credit.passerCredit();
	}

	/**
	 * Active les deux operation constituant l'ordre de virement (this)
	 * 
	 * @throws OperationBancaireException
	 *              Montant negatif | Solde negatif | Solde superieur au Solde
	 *              plafond
	 */
	public void passerOrdre_Virement() throws OperationBancaireException
	{
		this.getDebit().passerDebit();
		this.getCredit().passerCredit();
	}

	/**
	 * Getter - Donneur d'ordre d'un ordre de virement (this)
	 * 
	 * @return this.donneur_ordre
	 */
	public Client getDonneur_Ordre()
	{
		return this.donneur_ordre;
	}

	/**
	 * Getter - Compte d'origine, sur lequel sera effectue un debit
	 * 
	 * @return this.compte_origine
	 */
	public Compte getCompte_Origine()
	{
		return this.compte_origine;
	}

	/**
	 * Getter - Compte destinataire, sur lequel sera effectue un credit
	 * 
	 * @return this.compte_destinataire
	 */
	public Compte getCompte_Destinataire()
	{
		return this.compte_destinataire;
	}

	/**
	 * Getter - Debit qui sera effectue sur le compte d'origine
	 * 
	 * @return this.debit
	 */
	public Debit getDebit()
	{
		return this.debit;
	}

	/**
	 * Getter - Credit qui sera effectue sur le compte destiantaire
	 * 
	 * @return this.credit
	 */
	public Credit getCredit()
	{
		return this.credit;
	}

	/**
	 * Getter - Montant de l'ordre de virement
	 * 
	 * @return this.montant
	 */
	public double getMontant()
	{
		return this.montant;
	}

	/**
	 * Determine si les compte d'origine appartient au donneur d'ordre
	 * 
	 * @param donneur_ordre
	 *             Client qui demande l'ordre de virement
	 * @param compte_origine
	 *             Compte a verifier
	 * @return Vrai si le compte d'origine appartient au donneur d'ordre
	 */
	public boolean AppartientCompteO(Client donneur_ordre, Compte compte_origine)
	{
		int i = 0;
		while(donneur_ordre.gettabCompte().get(i) != compte_origine)
		{
			i++;
		}
		if(donneur_ordre.gettabCompte().get(i).equals(compte_origine))
		{
			return true;
		}
		else
			return false;
	}

	/**
	 * Determine si le ompte destinataire appartient au donneur d'ordre
	 * 
	 * @param donneur_ordre
	 *             Client qui demande l'ordre de virement (this)
	 * @param compte_destinataire
	 *             Compte a verifier
	 * @return : Vrai si le compte destinataire appartient au donneur d'ordre
	 */
	public boolean AppartientCompteD(Client donneur_ordre, Compte compte_destinataire)
	{
		int i = 0;
		while(donneur_ordre.gettabCompte().get(i) != compte_destinataire)
		{
			i++;
		}
		if(donneur_ordre.gettabCompte().get(i).equals(compte_destinataire))
		{
			return true;
		}
		else
			return false;
	}

	/**
	 * Determine si le compte d'origine et destinataire appartienne au donneur
	 * d'ordre de l'ordre de virement (this)
	 * 
	 * @param donneur_ordre
	 *             Client qui demande l'ordre de virement
	 * @param compte_origine
	 *             Compte a verifier
	 * @param compte_destinataire
	 *             Compte a verifier
	 * @return : Vrai si le compte d'origine et destinataire appartienne tous
	 *         les deux au donneur d'ordre.
	 */
	public boolean AppartientCompte(Client donneur_ordre, Compte compte_origine, Compte compte_destinataire)
	{
		return AppartientCompteO(donneur_ordre, compte_origine)
			&& AppartientCompteD(donneur_ordre, compte_destinataire);
	}

	/**
	 * Fait passer le statut du debit et du credit de l'ordre virement (this) a
	 * KO
	 */
	public void miseEchec()
	{
		this.getDebit().miseEchec();
		this.getCredit().miseEchec();
	}

	/**
	 * Fait passer le statut du debit et du credit de l'ordre virement (this)
	 * en Attente
	 */
	public void miseAttente()
	{
		this.getDebit().miseAttente();
		this.getCredit().miseAttente();
	}
}
