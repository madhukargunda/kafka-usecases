/**
 * Author: Madhu
 * User:madhu
 * Date:10/8/24
 * Time:7:42 AM
 * Project: compliance-stream-producer
 */


package io.madhu.complianceProducer.builder;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class LegalArchivingBuilder {

    private List<String> conversationIds;
    private byte[] token;

    public LegalArchivingBuilder getToken() {
        log.info("getToken ");
        return this;
    }

    public LegalArchivingBuilder getChatGroups() {
        return this;
    }

    public LegalArchivingBuilder getConversations() {
        return this;
    }
}
