package sk.stuba.fei.uim.oop.assignment3.list.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ListRepository extends JpaRepository<LentList, Long> {
    List<LentList> findAll();
    LentList findListById(Long id);
}
