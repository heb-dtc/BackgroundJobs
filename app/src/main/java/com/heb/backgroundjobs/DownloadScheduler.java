package com.heb.backgroundjobs;

import com.heb.backgroundjobs.task.DownloadCoverTask;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DownloadScheduler {
    private final ThreadPoolExecutor threadPool;
    private static final int MAX_THREAD_POOL_SIZE = 12;
    private final BlockingQueue<Runnable> downloadWorkQueue;

    public static DownloadScheduler newInstance() {
        BlockingQueue<Runnable> downloadWorkQueue = new LinkedBlockingQueue<>();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(MAX_THREAD_POOL_SIZE, MAX_THREAD_POOL_SIZE,
            1, TimeUnit.SECONDS, downloadWorkQueue);

        return new DownloadScheduler(threadPool, downloadWorkQueue);
    }

    public DownloadScheduler(ThreadPoolExecutor threadPool, BlockingQueue<Runnable> downloadWorkQueue) {
        this.threadPool = threadPool;
        this.downloadWorkQueue = downloadWorkQueue;
    }

    public void downloadContent(Content content) {
        DownloadCoverTask downloadCoverTask = new DownloadCoverTask(threadPool, content.getUrlCover());
        downloadCoverTask.retrieveCover();
    }
}
