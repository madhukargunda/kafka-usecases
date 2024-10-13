# Create the topic
#! /bin/bash
kafka-topics.sh --create --topic order --partitions 3 --replication-factor 1 --bootstrap-server localhost:9092


