package com.mnashat_dev.hadithsharif.utils

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import kotlinx.coroutines.CoroutineDispatcher

class RefreshDataWorker (appContext: Context, workerParams: WorkerParameters):
    CoroutineWorker(appContext, workerParams) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override val coroutineContext: CoroutineDispatcher
        get() = super.coroutineContext

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return super.getForegroundInfo()
    }

    override suspend fun doWork(): Result {

        return  try {
             Result.success()

        }catch (e: Exception){

            Result.retry()
        }

    }
}
