package com.siemens.osa.data.es.service.getdata.impl;

import com.siemens.osa.data.es.service.getdata.IGetESInfoService;
import com.siemens.osa.data.es.entity.ESInfo;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class GetESInfoServiceImpl implements IGetESInfoService {

    /** es client. */
    private RestHighLevelClient client;

    /** es index. */
    private String index;

    /**
     * scroll size.
     */
    private static final Integer SCROLL_SIZE = 1000;

    /**
     * no param constructor.
     */
    public GetESInfoServiceImpl() {
    }

    /**
     * constructor.
     *
     * @param restHighLevelClient es client
     * @param esIndex             es index
     */
    public GetESInfoServiceImpl(final RestHighLevelClient restHighLevelClient, final String esIndex) {
        this.client = restHighLevelClient;
        this.index = esIndex;
    }

    /**
     * get all result in es with index.
     *
     * @return {@link List}&lt;{@link ESInfo}&gt;
     * @throws IOException ioexception
     */
    @Override
    public List<ESInfo> getAllConfig() throws IOException {
        List<ESInfo> list = new LinkedList<>();
        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.scroll(scroll);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.size(SCROLL_SIZE);

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        String scrollId = searchResponse.getScrollId();

        SearchHit[] allSearchHits = searchResponse.getHits().getHits();
        while (allSearchHits != null && allSearchHits.length > 0) {
            SearchHits hits = searchResponse.getHits();

            SearchHit[] searchHits = hits.getHits();
            for (SearchHit hit : searchHits) {
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                String eID = (String) sourceAsMap.get("ID");
                String collectTime = (String) sourceAsMap.get("collectTime");
                String ruleId = (String) sourceAsMap.get("ruleId");
                List<String> result = (List<String>) sourceAsMap.get("result");
                String hostIP = (String) sourceAsMap.get("hostIp");
                String utc = (String) sourceAsMap.get("utc");

                int id = Integer.parseInt(eID);

                list.add(new ESInfo(id, collectTime, utc, result, hostIP, ruleId));
            }
            SearchScrollRequest searchScrollRequest = new SearchScrollRequest(scrollId);
            searchScrollRequest.scroll(scroll);
            searchResponse = client.scroll(searchScrollRequest, RequestOptions.DEFAULT);
            allSearchHits = searchResponse.getHits().getHits();
        }
        return list;
    }

}
