package vvt2022.miniProject.badWordDetecter.service;

import java.io.IOException;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import vvt2022.miniProject.badWordDetecter.model.Detector;
import vvt2022.miniProject.badWordDetecter.model.User;;

@Service
public class DetectorRedis implements DetectorRepo {
    private static final Logger logger = LoggerFactory.getLogger(DetectorRedis.class);

    private static String URL = "https://api.apilayer.com/bad_words?";
    // @Value("${bad.word.detecter}")
        String apiKey
            = System.getenv("BAD_WORD_DETECTER");

        @PostConstruct
        public void init() {
            if (Objects.isNull(apiKey))
                System.err.println("BTC_KEY is not set");
        }


    public Detector getResult(User user) throws IOException {
        
 
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;

      
            HttpHeaders headers = new HttpHeaders();
            headers.set("apikey", apiKey);
            HttpEntity request = new HttpEntity(user.getComment(),headers);

            resp = template.exchange(
                    URL,
                    HttpMethod.POST,
                    request,
                    String.class,
                    1);
            logger.info(request.getBody().toString());
            logger.info(resp.getBody().toString());
            if(resp.getStatusCode() == HttpStatus.OK) {
                logger.info("Response Successful");
            } else {
                logger.info("Response Failed");
            }
            Detector d = Detector.createJson(resp.getBody());
            return d;
        
    }
    // public Detecter getResult (User userDetails, Model model) {
    //     String apiKey = "v6IqNa9HxS9zTDKhY9jO406Q37rX936S";
    //     // String apiKey = System.getenv("API_KEY");
    //     System.out.println(userDetails.toString());
    //     System.out.println(userDetails);
    //     String url = "https://api.apilayer.com/bad_words?censor_character=*";
    //     RestTemplate template = new RestTemplate();
    //     RequestEntity<String> req = RequestEntity
    //                             .post(url) 
    //                             .accept(MediaType.TEXT_PLAIN)
    //                             .header("apikey", apiKey)
    //                             .body("fucker");
    //                             // userDetails.getComment();
    //     ResponseEntity<Detecter> resp = template.exchange(req, Detecter.class);

    //     JsonObject obj = new JsonObject(resp.getBody());
    //     // ResponseEntity<String[]> resp = template.exchange(req, String[].class);
    //     logger.info(resp.getStatusCode().toString());
        
    //     logger.info(resp.getBody().toString());
    //     logger.info("testing");
    //     return resp.getBody();
    // }
}
