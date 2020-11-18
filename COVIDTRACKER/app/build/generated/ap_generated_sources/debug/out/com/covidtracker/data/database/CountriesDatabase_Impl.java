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
public final class CountriesDatabase_Impl extends CountriesDatabase {
  private volatile CountriesDao _countriesDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `countries` (`country` TEXT NOT NULL, `cases` INTEGER NOT NULL, `active` INTEGER NOT NULL, `todayCases` INTEGER NOT NULL, `deaths` INTEGER NOT NULL, `todayDeaths` INTEGER NOT NULL, `recovered` INTEGER NOT NULL, `tests` INTEGER NOT NULL, `testsPerMillion` INTEGER NOT NULL, `flag` TEXT, `critical` INTEGER NOT NULL, `updated` INTEGER NOT NULL, PRIMARY KEY(`country`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"a00aedd8658dfa224923c222d4be528e\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `countries`");
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
        final HashMap<String, TableInfo.Column> _columnsCountries = new HashMap<String, TableInfo.Column>(12);
        _columnsCountries.put("country", new TableInfo.Column("country", "TEXT", true, 1));
        _columnsCountries.put("cases", new TableInfo.Column("cases", "INTEGER", true, 0));
        _columnsCountries.put("active", new TableInfo.Column("active", "INTEGER", true, 0));
        _columnsCountries.put("todayCases", new TableInfo.Column("todayCases", "INTEGER", true, 0));
        _columnsCountries.put("deaths", new TableInfo.Column("deaths", "INTEGER", true, 0));
        _columnsCountries.put("todayDeaths", new TableInfo.Column("todayDeaths", "INTEGER", true, 0));
        _columnsCountries.put("recovered", new TableInfo.Column("recovered", "INTEGER", true, 0));
        _columnsCountries.put("tests", new TableInfo.Column("tests", "INTEGER", true, 0));
        _columnsCountries.put("testsPerMillion", new TableInfo.Column("testsPerMillion", "INTEGER", true, 0));
        _columnsCountries.put("flag", new TableInfo.Column("flag", "TEXT", false, 0));
        _columnsCountries.put("critical", new TableInfo.Column("critical", "INTEGER", true, 0));
        _columnsCountries.put("updated", new TableInfo.Column("updated", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCountries = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCountries = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCountries = new TableInfo("countries", _columnsCountries, _foreignKeysCountries, _indicesCountries);
        final TableInfo _existingCountries = TableInfo.read(_db, "countries");
        if (! _infoCountries.equals(_existingCountries)) {
          throw new IllegalStateException("Migration didn't properly handle countries(com.covidtracker.data.database.entity.Countries).\n"
                  + " Expected:\n" + _infoCountries + "\n"
                  + " Found:\n" + _existingCountries);
        }
      }
    }, "a00aedd8658dfa224923c222d4be528e", "f6e51e56af28e3fdc2ec6ad870393524");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "countries");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `countries`");
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
  public CountriesDao countriesDao() {
    if (_countriesDao != null) {
      return _countriesDao;
    } else {
      synchronized(this) {
        if(_countriesDao == null) {
          _countriesDao = new CountriesDao_Impl(this);
        }
        return _countriesDao;
      }
    }
  }
}
