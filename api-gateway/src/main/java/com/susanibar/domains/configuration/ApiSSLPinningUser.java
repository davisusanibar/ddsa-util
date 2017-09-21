package com.susanibar.domains.configuration;

import okhttp3.*;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.cert.Certificate;

@RestController
@RequestMapping("/user")
public class ApiSSLPinningUser {

    final static Logger miLogger = Logger.getLogger(ApiSSLPinningUser.class);

    @RequestMapping("/dogs/breeds")
    public Response getDog() throws Exception {
        miLogger.info("API - Call -> getDog");
        try {
            String getUser = "https://dog.ceo/api/breeds/list/all";
            Request request = new Request.Builder().url(getUser).build();

            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

            Response response = okHttpClient.newCall(request).execute();

            miLogger.info("SSLPinning: " + response.body().string());

            return response;
        } catch (RestClientException rce){
            throw new Exception("Error to call rest template. Error: " + rce.getMessage());
        }
    }

    @RequestMapping("/pinning/dogs/breeds")
    public Response getDogPinning() throws Exception {
        miLogger.info("API - Call -> getDogPinning");
        try {
            String getUser = "https://dog.ceo/api/breeds/list/all";
            Request request = new Request.Builder().url(getUser).build();

            CertificatePinner pinner = new CertificatePinner.Builder()
                    .add("dog.ceo", "sha256/mOlW+yMRH5NnEhpOz4yXzm+G20o1vRJFPGLb2RusQXw=")
                    .build();


            OkHttpClient okHttpClient = new OkHttpClient.Builder().certificatePinner(pinner).build();

            Response response = okHttpClient.newCall(request).execute();

            miLogger.info("SSLPinning: " + response.body().string());

            return response;
        } catch (RestClientException rce){
            throw new Exception("Error to call rest template. Error: " + rce.getMessage());
        }
    }

    @RequestMapping("/pinning/interceptor/dogs/breeds")
    public Response getDogPinningInterceptor() throws Exception {
        miLogger.info("API - Call -> getDogPinningInterceptor");
        try {
            String getUser = "https://dog.ceo/api/breeds/list/all";
            Request request = new Request.Builder().url(getUser).build();

            CertificatePinner pinner = new CertificatePinner.Builder()
                    .add("dog.ceo", "sha256/mOlW+yMRH5NnEhpOz4yXzm+G20o1vRJFPGLb2RusQXw=")
                    .build();


            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));
            //OkHttpClient okHttpClient = new OkHttpClient.Builder().certificatePinner(pinner).proxy(proxy).build();
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .certificatePinner(pinner)
                    .addInterceptor(new LoggingInterceptor())
                    .build();

            Response response = okHttpClient.newCall(request).execute();

            /**
             * response.handshake() contains the TLS handshake of the connection that carried this response, or null if the response
             * was received without TLS.
             */

                for (Certificate certificate : response.handshake().peerCertificates())
                {
                    //Pin returns SHA-256 hash of the public key
                    String caPin = CertificatePinner.pin(certificate);

                    // then you we to check the hash value and apply the corresponding action
                    miLogger.info("Los pines son: " + caPin);
                }


            miLogger.info("SSLPinning: " + response.body().string());

            return response;
        } catch (RestClientException rce){
            throw new Exception("Error to call rest template. Error: " + rce.getMessage());
        }
    }

    class LoggingInterceptor implements Interceptor {
        @Override public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            miLogger.info(String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            miLogger.info(String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }
}
