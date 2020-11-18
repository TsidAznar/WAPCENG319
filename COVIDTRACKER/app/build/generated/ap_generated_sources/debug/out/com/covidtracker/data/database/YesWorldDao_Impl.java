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
import com.covidtracker.data.database.entity.YesWorld;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class YesWorldDao_Impl extends YesWorldDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfYesWorld;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public YesWorldDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfYesWorld = new EntityInsertionAdapter<YesWorld>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `yesworld`(`cases`,`deaths`,`recovered`,`updated`,`active`,`affectedCountries`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, YesWorld value) {
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
        final String _query = "DELETE FROM yesworld";
        return _query;
      }
    };
  }

  @Override
  void bulkInsert(YesWorld all) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfYesWorld.insert(all);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateAll(YesWorld yesworld) {
    __db.beginTransaction();
    try {
      super.updateAll(yesworld);
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
  public LiveData<YesWorld> getYesWorld() {
    final String _sql = "Select * FROM yesworld";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<YesWorld>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected YesWorld compute() {
        if (_observer == null) {
          _observer = new Observer("yesworld") {
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
          final YesWorld _result;
          if(_cursor.moveToFirst()) {
            _result = new YesWorld();
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
