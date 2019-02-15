package lapin.istic.com.lapin_android.model;

import lapin.istic.com.lapin_android.services.DroneService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Noureddine KADRI
 */
public class ApiManager {

    private static DroneService service;
    final static ApiManager apiManager = new ApiManager();

    private ApiManager() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DroneService.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(DroneService.class);
    }

    public static ApiManager getInstance() {
        return apiManager;
    }

    public void createMissionApi(DronePath mission, Callback<String> callback) {
        Call<String> userCall = service.sendMission(mission);
        userCall.enqueue(callback);
    }

    public void getResult(String missionId, String resultId, Callback<Result> callback){
        Call<Result> resultCall = service.getResult(resultId, missionId);
        resultCall.enqueue(callback);
    }
}