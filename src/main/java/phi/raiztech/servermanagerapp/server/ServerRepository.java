package phi.raiztech.servermanagerapp.server;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<ServerModel, Long> {
    ServerModel findByIpAddress(String ipAddress);
}
