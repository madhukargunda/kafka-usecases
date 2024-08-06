/**
 * Author: Madhu
 * User:madhu
 * Date:23/6/24
 * Time:8:43 AM
 * Project: random-user-stream-event
 */

package io.madhu.randomUser.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Timezone {

    public String offset;
    public String description;
}
