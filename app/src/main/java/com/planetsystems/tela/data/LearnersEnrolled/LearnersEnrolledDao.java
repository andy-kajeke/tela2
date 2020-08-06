package com.planetsystems.tela.data.LearnersEnrolled;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LearnersEnrolledDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void enrolledLearners(LearnersEnrolled learnersEnrolled);

    @Update
    void update(LearnersEnrolled learnersEnrolled);

    @Delete
    void deleteStaff(LearnersEnrolled learnersEnrolled);

    @Query("SELECT * FROM " + LearnersEnrolledConstants.TABLE_NAME)
    LiveData<List<LearnersEnrolled>> getAllLearners();

    @Query("SELECT * FROM " + LearnersEnrolledConstants.TABLE_NAME + " WHERE " + LearnersEnrolledConstants.COLUMN_IS_UPLOADED + " =:isUploaded")
    List<LearnersEnrolled> getLearnersRecords(boolean isUploaded);
}
