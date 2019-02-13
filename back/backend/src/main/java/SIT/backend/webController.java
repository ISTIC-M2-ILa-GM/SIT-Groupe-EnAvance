package SIT.backend;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import SIT.backend.service.GenerationIdService;
import SIT.backend.service.androidPushNotificationsService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class webController {
	
	@Autowired
	androidPushNotificationsService androidPushNotificationsService;
	
	@Autowired
	GenerationIdService generationIdService;
 
	@RequestMapping(value = "/send", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> send() throws JSONException {
 	
		String body = generationIdService.getBody();
		HttpEntity<String> request = new HttpEntity<>(body);
 
		CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
		CompletableFuture.allOf(pushNotification).join();
 
		try {
			String firebaseResponse = pushNotification.get();
			
			return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
 
		return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	}
}
