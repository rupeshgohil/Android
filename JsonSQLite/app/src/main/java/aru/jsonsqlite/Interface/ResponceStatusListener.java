package aru.jsonsqlite.Interface;

import org.json.JSONObject;

/**
 * Created by Aru on 02-04-2018.
 */

public interface ResponceStatusListener {
    public void OnSucess(JSONObject jobj);
    public void OnFail(JSONObject jobj);
}
