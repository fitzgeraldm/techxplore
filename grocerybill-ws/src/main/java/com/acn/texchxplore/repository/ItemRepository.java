package com.acn.texchxplore.repository;

import com.acn.texchxplore.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}