# Create the topic
#! /bin/bash
kafka-topics.sh --create --topic pos-invoice --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092

