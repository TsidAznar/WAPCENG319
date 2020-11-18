package com.covidtracker.data.database;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker.Observer;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.covidtracker.data.database.entity.World;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class WorldDao_Impl extends WorldDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfWorld;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public WorldDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWorld = new EntityInsertionAdapter<World>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `world`(`cases`,`deaths`,`recovered`,`updated`,`active`,`affectedCountries`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, World value) {
        stmt.bindLong(1, value.cases);
        stmt.bindLong(2, value.deaths);
        stmt.bindLong(3, value.recovered);
        stmt.bindLong(4, value.updated);
        stmt.bindLong(5, value.active);
        stmt.bindLong(6, value.affectedCountries);
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM world";
        return _query;
      }
    };
  }

  @Override
  void bulkInsert(World all) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfWorld.insert(all);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateAll(World world) {
    __db.beginTransaction();
    try {
      super.updateAll(world);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  void deleteAll() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public LiveData<World> getWorld() {
    final String _sql = "Select * FROM world";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<World>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected World compute() {
        if (_observer == null) {
          _observer = new Observer("world") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfCases = _cursor.getColumnIndexOrThrow("cases");
          final int _cursorIndexOfDeaths = _cursor.getColumnIndexOrThrow("deaths");
          final int _cursorIndexOfRecovered = _cursor.getColumnIndexOrThrow("recovered");
          final int _cursorIndexOfUpdated = _cursor.getColumnIndexOrThrow("updated");
          final int _cursorIndexOfActive = _cursor.getColumnIndexOrThrow("active");
          final int _cursorIndexOfAffectedCountries = _cursor.getColumnIndexOrThrow("affectedCountries");
          final World _result;
          if(_cursor.moveToFirst()) {
            _result = new World();
            _result.cases = _cursor.getInt(_cursorIndexOfCases);
            _result.deaths = _cursor.getInt(_cursorIndexOfDeaths);
            _result.recovered = _cursor.getInt(_cursorIndexOfRecovered);
            _result.updated = _cursor.getLong(_cursorIndexOfUpdated);
            _result.active = _cursor.getInt(_cursorIndexOfActive);
            _result.affectedCountries = _cursor.getInt(_cursorIndexOfAffectedCountries);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }
}
