package vvt2022.miniProject.badWordDetecter.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import vvt2022.miniProject.badWordDetecter.model.Detector;
import vvt2022.miniProject.badWordDetecter.model.User;;

@Service
public class DetectorRedis implements DetectorRepo {
    private static final Logger logger = LoggerFactory.getLogger(DetectorRedis.class);

    @Autowired
    RedisTemplate<String, Detector> redisTemplate;

    private static String URL = "https://api.apilayer.com/bad_words?";

    // @Value("${bad.word.detector}")
        String apiKey
            = System.getenv("BAD_WORD_DETECTOR");

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



    @Override
    public void save(Detector detector, User user) {
        redisTemplate.opsForHash().put(user.getUsername(), detector.getId(), detector);
    }


    @Override
    public void findByUser(Model model, String username) {
        List <Object> arr = new ArrayList<>();
    
        arr = redisTemplate.opsForHash().values(username);
        List <Detector> dArr = arr.stream()
                .map(element->(Detector) element)
                .collect(Collectors.toList());

        logger.info(dArr.toString());

        model.addAttribute("detectorList", dArr);
        
    }

}
