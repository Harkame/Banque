package fr.harkame.banque.main;

import fr.harkame.banque.model.compte.*;
import fr.harkame.banque.model.compte.remunere.*;
import fr.harkame.banque.model.exception.*;
import fr.harkame.banque.model.operation.*;
import fr.harkame.banque.model.ordre_virement.*;
import fr.harkame.banque.model.personne.*;

public abstract class Banque
{

	/**
	 * methode qui permet de sauter une ligne
	 * 
	 **/
	public static void AffNull()
	{
		System.out.println("");
	}

	/**
	 * Main principal
	 * 
	 * @param Args
	 *             Possible argument passer en ligne de commande
	 * 
	 * @throws OperationBancaireException
	 *              Montant negatif | Solde plafond depasse| Solde negatif
	 * @throws PersonnelNonAutoriseException
	 *              L'attache n'est pas associe au client proprietaire du
	 *              compte
	 */
	@SuppressWarnings("unused")
	public static void main(String[] Args) throws OperationBancaireException, PersonnelNonAutoriseException
	{
		System.err.println(
			"Il est possible que certain messages, nottement d'erreure ne se place pas au bon endroit, si cela arrive, veuillez relancer le programme, merci");
		AffNull();
		System.out.println("------------------------------------");
		System.out.println("--- Jeux de test 1 - Les comptes ---");
		System.out.println("------------------------------------");
		CompteCourant cc1 = new CompteCourant("Compte Courant C", 100);
		CompteEpargne ce1 = new CompteEpargne("Compte Epargne C", 500, 2, 700);
		cc1.ConsultationSolde();
		cc1.Crediter(200);
		cc1.ConsultationSolde();
		ce1.ConsultationSolde();
		ce1.Debiter(500);
		ce1.ConsultationSolde();
		AffNull();
		System.out.println("---------------------------------------");
		System.out.println("--- Jeux de test 2 - Premier modèle ---");
		System.out.println("---------------------------------------");
		AffNull();
		Attache a1 = new Attache("Attache principal");
		Attache a2 = new Attache("Attache secondaire");
		Client c = new Client("C");
		Client cl2 = new Client("Client secondaire");
		c.AttribuerCompte(cc1);
		c.AttribuerCompte(ce1);
		a1.SuivreClient(c);
		a1.SuivreClient(cl2);
		AffNull();
		System.out.println("----------------------------------");
		System.out.println("--- Jeux de test 3 - Operation ---");
		System.out.println("----------------------------------");
		AffNull();
		cc1.ConsultationSolde();
		Credit c1 = new Credit("Credit1", cc1, 2000);
		c1.passerCredit();
		cc1.ConsultationSolde();
		cc1.ConsultHistorique();
		ce1.ConsultationSolde();
		Credit c2 = new Credit("Credit2", ce1, 33);
		c2.passerCredit();
		ce1.ConsultationSolde();
		cc1.ConsultationSolde();
		Debit d1 = new Debit("Debit1", cc1, 500);
		d1.passerDebit();
		cc1.ConsultHistorique();
		cc1.ConsultationSolde();
		Debit d2 = new Debit("Debit2", cc1, 2500);
		try
		{
			d2.passerDebit();
		}
		catch(OperationBancaireException e)
		{
			System.out.println("Debit rejete");
		}
		cc1.ConsultationSolde();
		cc1.ConsultHistorique();
		AffNull();
		System.out.println("------------------------------------------");
		System.out.println("--- Jeux de test 4 - Ordre de virement ---");
		System.out.println("------------------------------------------");
		AffNull();
		Debit d3 = new Debit("Debit3", cc1, 123);
		d3.passerDebit();
		Credit c3 = new Credit("Credit3", cc1, 1.55);
		c3.passerCredit();
		Ordre_Virement o_v1 = new Ordre_Virement(c, cc1, ce1, 666);
		o_v1.PasserOrdre_Virement();
		Ordre_Virement o_v2 = new Ordre_Virement(c, cc1, ce1, 10000);
		try
		{
			o_v2.PasserOrdre_Virement();
		}
		catch(OperationBancaireException e)
		{
			System.err.println("Ordre de virement rejete - KO");
			o_v2.miseEchec();
		}

		Ordre_Virement o_v3 = new Ordre_Virement(c, cc1, ce1, 10);
		try
		{
			o_v3.PasserOrdre_Virement();
		}
		catch(OperationBancaireException e)
		{
			System.err.println("Ordre de virement rejete - Mise en attente"); // L'exception
		}
		o_v3.miseAttente();

		AffNull();
		System.out.println("------------------------------------------------");
		System.out.println("--- Question 4.2 - Remarque message d'alerte ---");
		System.out.println("------------------------------------------------");
		System.out.println(
			"Selon la vitesse de relance du programme, les message d'alerte ne se situe pas au même endroit");
		System.out.println(
			"Ces messages se trouve dans des bloc catch, ces dernier sont secondaire par rapport au reste du programme");
		System.out.println("La memoire va d'abord s'occuper du code, puis des bloc catch");
		AffNull();
		System.out.println("---------------------------------------------------------------------");
		System.out.println("--- Subsidiaire - Validation manuelle d'une operation en attente  ---");
		System.out.println("---------------------------------------------------------------------");
		AffNull();
		Debit d4 = new Debit("Debit4", cc1, 5000);
		cc1.ConsultationSolde();
		System.out.println("Solde compte :  " + a1.forcerDebit(d4));
		AffNull();
		Credit c4 = new Credit("Credit4", ce1, 2500);
		System.out.println("Solde compte : " + a1.forcerCredit(c4));
		// + ((CompteRemunere) c4.getCompte()).getSoldePlafond());

		// System.out.println(a2.forcerCredit(c4));
	}
}
