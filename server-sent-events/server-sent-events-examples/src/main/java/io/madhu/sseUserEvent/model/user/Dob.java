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
import java.util.Date;

@Data
@NoArgsConstructor
public class Dob implements Serializable {

    public Date date;
    public int age;
}
