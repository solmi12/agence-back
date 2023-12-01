package com.example.employe.management.Repo;

import com.example.employe.management.model.CartItem;
import com.example.employe.management.model.Haj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
        List<CartItem> findBySessionIdentifier(String sessionIdentifier);
        CartItem findByHaj_hajIdAndSessionIdentifier(Long hajId, String sessionIdentifier);
        @Modifying
        @Query("DELETE FROM CartItem c WHERE c.haj.hajId = :hajId")
        void deleteByHajId(@Param("hajId") Long hajId);
}
