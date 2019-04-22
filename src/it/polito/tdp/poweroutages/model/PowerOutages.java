package it.polito.tdp.poweroutages.model;

import java.time.*;

public class PowerOutages implements Comparable<PowerOutages>{

	private int id;
	private int custumersAffected;
	private LocalDateTime dateEventBegan;
	private LocalDateTime dateEventFinished;
	private Nerc nerc;
	private int demandLoss;
	

	public PowerOutages(int id, int custumersAffected, LocalDateTime dateEventBegan, LocalDateTime dateEventFinished, Nerc nerc, int demandLoss) {
		super();
		this.id = id;
		this.custumersAffected = custumersAffected;
		this.dateEventBegan = dateEventBegan;
		this.dateEventFinished = dateEventFinished;
		this.nerc=nerc;
		this.demandLoss = demandLoss;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getCustumersAffected() {
		return custumersAffected;
	}


	public void setCustumersAffected(int custumersAffected) {
		this.custumersAffected = custumersAffected;
	}


	public LocalDateTime getDateEventBegan() {
		return dateEventBegan;
	}


	public void setDateEventBegan(LocalDateTime dateEventBegan) {
		this.dateEventBegan = dateEventBegan;
	}


	public LocalDateTime getDateEventFinished() {
		return dateEventFinished;
	}


	public void setDateEventFinished(LocalDateTime dateEventFinished) {
		this.dateEventFinished = dateEventFinished;
	}


	public Nerc getNerc() {
		return nerc;
	}


	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}


	public int getDemandLoss() {
		return demandLoss;
	}


	public void setDemandLoss(int demandLoss) {
		this.demandLoss = demandLoss;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutages other = (PowerOutages) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
    
	public long getOre() {
		
	  return Duration.between(this.dateEventBegan,this.dateEventFinished).toHours();
				
	
	}


	@Override
	public int compareTo(PowerOutages po) {
		// TODO Auto-generated method stub
		return (int) dateEventBegan.compareTo(po.dateEventBegan);
	}
	
	public String toString() {
		return ""+" -- Began: "+this.dateEventBegan+" -- Finished: "+this.dateEventFinished+" -- Hours: "+this.getOre()+" -- Custumers affected: "+this.custumersAffected;
	}
}
