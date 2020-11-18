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
public final class WorldDatabase_Impl extends WorldDatabase {
  private volatile WorldDao _worldDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `world` (`cases` INTEGER NOT NULL, `deaths` INTEGER NOT NULL, `recovered` INTEGER NOT NULL, `updated` INTEGER NOT NULL, `active` INTEGER NOT NULL, `affectedCountries` INTEGER NOT NULL, PRIMARY KEY(`cases`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"4beb3dad71faff3fc13cd180e827ed97\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `world`");
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
        final HashMap<String, TableInfo.Column> _columnsWorld = new HashMap<String, TableInfo.Column>(6);
        _columnsWorld.put("cases", new TableInfo.Column("cases", "INTEGER", true, 1));
        _columnsWorld.put("deaths", new TableInfo.Column("deaths", "INTEGER", true, 0));
        _columnsWorld.put("recovered", new TableInfo.Column("recovered", "INTEGER", true, 0));
        _columnsWorld.put("updated", new TableInfo.Column("updated", "INTEGER", true, 0));
        _columnsWorld.put("active", new TableInfo.Column("active", "INTEGER", true, 0));
        _columnsWorld.put("affectedCountries", new TableInfo.Column("affectedCountries", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWorld = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWorld = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWorld = new TableInfo("world", _columnsWorld, _foreignKeysWorld, _indicesWorld);
        final TableInfo _existingWorld = TableInfo.read(_db, "world");
        if (! _infoWorld.equals(_existingWorld)) {
          throw new IllegalStateException("Migration didn't properly handle world(com.covidtracker.data.database.entity.World).\n"
                  + " Expected:\n" + _infoWorld + "\n"
                  + " Found:\n" + _existingWorld);
        }
      }
    }, "4beb3dad71faff3fc13cd180e827ed97", "226b863ee91a085d0c8d051fd2fe48e7");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "world");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `world`");
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
  public WorldDao worldDao() {
    if (_worldDao != null) {
      return _worldDao;
    } else {
      synchronized(this) {
        if(_worldDao == null) {
          _worldDao = new WorldDao_Impl(this);
        }
        return _worldDao;
      }
    }
  }
}
