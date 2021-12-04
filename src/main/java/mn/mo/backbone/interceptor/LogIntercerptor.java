package mn.mo.backbone.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@Slf4j
public class LogIntercerptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        requestLog(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        return response;
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        log.debug("===================Debugging response begin =========================");
        log.debug("Status code : {}", response.getStatusCode());
        log.debug("Status text : {}", response.getStatusText() );
        log.debug("Response body : {}", response.getBody());
        log.debug("===================Debugging request end ============================");
        MDC.clear();
    }

    private void requestLog(HttpRequest request, byte[] body){
        log.debug("===================Debugging request begin============================");
        log.debug("URI : {}", request.getURI());
        log.debug("Method : {}", request.getMethod());
        log.debug("Header : {}", request.getHeaders());
        log.debug("Method value : {}", request.getMethodValue());
        log.debug("Method value : {}", request.getMethodValue());
        log.debug("Body : {}", body);
        log.debug("===================Debugging request end============================");
    }
}
