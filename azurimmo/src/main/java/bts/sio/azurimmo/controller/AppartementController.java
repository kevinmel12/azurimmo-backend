package bts.sio.azurimmo.controller;

import bts.sio.azurimmo.model.Appartement;
import bts.sio.azurimmo.service.AppartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appartements")
public class AppartementController {

    @Autowired
    private AppartementService appartementService;

    // Récupérer tous les appartements
    @GetMapping("/")
    public List<Appartement> getAllAppartements() {
        return appartementService.findAllAppartements();
    }

    // Récupérer un appartement par ID
    @GetMapping("/{id}")
    public ResponseEntity<Appartement> getAppartementById(@PathVariable Long id) {
        return appartementService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Récupérer les appartements par ville
    @GetMapping("/ville/{ville}")
    public List<Appartement> findByVille(@PathVariable String ville) {
        return appartementService.findByVille(ville);
    }

    // Récupérer les appartements par ID de bâtiment
    @GetMapping("/batiment/{batimentId}")
    public List<Appartement> getAppartementsParBatiment(@PathVariable int batimentId) {
        return appartementService.getAppartementsParBatiment(batimentId);
    }

    // Créer un nouvel appartement
    @PostMapping("/")
    public ResponseEntity<Appartement> createAppartement(@RequestBody Appartement appartement) {
        // Vérifier et transformer l'ID = 0 en null
        if (appartement.getId() != null && appartement.getId() == 0) {
            appartement.setId(null);
        }
        Appartement savedAppartement = appartementService.saveAppartement(appartement);
        return ResponseEntity.ok(savedAppartement);
    }

    // Mettre à jour un appartement existant
    @PutMapping("/{id}")
    public ResponseEntity<Appartement> updateAppartement(@PathVariable Long id, @RequestBody Appartement appartement) {
        if (!appartementService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        appartement.setId(id);
        Appartement updatedAppartement = appartementService.saveAppartement(appartement);
        return ResponseEntity.ok(updatedAppartement);
    }

    // Supprimer un appartement
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppartement(@PathVariable Long id) {
        if (!appartementService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        appartementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Récupérer les appartements ayant une surface supérieure à un seuil
    @GetMapping("/surface/{surface}")
    public List<Appartement> findBySurfaceGreaterThan(@PathVariable float surface) {
        return appartementService.findBySurfaceGreaterThan(surface);
    }
}