package SIT.backend.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class androidPushNotificationsService {

	private static final String FIREBASE_SERVER_KEY = "AAAAT6Ud1G4:APA91bFAK-WdBAIO4ThV_vqb61IqvNM7Kc8cSEszxe2ID1wayuAnk5oYlVE4EWo_D8w859pT0BVXUkiFL-OhbI04f46Fvd4vNDb8irr0gwfHPIojKTGDTA3sj_4Q_SsvA686IR4nEhHy";
	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
	
	@Async
	public CompletableFuture<String> send(HttpEntity<String> entity) {
 
		RestTemplate restTemplate = new RestTemplate();
 
		/**
		https://fcm.googleapis.com/fcm/send
		Content-Type:application/json
		Authorization:key=FIREBASE_SERVER_KEY*/
 
		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new headerRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
		interceptors.add(new headerRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);
 
		String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);
 
		return CompletableFuture.completedFuture(firebaseResponse);
	}
}
