package org.baiduPush;

import com.alibaba.fastjson.JSON;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;

import java.util.HashMap;
import java.util.Map;

public class BaiduPush {

    private String apiKey = "eLURKgVUqCAkI7qVC16MocQC";
    private String secretKey = "NUrVA0D8E9uXv7iMsNarMk9zr4lsqhIo";
    private String host = "api.push.baidu.com";


    private BaiduPushClient getBaiduPushClient() {
        PushKeyPair pushKeyPair = new PushKeyPair(apiKey, secretKey);
        return new BaiduPushClient(pushKeyPair,host);
    }

    //向单个设备推送消息
    public String  PushMsgToSingleDevice() throws PushServerException {
        //用于接收分配给开发者app的apiKey 和 secretKey
        PushKeyPair pushKeyPair = new PushKeyPair(apiKey, secretKey);
        //该类提供了所有的面向用户使用的接口
        BaiduPushClient baiduPushClient= new BaiduPushClient(pushKeyPair, BaiduPushConstants.CHANNEL_REST_URL);


        // 3. 注册YunLogHandler，获取本次请求的交互信息
        baiduPushClient.setChannelLogHandler (new YunLogHandler() {
            @Override
            public void onHandle (YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });


        //PushMsgToSingleDeviceRequest类的实例对象
        PushMsgToSingleDeviceRequest request=new PushMsgToSingleDeviceRequest();
        //必须为客户端初始化成功之后返回的channelId，默认null 唯一对应一台设备
        request.addChannelId("4439602337560284355");
        //消息类型 0：透传消息1：通知默认值为0
        request.addMessageType(0);

        Map<String,String> map=new HashMap<String,String>();
        map.put("description","dddddddd");
        //消息内容，json格式
        request.addMessage(JSON.toJSONString(map));
        //类别主题 由数字，字母和下划线组成
        request.addTopicId("taa");
        //相对于当前时间的消息过期时间，单位为秒  取值：(0, 86400 x 7]，默认值为3600 x 5
        request.addMsgExpires(new Integer(3600));
        //设备类型 3：Android，4：IOS  deviceType => 1 for web, 2 for pc,3 for android, 4 for ios, 5 for wp
        request.addDeviceType(3);

        String res="";
        try {
         PushMsgToSingleDeviceResponse response= baiduPushClient.pushMsgToSingleDevice(request);
         //消息id
         String msgId=response.getMsgId();
         //unix timestamp，消息发送时间
         long sendTime=response.getSendTime();

         res= "msgId: "+msgId+",sendTime:"+sendTime;



        } catch (PushClientException e) {
            e.printStackTrace();
        } catch (PushServerException e) {
            //ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,
            //'true' 表示抛出, 'false' 表示捕获。
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }

        }

        return  res;

    }

    //推送消息给所有设备 ifFixedSend 1定时 0立即
    public void pushMsgToAll(String msgType,String msg,String msgExpires,String deviceType,String isFixedSend,String sendTime){
        //用于接收分配给开发者app的apiKey 和 secretKey
        PushKeyPair pushKeyPair = new PushKeyPair(apiKey, secretKey);
        //该类提供了所有的面向用户使用的接口
        BaiduPushClient baiduPushClient= new BaiduPushClient(pushKeyPair, BaiduPushConstants.CHANNEL_REST_URL);


        // 3. 注册YunLogHandler，获取本次请求的交互信息
        baiduPushClient.setChannelLogHandler (new YunLogHandler() {
            @Override
            public void onHandle (YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });


        PushMsgToAllRequest request=new PushMsgToAllRequest()
                //消息类型 0：透传消息 1：通知
                .addMessageType(new Integer(msgType))
                //消息内容，json格式
                .addMessage(msg)
                //相对于当前时间的消息过期时间，单位为秒
                .addMsgExpires(new Integer(msgExpires))
                //设备类型 3：Android，4：IOS
                .addDeviceType(new Integer(deviceType));






    }
    public static void main(String[] args) throws PushServerException {
       System.out.println(new BaiduPush().PushMsgToSingleDevice());
    }

}
