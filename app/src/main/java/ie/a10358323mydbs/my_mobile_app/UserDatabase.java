package ie.a10358323mydbs.my_mobile_app;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;

public class UserDatabase extends ListActivity {

	private DatabaseOpenHelper mDbHelper;
	private SimpleCursorAdapter mAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		// Create a new DatabaseHelper
		mDbHelper = new DatabaseOpenHelper(this);

		// start with an empty database
		clearAll();

		// Insert records
		insertAuthors();

		// Create a cursor
		Cursor c = readAuthors();
		mAdapter = new SimpleCursorAdapter(this, R.layout.list_layout, c,
				DatabaseOpenHelper.columns, new int[] { R.id._id, R.id.name, R.id.password,},
				0);

		setListAdapter(mAdapter);

		Button fixButton = (Button) findViewById(R.id.fix_button);
		fixButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// execute database operations
				fix();

				// Redisplay data
				mAdapter.getCursor().requery();
				mAdapter.notifyDataSetChanged();
			}
		});

	}

	// Insert several artist records
	private void insertAuthors() {

		ContentValues values = new ContentValues();

		values.put(DatabaseOpenHelper.USER_NAME, "tom");
		values.put(DatabaseOpenHelper.PASSWORD, "password");
		mDbHelper.getWritableDatabase().insert(DatabaseOpenHelper.TABLE_NAME, null, values);

		values.clear();

		values.put(DatabaseOpenHelper.USER_NAME, "jim");
		values.put(DatabaseOpenHelper.PASSWORD, "password");
		mDbHelper.getWritableDatabase().insert(DatabaseOpenHelper.TABLE_NAME, null, values);

		values.clear();
	}

	// Returns all artist records in the database
	private Cursor readAuthors() {
		return mDbHelper.getWritableDatabase().query(DatabaseOpenHelper.TABLE_NAME,
				DatabaseOpenHelper.columns, null, new String[] {}, null, null,
				null);
	}

	// Modify the contents of the database
	private void fix() {

		// Sorry Terry Pratchet :-(
		mDbHelper.getWritableDatabase().delete(DatabaseOpenHelper.TABLE_NAME,
				DatabaseOpenHelper.USER_NAME + "=?",
				new String[] { "tom" });

		// fix the George
		ContentValues values = new ContentValues();
		values.put(DatabaseOpenHelper.USER_NAME, "tom");

		mDbHelper.getWritableDatabase().update(DatabaseOpenHelper.TABLE_NAME, values,
				DatabaseOpenHelper.USER_NAME + "=?",
				new String[] { "tom" });

	}

	// Delete all records
	private void clearAll() {

		mDbHelper.getWritableDatabase().delete(DatabaseOpenHelper.TABLE_NAME, null, null);

	}

	// Close database
	@Override
	protected void onDestroy() {

		mDbHelper.getWritableDatabase().close();
		mDbHelper.deleteDatabase();

		super.onDestroy();

	}
}