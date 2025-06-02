package bts.sio.azurimmo.repository;

import org.springframework.stereotype.Repository;
import bts.sio.azurimmo.model.Associe;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AssocieRepository extends JpaRepository<Associe, Long> {
}