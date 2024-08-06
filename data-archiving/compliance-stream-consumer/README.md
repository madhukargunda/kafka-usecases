1. When using `ConcurrentKafkaListenerContainerFactory` in Spring Kafka, increasing the number of concurrent consumers can affect how consumersread data from topic partitions.
   Here is how it works:

### Topic Partitions and Consumer Groups

1. **Topic Partitions**: Each Kafka topic is divided into partitions. The number of partitions determines the parallelism of consumers.Each partition can be read by only one consumer within the same consumer group at a time.
2. **Consumer Groups**: Consumers are part of a consumer group. Each consumer in the group reads from one or more partitions of the topic. Kafka ensures that each partition is read by only one consumer within the same consumer group.

### Increasing Concurrent Consumers

When you increase the number of concurrent consumers in `ConcurrentKafkaListenerContainerFactory`,
the factory creates multiple consumer threads. These threads will be part of the same consumer group and will distribute the partitions among themselves.

Case 1:

If you have 3 partitions and you set 6 concurrent consumers using the `ConcurrentKafkaListenerContainerFactory`, here is how it will work:

* Kafka will assign each partition to one consumer with in the consumer group . here we have 6 consumers are part of the one group 3 consumers will activie consuming the
  messages from the partitions . The remaining three will be idle and not assigned to any partition.

### Partition to Consumer Mapping

1. **Number of Partitions**: 3
2. **Number of Consumers**: 6
3. **Consumer Group**: All 6 consumers belong to the same consumer group.

### Example Scenario

1. **Partition 0**: Assigned to Consumer 1
2. **Partition 1**: Assigned to Consumer 2
3. **Partition 2**: Assigned to Consumer 3
4. **Consumers 4, 5, 6**: Idle (waiting for a reassignment if any of the first three consumers go down)

Kafka will assign each partition to one consumer within the same consumer group. Since you have more consumers (6) than partitions (3), only 3 consumers will be actively consuming messages from the partitions. The remaining 3 consumers will be idle and not assigned to any partition

Case 2 :

If you have 3 partitions and you set 6 concurrent consumers distributed across 3 consumer groups (each group containing 2 consumers), here's how it will work:

* **Number of Partitions**: 3
* **Total Consumers**: 6
* **Consumer Groups**: 3
* **Consumers per Group**: 2

### Behavior

1. **Consumer Group A**:
   * Consumer A1
   * Consumer A2
2. **Consumer Group B**:
   * Consumer B1
   * Consumer B2
3. **Consumer Group C**:
   1. Consumer C1
   2. Consumer C2

1.Each consumer group will receive a copy of all messages from the topic

2.single partition's data is processed concurrently by multiple consumers, typically need to implement a
system where a single consumer reads the data and then distributes the processing tasks to multiple worker threads or executors.

### Partition Assignment

Each consumer group will receive a copy of all messages from the topic. Within each group, Kafka will distribute the partitions among the consumers in that group.

#### Group A

* **Partition 0**: Assigned to Consumer A1
* **Partition 1**: Assigned to Consumer A2
* **Partition 2**: Not assigned initially since there are only 2 consumers (one of A1 or A2 will get this partition during a rebalance)

#### Group B

* **Partition 0**: Assigned to Consumer B1
* **Partition 1**: Assigned to Consumer B2
* **Partition 2**: Not assigned initially since there are only 2 consumers (one of B1 or B2 will get this partition during a rebalance)

#### Group C

* **Partition 0**: Assigned to Consumer C1
* **Partition 1**: Assigned to Consumer C2
* **Partition 2**: Not assigned initially since there are only 2 consumers (one of C1 or C2 will get this partition during a rebalance)
