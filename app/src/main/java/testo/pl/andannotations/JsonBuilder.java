package testo.pl.andannotations;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Lukasz Marczak on 2015-07-19.
 * JsonBuilder makes output jsons
 */
public class JsonBuilder {
    public static final String TAG = JsonBuilder.class.getSimpleName();

    public static String update(String previousData,String sponsorUser, String value) { // value has format "15"
        try {
            JSONObject jsonObject = new JSONObject(previousData);
            JSONArray contacts = jsonObject.getJSONArray("results");
            int j = 0;
            for (String user : Config.USERS) {
                String currentValue = contacts.getJSONObject(j).getString(user);
                if (user.equals(sponsorUser)) {
                    Float result_of_equation = Float.valueOf(value) + Float.valueOf(currentValue);
                    JSONObject updatedJSON = jsonObject
                            .getJSONArray("results")
                            .getJSONObject(j)
                            .put(user, result_of_equation.toString());
                    String braceLeft = "{";
                    String braceRight = "}";
                    String quota = "\"";
                    String dots = ":";
                    String str2Replace = braceLeft + quota + user + quota + dots + quota + currentValue + quota + braceRight;

                    String newJson = updatedJSON.toString();
                    Log.d(TAG, "previous JSON: " + str2Replace);
                    Log.d(TAG, "updated JSON: " + newJson);

                    Log.i(TAG, "previous GET JSON: " + previousData.replaceAll(" ", ""));
                    String res = previousData.replaceAll(" ", "");
                    res = res.replace(str2Replace, newJson);
                    Log.i(TAG, "updated POST JSON: " + res);
                    return res;
                }
                ++j;
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return "{ \"results\" : { \"NaN\"}}";
    }
}