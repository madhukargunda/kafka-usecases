/**
 * Author: Madhu
 * User:madhu
 * Date:22/6/24
 * Time:6:11 PM
 * Project: random-user-stream-event
 */

package io.madhu.randomUser.wrapper;

import lombok.Data;

@Data
public class UserWrapper<T> {

    private T user;

}
