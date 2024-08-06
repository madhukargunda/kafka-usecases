/**
 * Author: Madhu
 * User:madhu
 * Date:23/6/24
 * Time:8:48 AM
 * Project: random-user-stream-event
 */

package io.madhu.sseUserEvent.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class Info implements Serializable {
    public String seed;
    public int results;
    public int page;
    public String version;
}
