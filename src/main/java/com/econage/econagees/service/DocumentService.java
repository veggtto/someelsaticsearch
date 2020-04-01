package com.econage.econagees.service;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.get.GetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DocumentService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public Object addDocument() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("code","skajfsdlaf");
            builder.field("name","projectname2222222");
            builder.field("comments","commemys4444");
        }
        builder.endObject();
        IndexRequest request = new IndexRequest("project").id("1").source(builder);
        IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        return indexResponse.getResult();
    }

    public Object getDocument() throws IOException {
        GetRequest getRequest = new GetRequest(
                "project",
                "1");

        GetResponse documentFields = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        if(documentFields.isExists()){
            return documentFields.getSourceAsMap();
        }

        return null;
    }

    public Object updateDocument() throws IOException {
        UpdateRequest request = new UpdateRequest("project", "1");
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("comments","commtttt33377");
        }
        builder.endObject();
        request.doc(builder);
        UpdateResponse updateResponse = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        return updateResponse.getResult();
    }

}
