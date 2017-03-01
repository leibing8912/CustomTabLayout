package cn.jianke.customtablayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: MainActivity
 * @classDescription: show custom tab layout page
 * @author: leibing
 * @createTime: 2017/2/28
 */
public class MainActivity extends FragmentActivity {
    // all、pay、send、receive、praise position index
    private final static int ALL_INDEX = 0;
    private final static int PAY_INDEX = 1;
    private final static int SEND_INDEX = 2;
    private final static int RECEIVE_INDEX = 3;
    private final static int PRAISE_INDEX = 4;
    // top horizontal scrollview
    private HorizontalScrollView tabHsv;
    // top horizontal tab container
    private LinearLayout tabLy;
    // content page
    private ViewPager contentVp;
    // top horizontal child view list
    private ArrayList<View> mChildViewList;
    // top horizontal tab name array
    private String[] tabNameArray = {
            "全部",
            "待付款",
            "待发货",
            "待收货",
            "待评价"
    };
    // last selected position
    private int lastSelectPosition = 0;
    // all、pay、send、receive、praise fragment
    private ContentFragment allFragment;
    private ContentFragment payFragment;
    private ContentFragment sendFragment;
    private ContentFragment receiveFragment;
    private ContentFragment praiseFragment;
    // fragment list
    private List<Fragment> mFragmentList;
    // title list
    private List<String> mTitleList;
    // screen width
    private int screenWidth = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // findView
        tabHsv = (HorizontalScrollView) findViewById(R.id.hsv_tab);
        contentVp = (ViewPager) findViewById(R.id.vp_content);
        tabLy = (LinearLayout) findViewById(R.id.ly_tab);
        // init screen width
        this.screenWidth = getResources().getDisplayMetrics().widthPixels;
        // get childview list
        getChildViewList();
        // add child view for top horizontal container
        addChildView();
        // init list
        initList();
        // init fragment
        initFragment();
        // set adapter for viewpager
        setAdapter();
    }

    /**
     * init list
     * @author leibing
     * @createTime 2017/2/28
     * @lastModify 2017/2/28
     * @param
     * @return
     */
    private void initList() {
        mFragmentList = new ArrayList<>();
        mTitleList = new ArrayList<>();
    }

    /**
     * set adapter for viewpager
     * @author leibing
     * @createTime 2017/2/28
     * @lastModify 2017/2/28
     * @param
     * @return
     */
    private void setAdapter() {
        // init adapter
        ViewPagerAdapter mAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
                mFragmentList, mTitleList);
        contentVp.setAdapter(mAdapter);
        contentVp.setOffscreenPageLimit(5);
        contentVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                // change tab position by index
                changeTabByIndex(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * change tab position by index
     * @author leibing
     * @createTime 2017/2/28
     * @lastModify 2017/2/28
     * @param
     * @return
     */
    private void changeTabByIndex(int index){
        // when current position not last selected position,invoke
        if (index == lastSelectPosition)
            return;
        // top horizontal layout selected item width
        int itemWidth = 0;
        // top horizontal layout selected item left distance
        int itemLeftDistance = 0;
        // need scroll to x position
        int scollX = 0;
        for (int j=0;j<mChildViewList.size();j++){
            // set current position attribute
            if (j == index){
                View childView = mChildViewList.get(j);
                TextView tabNameTv = (TextView) childView.findViewById(R.id.tv_tab_name);
                View bottomLineVw = (View) childView.findViewById(R.id.vw_bottom_line);
                tabNameTv.setTextColor(getResources().getColor(R.color.colorblue));
                bottomLineVw.setVisibility(View.VISIBLE);
                itemWidth = childView.getMeasuredWidth();
                itemLeftDistance = childView.getLeft();
                scollX = itemLeftDistance + itemWidth / 2 - screenWidth / 2;
            }
            // set last position attribute
            if (j == lastSelectPosition){
                View childView = mChildViewList.get(j);
                TextView tabNameTv = (TextView) childView.findViewById(R.id.tv_tab_name);
                View bottomLineVw = (View) childView.findViewById(R.id.vw_bottom_line);
                tabNameTv.setTextColor(getResources().getColor(R.color.colorgray));
                bottomLineVw.setVisibility(View.GONE);
            }
        }
        // add child view for top horizontal container
        addChildView();
        // scroll top horizontal view
        tabHsv.smoothScrollTo(scollX, 0);
        // set last position
        lastSelectPosition = index;
    }

    /**
     * init fragment
     * @author leibing
     * @createTime 2017/2/28
     * @lastModify 2017/2/28
     * @param
     * @return
     */
    private void initFragment() {
        // all
        allFragment =  new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ContentFragment.KEY_TAB_TITLE, tabNameArray[ALL_INDEX]);
        allFragment.setArguments(bundle);
        mFragmentList.add(allFragment);

        // pay
        payFragment =  new ContentFragment();
        bundle = new Bundle();
        bundle.putSerializable(ContentFragment.KEY_TAB_TITLE, tabNameArray[PAY_INDEX]);
        payFragment.setArguments(bundle);
        mFragmentList.add(payFragment);

        // send
        sendFragment =  new ContentFragment();
        bundle = new Bundle();
        bundle.putSerializable(ContentFragment.KEY_TAB_TITLE, tabNameArray[SEND_INDEX]);
        sendFragment.setArguments(bundle);
        mFragmentList.add(sendFragment);

        // receive
        receiveFragment =  new ContentFragment();
        bundle = new Bundle();
        bundle.putSerializable(ContentFragment.KEY_TAB_TITLE, tabNameArray[RECEIVE_INDEX]);
        receiveFragment.setArguments(bundle);
        mFragmentList.add(receiveFragment);

        // praise
        praiseFragment =  new ContentFragment();
        bundle = new Bundle();
        bundle.putSerializable(ContentFragment.KEY_TAB_TITLE, tabNameArray[PRAISE_INDEX]);
        praiseFragment.setArguments(bundle);
        mFragmentList.add(praiseFragment);
    }

    /**
     * get childview list
     * @author leibing
     * @createTime 2017/2/28
     * @lastModify 2017/2/28
     * @param
     * @return
     */
    private void getChildViewList() {
        // init top horizontal scroll child view item list
        mChildViewList = new ArrayList<>();
        // create child view ,add to item list
        for (int i=0;i<tabNameArray.length;i++){
            final View childView = LayoutInflater.from(this).inflate(R.layout.item_tab, null);
            final TextView tabNameTv = (TextView) childView.findViewById(R.id.tv_tab_name);
            final View bottomLineVw = (View) childView.findViewById(R.id.vw_bottom_line);
            // set a position tag for child view
            childView.setTag(i);
            // set tab name
            tabNameTv.setText(tabNameArray[i]);
            // first item selected default
            if (i == 0) {
                bottomLineVw.setVisibility(View.VISIBLE);
                lastSelectPosition = i;
            }
            else
                bottomLineVw.setVisibility(View.GONE);
            // set onclick listener
            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // get current position from child view tag
                    int currentPosition = (int) childView.getTag();
                    if (currentPosition != lastSelectPosition){
                        contentVp.setCurrentItem(currentPosition,false);
                        // change tab position by index
                        changeTabByIndex(currentPosition);
                    }
                }
            });
            mChildViewList.add(childView);
        }
    }

    /**
     * add child view for top horizontal container
     * @author leibing
     * @createTime 2017/2/28
     * @lastModify 2017/2/28
     * @param
     * @return
     */
    private void addChildView() {
        if (tabLy == null)
            return;
        tabLy.removeAllViews();
        for (int i=0;i<mChildViewList.size();i++){
            tabLy.addView(mChildViewList.get(i));
        }
    }
}
