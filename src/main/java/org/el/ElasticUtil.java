package org.el;

import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ElasticUtil {

    public void elastic() throws ExecutionException, InterruptedException {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "el")//使用超过一个el集群时必须设置el集群名
                .put("client.transport.sniff", true)//client可以嗅探集群的其余机器
                .build();

        //创建client
        TransportClient client = new TransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress("master", 9300));


        //get查找
        GetRequestBuilder requestBuilder=client.prepareGet("shop","userInfo","2");
        GetResponse response=requestBuilder.execute().actionGet();
        GetResponse response1=requestBuilder.get();
        ListenableActionFuture<GetResponse> response2= requestBuilder.execute();
        System.out.println(response1.getSourceAsString());



        //使用queryBuilder
        /**
         * 使用QueryBuilder
         * termQuery("key", obj) 完全匹配
         * termsQuery("key", obj1, obj2..)   一次匹配多个值
         * matchQuery("key", Obj) 单个匹配, field不支持通配符, 前缀具高级特性
         * multiMatchQuery("text", "field1", "field2"..);  匹配多个字段, field有通配符忒行
         * matchAllQuery();         匹配所有文件
         */
        QueryBuilder queryBuilder= QueryBuilders.termQuery("name","wang");
        QueryBuilder queryBuilder1=QueryBuilders.termsQuery("name","","");
        QueryBuilder queryBuilder2=QueryBuilders.matchQuery("name","wang");
        QueryBuilder queryBuilder3=QueryBuilders.matchPhraseQuery("","");
        QueryBuilder queryBuilder4=QueryBuilders.multiMatchQuery("name","");




        //组合查询
        /**
         *
         * must(QueryBuilders) :   AND
         * mustNot(QueryBuilders): NOT
         * should:                  : OR
         *
         */

        QueryBuilder queryBuilder5=QueryBuilders.boolQuery().must(QueryBuilders.termQuery("name","")).should(QueryBuilders.termQuery("name",""));

        SearchResponse searchResponse=client.prepareSearch("shop")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setScroll(new TimeValue(60000))
                .setQuery(queryBuilder)
                .setSize(100).execute().actionGet();

        for (SearchHit hit : searchResponse.getHits()) {
            Iterator<Map.Entry<String, Object>> iterator = hit.getSource().entrySet().iterator();
            while(iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                System.out.println(next.getKey() + ": " + next.getValue());
                if(searchResponse.getHits().hits().length == 0) {
                    break;
                }
            }
        }




    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        new ElasticUtil().elastic();
    }
}
