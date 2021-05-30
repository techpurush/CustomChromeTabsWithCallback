# CustomChromeTabsWithCallback
![alt text](https://github.com/techpurush/CustomChromeTabsWithCallback/blob/master/src3.jpeg?raw=true)

**Library:** 

allprojects {
    repositories {
        google()
        jcenter()

        maven { url 'https://jitpack.io' }

    }
}

dependencies {

    implementation 'com.github.techpurush:CustomChromeTabsWithCallback:1.1.1'

}

**How to use:**

        String url="https://techpurush.com";
        ChromeTabsWithCallback.Builder(getContext())
                .openCustomTab(url, new ChromeTabCallback() {
                    @Override
                    public void serviceConnected() {
                        
                    }

                    @Override
                    public void navigationStarted() {

                    }

                    @Override
                    public void navigationFinished() {

                    }

                    @Override
                    public void navigationFailed() {

                    }

                    @Override
                    public void navigationAborted() {

                    }

                    @Override
                    public void tabClosed() {

                    }

                    @Override
                    public void tabShown() {

                    }
                });

