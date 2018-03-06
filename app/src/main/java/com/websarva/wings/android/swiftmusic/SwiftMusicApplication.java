package com.websarva.wings.android.swiftmusic;

import android.app.Application;

/**
 * Activityで利用する際に
 * フィールドで
 * > SwiftMusicApplication *****
 * onCreateで
 * > ***** = context.getApplicationContext();
 *
 * Created by swift01 on 2018/03/06.
 */

public class SwiftMusicApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

    }


}
