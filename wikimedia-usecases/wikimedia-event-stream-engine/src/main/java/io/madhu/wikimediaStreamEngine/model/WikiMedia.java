/**
 * Author: Madhu
 * User:madhu
 * Date:9/7/24
 * Time:7:11 PM
 * Project: wikimedia-event-stream-engine
 */

package io.madhu.wikimediaStreamEngine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WikiMedia {

    private Integer id;

    private String data;
}
