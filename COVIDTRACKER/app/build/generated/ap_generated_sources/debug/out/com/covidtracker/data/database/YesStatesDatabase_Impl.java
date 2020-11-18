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
public final class YesStatesDatabase_Impl extends YesStatesDatabase {
  private volatile YesStateDao _yesStateDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `yesstates` (`state` TEXT NOT NULL, `cases` INTEGER NOT NULL, `todayCases` INTEGER NOT NULL, `deaths` INTEGER NOT NULL, `todayDeaths` INTEGER NOT NULL, `active` INTEGER NOT NULL, `tests` INTEGER NOT NULL, `testsPerMillion` INTEGER NOT NULL, PRIMARY KEY(`state`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"66e9acaebc22e9606916844a28c12d0c\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `yesstates`");
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
        final HashMap<String, TableInfo.Column> _columnsYesstates = new HashMap<String, TableInfo.Column>(8);
        _columnsYesstates.put("state", new TableInfo.Column("state", "TEXT", true, 1));
        _columnsYesstates.put("cases", new TableInfo.Column("cases", "INTEGER", true, 0));
        _columnsYesstates.put("todayCases", new TableInfo.Column("todayCases", "INTEGER", true, 0));
        _columnsYesstates.put("deaths", new TableInfo.Column("deaths", "INTEGER", true, 0));
        _columnsYesstates.put("todayDeaths", new TableInfo.Column("todayDeaths", "INTEGER", true, 0));
        _columnsYesstates.put("active", new TableInfo.Column("active", "INTEGER", true, 0));
        _columnsYesstates.put("tests", new TableInfo.Column("tests", "INTEGER", true, 0));
        _columnsYesstates.put("testsPerMillion", new TableInfo.Column("testsPerMillion", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysYesstates = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesYesstates = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoYesstates = new TableInfo("yesstates", _columnsYesstates, _foreignKeysYesstates, _indicesYesstates);
        final TableInfo _existingYesstates = TableInfo.read(_db, "yesstates");
        if (! _infoYesstates.equals(_existingYesstates)) {
          throw new IllegalStateException("Migration didn't properly handle yesstates(com.covidtracker.data.database.entity.YesStates).\n"
                  + " Expected:\n" + _infoYesstates + "\n"
                  + " Found:\n" + _existingYesstates);
        }
      }
    }, "66e9acaebc22e9606916844a28c12d0c", "dfc5556a3f333bb438f4474771babd64");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "yesstates");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `yesstates`");
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
  public YesStateDao yesstateDao() {
    if (_yesStateDao != null) {
      return _yesStateDao;
    } else {
      synchronized(this) {
        if(_yesStateDao == null) {
          _yesStateDao = new YesStateDao_Impl(this);
        }
        return _yesStateDao;
      }
    }
  }
}
