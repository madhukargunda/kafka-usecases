/**
 * Author: Madhu
 * User:madhu
 * Date:25/6/24
 * Time:3:55 PM
 * Project: sse-user-events
 */

package io.madhu.sseUserEvent.model.news;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class News implements Serializable   {

    private List<Article> articles;
}
