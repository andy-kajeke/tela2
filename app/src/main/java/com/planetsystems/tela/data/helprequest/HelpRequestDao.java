package com.planetsystems.tela.data.helprequest;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HelpRequestDao {

    @Insert
    void insertHelpRequest(HelpRequest helpRequest4);

    @Query("SELECT * FROM " + HelpRequestConstant.TABLE_NAME)
    LiveData<List<HelpRequest>> getHelpRequests();
}
