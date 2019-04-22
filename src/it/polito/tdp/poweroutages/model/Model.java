package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class Model {

	private final static int PRIMO_ANNO = 2000;
	private final static int ULTIMO_ANNO = 2014;
	
	private PowerOutageDAO podao;
	
	//Per la gestione della ricorsione
	
	private List<PowerOutages> best;
	private int bestOre;
	private int moreCustumers;
	
	private int oreFinale=0;
	private int custumersFinale=0;
	private List<PowerOutages> bestFinale=null; 
	
	List<PowerOutages> blackoutsIntervallo;
	
	public Model() {
		podao = new PowerOutageDAO();
	}

	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	
	/**
	 * Questo metodo riceve come parametro il {@link Nerc} l'intervallo di anni massimo da considerare
	 * ed il numero massimo di ore di {@link PowerOutages} ricercato.
	 * 
	 * Si serve dunque di una classe DAO per interagire con il DB dove ricerca, nel rispettivo {@link Nerc},
	 * i {@link PowerOutages} che cadono in un intervallo di anni massimo pari al valore intero passato come parametro.
	 * 
	 * Avvia dunque una procedura ricorsiva per la ricerca della migliore soluzione. Una soluzione è data da un Set 
	 * di {@link PowerOutages} il cui anno sia vincolato ad un intervallo massimo ed il cui numero di ore complessivo sia inferiore al dato passato
	 *   
	 * 
	 * @param nerc
	 * @param anni
	 * @param ore
	 * @return la {@code String} avente il contenuto dell'interfaccia grafica
	 */
	
	public String worstCase(Nerc nerc, int anni, int ore) {
		
		this.oreFinale=0;
		this.custumersFinale=0;
		this.bestFinale=null; 
		
		String risultato = "";
		
		//Uso un for per il calcolo di tutti gli intervalli possibili
		for(int i = Model.PRIMO_ANNO; i<(Model.ULTIMO_ANNO-(anni-1));i++) {
			
			this.bestOre=0;
			this.moreCustumers=0;
			this.best=null;
			
		    blackoutsIntervallo = this.podao.getBlackoutsIntervallo(nerc, i, i+(anni-1));
		    
		    Set<PowerOutages> parziale = new HashSet<PowerOutages>();
		    
		    cerca(parziale, 0, ore, blackoutsIntervallo ); 
		    
		    if(this.moreCustumers>this.custumersFinale) {
		    	this.bestFinale=this.best;
		    	this.custumersFinale=this.moreCustumers;
		    	this.oreFinale=this.bestOre;
		    }
			
		}
		
		
		risultato+=String.format("Refering to %s:\n", nerc.getValue());
		risultato+=String.format("Tot people affected: %d \n",this.custumersFinale );
		risultato+=String.format("Tot hours of outage: %d\n",this.oreFinale );
		
		
		Collections.sort(this.bestFinale);
		
		
		for(PowerOutages po : this.bestFinale) {
		
			risultato+=String.format("%s\n", po.toString());
			
		}
		
		return risultato;
	}

	private void cerca(Set<PowerOutages> parziale, int L, int ore, List<PowerOutages> blackoutsIntervallo) {
		
		int totOre = sommaOre(parziale);
		
		if(totOre>ore)
			return;
		
		int sommaCustumers = calcolaTotCustumers(parziale);
			
		if(sommaCustumers>this.moreCustumers) {
				//evviva
			if(totOre<=ore) {
				bestOre=totOre;
				best = new ArrayList<PowerOutages>(parziale);			
				moreCustumers=sommaCustumers;
				
			}
			
		}
		
		if(L==blackoutsIntervallo.size()) {
			return;
		}
		
		//caso normale
		
		//NON CONTA L'ORDINE
		//PowerOutages L è da aggiungere o no?
		
		//provo a non aggiungerlo
		cerca(parziale, L+1,ore, blackoutsIntervallo);
		
		//provo ad aggiungerlo
		
		parziale.add(blackoutsIntervallo.get(L));
		
		cerca(parziale, L+1,ore,blackoutsIntervallo);
		
	    parziale.remove(blackoutsIntervallo.get(L));
		
	}

	private int calcolaTotCustumers(Set<PowerOutages> parziale) {
		
        int somma = 0;
		
		for (PowerOutages po : parziale) {
			somma+=(int)po.getCustumersAffected();
		}
		
		return somma;
	}

	private int sommaOre(Set<PowerOutages> parziale) {
		
		int somma = 0;
		
		for (PowerOutages po : parziale) {
			somma+=(int)po.getOre();
		}
		
		return somma;
	}

}
