package rupesh.androidpracticaletwo.Interface;

import org.json.JSONException;

/**
 * Created by Aru on 23-09-2017.
 */

public interface Listner {

    void onSuccess(Object object);
    void onFail(Object object) throws JSONException;
}
