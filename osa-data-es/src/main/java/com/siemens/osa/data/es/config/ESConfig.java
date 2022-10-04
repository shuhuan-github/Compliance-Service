package com.siemens.osa.data.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESConfig {

    /** host list. */
    @Value("${elasticsearch.hostlist}")
    private String hostList;

    /**
     * get es client.
     *
     * @return {@link RestHighLevelClient}
     */
    @Bean("osa-data-esConfig")
    public RestHighLevelClient restHighLevelClient() {
        String[] split = hostList.split(",");
        HttpHost[] httpHostsArray = new HttpHost[split.length];
        for (int i = 0; i < split.length; i++) {
            String item = split[i];
            httpHostsArray[i] = new HttpHost(item.split(":")[0], Integer.parseInt(item.split(":")[1]), "http");
        }

        return new RestHighLevelClient(RestClient.builder(httpHostsArray));
    }

}
