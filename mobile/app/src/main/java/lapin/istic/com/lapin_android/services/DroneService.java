package lapin.istic.com.lapin_android.services;

import java.util.List;
import lapin.istic.com.lapin_android.model.DronePath;
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

    @POST("/api/mission")
    Call<Integer> createMission(@Body DronePath mission);

    @GET("/api/mision/result/{id}")
    Call<List<Integer>> getResultsId(@Path("id") Integer id);

    @GET("/api/mission/{id}/result/{index}")
    Call<ResponseBody> getImage(@Path("id") Integer id, @Path("index") Integer index);

    @POST("/sendMission")
    Call<DronePath> sendMission(@Body DronePath dronePath);
}
