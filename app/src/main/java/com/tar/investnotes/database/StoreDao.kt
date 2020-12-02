package com.tar.investnotes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tar.investnotes.database.models.*

@Dao
interface StoreDao {

    // UserR ============================================================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(activationModelR: UserR)

    @Query("SELECT * FROM UserR")
    fun getUsers(): List<UserR>?

    @Query("DELETE FROM UserR")
    fun clearUserR()

    @Query("DELETE FROM UserR WHERE login =:login")
    fun deleteUser(login : String)

    // InvestmentR ============================================================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInvestment(investmentR: InvestmentR)

    @Query("SELECT * FROM InvestmentR")
    fun getAllInvestments(): List<InvestmentR>?

    @Query("DELETE FROM InvestmentR")
    fun clearInvestments()

    @Query("DELETE FROM InvestmentR WHERE id =:id")
    fun deleteInvestmentById(id : Long)

    // InvestmentR ============================================================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOwner(ownerR: OwnerR)

    @Query("SELECT name FROM OwnerR")
    fun getOwners(): List<String>?

    @Query("DELETE FROM OwnerR WHERE name =:name")
    fun deleteOwner(name : String)

    // InvestTypeR ============================================================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInvestType(investTypeR: InvestTypeR)

    @Query("SELECT name FROM InvestTypeR")
    fun getInvestTypes(): List<String>?

    @Query("DELETE FROM InvestTypeR WHERE name =:name")
    fun deleteType(name : String)

    // BrokerR ============================================================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBroker(brokerR: BrokerR)

    @Query("SELECT name FROM BrokerR")
    fun getBrokers(): List<String>?

    @Query("DELETE FROM BrokerR WHERE name =:name")
    fun deleteBroker(name : String)

    // IndexR ============================================================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIndex(indexR: IndexR)

    @Query("SELECT * FROM IndexR WHERE code = :code LIMIT 1")
    fun getIndex(code : String): IndexR?

}