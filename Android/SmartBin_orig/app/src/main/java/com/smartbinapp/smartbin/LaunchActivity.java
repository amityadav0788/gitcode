package com.smartbinapp.smartbin;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import org.json.JSONArray;


public class LaunchActivity extends AppCompatActivity {

    private static final String TAB_FillLevels_TAG = "tab_FillLevels";
    private static final String TAB_Map_TAG = "tab_Map";
    private static final String TAB_Analaysis_TAG = "tab_Analysis";
    private FragmentTabHost mTabHost;

    BinInfo allBinsInfo = BinInfo.getInstance();
    JSONArray bins = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        // Loading bins in Background Thread

//        allBinsInfo.setContext(LaunchActivity.this);
//        bins = allBinsInfo.getAllRegBins();

        /// add tab view
        InitView();
    }

    private void InitView() {
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(setIndicator(LaunchActivity.this, mTabHost.newTabSpec(TAB_FillLevels_TAG),
                R.drawable.filllevel_tab_indicator), FillLevelContainer.class, null);
        mTabHost.addTab(setIndicator(LaunchActivity.this, mTabHost.newTabSpec(TAB_Map_TAG),
                R.drawable.map_tab_indicator), MapContainer.class, null);
        mTabHost.addTab(setIndicator(LaunchActivity.this, mTabHost.newTabSpec(TAB_Analaysis_TAG),
                R.drawable.analysis_tab_indicator), AnalysisContainer.class, null);


    }

    private TabHost.TabSpec setIndicator(Context ctx, TabHost.TabSpec spec, int resid) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.tab_item, null);
        ImageView img = (ImageView)v.findViewById(R.id.img_tab);
        img.setBackgroundResource(resid);
        return spec.setIndicator(v);
    }

    @Override
    public void onBackPressed() {
        boolean isPopFragment=false;
        String currentTabTag = mTabHost.getCurrentTabTag();

        if(currentTabTag.equals(TAB_FillLevels_TAG)){
            isPopFragment=((BaseContainerFragment)getSupportFragmentManager().findFragmentByTag(TAB_FillLevels_TAG)).popFragment();
        }
        else if(currentTabTag.equals(TAB_Map_TAG)){
            isPopFragment = ((BaseContainerFragment)getSupportFragmentManager().findFragmentByTag(TAB_Map_TAG)).popFragment();

        }
        else if (currentTabTag.equals(TAB_Analaysis_TAG)) {
        isPopFragment = ((BaseContainerFragment)getSupportFragmentManager().findFragmentByTag(TAB_Analaysis_TAG)).popFragment();
        }

        if (!isPopFragment) {
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launch, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_refresh:
                doRefresh();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void doRefresh(){

    }

    private void openSettings(){

    }
}


//import com.couchbase.lite.Database;
//import com.couchbase.lite.LiveQuery;
//import com.couchbase.lite.Manager;
//import com.couchbase.lite.util.*;



//    //    couch internals
//    protected static Manager manager;
//    private Database database;
//    private LiveQuery liveQuery;

//    //public static final String SYNC_URL = "http://127.0.0.1:5984/"; //couch database
//
//    // url to get all products list
//    private static String url_all_products = "http://10.245.213.135/binapp/get_all_bins_pdo.php";
//
//    public static String TAG = "BinApp";

//all child activity should have this arrow
//getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        //button will be displayed in this layout
//        final LinearLayout llFillLevels = (LinearLayout) findViewById(R.id.fillLevelsActivity);
//
//        //Create four
//        for(int j=0;j<=10;j++) {
////            // Create LinearLayout
////            LinearLayout llLayout = new LinearLayout(this);
////            llLayout.setOrientation(LinearLayout.VERTICAL);
//
//            // Create Button
//            final Button btn = new Button(this);
//
//            // Give button an ID
//            btn.setId(j+1);
//            btn.setText("Bin " + j + 1);
//            // set the layoutParams on the button
//            btn.setLayoutParams(llFillLevels.getLayoutParams());
////            btn.setMinimumWidth(launch.getMeasuredWidth());
//
//            final int index = j;
//            // Set click listener for button
//            btn.setOnClickListener(new OnClickListener() {
//                public void onClick(View v) {
//
//                    android.util.Log.i("TAG", "index :" + index);
//
//                    Toast.makeText(getApplicationContext(), "Clicked Button Index :" + index, Toast.LENGTH_LONG).show();
//
//                }
//            });
//
//            //Add button to LinearLayout
//            //Add button to LinearLayout
//            llFillLevels.addView(btn);
//            //Add button to LinearLayout defined in XML
////            launch.addView(llLayout);
//        }


//        //to start couch db
//        try {
//            startCBLite();
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "Error Initializing CBLIte, see logs for details", Toast.LENGTH_LONG).show();
//            com.couchbase.lite.util.Log.e(TAG, "Error initializing CBLite", e);
//        }

// addlistener for image button
//        addListenerOnImageButton((ImageButton) findViewById(R.id.btn_fill_levels));
//        addListenerOnImageButton((ImageButton) findViewById(R.id.btn_map));
//        addListenerOnImageButton((ImageButton) findViewById(R.id.btn_analysis));


//    public static List<BinInfo> BinList = new ArrayList<>();
//    public static JSONObject json =null;
//
//
//    // JSON Node names
//    private static final String TAG_SUCCESS = "success";
//    private static final String TAG_PRODUCTS = "bins";
//    private static final String TAG_PID = "ID";
//    private static final String TAG_NAME = "Fill";
//
//    // products JSONArray
//    public static JSONArray bins = null;
//    // Creating JSON Parser object
//    JSONParser jParser = new JSONParser();


//    public void addListenerOnImageButton(ImageButton imageButton) {
//
//        imageButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(getApplicationContext(), "ImageButton is clicked!", Toast.LENGTH_SHORT).show();
//                v.findViewById(R.id.btn_analysis).setBackgroundResource(R.drawable.button_analysis_white);
//
//            }
//        });
//
//
//
//        imageButton.bringToFront();
//        imageButton.setFocusable(true);
//        imageButton.setFocusableInTouchMode(true);
//        return;
//    }

//    protected void startCBLite() throws Exception {
//
//        Manager.enableLogging(TAG, com.couchbase.lite.util.Log.VERBOSE);
//        Manager.enableLogging(com.couchbase.lite.util.Log.TAG, com.couchbase.lite.util.Log.VERBOSE);
//        Manager.enableLogging(com.couchbase.lite.util.Log.TAG_SYNC_ASYNC_TASK, com.couchbase.lite.util.Log.VERBOSE);
//        Manager.enableLogging(com.couchbase.lite.util.Log.TAG_SYNC, com.couchbase.lite.util.Log.VERBOSE);
//        Manager.enableLogging(com.couchbase.lite.util.Log.TAG_QUERY, com.couchbase.lite.util.Log.VERBOSE);
//        Manager.enableLogging(com.couchbase.lite.util.Log.TAG_VIEW, com.couchbase.lite.util.Log.VERBOSE);
//        Manager.enableLogging(com.couchbase.lite.util.Log.TAG_DATABASE, com.couchbase.lite.util.Log.VERBOSE);
//
//        manager = new Manager(new AndroidContext(this), Manager.DEFAULT_OPTIONS);
//
//        //install a view definition needed by the application
//        database = manager.getDatabase(DATABASE_NAME);
//        com.couchbase.lite.View viewItemsByDate = database.getView(String.format("%s/%s", designDocName, byDateViewName));
//        viewItemsByDate.setMap(new Mapper() {
//            @Override
//            public void map(Map<String, Object> document, Emitter emitter) {
//                Object createdAt = document.get("created_at");
//                if (createdAt != null) {
//                    emitter.emit(createdAt.toString(), null);
//                }
//            }
//        }, "1.0");
//
//        initItemListAdapter();
//
//        startLiveQuery(viewItemsByDate);
//
//        startSync();
//
//    }
//
//    private void startSync() {
//
//        URL syncUrl;
//        try {
//            syncUrl = new URL(SYNC_URL);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//
//        Replication pullReplication = database.createPullReplication(syncUrl);
//        pullReplication.setContinuous(true);
//
//        Replication pushReplication = database.createPushReplication(syncUrl);
//        pushReplication.setContinuous(true);
//
//        pullReplication.start();
//        pushReplication.start();
//
//        pullReplication.addChangeListener(this);
//        pushReplication.addChangeListener(this);
//
//    }


//    /**
//     * Background Async Task to Load all product by making HTTP Request
//     * */
//    class LoadAllProducts extends AsyncTask<String, String, String> {
//
//        // Progress Dialog
//        private ProgressDialog pDialog;

//        /**
//         * Before starting background thread Show Progress Dialog
//         * */
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            if(pDialog !=null)
//            {
//                pDialog = null;
//            }
//            pDialog = new ProgressDialog(LaunchActivity.this);
//            pDialog.setMessage("Loading products. Please wait...");
//            pDialog.setIndeterminate(false);
//            pDialog.setCancelable(false);
//            pDialog.show();
//        }
//
//        /**
//         * getting All products from url
//         * */
//        protected String doInBackground(String... args) {
//            // Building Parameters
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            // getting JSON string from URL
//            json = jParser.makeHttpRequest(url_all_products, "GET", params);
//            // Check your log cat for JSON reponse
//            //Log.d("All Products: ", json.toString());
//
//            try {
//                Log.d("hehehe", url_all_products.toString() + " --------- " + params.toString() + "---------- " + json.getString(TAG_SUCCESS));
//
//                // Checking for SUCCESS TAG
//                int success = json.getInt(TAG_SUCCESS);
//                Log.d("hehehe sucback",Integer.toString(success));
//                if (success == 1) {
//                    // products found
//                    // Getting Array of Products
//                    bins= json.getJSONArray(TAG_PRODUCTS);
//                    Log.d("hehehe i in doinback",Integer.toString(bins.length()));
//                    // looping through All Products
//
//                    BinList = new ArrayList<BinInfo>();
//                    for (int i = 0; i < bins.length(); i++) {
//                        JSONObject c = bins.getJSONObject(i);
//                        BinInfo bininfo = new BinInfo();
//
//                        // Storing each json item in variable
//                        String id = c.getString(TAG_PID);
//                        String fill = c.getString(TAG_NAME);
//
//                        Log.d("hehehe id inkground ",id);
//                        Log.d("hehehe round ", fill);
//                        // creating new HashMap
////                        HashMap<String, String> map = new HashMap<String, String>();
////
//                        // adding each child node to HashMap key => value
//                        //map.put(TAG_PID, id);
//                        //map.put(TAG_NAME, fill);
//
//                        bininfo.id =Integer.parseInt(id);
//                        bininfo.fillLevel=Double.parseDouble(fill);
//
//                        // adding HashList to ArrayList
//                        //productsList.add(map);
//                        BinList.add(bininfo);
//
//                        Log.d("just finLoad", BinList.toString());
//                    }
//                } else {
//                    // no products found
//                    // Launch Add New product Activity
//                    //Intent i = new Intent(getApplicationContext(),
//                    // NewProductActivity.class);
//                    // Closing all previous activities
//                    //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    // startActivity(i);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        /**
//         * After completing background task Dismiss the progress dialog
//         * **/
//        protected void onPostExecute(String file_url) {
//            // dismiss the dialog after getting all products
//            pDialog.dismiss();
//            // updating UI from Background Thread
////            runOnUiThread(new Runnable() {
////                public void run() {
////                    /**
////                     * Updating parsed JSON data into ListView
////                     * */
////                    ListAdapter adapter = new SimpleAdapter(
////                            AllProductsActivity.this, productsList,
////                            R.layout.list_item, new String[] { TAG_PID,
////                            TAG_NAME},
////                            new int[] { R.id.id, R.id.fill });
////                    // updating listview
////                    setListAdapter(adapter);
////                }
////            });
//
//        }
//
//}




