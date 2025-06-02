package bts.sio.azurimmo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bts.sio.azurimmo.model.Associe;
import bts.sio.azurimmo.repository.AssocieRepository;
import bts.sio.azurimmo.service.AssocieService;

@RestController
@RequestMapping("/api/associes")
public class AssocieController {
    @Autowired
    private AssocieRepository associeRepository;
    
    @Autowired
    private AssocieService associeService;
    
    @GetMapping("/")
    public List<Associe> getAllAssocies() {
        return associeRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Associe> getAssocieById(@PathVariable Long id) {
        return associeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/")
    public ResponseEntity<Associe> createAssocie(@RequestBody Associe associe) {
        if (associe.getId() != null && associe.getId() == 0) {
            associe.setId(null);
        }
        Associe savedAssocie = associeService.saveAssocie(associe);
        return ResponseEntity.ok(savedAssocie);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Associe> updateAssocie(@PathVariable Long id, @RequestBody Associe associe) {
        if (!associeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        associe.setId(id);
        Associe updatedAssocie = associeService.saveAssocie(associe);
        return ResponseEntity.ok(updatedAssocie);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssocie(@PathVariable Long id) {
        if (!associeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        associeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}