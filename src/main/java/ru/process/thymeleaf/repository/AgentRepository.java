package ru.process.thymeleaf.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.process.thymeleaf.entity.Agent;

@Repository
@Transactional
public interface AgentRepository extends JpaRepository<Agent, Integer> {

}
