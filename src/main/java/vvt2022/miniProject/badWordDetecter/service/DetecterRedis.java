package vvt2022.miniProject.badWordDetecter.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import vvt2022.miniProject.badWordDetecter.model.*;;

@Service
public class DetecterRedis implements DetecterRepo {
    private static final Logger logger = LoggerFactory.getLogger(DetecterRedis.class);

    private static String URL = "https://api.apilayer.com/bad_words";

    @Value ("${bad.word.detecter}")
    private String apiKey;
    private boolean hasKey;

    @PostConstruct
    private void init() {
        hasKey = null != apiKey;
        logger.info(">>> API KEY set " + apiKey);
    }

    public Optional<Detecter> getResult (String centent) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "body");
    
        Request request = new Request.Builder()
          .url("https://api.apilayer.com/bad_words?censor_character=censor_character")
          .addHeader("apikey", "v6IqNa9HxS9zTDKhY9jO406Q37rX936S")
          .method("POST", body})
          .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    
        return null;

    }

//     @Autowired
//     @Qualifier("detect")
//     RedisTemplate<String, Detecter> redisTemplate;

//     @Override
//     public void save(final Detecter user) {
//         logger.info("Save user > " + logger);
//         redisTemplate.opsForValue().set(user.getId(), user);
//         Detecter result = (Detecter) redisTemplate.opsForValue().get(user.getId());
//     }

//     // @Override
//     // public Detecter findById(final String userId) {
//     //     logger.info("find user by id> " + userId);
//     //     Detecter result = (Detecter) redisTemplate.opsForValue().get(userId);
//     //     return result;
//     // }

//     // @Override
//     // public int update(final Detecter user) {
//     //     logger.info("Save mastermind > " + logger);
//     //     if (user.isUpsert())
//     //         redisTemplate.opsForValue().setIfAbsent(user.getId(), user);
//     //     else
//     //         redisTemplate.opsForValue().setIfPresent(user.getId(), user);
//     //         Detecter result = (Detecter) redisTemplate.opsForValue().get(user.getId());
//     //     if (result != null)
//     //         return 1;
//     //     return 0;
//     // }

//     // public Set<String> searchKeys(String index) {
//     //     String pattern = "*%s*".formatted(index);
//     //     return redisTemplate.keys(pattern);
//     // }

//     // public Detecter[] getAllMasterMind() {
//     //     Set<String> allMastermindKeys = redisTemplate.keys("*");
//     //     List<Detecter> mArr = new LinkedList<Detecter>();
//     //     for (String mastermindKey : allMastermindKeys) {
//     //         Detecter result = (Detecter) redisTemplate.opsForValue().get(mastermindKey);
//     //         mArr.add(result);
//     //     }

//     //     return mArr.toArray(new Detecter[mArr.size()]);
//     // }

}
