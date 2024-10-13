/**
 * Author: Madhu
 * User:madhu
 * Date:8/9/24
 * Time:11:32 AM
 * Project: library-events-producer
 */

package io.madhu.libraryEventsProducer.dto;

import lombok.Data;

@Data
public class Book {

    private Integer bookId;
    private String bookName;
    private String bookAuthor;
}
