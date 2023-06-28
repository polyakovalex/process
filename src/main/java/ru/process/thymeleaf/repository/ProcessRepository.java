package ru.process.thymeleaf.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.process.thymeleaf.entity.Process;

@Repository
@Transactional
public interface ProcessRepository extends JpaRepository<Process, Integer> {
  List<Process> findByTitleContainingIgnoreCase(String keyword);

 /* @Query(value = "SELECT p.id, p.title, p.dateAdd, a.title as agent, pp.title as place  FROM process p , agent a, place pp " +
      "WHERE a.id = p.agent_id AND pp.id = a.place_id " +
      "ORDER BY p.id", nativeQuery = true)
  List<Process> findAllBySort();*/

 /* @Query("UPDATE Process t SET t.published = :published WHERE t.id = :id")
  @Modifying
  public void updatePublishedStatus(Integer id, boolean published);*/
}
