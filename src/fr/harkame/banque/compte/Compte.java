package fr.harkame.banque.compte;

import java.util.ArrayList;

import fr.harkame.banque.operation.Operation;

public abstract class Compte {
	private final int				idCompte;
	private static int				incr;
	private String					libelle;
	private double					solde;
	private ArrayList<Operation>	historique;

	public enum Statut {
		Attribue, Attente
	};

	private Statut statut;

	/**
	 * Constructeur - Compte
	 * 
	 * @param libelle
	 *             Nom du compte
	 * @param solde
	 *             Somme d'argent sur le compte
	 */
	public Compte(String libelle, double solde) {
		this.idCompte = incr++;
		this.libelle = libelle;
		this.solde = solde;
		this.statut = Statut.Attente;
		this.historique = new ArrayList<Operation>();
	}

	/**
	 * Redefinition de la methode equals
	 * 
	 * @param compte
	 *            Compte a teste
	 * @return true Si tous les attributs des 2 comptes sont les mêmes
	 */
	public boolean equals(Compte compte) {
		return this.getidCompte() == compte.getidCompte() && this.getLibelle() == compte.getLibelle() && this.getSolde() == compte.getSolde();
	}

	/**
	 * Getter - Libelle d'un compte (this)
	 * 
	 * @return this.libelle
	 */
	public String getLibelle() {
		return this.libelle;
	}

	/**
	 * Getter - Statut d'un compte (this)
	 * 
	 * @return this.statut
	 */
	public Statut getStatut() {
		return this.statut;
	}

	/**
	 * Affecte un Statut s a un compte (this)
	 * 
	 * @param statut
	 *            Statut a attribuer
	 */
	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	/**
	 * Getter - Identifiant d'un compte (this)
	 * 
	 * @return this.idCompte
	 */
	public int getidCompte() {
		return this.idCompte;
	}

	/**
	 * Retire directement le montant au solde du compte (this)
	 * 
	 * @param montant
	 *            Montant a debiter
	 */
	public void Debiter(double montant) {
		this.solde -= montant;
		System.out.println("Compte : " + this.getidCompte() + " debite de : " + montant);
	}

	/**
	 * Ajoute directement le montant au solde du compte (this)
	 * 
	 * @param montant
	 *            Montant a crediter
	 * 
	 */
	public void Crediter(double montant) {
		this.solde += montant;
		System.out.println("Compte : " + this.getidCompte() + " credite de : " + montant);
	}

	/**
	 * Permet d'afficher le solde d'un compte (this)
	 * 
	 */
	public void ConsultationSolde() {
		System.out.println("Solde compte : " + this.getidCompte() + " est de : " + this.solde);
	}

	/**
	 * Getter - Solde d'un compte (this)
	 * 
	 * @return this.solde
	 */
	public double getSolde() {
		return this.solde;
	}

	/**
	 * Permet d'afficher l'historique des Operations d'un compte this
	 */
	public void ConsultHistorique() {
		System.out.println("Consultation historique ");
		for (int i = 0; i < this.getHistorique().size(); i++) {
			System.out.println("                  - iddentifiant : " + this.historique.get(i).getidOperation());
			System.out.println("                  - Libelle : " + this.historique.get(i).getLibelle());
			System.out.println("                  - Compte : " + this.historique.get(i).getCompte().getidCompte());
			System.out.println("                  - Montant : " + this.historique.get(i).getMontant());
			System.out.println("                  - Date : " + this.historique.get(i).getDate());
			System.out.println("                  - Statut : " + this.historique.get(i).getStatut());
			System.out.println("");
		}
	}

	/**
	 * Getter - Historique des operation d'un compte this
	 * 
	 * @return this.historique
	 */
	public ArrayList<Operation> getHistorique() {
		return this.historique;
	}
}
