package com.heb.backgroundjobs.task;

public class GetCoverRunnable implements Runnable {
    private final DownloadCoverTaskCallback callback;
    private final String coverUrl;

    public interface DownloadCoverTaskCallback {
        void onCoverRetrieved();
    }

    public GetCoverRunnable(DownloadCoverTaskCallback callback, String coverUrl) {
        this.callback = callback;
        this.coverUrl = coverUrl;
    }

    @Override
    public void run() {
        //move current thread in the background
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

        callback.onCoverRetrieved();
    }
}
