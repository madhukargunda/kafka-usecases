/**
 * Author: Madhu
 * User:madhu
 * Date:23/6/24
 * Time:8:46 AM
 * Project: random-user-stream-event
 */

package io.madhu.sseUserEvent.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Name implements Serializable {
    public String title;
    public String first;
    public String last;
}
