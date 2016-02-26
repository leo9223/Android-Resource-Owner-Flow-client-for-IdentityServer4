package abubakar.IDSvr4ROClientDroid;

import org.json.JSONObject;

/**
 * Created by Abubakar on 25-Feb-16.
 */
public class jsonManipulation {
    public static String getAttrFromJson(String json, String attribute){
        try{
            return (new JSONObject(json)).getString(attribute);
        }
        catch (Exception e){
            return "{Exception: " + e.getMessage() +"}";
        }
    }
}
