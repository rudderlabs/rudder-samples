package com.rudderstack.test;

import android.app.Application;
import android.util.Log;

import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.rudderstack.android.sdk.core.RudderClient;
import com.rudderstack.android.sdk.core.RudderConfig;
import com.rudderstack.android.sdk.core.RudderOption;
import com.rudderstack.android.sdk.core.RudderTraits;

import java.util.Map;

public class MainApplication extends Application {
    public static final String AF_DEV_KEY = "af_api_key";
    public static final String WRITE_KEY = "rudder_write_key";
    public static final String DATA_PLANE_URL = "rudder_data_plane_url";

    @Override
    public void onCreate() {
        super.onCreate();

        AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {
            @Override
            public void onConversionDataSuccess(Map<String, Object> conversionData) {

                for (String attrName : conversionData.keySet()) {
                    Log.d("LOG_TAG", "attribute: " + attrName + " = " + conversionData.get(attrName));
                }
            }

            @Override
            public void onConversionDataFail(String errorMessage) {
                Log.d("LOG_TAG", "error getting conversion data: " + errorMessage);
            }

            @Override
            public void onAppOpenAttribution(Map<String, String> attributionData) {
                for (String attrName : attributionData.keySet()) {
                    Log.d("LOG_TAG", "attribute: " + attrName + " = " + attributionData.get(attrName));
                }
            }

            @Override
            public void onAttributionFailure(String errorMessage) {
                Log.d("LOG_TAG", "error onAttributionFailure : " + errorMessage);
            }
        };

        // Initiate the AppsFlyer SDK
        AppsFlyerLib.getInstance().init(AF_DEV_KEY, conversionListener, this);

        // Once AF SDK is initialized, fetch the appsFlyerID
        // This is an important piece for cloud-mode to work
        String appsFlyerId = AppsFlyerLib.getInstance().getAppsFlyerUID(this);

        // Initiate Rudder after getting the AppsFlyer ID using the AppsFlyer SDK
        RudderClient rudderClient = RudderClient.getInstance(
                this,
                WRITE_KEY,
                new RudderConfig.Builder()
                        .withDataPlaneUrl(DATA_PLANE_URL)
                        .withTrackLifecycleEvents(true)
                        .withRecordScreenViews(true)
                        .build()
        );

        // Make an identify call with the externalID so that it's persisted and sent across to
        // all successive calls
        rudderClient.identify(
                "sample_user_id",
                new RudderTraits().putFirstName("First Name"),
                new RudderOption()
                        .putExternalId("appsflyerExternalId", appsFlyerId)
        );

        // Now you can send the event to RudderStack cloud-mode AppsFlyer destination
        rudderClient.track("New Event");
    }
}
