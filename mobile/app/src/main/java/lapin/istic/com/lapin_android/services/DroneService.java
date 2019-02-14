package lapin.istic.com.lapin_android.services;

import java.util.List;
import lapin.istic.com.lapin_android.model.DronePath;
import lapin.istic.com.lapin_android.model.Result;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author DESCHAMPS Mathieu
 */
public interface DroneService {

    public static final String END_POINT = "http://localhost:8080";


    @GET("/api/mission/{missionId/result/{id}")
    Call<Result> getResult(@Path("misionId") String missionId, @Path("id") String id);

    @POST("/sendMission")
    Call<String> sendMission(@Body DronePath dronePath);
}
