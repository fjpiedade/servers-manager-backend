package phi.raiztech.servermanagerapp.server;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static java.util.Map.of;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/api/v1/server")
@RequiredArgsConstructor
public class ServerController {
    private final ServerServiceImplementation serverServiceImplementation;

    @GetMapping("/")
    public ResponseEntity<ServerResponse> getServers() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return ResponseEntity.ok(
                ServerResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(of("server", serverServiceImplementation.listOfServer(10)))
                        .message("Servers retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("/")
    public ResponseEntity<ServerResponse> saveServer(@RequestBody @Valid ServerModel server) {
        return ResponseEntity.ok(
                ServerResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(of("server", serverServiceImplementation.createServer(server)))
                        .message("Server created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServerResponse> getServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                ServerResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(of("server", serverServiceImplementation.getServerById(id)))
                        .message("Server retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServerResponse> deleteServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                ServerResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(of("deleted", serverServiceImplementation.deleteServer(id)))
                        .message("Server deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PutMapping("/")
    public ResponseEntity<ServerResponse> updateServer(@RequestBody @Valid ServerModel server) {
        ServerModel newServer = serverServiceImplementation.updateServer(server);
        return ResponseEntity.ok(
                ServerResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(of("server", newServer))
                        .message("Server updated")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        //System.out.println(Arrays.toString(Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/javaPROJECTS/servermanagerapp/images/" + fileName))));
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/javaPROJECTS/servermanagerapp/images/" + fileName));
    }

    @GetMapping("ping/{ipAddress}")
    public ResponseEntity<ServerResponse> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        return ResponseEntity.ok(
                ServerResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(of("server", serverServiceImplementation.pingServer(ipAddress)))
                        .message("Pinging server")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
