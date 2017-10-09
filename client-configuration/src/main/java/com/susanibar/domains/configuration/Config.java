package com.susanibar.domains.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class Config {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");

            }
        };
    }

//    @Bean
//    public ClientHttpRequestFactory clientHttpRequestFactory(HttpClient httpClient) {
//        return new HttpComponentsClientHttpRequestFactory(httpClient);
//    }
//
//    private SSLContext getContext(File pKeyFile, String pKeyPassword) throws NoSuchAlgorithmException,
//            KeyStoreException, IOException, CertificateException, UnrecoverableKeyException, KeyManagementException {
//
//        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
//        KeyStore keyStore = KeyStore.getInstance("PKCS12");
//
//        InputStream keyInput = new FileInputStream(pKeyFile);
//        keyStore.load(keyInput, pKeyPassword.toCharArray());
//        keyInput.close();
//
//        keyManagerFactory.init(keyStore, pKeyPassword.toCharArray());
//
//        TrustManagerFactory walleTmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//        KeyStore walleTrustKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//        InputStream walleKeyTrustInput = new FileInputStream(storeFile);
//        walleTrustKeyStore.load(walleKeyTrustInput, storePwd.toCharArray());
//        walleKeyTrustInput.close();
//        walleTmf.init(walleTrustKeyStore);
//
//        SSLContext context = SSLContext.getInstance("TLS");
//        context.init(keyManagerFactory.getKeyManagers(), walleTmf.getTrustManagers(), new SecureRandom());
//
//        return context;
//    }
//
//    @Bean
//    public HttpClient httpClient()
//            throws NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException,
//            UnrecoverableKeyException, KeyManagementException {
//
//        SSLContext sslcontext = getContext(new File(pfxFile), pfxPwd);
//        SSLConnectionSocketFactory sslsf =
//                new SSLConnectionSocketFactory(sslcontext, new javax.net.ssl.HostnameVerifier() {
//
//                    @Override
//                    public boolean verify(String arg0, SSLSession arg1) {
//                        return true;
//                    }
//
//                });
//        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
//    }

}
