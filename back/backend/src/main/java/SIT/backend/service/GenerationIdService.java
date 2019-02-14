package SIT.backend.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import SIT.backend.entity.Mission;

@Service
public class GenerationIdService {
	
	private final String TOPIC = "drone";
	// TODO: initialize with the last message ....
	private String body="";

	public void generer(String id_result, String id_mission) throws JSONException {
		
		
		JSONObject body = new JSONObject();
		body.put("to", "/topics/" + TOPIC);
		body.put("priority", "high");
		JSONObject notification = new JSONObject();
		notification.put("title", "Notification");
		notification.put("body", "Mission notification");
		
		// id mission id du point et id de la photo 
		JSONObject data = new JSONObject();
	
		
		data.put("mission_id",id_mission);
		data.put("result_id", id_result);
 
		body.put("notification", notification);
		body.put("data", data);
 
		
/**
		{
		   "notification": {
		      "title": "JSA Notification",
		      "body": "Happy Message!"
		   },
		   "data": {
		      "mission_id": "m_id",
		      "result_id": "point id"
		   },
		   "to": "/topics/JavaSampleApproach",
		   "priority": "high"
		}
*/
				
		this.body = body.toString();
 
		
	}
	

	public String getBody() {
		return body;
	}


}
