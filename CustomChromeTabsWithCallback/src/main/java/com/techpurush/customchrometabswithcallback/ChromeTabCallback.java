package com.techpurush.customchrometabswithcallback;


public interface ChromeTabCallback {

    void serviceConnected();
    void navigationStarted();
    void navigationFinished();
    void navigationFailed();
    void navigationAborted();
    void tabClosed();
    void tabShown();

}
