package SIT.backend.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import SIT.backend.entity.MissionResult;

@Service
public class GenerationIdService {
	
	private final String TOPIC = "drone";
	// TODO: initialize with the last message ....
	private String body="";

	public void generer(MissionResult missionResult) throws JSONException {
		
		
		JSONObject body = new JSONObject();
		body.put("to", "/topics/" + TOPIC);
		body.put("priority", "high");
		JSONObject notification = new JSONObject();
		notification.put("title", "Lapin Notification");
		notification.put("body", "Hola Lapin");
		
		// id mission id du point et id de la photo 
		JSONObject data = new JSONObject();
	
		
		data.put("mission_id", missionResult.getId());
		
		for(int i = 0; i< missionResult.getPoints().size();i++) {
			data.put("id point"+i, missionResult.getPoints().get(i).getIndex());
		}
 
		body.put("notification", notification);
		body.put("data", data);
 
		
/**
		{
		   "notification": {
		      "title": "JSA Notification",
		      "body": "Happy Message!"
		   },
		   "data": {
		      "mission id": "m_id",
		      "id point": "point id"
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
