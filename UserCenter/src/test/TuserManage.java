package test;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MultivaluedMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * 用户注册接口测试类
 * 
 * @author wangq
 * @version $Id: TUserClent.java, v 0.1 2014-11-19 上午11:07:50 wangq Exp $
 */
@SuppressWarnings({ "unused", "rawtypes", "unchecked"})
public class TuserManage extends BaseTest {

    public ClientConfig cc      = null;
    public Client       client  = null;

    public String       url    = "http://localhost/usercenter/manage";

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

//                userClentCreate();
//                userClentCreates();
//        userClentUpdate();
//                updatenickname();
//                
//                deletebynames();
//                deletedevbyname();
//                count();
//                findByName();
//                findByAccount();
                getphone();
//                getPassword();
        //        
    }



    private void userClentCreate() {
        System.out.println("-------------  begin create ------- ");
        WebResource wr = client.resource(url + "/createuser");

        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("username", System.currentTimeMillis() + "");
        form.add("password", "123456");
        form.add("appkey", "888888");
        form.add("nickname", "nickname001");
        form.add("email", "");
        form.add("phone", "13820001345");
        ClientResponse response = wr.post(ClientResponse.class, form);

        System.out.println("userClentCreate:" + response);
        System.out.println("userClentCreate:" + response.getEntity(String.class));
        
    }

    private void userClentCreates() {
        System.out.println("-------------  begin userClentCreates ------- ");
        WebResource wr = client.resource(url + "/createusers");

        JSONArray array = new JSONArray();
        for (int i = 0; i < 10; i++) {
            JSONObject json = new JSONObject();
            //{"username":"xxx","appkey":"key1","nickname":"nickname","email":"xxxx","Phone":"xxxx","password":"xxxx", }

            try {
                json.put("username", System.currentTimeMillis() + "" + i);
                json.put("password", "123456");
                json.put("appkey", "888888");
                json.put("nickname", "nickname001");
                json.put("email", "");
                json.put("phone", "13820001345");
            } catch (JSONException e) {
            }
            array.put(json);
        }


        ClientResponse response = wr.header("appkey", "888888").entity(array.toString()).post(ClientResponse.class);

        System.out.println(response);
        System.out.println(response.getEntity(String.class));
        assertEquals("", "");
    }



    private void userClentUpdate() {
        System.out.println("-------------  begin userClentUpdate ------- ");
        WebResource wr = client.resource(url + "/createuser");
        
        String username = System.currentTimeMillis() + "";
        
        
        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("username", username);
        form.add("password", "123456");
        form.add("appkey", "888888");
        form.add("nickname", "nickname001");
        form.add("email", "");
        form.add("phone", "13820001345");
        ClientResponse response = wr.post(ClientResponse.class, form);

        System.out.println("userClentCreate:" + response);
        System.out.println("userClentCreate:" + response.getEntity(String.class));

        wr = client.resource(url + "/updateuser");
        JSONObject json = new JSONObject();
        try {
            json.put("username",username);
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
    
    private void updatenickname() {
        System.out.println("-------------  begin updatenickname ------- ");
        WebResource wr = client.resource(url + "/createuser");
        JSONObject json = new JSONObject();
        String username = System.currentTimeMillis() + "";
        try {
            json.put("username", username);
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
        
        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("username",username);
        form.add("nickname", "updatenick");
        form.add("appkey", "888888");
        
         response = wr.post(ClientResponse.class, form);

        System.out.println("userClentCreate:" + response);
        System.out.println("userClentCreate:" + response.getEntity(String.class));
    }

    private void deletebynames(){
        
        System.out.println("-------------  begin deletebynames ------- ");
        WebResource wr = client.resource(url + "/createusers");

        String usernames = "";
        
        JSONArray array = new JSONArray();
        for (int i = 0; i < 10; i++) {
            JSONObject json = new JSONObject();
            try {
                String user = System.currentTimeMillis() + "" + i;
                usernames += user + ";"; 
                        
                json.put("username", user);
                json.put("password", "123456");
                json.put("appkey", "888888");
                json.put("nickname", "nickname001");
                json.put("email", "");
                json.put("phone", "13820001345");
            } catch (JSONException e) {
            }
            array.put(json);
        }


        ClientResponse response = wr.header("appkey", "888888").entity(array.toString()).post(ClientResponse.class);

        System.out.println("createusers:" + response);
        System.out.println("createusers:" + response.getEntity(String.class));
        
        wr = client.resource(url + "/deletebynames");
        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("usernames", usernames);
        form.add("appkey", "888888");
        
        response = wr.post(ClientResponse.class, form);

        System.out.println("deletebynames:" + response);
        System.out.println("deletebynames:" + response.getEntity(String.class));
    }

    
    private void deletedevbyname(){
        
        System.out.println("-------------  begin deletedevbyname ------- ");
        WebResource wr = client.resource(url + "/createuser");

        String username =  System.currentTimeMillis() + "";
        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("username",username);
        form.add("password", "123456");
        form.add("appkey", "888888");
        form.add("nickname", "nickname001");
        form.add("email", "");
        form.add("phone", "13820001345");
        ClientResponse response = wr.post(ClientResponse.class, form);

        System.out.println("userClentCreate:" + response);
        System.out.println("userClentCreate:" + response.getEntity(String.class));
        
        
        wr = client.resource(url + "/deletedevbyname");
        MultivaluedMap form1 = new MultivaluedMapImpl();
        form1.add("username", username);
        
        response = wr.post(ClientResponse.class, form1);

        System.out.println("deletedevbyname:" + response);
        System.out.println("deletedevbyname:" + response.getEntity(String.class));
    }

    
    private void count() {
        System.out.println("-------------  begin count ------- ");
        WebResource wr = client.resource(url + "/count?appkey=888888");
        String response = wr.get(String.class);

        System.out.println("userClentCreate:" + response);
        
    }
    
    

    private void getPassword() {
        // TODO Auto-generated method stub
        
    }

    private void getphone() {
        System.out.println("-------------  begin findByName ------- ");
        WebResource wr = client.resource(url + "/createuser");

        String username =  System.currentTimeMillis() + "";
        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("username",username);
        form.add("password", "123456");
        form.add("appkey", "888888");
        form.add("nickname", "nickname001");
        form.add("email", "");
        form.add("phone", "13820001345");
        ClientResponse response = wr.post(ClientResponse.class, form);

        System.out.println("userClentCreate:" + response);
        System.out.println("userClentCreate:" + response.getEntity(String.class));
        
        
        wr = client.resource(url + "/getphone?username="+ username);
        
        String ret = wr.get(String.class);

        System.out.println("getphone:" + ret);
        
    }

    private void findByAccount() {
        System.out.println("-------------  begin findByAccount ------- ");
        WebResource wr = client.resource(url + "/createuser");

        String username =  System.currentTimeMillis() + "";
        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("username",username);
        form.add("password", "123456");
        form.add("appkey", "888888");
        form.add("nickname", "nickname001");
        form.add("email", "");
        form.add("phone", "13820001345");
        ClientResponse response = wr.post(ClientResponse.class, form);

        System.out.println("userClentCreate:" + response);
        System.out.println("userClentCreate:" + response.getEntity(String.class));
        
        
        wr = client.resource(url + "/finduserbyname?username="+ username);
        
        response = wr.get(ClientResponse.class);

        String ret = response.getEntity(String.class);
        System.out.println("findByName:" + response);
        System.out.println("findByName:" + ret);
        
        String account = "";
        try {
            JSONObject json = new JSONObject(ret);
            account = json.getString("account");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        wr = client.resource(url + "/finduserbyaccount?account="+ account);
        
        response = wr.get(ClientResponse.class);

        System.out.println("findByAccount:" + response);
        System.out.println("findByAccount:" + response.getEntity(String.class));
        
    }

    private void findByName() {
        System.out.println("-------------  begin findByName ------- ");
        WebResource wr = client.resource(url + "/createuser");

        String username =  System.currentTimeMillis() + "";
        MultivaluedMap form = new MultivaluedMapImpl();
        form.add("username",username);
        form.add("password", "123456");
        form.add("appkey", "888888");
        form.add("nickname", "nickname001");
        form.add("email", "");
        form.add("phone", "13820001345");
        ClientResponse response = wr.post(ClientResponse.class, form);

        System.out.println("userClentCreate:" + response);
        System.out.println("userClentCreate:" + response.getEntity(String.class));
        
        
        wr = client.resource(url + "/finduserbyname?username="+ username +"&sss=ss");
        
        response = wr.get(ClientResponse.class);

        System.out.println("findByName:" + response);
        System.out.println("findByName:" + response.getEntity(String.class));
        
    }

}
