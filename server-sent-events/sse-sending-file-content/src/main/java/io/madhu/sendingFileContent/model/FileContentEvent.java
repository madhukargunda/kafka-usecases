/**
 * Author: Madhu
 * User:madhu
 * Date:24/7/24
 * Time:11:22 PM
 * Project: sse-sending-file-content
 */

package io.madhu.sendingFileContent.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
public class FileContentEvent extends ApplicationEvent {
    private String content;

    public FileContentEvent(Object source, String content) {
        super(source);
        this.content = content;
    }
}
