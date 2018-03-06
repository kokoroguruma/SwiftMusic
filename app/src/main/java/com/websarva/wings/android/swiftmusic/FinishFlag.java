package com.websarva.wings.android.swiftmusic;

/**
 * 利用対象Activityで
 * public static FinishFlag *****
 * として宣言する事。
 *
 * onRestart() {}
 * メソッド（override）にて
 * > super.onRestart();
 * > if(*****.isFlag() { finish89; }
 * 戻ってきて再表示されたときに値しだいで終わらせられる。
 *
 * 利用時は、利用Activity.*****.setFlag(boolean)
 * で設定をすることが出来る。
 *
 * Created by hotta on 2018/03/06.
 */

public class FinishFlag {
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
