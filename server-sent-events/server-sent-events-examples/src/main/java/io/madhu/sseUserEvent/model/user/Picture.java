/**
 * Author: Madhu
 * User:madhu
 * Date:23/6/24
 * Time:8:47 AM
 * Project: random-user-stream-event
 */

package io.madhu.sseUserEvent.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class Picture  implements Serializable {
    public String large;
    public String medium;
    public String thumbnail;
}
