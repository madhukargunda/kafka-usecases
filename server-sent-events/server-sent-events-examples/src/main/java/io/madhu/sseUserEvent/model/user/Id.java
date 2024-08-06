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

@Data
@NoArgsConstructor
public class Id implements Serializable {
    public String name;
    public String value;
}
