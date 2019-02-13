package lapin.istic.com.lapin_android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Noureddine KADRI
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 0;
    private static final String DATABASE_NAME = "LapinDB.db";

    private static final String TABLE_POINT = "Point";
    private static final String POINT_ID = "pointID";
    private static final String POINT_INDEX = "pointIndex";
    private static final String POINT_X = "pointX";
    private static final String POINT_Y = "pointY";
    private static final String POINT_Z = "pointZ";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_TEAM = "CREATE TABLE "
                + TABLE_POINT + " ( "
                + POINT_ID + " " +
                "INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + POINT_INDEX + " INTEGER UNIQUE NOT NULL , "
                + POINT_X + " INTEGER  NOT NULL , "
                + POINT_Y + " INTEGER  NOT NULL , "
                + POINT_Z + " INTEGER  NOT NULL "
                + " );";
        db.execSQL(CREATE_TABLE_TEAM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POINT);
        // Creating tables again
        onCreate(db);
    }
}
