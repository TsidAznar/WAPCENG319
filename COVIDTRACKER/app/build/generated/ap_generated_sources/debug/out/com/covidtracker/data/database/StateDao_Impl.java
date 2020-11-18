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
import com.covidtracker.data.database.entity.States;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class StateDao_Impl extends StateDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfStates;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public StateDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStates = new EntityInsertionAdapter<States>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `states`(`state`,`cases`,`todayCases`,`deaths`,`todayDeaths`,`active`,`tests`,`testsPerMillion`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, States value) {
        if (value.state == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.state);
        }
        stmt.bindLong(2, value.cases);
        stmt.bindLong(3, value.todayCases);
        stmt.bindLong(4, value.deaths);
        stmt.bindLong(5, value.todayDeaths);
        stmt.bindLong(6, value.active);
        stmt.bindLong(7, value.tests);
        stmt.bindLong(8, value.testsPerMillion);
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM states";
        return _query;
      }
    };
  }

  @Override
  void bulkInsert(List<States> states) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfStates.insert(states);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateAll(List<States> states) {
    __db.beginTransaction();
    try {
      super.updateAll(states);
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
  public LiveData<List<States>> getStatesList() {
    final String _sql = "Select * FROM states ORDER BY state ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<States>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<States> compute() {
        if (_observer == null) {
          _observer = new Observer("states") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfState = _cursor.getColumnIndexOrThrow("state");
          final int _cursorIndexOfCases = _cursor.getColumnIndexOrThrow("cases");
          final int _cursorIndexOfTodayCases = _cursor.getColumnIndexOrThrow("todayCases");
          final int _cursorIndexOfDeaths = _cursor.getColumnIndexOrThrow("deaths");
          final int _cursorIndexOfTodayDeaths = _cursor.getColumnIndexOrThrow("todayDeaths");
          final int _cursorIndexOfActive = _cursor.getColumnIndexOrThrow("active");
          final int _cursorIndexOfTests = _cursor.getColumnIndexOrThrow("tests");
          final int _cursorIndexOfTestsPerMillion = _cursor.getColumnIndexOrThrow("testsPerMillion");
          final List<States> _result = new ArrayList<States>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final States _item;
            _item = new States();
            _item.state = _cursor.getString(_cursorIndexOfState);
            _item.cases = _cursor.getInt(_cursorIndexOfCases);
            _item.todayCases = _cursor.getInt(_cursorIndexOfTodayCases);
            _item.deaths = _cursor.getInt(_cursorIndexOfDeaths);
            _item.todayDeaths = _cursor.getInt(_cursorIndexOfTodayDeaths);
            _item.active = _cursor.getInt(_cursorIndexOfActive);
            _item.tests = _cursor.getInt(_cursorIndexOfTests);
            _item.testsPerMillion = _cursor.getInt(_cursorIndexOfTestsPerMillion);
            _result.add(_item);
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
