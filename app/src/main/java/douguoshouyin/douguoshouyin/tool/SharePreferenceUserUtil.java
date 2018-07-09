package douguoshouyin.douguoshouyin.tool;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceUserUtil {

	private SharedPreferences sp;
	private SharedPreferences.Editor editor;


	public SharePreferenceUserUtil(Context context, String file) {
		sp = context.getSharedPreferences(file, Context.MODE_PRIVATE);
		editor = sp.edit();
	}


	public void put(String key, String value) {
		editor.putString("put_" + key, value);
		editor.commit();
	}

	public String get(String key) {
		return sp.getString("put_" + key, "");
	}

	// UserID
	public String getUserId() {
		return sp.getString("_user_id", "");

	}

	public void setUserId(String uid) {
		editor.putString("_user_id", uid);
		editor.commit();

	}


}