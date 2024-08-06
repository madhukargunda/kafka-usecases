/**
 * Author: Madhu
 * User:madhu
 * Date:23/6/24
 * Time:8:40 AM
 * Project: random-user-stream-event
 */

package io.madhu.sseUserEvent.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Location implements Serializable {

    public Street street;
    public String city;
    public String state;
    public String country;
    public String postcode;
    public Coordinates coordinates;

}
