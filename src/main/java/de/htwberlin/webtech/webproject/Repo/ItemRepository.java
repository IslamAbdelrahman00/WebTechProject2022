package de.htwberlin.webtech.webproject.Repo;

import de.htwberlin.webtech.webproject.Entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}