package bts.sio.azurimmo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bts.sio.azurimmo.model.Batiment;
import bts.sio.azurimmo.repository.BatimentRepository;

@Service
public class BatimentService {
    
    @Autowired
    private BatimentRepository batimentRepository;

    public Batiment saveBatiment(Batiment batiment) {
        if (batiment.getId() == 0) {
            batiment.setId(null);
        }
        return batimentRepository.save(batiment);
    }
    
    
}
