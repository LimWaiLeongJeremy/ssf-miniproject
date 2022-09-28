package vvt2022.miniProject.badWordDetecter.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vvt2022.miniProject.badWordDetecter.model.Detector;
import vvt2022.miniProject.badWordDetecter.service.DetectorRedis;

@RestController
@RequestMapping(path = "/api/detector", produces = MediaType.APPLICATION_JSON_VALUE)
public class DetectorRestControllor {
	private static final Logger logger = LoggerFactory.getLogger(DetectorRestControllor.class);

	@Autowired
	private DetectorRedis svc;

	@GetMapping(path="/{username}")
	public ResponseEntity<String> getHistoryByUser(@PathVariable(name="username") String username) {
		Optional<List<Detector>> opt = svc.get(username);
		if (opt.isEmpty()) {
			JsonObject payload = Json.createObjectBuilder()
				.add("error", "Cannot find user %s".formatted(username))
				.build();
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(payload.toString());
		}
		List<Detector> userHistory = opt.get();
		logger.info(userHistory.toString());
		return ResponseEntity.ok(userHistory.toString());
	}
}
