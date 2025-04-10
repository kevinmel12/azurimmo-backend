package bts.sio.azurimmo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import bts.sio.azurimmo.model.Contrat;
import bts.sio.azurimmo.repository.ContratRepository;
import bts.sio.azurimmo.service.ContratService;

@RestController
@RequestMapping("/api/contrats")
public class ContratController {

    @Autowired
    private ContratRepository contratRepository;
    
    @Autowired
    private ContratService contratService;
    
    // Récupérer tous les contrats
    @GetMapping("/")
    public List<Contrat> getAllContrats() {
    	return contratRepository.findAll();
    }
    
    // Récupérer un contrat par ID
    public ResponseEntity<Contrat> getContratById(@PathVariable Long id) {
    	return contratRepository.findById(id)
    			.map(ResponseEntity::ok)
    			.orElseGet(() -> ResponseEntity.notFound().build());
    	
    }
    
    // Créer un nouveau contrat
    @PostMapping("/")
    public ResponseEntity<Contrat> createContrat(@RequestBody Contrat contrat){
    	if (contrat.getId() !=0) {
    		contrat.setId(null);
    	}
    	Contrat saveContrat = contratService.saveContrat(contrat);
    	return ResponseEntity.ok(saveContrat);
    }
    
    // Mettre à jour un contrat
    @PutMapping("/{id}")
    public ResponseEntity<Contrat> updateContrat(@PathVariable Long id, @RequestBody Contrat contrat) {
    	if (!contratRepository.existsById(id)) {
    		return ResponseEntity.notFound().build();
    	}
    
	contrat.setId(id);
	Contrat updatedContrat = contratService.saveContrat(contrat);
	return ResponseEntity.ok(updatedContrat);
    }
    
    // Supprimer un contrat
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContrat(@PathVariable Long id) {
    	if (!contratRepository.existsById(id)) {
    		return ResponseEntity.notFound().build();
    	}
    	
    	contratRepository.deleteById(id);
    	return ResponseEntity.noContent().build();
    }
}
