package bts.sio.azurimmo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bts.sio.azurimmo.model.Associe;
import bts.sio.azurimmo.repository.AssocieRepository;

@Service
public class AssocieService {
    @Autowired
    private AssocieRepository associeRepository;
    
    public Associe saveAssocie(Associe associe) {
        if (associe.getId() != null && associe.getId() == 0) {
            associe.setId(null);
        }
        return associeRepository.save(associe);
    }
}