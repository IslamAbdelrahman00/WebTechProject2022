package de.htwberlin.webtech.webproject.Repo;

import de.htwberlin.webtech.webproject.Entity.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepository extends JpaRepository<ListEntity, Long> {
}
