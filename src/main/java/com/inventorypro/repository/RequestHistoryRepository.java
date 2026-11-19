package com.inventorypro.repository;
import com.inventorypro.model.RequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface RequestHistoryRepository extends JpaRepository<RequestHistory,Long> {
 List<RequestHistory> findByRequestIdOrderByActionAtDesc(Long requestId);
}
