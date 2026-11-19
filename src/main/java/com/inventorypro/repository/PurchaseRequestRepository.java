package com.inventorypro.repository;
import com.inventorypro.model.PurchaseRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest,Long> {
 List<PurchaseRequest> findByRequestedByOrderByCreatedAtDesc(String username);
 List<PurchaseRequest> findAllByOrderByCreatedAtDesc();
 long countByStatus(String status);
}
