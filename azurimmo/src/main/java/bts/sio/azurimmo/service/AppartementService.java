package bts.sio.azurimmo.service;

import bts.sio.azurimmo.model.Appartement;
import bts.sio.azurimmo.repository.AppartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppartementService {

    @Autowired
    private AppartementRepository appartementRepository;

    // Récupérer tous les appartements
    public List<Appartement> findAllAppartements() {
        return appartementRepository.findAll();
    }

    // Récupérer un appartement par son ID
    public Optional<Appartement> findById(Long id) {
        return appartementRepository.findById(id);
    }

    // Vérifier si un appartement existe
    public boolean existsById(Long id) {
        return appartementRepository.existsById(id);
    }

    // Supprimer un appartement par son ID
    public void deleteById(Long id) {
        appartementRepository.deleteById(id);
    }

    // Récupérer les appartements par ville
    public List<Appartement> findByVille(String ville) {
        return appartementRepository.findByBatiment_Ville(ville);
    }

    // Récupérer les appartements appartenant à un bâtiment spécifique
    public List<Appartement> getAppartementsParBatiment(long batimentId) {
        return appartementRepository.findByBatiment_Id(batimentId);
    }

    // Sauvegarder un nouvel appartement ou mettre à jour un existant
    public Appartement saveAppartement(Appartement appartement) {
        return appartementRepository.save(appartement);
    }

    // Récupérer les appartements ayant une surface supérieure à une valeur donnée
    public List<Appartement> findBySurfaceGreaterThan(float surface) {
        return appartementRepository.findBySurfaceGreaterThan(surface);
    }
}