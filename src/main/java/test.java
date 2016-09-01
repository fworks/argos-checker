

import java.io.IOException;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson.JacksonFactory;
import com.test.argos.ProductStockStatus;
import com.test.argos.Store;
import com.test.argos.StoreList;


public class test {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static final JsonFactory JSON_FACTORY = new JacksonFactory();
	static HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
		
		public void initialize(HttpRequest request) {
			request.setParser(new JsonObjectParser(JSON_FACTORY));
		}
	});
	
	static String productCode = "4984490";

	public static void main(String[] args) {
		try {
			if(args == null || args.length == 0 || args[0] == null) {
				throw new RuntimeException("please pass the product code as parameter");
			}
			productCode = args[0];
			productCode = productCode.replaceAll("/", "").trim();
			if(productCode.length() != 7) {
				throw new RuntimeException("please verify the product code. It might be something like 464/4510");
			}
			
			String stores = "http://checkargos.com/assets/stores.json";
			
			GenericUrl url = new GenericUrl(stores);
			HttpRequest request = requestFactory.buildGetRequest(url);
			final HttpResponse execute = request.execute();
			StoreList list = execute.parseAs(StoreList.class);
			for (final Store store : list) {
				new Thread() {
					@Override
					public void run() {
						super.run();
						try {
							GenericUrl url2 = new GenericUrl("http://checkargos.com/stockcheck/"+store.getCode()+"/" + productCode);
							HttpRequest request2 = requestFactory.buildGetRequest(url2);
							final HttpResponse execute2 = request2.execute();
							String parseAsString = execute2.parseAsString();
							if(parseAsString != null && !parseAsString.contains("stockQuantity\":0")) {
								System.out.println("############" + store.getName() + ": "+parseAsString);
							} else {
								System.out.println(store.getName() + ": -OutOfStock-");
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}