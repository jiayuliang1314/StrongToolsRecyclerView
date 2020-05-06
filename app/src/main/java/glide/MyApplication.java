package glide;

import android.app.Application;

public class MyApplication extends Application {
    public static MyApplication myApplication;
    public static MyApplication getInstance(){
        return myApplication;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        myApplication=this;
    }
}
