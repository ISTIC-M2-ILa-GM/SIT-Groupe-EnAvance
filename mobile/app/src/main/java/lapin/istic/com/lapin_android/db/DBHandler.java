package lapin.istic.com.lapin_android.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import lapin.istic.com.lapin_android.model.Point;

/**
 * @author Noureddine KADRI
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
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
                + POINT_X + " REAL  NOT NULL , "
                + POINT_Y + " REAL  NOT NULL , "
                + POINT_Z + " REAL  NOT NULL "
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

    public void addPoint(Point point) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(POINT_INDEX, point.getIndex());
        values.put(POINT_X, point.getX());
        values.put(POINT_Y, (point.getY()));
        values.put(POINT_Z, (point.getZ()));
        // Inserting Row
        db.insert(TABLE_POINT, null, values);
        db.close(); // Closing database connection
    }

    public List<Point> getPoints() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Point> points = new ArrayList<>();
        String query = "SELECT * FROM Point";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        do {
            Point point = new Point();
            point.setIndex(cursor.getInt(1));
            point.setX(cursor.getDouble(2));
            point.setY(cursor.getDouble(3));
            point.setZ(cursor.getDouble(4));
            points.add(point);
        } while (cursor.moveToNext());

        return points;
    }

}
