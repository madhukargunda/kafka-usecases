/**
 * Author: Madhu
 * User:madhu
 * Date:25/6/24
 * Time:3:47 PM
 * Project: sse-user-events
 */

package io.madhu.sseUserEvent.model.news;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Article implements Serializable {

    private String author;
    private String title;
    private String description;
    private String urlToImage;
}
