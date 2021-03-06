package com.fanap.podchat.persistance.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.fanap.podchat.cachemodel.PhoneContact;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface PhoneContactDao {

    @Insert(onConflict = REPLACE)
    void insertPhoneContacts(List<PhoneContact> phoneContacts);

    @Query("SELECT * FROM PHONECONTACT")
    List<PhoneContact> getPhoneContacts();

    @Insert(onConflict = REPLACE)
    void insertPhoneContact(PhoneContact phoneContact);
}
