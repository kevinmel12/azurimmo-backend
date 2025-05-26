package bts.sio.azurimmo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bts.sio.azurimmo.model.Locataire;
import bts.sio.azurimmo.repository.LocataireRepository;
import bts.sio.azurimmo.service.LocataireService;

@RestController
@RequestMapping("/api/locataires")
public class LocataireController {

    @Autowired
    private LocataireRepository locataireRepository;
    
    @Autowired
    private LocataireService locataireService;
    
    @GetMapping("/")
    public List<Locataire> getAllLocataires() {
        return locataireRepository.findAll();
    }
    
    // Récupérer un locataire par ID
    @GetMapping("/{id}")
    public ResponseEntity<Locataire> getLocataireById(@PathVariable Long id) {
        return locataireRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // Créer un nouveau locataire
    @PostMapping("/")
    public ResponseEntity<Locataire> createLocataire(@RequestBody Locataire locataire) {
        if (locataire.getId() != 0) {
            locataire.setId(null);
        }
        Locataire savedLocataire = locataireService.saveLocataire(locataire);
        return ResponseEntity.ok(savedLocataire);
    }
    
    // Mettre à jour un locataire
    @PutMapping("/{id}")
    public ResponseEntity<Locataire> updateLocataire(@PathVariable Long id, @RequestBody Locataire locataire) {
        if (!locataireRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        locataire.setId(id);
        Locataire updatedLocataire = locataireService.saveLocataire(locataire);
        return ResponseEntity.ok(updatedLocataire);
    }
    
    // Supprimer un locataire
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocataire(@PathVariable Long id) {
        if (!locataireRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        locataireRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}