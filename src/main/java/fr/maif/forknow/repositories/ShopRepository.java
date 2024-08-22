package fr.maif.forknow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.maif.forknow.model.Shop;



@Repository
public interface ShopRepository extends JpaRepository<Shop, Long>{

}
