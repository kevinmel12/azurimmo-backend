package bts.sio.azurimmo.model;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "contrat")
public class Contrat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "dateEntree")
	private Date dateEntree;
	
	@Column(name = "dateSortie")
	private Date dateSortie;
	
	@Column(name = "montantLoyer")
	private double montantLoyer;
	
	@Column(name = "montantCharges")
	private double montantCharges;
	
	@Column(name = "statut")
	private String statut;

	//Ajout des getters et setters
	//Id
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //dateEntree
    public Date getDateEntree() {
    	return dateEntree;
    }
    
    public void setDateEntree(Date dateEntree) {
    	this.dateEntree = dateEntree;
    }
    
    //dateSortie
    public Date getDateSortie() {
    	return dateSortie;
    }
    
    public void setDateSortie(Date dateSortie) {
    	this.dateSortie = dateSortie; 
    }
    
    //montantLoyer
    public double getMontantLoyer() {
    	return montantLoyer;
    }
    
    public void setMontantLoyer(double montantLoyer) {
    	this.montantLoyer = montantLoyer;
    }
    
    //montantCharges
    public double getMontantCharges() {
    	return montantCharges;
    }
    
    public void setMontantCharges(double montantCharges) {
    	this.montantCharges = montantCharges;
    }
    
    //statut
    public String getStatut() {
    	return statut;
    }
    
    public void setStatut(String statut) {
    	this.statut = statut;
    }
    // Getter et Setter pour appartement
    public Appartement getAppartement() {
        return appartement;
    }

    public void setAppartement(Appartement appartement) {
        this.appartement = appartement;
    }

    // Getter et Setter pour locataire
    public Locataire getLocataire() {
        return locataire;
    }

    public void setLocataire(Locataire locataire) {
        this.locataire = locataire;
    }

    // Relations ManyToOne
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "appartement_id")
    private Appartement appartement;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "locataire_id")
    private Locataire locataire;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "associe_id") 
    private Associe associe;

    // Getter et Setter pour associe
    public Associe getAssocie() {
        return associe;
    }

    public void setAssocie(Associe associe) {
        this.associe = associe;
    }
}