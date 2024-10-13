/**
 * Author: Madhu
 * User:madhu
 * Date:8/9/24
 * Time:11:32 AM
 * Project: library-events-producer
 */

package io.madhu.libraryEventsConsumer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Book {

    @Id
    private Integer bookId;
    private String bookName;
    private String bookAuthor;

    @OneToOne
    @JoinColumn(name="libraryEventId")
    private LibraryEvent libraryEvent;
}
