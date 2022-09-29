package com.example.notlonesomegeorge

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notlonesomegeorge.Configs.getDefaultProfileDir
import java.io.File

private const val TAG = "DataSource"

/**
 * This class is similar to the DataSource we created in the RecyclerView demo
 * Instead of maintaining a list of homework, we keep track of all the files inside a directory of interest
 */
class DataSource {

    private val initialChoiceList = getInitFileNames()
    private val choicesLiveData = MutableLiveData(initialChoiceList)

    fun getFileNames(): LiveData<List<String>> {
        return choicesLiveData
    }

    fun addFile(fileName: String) {
        val currentList = choicesLiveData.value
        if (currentList == null){
            choicesLiveData.postValue(listOf(fileName))
        } else {
            Log.d(TAG, "update the current list")
            val updatedList = currentList.toMutableList()
            updatedList.add(0, fileName)
            choicesLiveData.postValue(updatedList)
        }
    }

    fun getInitFileNames (): List<String>{
        val dirFiles: File = getDefaultProfileDir(CameraBaseApplication.getAppContext())
        // dirFiles should have been created at this point
        // so dirFiles.list() is non-null: https://docs.oracle.com/javase/7/docs/api/java/io/File.html#list()
        return dirFiles.list()!!.toList()
    }

    companion object {
        private var INSTANCE: DataSource? = null

        // important to only have one instance of datasource
        // if you do Datasource() inside the Activity(),
        // you might see weird behavior such as LiveData does not observe correctly
        fun getDataSource(): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource()
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}