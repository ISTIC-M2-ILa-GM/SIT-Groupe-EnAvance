package lapin.istic.com.lapin_android.model;

import android.app.Application;

/**
 * @author Noureddine KADRI
 */
public class MainApplication extends Application {
    public static ApiManager apiManager;

    @Override
    public void onCreate() {
        super.onCreate();
        apiManager = ApiManager.getInstance();
    }
}
