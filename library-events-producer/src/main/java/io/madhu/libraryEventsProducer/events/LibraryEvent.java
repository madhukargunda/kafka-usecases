/**
 * Author: Madhu
 * User:madhu
 * Date:8/9/24
 * Time:11:17 AM
 * Project: library-events-producer
 */

package io.madhu.libraryEventsProducer.events;

import io.madhu.libraryEventsProducer.constants.LibraryEventType;
import io.madhu.libraryEventsProducer.dto.Book;
import lombok.Data;

@Data
public class LibraryEvent {

    private String libraryEventId;
    private LibraryEventType libraryEventType;
    private Book book;
}
