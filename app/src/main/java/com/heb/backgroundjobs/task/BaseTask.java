package com.heb.backgroundjobs.task;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ThreadPoolExecutor;

public abstract class BaseTask {
    private Handler handler;
    private final ThreadPoolExecutor threadPoolExecutor;

    public BaseTask(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    protected void initializeHandler(Handler.Callback handlerCallback) {
        handler = new Handler(Looper.getMainLooper(), handlerCallback);
    }

    protected void execute(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    protected Handler getHandler() {
        return handler;
    }
}
