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
public final class YesCountriesDatabase_Impl extends YesCountriesDatabase {
  private volatile YesCountriesDao _yesCountriesDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `yescountries` (`country` TEXT NOT NULL, `cases` INTEGER NOT NULL, `active` INTEGER NOT NULL, `todayCases` INTEGER NOT NULL, `deaths` INTEGER NOT NULL, `todayDeaths` INTEGER NOT NULL, `recovered` INTEGER NOT NULL, `tests` INTEGER NOT NULL, `testsPerMillion` INTEGER NOT NULL, `flag` TEXT, `critical` INTEGER NOT NULL, `updated` INTEGER NOT NULL, PRIMARY KEY(`country`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"2e981c9a2f0b72000772b5b4bcb8fea5\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `yescountries`");
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
        final HashMap<String, TableInfo.Column> _columnsYescountries = new HashMap<String, TableInfo.Column>(12);
        _columnsYescountries.put("country", new TableInfo.Column("country", "TEXT", true, 1));
        _columnsYescountries.put("cases", new TableInfo.Column("cases", "INTEGER", true, 0));
        _columnsYescountries.put("active", new TableInfo.Column("active", "INTEGER", true, 0));
        _columnsYescountries.put("todayCases", new TableInfo.Column("todayCases", "INTEGER", true, 0));
        _columnsYescountries.put("deaths", new TableInfo.Column("deaths", "INTEGER", true, 0));
        _columnsYescountries.put("todayDeaths", new TableInfo.Column("todayDeaths", "INTEGER", true, 0));
        _columnsYescountries.put("recovered", new TableInfo.Column("recovered", "INTEGER", true, 0));
        _columnsYescountries.put("tests", new TableInfo.Column("tests", "INTEGER", true, 0));
        _columnsYescountries.put("testsPerMillion", new TableInfo.Column("testsPerMillion", "INTEGER", true, 0));
        _columnsYescountries.put("flag", new TableInfo.Column("flag", "TEXT", false, 0));
        _columnsYescountries.put("critical", new TableInfo.Column("critical", "INTEGER", true, 0));
        _columnsYescountries.put("updated", new TableInfo.Column("updated", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysYescountries = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesYescountries = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoYescountries = new TableInfo("yescountries", _columnsYescountries, _foreignKeysYescountries, _indicesYescountries);
        final TableInfo _existingYescountries = TableInfo.read(_db, "yescountries");
        if (! _infoYescountries.equals(_existingYescountries)) {
          throw new IllegalStateException("Migration didn't properly handle yescountries(com.covidtracker.data.database.entity.YesCountries).\n"
                  + " Expected:\n" + _infoYescountries + "\n"
                  + " Found:\n" + _existingYescountries);
        }
      }
    }, "2e981c9a2f0b72000772b5b4bcb8fea5", "283ce359f5aa615c9e1694c65136d838");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "yescountries");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `yescountries`");
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
  public YesCountriesDao yescountriesDao() {
    if (_yesCountriesDao != null) {
      return _yesCountriesDao;
    } else {
      synchronized(this) {
        if(_yesCountriesDao == null) {
          _yesCountriesDao = new YesCountriesDao_Impl(this);
        }
        return _yesCountriesDao;
      }
    }
  }
}
