package bts.sio.azurimmo.controller;

import bts.sio.azurimmo.model.Batiment;

import bts.sio.azurimmo.repository.BatimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;



import java.util.List;

@RestController
@RequestMapping("/api/batiments")
public class BatimentController {

    @Autowired
    private BatimentRepository batimentRepository;

    // Nouveau point de terminaison pour renvoyer une liste simple
    @GetMapping("/")
    public List<Batiment> getAllBatiments() {
        return batimentRepository.findAll();
    }
    
    @GetMapping("/{id}") // <-- Ceci permet d'accéder à /api/batiments/1
    public ResponseEntity<Batiment> getBatimentById(@PathVariable Long id) {
        return batimentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
   
    
    @PostMapping("/")
    public ResponseEntity<Batiment> createBatiment(@RequestBody Batiment batiment) {
        // Vérifier et transformer l'ID = 0 en null
        if (batiment.getId() != null && batiment.getId() == 0) {
            batiment.setId(null);
        }
        Batiment savedBatiment = batimentRepository.save(batiment);
        return ResponseEntity.ok(savedBatiment);
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBatiment(@PathVariable Long id){
    	if (batimentRepository.existsById(id)) {
    		batimentRepository.deleteById(id);
    		return ResponseEntity.noContent().build();
		
			
    	} else {
    		return ResponseEntity.notFound().build();
    	}
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Batiment> updateBatiment(@PathVariable Long id, @RequestBody Batiment batiment) {
    	if (!batimentRepository.existsById(id)) {
    		return ResponseEntity.notFound().build();
    	}
    	
    	batiment.setId(id);
    	Batiment updateBatiment = batimentRepository.save(batiment);
    	return ResponseEntity.ok(updateBatiment);
    }
    
}
