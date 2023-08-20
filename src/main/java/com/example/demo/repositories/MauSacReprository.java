package com.example.demo.repositories;

import com.example.demo.model.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MauSacReprository extends JpaRepository<MauSac, UUID> {

    @Query("select ms from MauSac ms where ms.ma = ?1")
    Optional<MauSac> findAllByMa(String ma);
}
