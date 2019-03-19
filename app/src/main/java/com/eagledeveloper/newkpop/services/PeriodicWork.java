package com.eagledeveloper.newkpop.services;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.eagledeveloper.newkpop.utils.AlertUtils;
import com.eagledeveloper.newkpop.utils.Configuration;
import com.eagledeveloper.newkpop.utils.FileUtils;

import androidx.work.Worker;
import androidx.work.WorkerParameters;


public class PeriodicWork extends Worker {
    private static final String TAG = "PeriodicWork";
    Context context;

    public PeriodicWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        return Result.success();
    }

}
