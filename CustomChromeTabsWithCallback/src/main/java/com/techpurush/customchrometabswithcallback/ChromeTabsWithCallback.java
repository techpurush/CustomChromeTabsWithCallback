package com.techpurush.customchrometabswithcallback;

import android.app.Activity;
import android.content.ComponentName;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsCallback;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;
import androidx.core.content.ContextCompat;


public class ChromeTabsWithCallback {

    static CustomTabsClient mClient = null;
    static String packageName = "com.android.chrome";
    Activity activity;

    public static ChromeTabsWithCallback Builder(Activity context) {

        ChromeTabsWithCallback chromeTabsWithCallback = new ChromeTabsWithCallback();
        chromeTabsWithCallback.activity = context;

        return chromeTabsWithCallback;

    }

    public void openCustomTab(String url, ChromeTabCallback chromeTabCallback) {
        // package name is the default package
        // for our custom chrome tab

        CustomTabsIntent.Builder customTabsIntent = new CustomTabsIntent.Builder();


        // below line is setting toolbar color
        // for our custom chrome tab.
        customTabsIntent.setToolbarColor(ContextCompat.getColor(activity, R.color.AliceBlue));

        Uri uri = Uri.parse(url);

        try {


            // Binds to the service.
            CustomTabsClient.bindCustomTabsService(activity, packageName, new CustomTabsServiceConnection() {
                @Override
                public void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client) {
                    // mClient is now valid.
                    mClient = client;

                    // With a valid mClient.
                    mClient.warmup(0);

                    // With a valid mClient.
                    CustomTabsSession session = mClient.newSession(new CustomTabsCallback() {
                        @Override
                        public void onNavigationEvent(int navigationEvent, @Nullable Bundle extras) {
                            super.onNavigationEvent(navigationEvent, extras);

                            switch (navigationEvent) {

                                case CustomTabsCallback.NAVIGATION_ABORTED:

                                    chromeTabCallback.navigationAborted();
                                    //DialogUtils.tst(activity, "NAVIGATION_ABORTED");
                                    break;
                                case CustomTabsCallback.NAVIGATION_FAILED:
                                    chromeTabCallback.navigationFailed();

                                    //DialogUtils.tst(activity, "NAVIGATION_FAILED");
                                    break;
                                case CustomTabsCallback.NAVIGATION_FINISHED:

                                    chromeTabCallback.navigationFinished();

                                    //DialogUtils.tst(activity, "NAVIGATION_FINISHED");
                                    break;
                                case CustomTabsCallback.NAVIGATION_STARTED:
                                    chromeTabCallback.navigationStarted();

                                    //DialogUtils.tst(activity, "NAVIGATION_STARTED");
                                    break;
                                case CustomTabsCallback.TAB_HIDDEN:

                                    chromeTabCallback.tabClosed();

                                    //DialogUtils.tst(activity, "TAB_HIDDEN");
                                    break;
                                case CustomTabsCallback.TAB_SHOWN:

                                    chromeTabCallback.tabShown();

                                    //DialogUtils.tst(activity, "TAB_SHOWN");
                                    break;

                            }


                        }
                    });

                    customTabsIntent.setSession(session);


                    // session.mayLaunchUrl(uri, null, null);

                    //DialogUtils.tst(activity, "Service Connected");

                    chromeTabCallback.serviceConnected();

                    // Shows the Custom Tab
                    customTabsIntent.build().launchUrl(activity, uri);

                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    // mClient is no longer valid. This also invalidates sessions.
                    mClient = null;

                    //DialogUtils.tst(activity, "Service Disconnected");

                }
            });


           /* if (packageName != null) {

                // we are checking if the package name is not null
                // if package name is not null then we are calling
                // that custom chrome tab with intent by passing its
                // package name.

                customTabsIntent.intent.setPackage(packageName);
                customTabsIntent.intent.setData(uri);
                startActivityForResult(customTabsIntent.intent, CHROME_CUSTOM_TAB_REQUEST_CODE);

                // in that custom tab intent we are passing
                // our url which we have to browse.
                //customTabsIntent.launchUrl(activity, uri);
            } else {
                // if the custom tabs fails to load then we are simply
                // redirecting our user to users device default browser.
                activity.startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }*/

        } catch (Exception e) {

        }

    }


}
