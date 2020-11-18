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
public final class StatesDatabase_Impl extends StatesDatabase {
  private volatile StateDao _stateDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `states` (`state` TEXT NOT NULL, `cases` INTEGER NOT NULL, `todayCases` INTEGER NOT NULL, `deaths` INTEGER NOT NULL, `todayDeaths` INTEGER NOT NULL, `active` INTEGER NOT NULL, `tests` INTEGER NOT NULL, `testsPerMillion` INTEGER NOT NULL, PRIMARY KEY(`state`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"3650a38edd856d9614429e1111535868\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `states`");
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
        final HashMap<String, TableInfo.Column> _columnsStates = new HashMap<String, TableInfo.Column>(8);
        _columnsStates.put("state", new TableInfo.Column("state", "TEXT", true, 1));
        _columnsStates.put("cases", new TableInfo.Column("cases", "INTEGER", true, 0));
        _columnsStates.put("todayCases", new TableInfo.Column("todayCases", "INTEGER", true, 0));
        _columnsStates.put("deaths", new TableInfo.Column("deaths", "INTEGER", true, 0));
        _columnsStates.put("todayDeaths", new TableInfo.Column("todayDeaths", "INTEGER", true, 0));
        _columnsStates.put("active", new TableInfo.Column("active", "INTEGER", true, 0));
        _columnsStates.put("tests", new TableInfo.Column("tests", "INTEGER", true, 0));
        _columnsStates.put("testsPerMillion", new TableInfo.Column("testsPerMillion", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStates = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStates = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStates = new TableInfo("states", _columnsStates, _foreignKeysStates, _indicesStates);
        final TableInfo _existingStates = TableInfo.read(_db, "states");
        if (! _infoStates.equals(_existingStates)) {
          throw new IllegalStateException("Migration didn't properly handle states(com.covidtracker.data.database.entity.States).\n"
                  + " Expected:\n" + _infoStates + "\n"
                  + " Found:\n" + _existingStates);
        }
      }
    }, "3650a38edd856d9614429e1111535868", "6c9d12700d7f863895ca4c511181a4f4");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "states");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `states`");
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
  public StateDao stateDao() {
    if (_stateDao != null) {
      return _stateDao;
    } else {
      synchronized(this) {
        if(_stateDao == null) {
          _stateDao = new StateDao_Impl(this);
        }
        return _stateDao;
      }
    }
  }
}
