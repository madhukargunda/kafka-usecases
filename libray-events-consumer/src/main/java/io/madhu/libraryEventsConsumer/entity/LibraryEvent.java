/**
 * Author: Madhu
 * User:madhu
 * Date:18/9/24
 * Time:5:13 PM
 * Project: libray-events-consumer
 */

package io.madhu.libraryEventsConsumer.entity;

import io.madhu.libraryEventsConsumer.constants.LibraryEventType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryEvent {

    @Id
    @GeneratedValue
    Integer libraryEventId;

    @Enumerated(EnumType.STRING)
    LibraryEventType libraryEventType;

    @OneToOne(mappedBy = "bookId",cascade = CascadeType.ALL)
    Book book;
}
