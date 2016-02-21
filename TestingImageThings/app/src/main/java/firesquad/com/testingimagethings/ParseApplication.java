package firesquad.com.testingimagethings;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;

/**
 * Created by Noman on 2/20/2016.
 */
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "xhMWTGxOpUDpFssFGW45IxT6W5mg7BLkw8Es5iaj", "uBBQt9UMlT2kMtO6P4uFlCLK36hL9H7WYmbujvIM");
        ParseFacebookUtils.initialize(getApplicationContext());

    }
}
