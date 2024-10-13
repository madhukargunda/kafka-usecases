#!/bin/bash

# Define fixed sets of values for random selection (50 book names and authors)
BOOK_IDS=("101" "102" "103" "104" "105" "106" "107" "108" "109" "110" "111" "112" "113" "114" "115" "116" "117" "118" "119" "120" "121" "122" "123" "124" "125" "126" "127" "128" "129" "130" "131" "132" "133" "134" "135" "136" "137" "138" "139" "140" "141" "142" "143" "144" "145" "146" "147" "148" "149" "150")
BOOK_NAMES=("Kafka Using Spring Boot" "Microservices Patterns" "Spring Boot in Action" "Reactive Programming with Spring" "Mastering Kafka Streams" \
"Java Concurrency in Practice" "Clean Code" "Effective Java" "The Pragmatic Programmer" "Design Patterns" \
"Head First Java" "Spring in Action" "Kafka: The Definitive Guide" "Building Microservices" "Docker Deep Dive" \
"Learning Spring Boot 2.0" "Hands-On Reactive Programming with Spring" "High-Performance Java Persistence" "Java Performance: The Definitive Guide" "Refactoring" \
"Continuous Delivery" "Kubernetes: Up and Running" "Architecting for Scale" "Cloud Native Java" "Functional Programming in Java" \
"Pro Spring Boot" "Pro Kafka" "Java 8 in Action" "Spring Microservices in Action" "Spring Integration in Action" \
"Pro Spring Security" "Enterprise Integration Patterns" "Test Driven Development with Java" "Gradle in Action" "JUnit in Action" \
"Pro RESTful APIs with Spring" "Spring Data JPA" "Reactive Spring" "Spring Cloud: Cloud-Native Java" "Kotlin in Action" \
"Mastering Kubernetes" "Architecting Cloud-Native Applications" "Building Scalable Apps with Kubernetes" "Learning Microservices" "Modern Java in Action" \
"Spring Security in Action" "Learning Reactive Programming with Java" "Java Lambdas" "Kubernetes Patterns" "Advanced Java" \
"Java 11 Programming" "Building Event-Driven Microservices")

BOOK_AUTHORS=("Madhu" "John" "Doe" "Alice" "Bob" \
"James Gosling" "Martin Fowler" "Robert C. Martin" "Joshua Bloch" "Ken Kousen" \
"Neal Ford" "Sam Newman" "Viktor Gamov" "Chris Richardson" "Ben Evans" \
"Mark Richards" "Susan J. Fowler" "Venkat Subramaniam" "Juergen Hoeller" "Oliver Gierke" \
"Dan Woods" "Eric Evans" "Kathy Sierra" "Bert Bates" "Gregor Hohpe" \
"Arun Gupta" "Dave Syer" "Rod Johnson" "Pivotal Team" "Craig Walls" \
"Richard Monson-Haefel" "Steve Freeman" "Dan North" "Oren Eini" "Michael Minella" \
"Adam Bien" "Nicolai Parlog" "Markus Eisele" "John Carnell" "Nickolay Tsvetinov" \
"Jonathan Leaver" "Laurentiu Spilca" "Alex Holmes" "Sander Mak" "Peter Ledbrook" \
"Juval Löwy" "Ben Stopford" "Eberhard Wolff" "Jens Schauder" "Pieter Humphrey" \
"Josh Long" "Dmytro Volk")

# Kafka topic name
TOPIC_NAME="your-kafka-topic"

# Kafka broker
BROKER="localhost:9092"

# Infinite loop to continuously send messages
while true
do
  # Generate random values from the fixed sets
  BOOK_ID=${BOOK_IDS[$RANDOM % ${#BOOK_IDS[@]}]}
  BOOK_NAME=${BOOK_NAMES[$RANDOM % ${#BOOK_NAMES[@]}]}
  BOOK_AUTHOR=${BOOK_AUTHORS[$RANDOM % ${#BOOK_AUTHORS[@]}]}

  # Generate a random UUID for libraryEventId
  LIBRARY_EVENT_ID=$(uuidgen)

  # Create the JSON object in a single line
  JSON_DATA="{\"libraryEventId\":\"$LIBRARY_EVENT_ID\",\"libraryEventType\":\"NEW\",\"book\":{\"bookId\":$BOOK_ID,\"bookName\":\"$BOOK_NAME\",\"bookAuthor\":\"$BOOK_AUTHOR\"}}"

  # Send the JSON data to Kafka topic
  echo "$JSON_DATA" #| kafka-console-producer.sh --broker-list "$BROKER" --topic "$TOPIC_NAME" > /dev/null

curl -i \
-d '{"libraryEventId":null,"libraryEventType": "NEW","book":{"bookId":456,"bookName":"Kafka Using Spring Boot","bookAuthor":"Madhu"}}' \
-H "Content-Type: application/json" \
-X POST http://localhost:8080/v1/libraryevent
  # Sleep for 1 second before sending the next message
  sleep 5
done
