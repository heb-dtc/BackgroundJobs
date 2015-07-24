package com.heb.backgroundjobs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements ContentListAdapter.ClickHandler {

    private final static int COLUMNS_NUMBER = 1;

    private RecyclerView contentRecyclerView;
    private ContentListAdapter adapter;
    private DownloadScheduler downloadScheduler;
    private List<Content> contentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentRecyclerView = (RecyclerView) findViewById(R.id.content_list);
        initContentList();
        initDownloadScheduler();
        initRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initContentList() {
        contentList = new ArrayList<>();

        Content mixOne = new Content("http://flow.ddns.net/public/cover.jpg", "http://flow.ddns.net/public/rone.mp3", "Rone");
        Content mixTwo = new Content("http://flow.ddns.net/public/cover.jpg", "http://flow.ddns.net/public/sbtrkt.mp3", "SBTRKT");
        Content mixThree = new Content("http://flow.ddns.net/public/cover.jpg", "http://flow.ddns.net/public/murphy.mp3", "James Murphy");

        contentList.add(mixOne);
        contentList.add(mixTwo);
        contentList.add(mixThree);
    }

    private void initDownloadScheduler() {
        downloadScheduler = DownloadScheduler.newInstance();
    }

    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), COLUMNS_NUMBER);
        contentRecyclerView.setLayoutManager(gridLayoutManager);

        adapter = new ContentListAdapter(this);
        contentRecyclerView.setAdapter(adapter);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                 if (adapter.isPositionHeader(position)) {
                    return COLUMNS_NUMBER;
                }

                return 1;
            }
        });
    }

    @Override
    public void onDownloadButtonClicked() {
        Random random = new Random();
        int position = random.nextInt(contentList.size());

        Content content = contentList.get(position);
        adapter.addContent(content);
        //downloadScheduler.downloadContent(content);
    }
}
