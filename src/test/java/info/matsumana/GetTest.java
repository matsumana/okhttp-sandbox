package info.matsumana;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 単純なGETリクエストのテスト
 *
 * @author matsumana
 */
public class GetTest {

    Get sut = new Get();

    @Test
    public void test() throws Exception {

        // Start MockWebServer
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody("hoge"));
        server.start();

        sut.url = server.url("/");
        String body = sut.request();

        assertThat(body, is("hoge"));

        // Shutdown MockWebServer
        server.shutdown();
    }
}
