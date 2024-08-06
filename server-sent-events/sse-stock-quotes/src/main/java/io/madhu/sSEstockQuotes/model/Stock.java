/**
 * Author: Madhu
 * User:madhu
 * Date:23/7/24
 * Time:11:50 AM
 * Project: sse-stock-quotes
 */

package io.madhu.sSEstockQuotes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stock {

    private BigDecimal price;
}
