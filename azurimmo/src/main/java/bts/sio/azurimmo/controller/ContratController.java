// File: azurimmo/src/main/java/bts/sio/azurimmo/controller/ContratController.java
package bts.sio.azurimmo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    
    // Récupérer les contrats par ID d'appartement  
    @GetMapping("/appartement/{appartementId}")
    public List<Contrat> getContratsByAppartementId(@PathVariable int appartementId) {
        return contratRepository.findByAppartement_Id(appartementId);
    }
    
    // Récupérer un contrat par ID
    @GetMapping("/{id}") 
    public ResponseEntity<Contrat> getContratById(@PathVariable Long id) {
        return contratRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // Créer un nouveau contrat
    @PostMapping("/")
    public ResponseEntity<Contrat> createContrat(@RequestBody Contrat contrat) {
        if (contrat.getId() != 0) {
            contrat.setId(null);
        }
        Contrat savedContrat = contratService.saveContrat(contrat);
        return ResponseEntity.ok(savedContrat);
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