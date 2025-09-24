package pe.upc.vacappbackend.campaignManagement.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.vacappbackend.campaignManagement.domain.model.aggregates.Campaign;
import pe.upc.vacappbackend.iam.domain.model.aggregates.User;

import java.util.List;
import java.util.Optional;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    Optional<Campaign>findById(Long id);
    List<Campaign> findCampaignsByUserId(Long userId);
}
