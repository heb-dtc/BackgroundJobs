package com.heb.backgroundjobs.task;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import java.util.concurrent.ThreadPoolExecutor;

public class DownloadCoverTask extends BaseTask implements Handler.Callback, GetCoverRunnable.DownloadCoverTaskCallback {
    public static final int READY_TO_DOWNLOAD_COVER = 0;
    public static final int CONTENT_COVER_RETRIEVED = 1;

    private Runnable getCoverRunnable;
    private final String coverUrl;

    public DownloadCoverTask(ThreadPoolExecutor threadPool, String coverUrl) {
        super(threadPool);
        initializeHandler(this);

        this.coverUrl = coverUrl;
        this.getCoverRunnable = new GetCoverRunnable(this, coverUrl);
    }

    public void retrieveCover() {
        execute(getCoverRunnable);
    }

    @Override
    public void onCoverRetrieved() {
        sendMessage(CONTENT_COVER_RETRIEVED, null);
    }

    private void sendMessage(int what, @Nullable Object object) {
        Message message = getHandler().obtainMessage(what, object);
        message.sendToTarget();
    }

    @Override
    public boolean handleMessage(Message message) {
        int what = message.what;
        //call back the DownloadScheduler
        return true;
    }
}
