package ie.a10358323mydbs.my_mobile_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
	
	final static String TABLE_NAME = "usernames";
	final static String USER_NAME = "name";
	final static String PASSWORD = "password";
	final static String _ID = "_id";
	final static String[] columns = { _ID, USER_NAME, PASSWORD};

	final private static String CREATE_CMD = "CREATE TABLE users (" + _ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ USER_NAME + " TEXT NOT NULL,"
			+ PASSWORD + " TEXT NOT NULL,";

	final private static String NAME = "username_db";
	final private static Integer VERSION = 1;
	final private Context mContext;

	public DatabaseOpenHelper(Context context) {
		super(context, NAME, null, VERSION);
		this.mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_CMD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// N/A
	}

	void deleteDatabase() {
		mContext.deleteDatabase(NAME);
	}
}
