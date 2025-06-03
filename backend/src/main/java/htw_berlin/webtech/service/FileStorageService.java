package htw_berlin.webtech.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path storageDirectory = Paths.get("uploads");

    public FileStorageService() {
        try {
            Files.createDirectories(storageDirectory);
        } catch (IOException e) {
            throw new RuntimeException("Upload-Directory konnte nicht erstellt werden!", e);
        }
    }

    public String storeFile(MultipartFile file) {
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path targetPath = storageDirectory.resolve(filename).normalize();
        try (InputStream in = file.getInputStream()) {
            Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
            return "/uploads/" + filename;
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + filename, e);
        }
    }

    public String extractText(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
                content.append(line.replace("\u0000","")).append("\n");
            return content.toString().trim();
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Lesen des Dateiinhalts", e);
        }
    }
}
