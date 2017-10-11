package wesave.farabi.com.wesave;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import wesave.farabi.com.wesave.Adapters.CallForDonationAdapter;
import wesave.farabi.com.wesave.Data.CallForDonation;
import wesave.farabi.com.wesave.Utility.MyArrayUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private CallForDonationAdapter adapter;
    private List<CallForDonation> calllist;
    Boolean on =false ;
    ResideMenu resideMenu  ;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemProfile;
    private ResideMenuItem itemCalendar;
    private ResideMenuItem itemMap;
    private ResideMenuItem itemcreatecall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //recycler view setup

        setContentView(R.layout.activity_main);

        setupAutoComplete();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        calllist = new ArrayList<>();
        adapter = new CallForDonationAdapter(this, calllist);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();




        //reside Menu


        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.background);
        resideMenu.attachToActivity(this);
        // create menu items;
        itemHome     = new ResideMenuItem(this, R.drawable.home,     "Home");
        itemProfile  = new ResideMenuItem(this, R.drawable.profile,  "Profile");
        itemCalendar = new ResideMenuItem(this, R.drawable.calendar, "Calendar");
        itemMap = new ResideMenuItem(this, R.drawable.loc, " Donation Centers");
        itemcreatecall  =new ResideMenuItem(this, R.drawable.create, " Call for Donation");
        itemHome.setOnClickListener(this);
        itemProfile.setOnClickListener(this);
        itemCalendar.setOnClickListener(this);
        itemMap.setOnClickListener(this);
        itemcreatecall.setOnClickListener(this);






        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemMap, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemcreatecall, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_RIGHT);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action


                    resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
                    on=true ;


            }
        });

    }

    private void prepareAlbums() {

        CallForDonation a = new CallForDonation("http://ofad.org/files/imagecache/daily_picture/daily-photo/recent-and-random-portraits_5.jpg", "Ariana","04/07/2015","O-");
        calllist.add(a);

        Log.e("callfordonation", a.toString()) ;

 a = new CallForDonation("http://ofad.org/files/imagecache/daily_picture/daily-photo/recent-and-random-portraits_6.jpg", "SAHLOUL","04/07/2015","O-");
        calllist.add(a);

        a = new CallForDonation("http://ofad.org/files/imagecache/daily_picture/daily-photo/recent-and-random-portraits_8.jpg", "ELKEF","04/07/2015","AB+");
        calllist.add(a);

        a = new CallForDonation("https://assets.babycenter.com/ims/2015/09/iStock_66240995_4x3.jpg", "MEHIDA","04/07/2015","AB-");

        calllist.add(a);   a = new CallForDonation("https://upload.wikimedia.org/wikipedia/commons/thumb/7/72/Hayley_McFarland.jpg/800px-Hayley_McFarland.jpg", "Mehida","04/07/2015","B+");

        calllist.add(a);

        adapter.notifyDataSetChanged();
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private void initCollapsingToolbar() {

        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }




    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public void onClick(View view) {

        if (view == itemHome){

            YoYo.with(Techniques.Tada)
                    .duration(700)
                    .repeat(1)
                    .playOn(view)
            ;

            new Handler().postDelayed(new Runnable() {
                @Override

                public void run() {
                  resideMenu.closeMenu();
                }
            }, 720);


        }
        else if (view == itemProfile){
            YoYo.with(Techniques.Tada)
                    .duration(700)
                    .repeat(5)
                    .playOn(view)
            ;

            new Handler().postDelayed(new Runnable() {
                @Override

                public void run() {
                    resideMenu.closeMenu();
                }
            }, 720);

        }else if (view == itemCalendar){
            YoYo.with(Techniques.Tada)
                    .duration(700)
                    .repeat(5)
                    .playOn(view)
            ;

            new Handler().postDelayed(new Runnable() {
                @Override

                public void run() {
                    resideMenu.closeMenu();
                }
            }, 720);

        }


        else if (view == itemMap){
            YoYo.with(Techniques.Tada)
                    .duration(700)
                    .repeat(5)
                    .playOn(view)
            ;

            new Handler().postDelayed(new Runnable() {
                @Override

                public void run() {
                    resideMenu.closeMenu();
                    Intent i = new Intent(MainActivity.this,MapActivity.class);

                    MainActivity.this.startActivity(i);

                }
            }, 720);

        }

        else if (view == itemcreatecall){
            YoYo.with(Techniques.Tada)
                    .duration(700)
                    .repeat(5)
                    .playOn(view)
            ;

            new Handler().postDelayed(new Runnable() {
                @Override

                public void run() {
                    resideMenu.closeMenu();
                    Intent i = new Intent(MainActivity.this,CallActivity.class);

                    MainActivity.this.startActivity(i);

                }
            }, 720);

        }

    }


    private void setupAutoComplete() {
        String[] States = {"Ariana", "Benarous", "Tunis", "Sousse", "Monastir", "Bizerte", "Mango", "Pear"};
        //Creating the instance of ArrayAdapter containing list of fruit names
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, States);
        //Getting the instance of AutoCompleteTextView
        final AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter1);//setting the adapter data into the AutoCompleteTextView

        //TextWatcher

        actv.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_DEL){
                    List<CallForDonation> clone =new ArrayList<>(calllist);
                    adapter = new CallForDonationAdapter(MainActivity.this, MyArrayUtils.filterbylocation(clone,actv.getText().toString()));
                    recyclerView.setAdapter(adapter);
                }else{

                }
                return false;
            }
        });


        final TextWatcher LocationWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 0) {
                    adapter = new CallForDonationAdapter(MainActivity.this, calllist);
                    recyclerView.setAdapter(adapter);
                    Log.e(" aaaaaaaaaaaaaaaa", String.valueOf(calllist.size()));

                } else{
                      List<CallForDonation> clone =new ArrayList<>(calllist);
                    adapter = new CallForDonationAdapter(MainActivity.this, MyArrayUtils.filterbylocation(clone,s.toString()));
                    recyclerView.setAdapter(adapter);
                }

            }

            public void afterTextChanged(Editable s) {
            }
        };
        actv.addTextChangedListener(LocationWatcher);



    }
}
