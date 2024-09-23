package x5.ppln.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import x5.ppln.Entities.Clients;
import x5.ppln.Entities.Wine;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, Integer>{
    @Transactional(readOnly = true)
    Clients findClientsByCode(Integer code);

    @Transactional(readOnly = true)
    boolean existsByCode(Integer code);
}
