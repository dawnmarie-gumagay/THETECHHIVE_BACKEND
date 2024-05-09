package WildCatOneTap.TECHHIVE.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import WildCatOneTap.TECHHIVE.Entity.IncidentReportEntity;

@Repository
public interface IncidentReportRepository extends JpaRepository<IncidentReportEntity, Integer> {

}
