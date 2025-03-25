package bts.sio.azurimmo.controller;

import bts.sio.azurimmo.model.Appartement;
import bts.sio.azurimmo.service.AppartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appartements")
public class AppartementController {

    @Autowired
    private AppartementService appartementService;

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
    public Appartement createAppartement(@RequestBody Appartement appartement) {
        return appartementService.saveAppartement(appartement);
    }

    // Récupérer les appartements ayant une surface supérieure à un seuil
    @GetMapping("/surface/{surface}")
    public List<Appartement> findBySurfaceGreaterThan(@PathVariable float surface) {
        return appartementService.findBySurfaceGreaterThan(surface);
    }

    // Récupérer tous les appartements
    @GetMapping("/")
    public List<Appartement> getAllAppartements() {
        return appartementService.findAllAppartements();
    }
}
