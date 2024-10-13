#!/bin/bash

# Kafka topic
TOPIC="users-topic"
BROKER="localhost:9092"

# Infinite loop to fetch data from Randomuser api and send data
while true; do
    # Fetch JSON data from the API
    json_data=$(curl -s https://randomuser.me/api/)

    # Send the JSON data to the Kafka topic
    echo "$json_data"
    echo "$json_data" | kafka-console-producer.sh --broker-list $BROKER --topic $TOPIC > /dev/null

    # Sleep for a while before fetching the next data
    sleep 3
done
