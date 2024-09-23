package x5.ppln.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import x5.ppln.Entities.Wine;

@Repository
public interface  WineRepository extends JpaRepository<Wine, Integer>{
    @Transactional(readOnly = true)
    Wine findWineByCode(Integer code);

    @Transactional(readOnly = true)
    boolean existsByCode(Integer code);
}
