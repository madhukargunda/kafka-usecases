/**
 * Author: Madhu
 * User:madhu
 * Date:23/6/24
 * Time:8:41 AM
 * Project: random-user-stream-event
 */

package io.madhu.sseUserEvent.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class Coordinates implements Serializable {

    public String latitude;
    public String longitude;
}
