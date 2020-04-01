package com.econage.econagees;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient(ElasticSearchSetting elasticSearchSetting){

        HttpHost[] hosts=elasticSearchSetting.getUris()
                .stream()
                .map(HttpHost::create).toArray(HttpHost[]::new);

        RestClientBuilder builder = RestClient.builder(hosts);
        builder.setRequestConfigCallback(requestConfigBuilder->{
            requestConfigBuilder.setConnectTimeout(elasticSearchSetting.getConnectTimeout());
            requestConfigBuilder.setSocketTimeout(elasticSearchSetting.getSocketTimeout());
            requestConfigBuilder.setConnectionRequestTimeout(elasticSearchSetting.getConnectionRequestTimeout());
            return requestConfigBuilder;
        });

        builder.setHttpClientConfigCallback(httpClientBuilder->{
            httpClientBuilder.setMaxConnTotal(elasticSearchSetting.getMaxConnectNum());
            httpClientBuilder.setMaxConnPerRoute(elasticSearchSetting.getMaxConnectPerRoute());
            return httpClientBuilder;
        });

        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }

}
