package info.matsumana;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.eclipse.jetty.alpn.ALPN;

import java.io.IOException;

/**
 * 単純なGETリクエスト
 *
 * @author matsumana
 */
public class Get {

    HttpUrl url = HttpUrl.parse("https://matsumana.info/");

    public String request() throws IOException {

        // Enable ALPN debug log
        ALPN.debug = true;

        Request request = new Request.Builder()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }

        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }

        return response.body().string();
    }
}
