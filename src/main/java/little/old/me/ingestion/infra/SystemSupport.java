package little.old.me.ingestion.infra;

import java.nio.file.Path;

public class SystemSupport {
    public static String getDownloadsDirectory() {
        String userHome = System.getProperty("user.home");
        String downloadsDir;

        // Detect the operating system
        String os = System.getProperty("os.name").toLowerCase();

        Path downloads = Path.of(userHome, "Downloads");
        if (os.contains("win")) {
            // Windows Downloads directory
            downloadsDir = downloads.toString();
        } else if (os.contains("mac") || os.contains("darwin")) {
            // macOS Downloads directory
            downloadsDir = downloads.toString();
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            // Linux/Unix Downloads directory
            downloadsDir = downloads.toString();
        } else {
            // Default fallback (e.g., unknown OS)
            downloadsDir = downloads.toString();
        }
        return downloadsDir;
    }
}
