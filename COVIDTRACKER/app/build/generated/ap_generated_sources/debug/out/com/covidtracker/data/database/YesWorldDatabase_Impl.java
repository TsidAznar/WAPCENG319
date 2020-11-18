package com.covidtracker.data.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class YesWorldDatabase_Impl extends YesWorldDatabase {
  private volatile YesWorldDao _yesWorldDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `yesworld` (`cases` INTEGER NOT NULL, `deaths` INTEGER NOT NULL, `recovered` INTEGER NOT NULL, `updated` INTEGER NOT NULL, `active` INTEGER NOT NULL, `affectedCountries` INTEGER NOT NULL, PRIMARY KEY(`cases`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"e2028aebbe84cb2c63610ee359294a72\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `yesworld`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsYesworld = new HashMap<String, TableInfo.Column>(6);
        _columnsYesworld.put("cases", new TableInfo.Column("cases", "INTEGER", true, 1));
        _columnsYesworld.put("deaths", new TableInfo.Column("deaths", "INTEGER", true, 0));
        _columnsYesworld.put("recovered", new TableInfo.Column("recovered", "INTEGER", true, 0));
        _columnsYesworld.put("updated", new TableInfo.Column("updated", "INTEGER", true, 0));
        _columnsYesworld.put("active", new TableInfo.Column("active", "INTEGER", true, 0));
        _columnsYesworld.put("affectedCountries", new TableInfo.Column("affectedCountries", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysYesworld = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesYesworld = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoYesworld = new TableInfo("yesworld", _columnsYesworld, _foreignKeysYesworld, _indicesYesworld);
        final TableInfo _existingYesworld = TableInfo.read(_db, "yesworld");
        if (! _infoYesworld.equals(_existingYesworld)) {
          throw new IllegalStateException("Migration didn't properly handle yesworld(com.covidtracker.data.database.entity.YesWorld).\n"
                  + " Expected:\n" + _infoYesworld + "\n"
                  + " Found:\n" + _existingYesworld);
        }
      }
    }, "e2028aebbe84cb2c63610ee359294a72", "7c83f69c9ad11cf2ff864ccbb27b2a63");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "yesworld");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `yesworld`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public YesWorldDao yesworldDao() {
    if (_yesWorldDao != null) {
      return _yesWorldDao;
    } else {
      synchronized(this) {
        if(_yesWorldDao == null) {
          _yesWorldDao = new YesWorldDao_Impl(this);
        }
        return _yesWorldDao;
      }
    }
  }
}
