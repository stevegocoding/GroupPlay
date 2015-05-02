package test;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * 用户注册接口测试类
 * 
 * @author wangq
 * @version $Id: TUserClent.java, v 0.1 2014-11-19 上午11:07:50 wangq Exp $
 */
public class TuserclientManage {

    public ClientConfig cc     = null;
    public Client       client = null;

    public String       url    = "http://localhost:8080/usercenter/client";

    @Before
    public void prepare() {
        cc = new DefaultClientConfig();
        client = Client.create(cc);
    }

    @After
    public void clear() {
    }

    @Test
    public void testAll() {

        create();
        //        update();
        //        updatenickname();
        //        updatepwd();
        //        load();
        isexistuser();

    }

    private void create() {
        System.out.println("-------------  begin create ------- ");
        WebResource wr = client.resource(url + "/createuser");
        JSONObject json = new JSONObject();
        //{"username":"xxx","appkey":"key1","nickname":"nickname","email":"xxxx","Phone":"xxxx","password":"xxxx", }

        try {
            json.put("username", System.currentTimeMillis());
            json.put("password", "123456");
            json.put("appkey", "888888");
            json.put("nickname", "nickname001");
            json.put("email", "");
            json.put("phone", "13820001345");
        } catch (JSONException e) {
        }

        ClientResponse response = wr.entity(json.toString()).post(ClientResponse.class);

        System.out.println(response);
        System.out.println(response.getEntity(String.class));
    }

    private void update() {
        System.out.println("-------------  begin update ------- ");
        WebResource wr = client.resource(url + "/createuser");
        JSONObject json = new JSONObject();
        //{"username":"xxx","appkey":"key1","nickname":"nickname","email":"xxxx","Phone":"xxxx","password":"xxxx", }
        try {
            json.put("username", System.currentTimeMillis());
            json.put("password", "123456");
            json.put("appkey", "888888");
            json.put("nickname", "nickname001");
            json.put("email", "");
            json.put("phone", "13820001345");
        } catch (JSONException e) {
        }

        ClientResponse response = wr.entity(json.toString()).post(ClientResponse.class);

        System.out.println(response);
        System.out.println(response.getEntity(String.class));

        wr = client.resource(url + "/updateuser");
        try {
            json.put("password", "123456");
            json.put("appkey", "xxxxxxx");
            json.put("nickname", "nickname00666");
            json.put("email", "");
            json.put("phone", "0000000");
        } catch (JSONException e) {
        }

        response = wr.entity(json.toString()).post(ClientResponse.class);

        System.out.println(response);
        System.out.println(response.getEntity(String.class));

    }

    private void isexistuser() {
        System.out.println("-------------  begin isexistuser ------- ");
        WebResource wr = client.resource(url + "/createuser");
        JSONObject json = new JSONObject();
        //{"username":"xxx","appkey":"key1","nickname":"nickname","email":"xxxx","Phone":"xxxx","password":"xxxx", }
        try {
            json.put("username", System.currentTimeMillis());
            json.put("password", "123456");
            json.put("appkey", "888888");
            json.put("nickname", "nickname001");
            json.put("email", "");
            json.put("phone", "13820001345");
        } catch (JSONException e) {
        }

        ClientResponse response = wr.entity(json.toString()).post(ClientResponse.class);

        System.out.println(response);
        System.out.println(response.getEntity(String.class));

        String ul = url + "/isexistuser?username=";
        try {
            ul = ul + json.getString("username");
        } catch (JSONException e) {
        }

        String str = client.resource(ul).get(String.class);

        System.out.println("isexistuser return : " + str);
    }

    private void load() {
    }

    private void updatepwd() {
        System.out.println("-------------  begin updatepwd ------- ");
        WebResource wr = client.resource(url + "/createuser");
        JSONObject json = new JSONObject();
        //{"username":"xxx","appkey":"key1","nickname":"nickname","email":"xxxx","Phone":"xxxx","password":"xxxx", }
        try {
            json.put("username", System.currentTimeMillis());
            json.put("password", "123456");
            json.put("appkey", "888888");
            json.put("nickname", "nickname001");
            json.put("email", "");
            json.put("phone", "13820001345");
        } catch (JSONException e) {
        }

        ClientResponse response = wr.entity(json.toString()).post(ClientResponse.class);

        System.out.println(response);
        System.out.println(response.getEntity(String.class));

        wr = client.resource(url + "/updatepwd");
        try {
            json.put("newpwd", "000000");
            json.put("appkey", "888888");
            json.put("oldpwd", "123456");

        } catch (JSONException e) {
        }

        response = wr.entity(json.toString()).post(ClientResponse.class);

        System.out.println(response);
        System.out.println(response.getEntity(String.class));

    }

    private void updatenickname() {
        System.out.println("-------------  begin updatenickname ------- ");
        WebResource wr = client.resource(url + "/createuser");
        JSONObject json = new JSONObject();
        //{"username":"xxx","appkey":"key1","nickname":"nickname","email":"xxxx","Phone":"xxxx","password":"xxxx", }
        try {
            json.put("username", System.currentTimeMillis());
            json.put("password", "123456");
            json.put("appkey", "888888");
            json.put("nickname", "nickname001");
            json.put("email", "");
            json.put("phone", "13820001345");
        } catch (JSONException e) {
        }

        ClientResponse response = wr.entity(json.toString()).post(ClientResponse.class);

        System.out.println(response);
        System.out.println(response.getEntity(String.class));

        wr = client.resource(url + "/updatenickname");
        try {
            json.put("nickname", "updatenick");
            json.put("appkey", "888888");

        } catch (JSONException e) {
        }

        response = wr.entity(json.toString()).post(ClientResponse.class);

        System.out.println(response);
        System.out.println(response.getEntity(String.class));

    }

}
