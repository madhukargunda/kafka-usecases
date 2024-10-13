/**
 * Author: Madhu
 * User:madhu
 * Date:3/9/24
 * Time:7:21 PM
 * Project: parallel-kafka-consumer
 */

package io.madhu.kafkaConcurrentFlow.processor;

import io.madhu.kafkaConcurrentFlow.service.ConcurrentRandomUserConsumerService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class ConsumeMessagesPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof ConcurrentRandomUserConsumerService){
            ((ConcurrentRandomUserConsumerService) bean).consumeAndProcess();
        }
        return bean;
    }
}
