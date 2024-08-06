/**
 * Author: Madhu
 * User:madhu
 * Date:23/6/24
 * Time:8:47 AM
 * Project: random-user-stream-event
 */

package io.madhu.randomUser.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Picture {
    public String large;
    public String medium;
    public String thumbnail;
}
