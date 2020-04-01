package com.econage.econagees.service;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class IndexService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;


    public boolean indexExist() throws IOException {
        GetIndexRequest request = new GetIndexRequest("project");
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        return exists;
    }

    public Object createIndex() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("project");
        createIndexRequest.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 0));
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.startObject("properties");
            {
                builder.startObject("code");
                {
                    builder.field("type", "text");
                }
                builder.endObject();
                builder.startObject("name");
                {
                    builder.field("type", "text");
                }
                builder.endObject();
                builder.startObject("comments");
                {
                    builder.field("type", "text");
                }
                builder.endObject();
            }
            builder.endObject();
        }
        builder.endObject();
        createIndexRequest.mapping(builder);
        CreateIndexResponse createIndexResponse=restHighLevelClient.indices()
                .create(createIndexRequest, RequestOptions.DEFAULT);
        return createIndexResponse.isAcknowledged();
    }

    public Object deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest ("project");
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        return delete.isAcknowledged();
    }

}
