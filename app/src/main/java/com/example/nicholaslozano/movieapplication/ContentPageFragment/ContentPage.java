//this fragment holds the recyclerview that shows the list of movies
package com.example.nicholaslozano.movieapplication.ContentPageFragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nicholaslozano.movieapplication.ContentData.Content;
import com.example.nicholaslozano.movieapplication.R;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentPage extends Fragment {
    private RecyclerView contentRecyclerView;
    private Content pageContent;
    private LinearLayoutManager linearLayoutManager;
    private ContentAdapter contentAdapter;
    private Parcelable contentPageState;
    private Future<String> future;
    private ExecutorService executorService;
    private String PAGE_STATE = "page_state";

    public ContentPage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageContent = new Content();

        //We create a future to get content information
        executorService = Executors.newSingleThreadExecutor();
        future = executorService.submit(new GetContentInformation());
        try {
            System.out.println(future.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewItem = inflater.inflate(R.layout.fragment_content_page, container, false);

        setUpRecyclerView(viewItem);

        return viewItem;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //Saves the recyclerview's position
        contentPageState = linearLayoutManager.onSaveInstanceState();
        outState.putParcelable(PAGE_STATE, contentPageState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        //restores the recyclerview's position
        if (savedInstanceState != null){
            contentPageState = savedInstanceState.getParcelable(PAGE_STATE);
        }

        contentPageState = null;
    }


    @Override
    public void onResume() {
        super.onResume();
        //when resumed, resume the recyclerview's position
        if(contentPageState != null){
            linearLayoutManager.onRestoreInstanceState(contentPageState);
        }
    }

    private void setUpRecyclerView(View viewItem){
        linearLayoutManager = new LinearLayoutManager(viewItem.getContext());

        contentRecyclerView = viewItem.findViewById(R.id.content_recyclerview);
        contentRecyclerView.setNestedScrollingEnabled(true);
        contentRecyclerView.setLayoutManager(linearLayoutManager);
        contentRecyclerView.setItemViewCacheSize(20);
        contentRecyclerView.setDrawingCacheEnabled(true);
        contentRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        //if the future that was created when the fragment was created is finished
        if(future.isDone()){
            //create the content adapter with the content retrieved and add it to our recycler view
            contentAdapter = new ContentAdapter(pageContent.getContentList(), pageContent.getHeaders());
            contentRecyclerView.setAdapter(contentAdapter);
            System.out.println(pageContent.getContentList().size());
        }

        executorService.shutdown();
    }


    //This class is responsible for ensuring that the application gets all the content from the internet
    //that is needed before adding the content to the recyclerview.
    private class GetContentInformation implements Callable<String>{

        @Override
        public String call() {
            pageContent.getObjectsFromJson();
            return "Finished";
        }
    }

}
