package com.example.employe.management.Repo;

import com.example.employe.management.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    // You can add custom query methods here if needed
}