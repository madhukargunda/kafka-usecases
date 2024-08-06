/**
 * Author: Madhu
 * User:madhu
 * Date:23/6/24
 * Time:8:45 AM
 * Project: random-user-stream-event
 */

package io.madhu.sseUserEvent.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Result implements Serializable {

    public String gender;
    public Name name;
    public Location location;
    public String email;
    public Login login;
    public Dob dob;
    public Registered registered;
    public String phone;
    public String cell;
    public Id id;
    public Picture picture;
    public String nat;
}
