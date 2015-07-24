package com.heb.backgroundjobs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_FOOTER = 0;
    private static final int TYPE_CONTENT = 1;

    private List<Content> contentList;
    private final ClickHandler clickHandler;

    public interface ClickHandler {
        void onDownloadButtonClicked();
    }

    public ContentListAdapter(ClickHandler clickHandler) {
        contentList = new ArrayList<>();
        this.clickHandler = clickHandler;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_footer, viewGroup, false);
                return new FooterViewHolder(view);
            } else if (viewType == TYPE_CONTENT){
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item, viewGroup, false);
            return new ItemViewHolder(view);
        }

        throw new RuntimeException("Unknown item type");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
            Content content = getItem(position);
            itemViewHolder.initWithContent(content);
            itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //itemClickListener.onItemClick(view, position);
                }
            });
        } else if (viewHolder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) viewHolder;
            footerViewHolder.downloadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickHandler.onDownloadButtonClicked();
                }
            });
        }
    }

    public void addContent(Content content) {
        contentList.add(content);
        notifyItemInserted(0);
    }

    @Override
    public int getItemCount() {
        return contentList.size() + 1;
    }

    private Content getItem(int position) {
        if (isPositionHeader(position)) {
            return contentList.get(position - 1);
        } else {
            return contentList.get(position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_FOOTER;
        }

        return TYPE_CONTENT;
    }

    public boolean isPositionHeader(int position) {
        return position == contentList.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ImageView coverView;
        private final TextView titleView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            coverView = (ImageView) itemView.findViewById(R.id.content_cover_view);
            titleView = (TextView) itemView.findViewById(R.id.content_title_view);
        }

        public void initWithContent(Content content) {
            titleView.setText(content.getTitle());
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {
        private final Button downloadButton;
        public FooterViewHolder(View itemView) {
            super(itemView);
            downloadButton = (Button) itemView.findViewById(R.id.download_button);
        }
    }
}
