/**
 * Author: Madhu
 * User:madhu
 * Date:24/7/24
 * Time:6:04 PM
 * Project: sse-sending-file-content
 */

package io.madhu.sendingFileContent.service;

import io.madhu.sendingFileContent.listener.FileListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
@EnableScheduling
public class FilePollingService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    private final CopyOnWriteArrayList<FileListener> listeners = new CopyOnWriteArrayList<>();
    private final Path folderPath = Paths.get("/Users/madhu/work/fils");

    public void registerListener(FileListener listener) {
        listeners.add(listener);
    }

    @Scheduled(fixedRate = 5000)
    public void pollFolder() {
        log.info("Polling the path for new files - {}", folderPath.toString());
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folderPath)) {
            for (Path path : stream) {
                if (Files.isRegularFile(path)) {
                    String content = new String(Files.readAllBytes(path));
                    notifyListeners(content);
                    //  eventPublisher.publishEvent(new FileContent(this,content));
                    Files.delete(path);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void notifyListeners(String content) {
        for (FileListener listener : listeners) {
            listener.onFileDetected(content);
        }
    }
}
