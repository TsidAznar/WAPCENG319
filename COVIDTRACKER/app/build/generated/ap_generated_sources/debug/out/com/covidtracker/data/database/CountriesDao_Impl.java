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
import com.covidtracker.data.database.entity.Countries;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class CountriesDao_Impl extends CountriesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCountries;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public CountriesDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCountries = new EntityInsertionAdapter<Countries>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `countries`(`country`,`cases`,`active`,`todayCases`,`deaths`,`todayDeaths`,`recovered`,`tests`,`testsPerMillion`,`flag`,`critical`,`updated`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Countries value) {
        if (value.country == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.country);
        }
        stmt.bindLong(2, value.cases);
        stmt.bindLong(3, value.active);
        stmt.bindLong(4, value.todayCases);
        stmt.bindLong(5, value.deaths);
        stmt.bindLong(6, value.todayDeaths);
        stmt.bindLong(7, value.recovered);
        stmt.bindLong(8, value.tests);
        stmt.bindLong(9, value.testsPerMillion);
        if (value.flag == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.flag);
        }
        stmt.bindLong(11, value.critical);
        stmt.bindLong(12, value.updated);
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM countries";
        return _query;
      }
    };
  }

  @Override
  void bulkInsert(List<Countries> countries) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfCountries.insert(countries);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateAll(List<Countries> countries) {
    __db.beginTransaction();
    try {
      super.updateAll(countries);
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
  public LiveData<List<Countries>> getCountriesList() {
    final String _sql = "Select * FROM countries ORDER BY country ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<Countries>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<Countries> compute() {
        if (_observer == null) {
          _observer = new Observer("countries") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfCountry = _cursor.getColumnIndexOrThrow("country");
          final int _cursorIndexOfCases = _cursor.getColumnIndexOrThrow("cases");
          final int _cursorIndexOfActive = _cursor.getColumnIndexOrThrow("active");
          final int _cursorIndexOfTodayCases = _cursor.getColumnIndexOrThrow("todayCases");
          final int _cursorIndexOfDeaths = _cursor.getColumnIndexOrThrow("deaths");
          final int _cursorIndexOfTodayDeaths = _cursor.getColumnIndexOrThrow("todayDeaths");
          final int _cursorIndexOfRecovered = _cursor.getColumnIndexOrThrow("recovered");
          final int _cursorIndexOfTests = _cursor.getColumnIndexOrThrow("tests");
          final int _cursorIndexOfTestsPerMillion = _cursor.getColumnIndexOrThrow("testsPerMillion");
          final int _cursorIndexOfFlag = _cursor.getColumnIndexOrThrow("flag");
          final int _cursorIndexOfCritical = _cursor.getColumnIndexOrThrow("critical");
          final int _cursorIndexOfUpdated = _cursor.getColumnIndexOrThrow("updated");
          final List<Countries> _result = new ArrayList<Countries>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Countries _item;
            _item = new Countries();
            _item.country = _cursor.getString(_cursorIndexOfCountry);
            _item.cases = _cursor.getInt(_cursorIndexOfCases);
            _item.active = _cursor.getInt(_cursorIndexOfActive);
            _item.todayCases = _cursor.getInt(_cursorIndexOfTodayCases);
            _item.deaths = _cursor.getInt(_cursorIndexOfDeaths);
            _item.todayDeaths = _cursor.getInt(_cursorIndexOfTodayDeaths);
            _item.recovered = _cursor.getInt(_cursorIndexOfRecovered);
            _item.tests = _cursor.getInt(_cursorIndexOfTests);
            _item.testsPerMillion = _cursor.getInt(_cursorIndexOfTestsPerMillion);
            _item.flag = _cursor.getString(_cursorIndexOfFlag);
            _item.critical = _cursor.getInt(_cursorIndexOfCritical);
            _item.updated = _cursor.getLong(_cursorIndexOfUpdated);
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
