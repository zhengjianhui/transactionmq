package transaction.service.weixin.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;

public final class OkhttpUtil {

    public static final MediaType JSON = MediaType.parse("application/json");

    private static OkHttpClient client = new OkHttpClient();

    private final static OkHttpClient proxyClient;

    static {

        // 此部分为代理配置
        int proxyPort = 8001;
        String proxyHost = "dev.uvoplus.com";
        final String username = null;
        final String password = null;

        Authenticator proxyAuthenticator = new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                String credential = Credentials.basic(username, password);
                return response.request().newBuilder()
                        .header("Proxy-Authorization", credential)
                        .build();
            }
        };

        proxyClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)))
                .proxyAuthenticator(proxyAuthenticator)
                .build();

    }

    public static String get(String url, Boolean proxy) {
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            if(proxy) {
                response = proxyClient.newCall(request).execute();
            } else {
                response = client.newCall(request).execute();
            }
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String post(String url, String json, Boolean proxy) {
        try {
            RequestBody body = RequestBody.create(JSON, json.getBytes("utf-8"));
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = null;

            if(proxy) {
                response = proxyClient.newCall(request).execute();
            } else {
                response = client.newCall(request).execute();
            }

            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new RuntimeException(response.message());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
