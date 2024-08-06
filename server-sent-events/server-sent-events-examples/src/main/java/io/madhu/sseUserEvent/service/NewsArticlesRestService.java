/**
 * Author: Madhu
 * User:madhu
 * Date:7/7/24
 * Time:3:51 PM
 * Project: server-sent-events-examples
 */

package io.madhu.sseUserEvent.service;

import io.madhu.sseUserEvent.model.news.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
public class NewsArticlesRestService {

    @Autowired
    RestTemplate restTemplate;

    public List<Article> getNewsArticles() {
        return List.of();
    }
}
