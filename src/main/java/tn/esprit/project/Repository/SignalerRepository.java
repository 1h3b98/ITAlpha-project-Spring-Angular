package tn.esprit.project.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.Signaler;
import tn.esprit.project.Entities.SignalerId;

@Repository
public interface SignalerRepository extends CrudRepository<Signaler, SignalerId> {
}
