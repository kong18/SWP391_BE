package com.FPTU.repository;

import com.FPTU.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAll();

    @Query(value = "SELECT * FROM item WHERE name LIKE %:name%", nativeQuery = true)
    List<Item> findByNameContainingIgnoreCase(@Param("name")String name);
}
