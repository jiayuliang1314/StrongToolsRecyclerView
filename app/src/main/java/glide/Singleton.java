package glide;

import android.content.Context;

/**
 * Created by yuchaofei on 2018/2/23.
 */

public abstract class Singleton<T> {
    private T mInstance;

    protected abstract T create(Context context);

    public final T get(Context context) {
        synchronized (this) {
            if (mInstance == null) {
                mInstance = create(context);
            }
            return mInstance;
        }
    }

    public void destroy() {
        synchronized (this) {
            mInstance = null;
        }
    }
}

