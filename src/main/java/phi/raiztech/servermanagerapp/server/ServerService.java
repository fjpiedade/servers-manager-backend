package phi.raiztech.servermanagerapp.server;


import java.io.IOException;
import java.util.Collection;

public interface ServerService {
    ServerModel createServer(ServerModel server);
    ServerModel pingServer(String ipAddress) throws IOException;
    Collection<ServerModel> listOfServer(int limit);
    ServerModel getServerById(Long id);
    ServerModel updateServer(ServerModel server);
    Boolean deleteServer(Long id);
}
