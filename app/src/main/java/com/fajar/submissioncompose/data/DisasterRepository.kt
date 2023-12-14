package com.fajar.submissioncompose.data

import com.fajar.submissioncompose.model.BookmarkedDisaster
import com.fajar.submissioncompose.model.Disaster
import com.fajar.submissioncompose.model.DisasterData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class DisasterRepository {

    private val disasterDetail : List<Disaster> = DisasterData.disaster
    private val bookmarkedDisasters: MutableSet<Long> = mutableSetOf()

    fun getAllDisaster(): Flow<List<Disaster>> {
        return flowOf(disasterDetail)
    }

    fun getDisaster(): List<Disaster>{
        return DisasterData.disaster
    }

    fun searchDisaster(query: String): List<Disaster>{
        return DisasterData.disaster.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun searchDisasterById(id: Long): Disaster{
        return DisasterData.disaster.first{
            it.id == id
        }
    }

    fun getDisasterById(disasterId: Long): Disaster {
        return disasterDetail.first {
            it.id == disasterId
        }
    }


    fun getBookmarkedDisasters(): List<Disaster> {
        return disasterDetail.filter {
            it.id in bookmarkedDisasters
        }
    }

    fun toggleBookmark(disasterId: Long) {
        if (bookmarkedDisasters.contains(disasterId)) {
            bookmarkedDisasters.remove(disasterId)
        } else {
            bookmarkedDisasters.add(disasterId)
        }
    }

    fun isBookmarked(disasterId: Long): Boolean {
        return bookmarkedDisasters.contains(disasterId)
    }


    companion object {
        @Volatile
        private var instance: DisasterRepository? = null

        fun getInstance(): DisasterRepository =
            instance ?: synchronized(this) {
                DisasterRepository().apply {
                    instance = this
                }
            }
    }
}