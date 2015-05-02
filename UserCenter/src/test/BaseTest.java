package test;

import org.junit.After;
import org.junit.Before;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class BaseTest {

	public static ClientConfig cc = null;
	public static WebResource wr = null;
	public static Client client = null;
	
	public static Integer ps=0;
	
	@Before
	public void prepare(){
		cc = new DefaultClientConfig();
		client = Client.create(cc);
		System.out.println("===============Start Test "+this.getClass().toString()+"=================");
	}
	
	@After
	public void clear(){
		System.out.println("===============End Test " +this.getClass().toString()+"=================");
	}
	
	public static void ps(){
		if (ps==0){
			System.out.println(">>>>Start "+Thread.currentThread().getStackTrace()[2].getMethodName());
		}
		ps++;

	}
	public static void pe(){
		if (ps<=1){
			System.out.println(">>>>End "+Thread.currentThread().getStackTrace()[2].getMethodName()+"\n");
		}
		ps--;
		
	}
}
