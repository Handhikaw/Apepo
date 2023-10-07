package com.harkandavez.android.apepo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Handhika on 7/9/2017.
 */
public class Menu1 extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener {

    SwipeRefreshLayout swipe;
    private RecyclerView tree_rv;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private ArrayList<Tree> trees;
    private AdapterList adapter;
    String url = Server.URL+"getdata.php";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Daftar Pohon");



        
        tree_rv = (RecyclerView) getView().findViewById(R.id.tree_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        tree_rv.setLayoutManager(llm);

        requestQueue = Volley.newRequestQueue(getContext());

        swipe = (SwipeRefreshLayout) getView().findViewById(R.id.swipe_refresh_layout);
        swipe.setOnRefreshListener(this);
        swipe.setColorSchemeResources(android.R.color.holo_green_dark,
                android.R.color.holo_green_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipe.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipe.setRefreshing(true);
                                        sendJsonRequest();
                                    }
                                }
        );


        trees = new ArrayList<>();

    }

    public void sendJsonRequest() {
        //swipe.setRefreshing(true);
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response ", response);
                try {
                    Tree tree;
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("tree");
                    for (int a = 0; a < jsonArray.length(); a++) {
                        JSONObject json = jsonArray.getJSONObject(a);
                        int id = json.getInt("id_tree");
                        String name = json.getString("name_tree");
                        String qrUrl=json.getString("qr_url");
                        String desc = json.getString("desc_tree");
                        String imageUrl = json.getString("image_resource");
                        String img1 = json.getString("img1");
                        String img2 = json.getString("img2");
                        String img3 = json.getString("img3");

                        tree = new Tree();

                        tree.setId(id);
                        tree.setTreeName(name);
                        tree.setQrUrl(qrUrl);
                        tree.setTreeDesc(desc);
                        tree.setImageURL(imageUrl);
                        tree.setImage1(img1);
                        tree.setImage2(img2);
                        tree.setImage3(img3);


                        trees.add(tree);


                        adapter = new AdapterList(getContext(), trees);
                        tree_rv.setAdapter(adapter);
                    }
                    swipe.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                    swipe.setRefreshing(false);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Tidak Ada Koneksi", Toast.LENGTH_LONG).show();
                swipe.setRefreshing(false);
            }
        });

        requestQueue.add(stringRequest);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        setHasOptionsMenu(true);
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_menu_1, container, false);
    }

    @Override
    public void onRefresh() {
        trees.clear();
        sendJsonRequest();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Make sure you have this line of code.

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.main, menu);

        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter != null) adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

}
