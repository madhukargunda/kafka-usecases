package io.madhu.wikimediaConsumer.repository;

import io.madhu.wikimediaConsumer.entity.WikiMediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikiMediaDataRepository extends JpaRepository<WikiMediaData, Long> {
}