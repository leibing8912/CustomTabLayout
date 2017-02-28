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
 * @classDescription: 演示自定义TabLayout页面
 * @author: leibing
 * @createTime: 2017/2/28
 */
public class MainActivity extends FragmentActivity {
    // 全部、待付款、待发货、待收货、待评价等位置索引
    private final static int ALL_INDEX = 0;
    private final static int PAY_INDEX = 1;
    private final static int SEND_INDEX = 2;
    private final static int RECEIVE_INDEX = 3;
    private final static int PRAISE_INDEX = 4;
    // 顶部横向滑动控件
    private HorizontalScrollView tabHsv;
    // 顶部横向容器
    private LinearLayout tabLy;
    // 内容页
    private ViewPager contentVp;
    // 顶部滑动布局子view列表
    private ArrayList<View> mChildViewList;
    // 顶部滑动布局tab名称数组
    private String[] tabNameArray = {
            "全部",
            "待付款",
            "待发货",
            "待收货",
            "待评价"
    };
    // 上一个被选中的位置
    private int lastSelectPosition = 0;
    // 全部、待付款、待发货、待收货、待评价等Fragment
    private ContentFragment allFragment;
    private ContentFragment payFragment;
    private ContentFragment sendFragment;
    private ContentFragment receiveFragment;
    private ContentFragment praiseFragment;
    //Fragment列表
    private List<Fragment> mFragmentList;
    //标题列表
    private List<String> mTitleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // findView
        tabHsv = (HorizontalScrollView) findViewById(R.id.hsv_tab);
        contentVp = (ViewPager) findViewById(R.id.vp_content);
        tabLy = (LinearLayout) findViewById(R.id.ly_tab);
        // 获取子view列表
        getChildViewList();
        // 给顶部横向容器添加子view
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
                // 改变tab位置
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
     * 改变tab位置
     * @author leibing
     * @createTime 2017/2/28
     * @lastModify 2017/2/28
     * @param
     * @return
     */
    private void changeTabByIndex(int index){
        // 当前点击位置不为上次选中位置则处理
        if (index == lastSelectPosition)
            return;
        for (int j=0;j<mChildViewList.size();j++){
            // 设置本次位置
            if (j == index){
                View childView = mChildViewList.get(j);
                TextView tabNameTv = (TextView) childView.findViewById(R.id.tv_tab_name);
                View bottomLineVw = (View) childView.findViewById(R.id.vw_bottom_line);
                tabNameTv.setTextColor(getResources().getColor(R.color.colorblue));
                bottomLineVw.setVisibility(View.VISIBLE);
            }
            // 设置上次位置
            if (j == lastSelectPosition){
                View childView = mChildViewList.get(j);
                TextView tabNameTv = (TextView) childView.findViewById(R.id.tv_tab_name);
                View bottomLineVw = (View) childView.findViewById(R.id.vw_bottom_line);
                tabNameTv.setTextColor(getResources().getColor(R.color.colorgray));
                bottomLineVw.setVisibility(View.GONE);
            }
        }
        // 添加子view
        addChildView();
        // 设置上次位置
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
        // 全部
        allFragment =  new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ContentFragment.KEY_TAB_TITLE, tabNameArray[ALL_INDEX]);
        allFragment.setArguments(bundle);
        mFragmentList.add(allFragment);

        // 待付款
        payFragment =  new ContentFragment();
        bundle = new Bundle();
        bundle.putSerializable(ContentFragment.KEY_TAB_TITLE, tabNameArray[PAY_INDEX]);
        payFragment.setArguments(bundle);
        mFragmentList.add(payFragment);

        // 待发货
        sendFragment =  new ContentFragment();
        bundle = new Bundle();
        bundle.putSerializable(ContentFragment.KEY_TAB_TITLE, tabNameArray[SEND_INDEX]);
        sendFragment.setArguments(bundle);
        mFragmentList.add(sendFragment);

        // 待收货
        receiveFragment =  new ContentFragment();
        bundle = new Bundle();
        bundle.putSerializable(ContentFragment.KEY_TAB_TITLE, tabNameArray[RECEIVE_INDEX]);
        receiveFragment.setArguments(bundle);
        mFragmentList.add(receiveFragment);

        // 待评价
        praiseFragment =  new ContentFragment();
        bundle = new Bundle();
        bundle.putSerializable(ContentFragment.KEY_TAB_TITLE, tabNameArray[PRAISE_INDEX]);
        praiseFragment.setArguments(bundle);
        mFragmentList.add(praiseFragment);
    }

    /**
     * 获取子view列表
     * @author leibing
     * @createTime 2017/2/28
     * @lastModify 2017/2/28
     * @param
     * @return
     */
    private void getChildViewList() {
        // 初始化顶部滑动布局子view列表
        mChildViewList = new ArrayList<>();
        // 构造子view
        for (int i=0;i<tabNameArray.length;i++){
            final View childView = LayoutInflater.from(this).inflate(R.layout.item_tab, null);
            final TextView tabNameTv = (TextView) childView.findViewById(R.id.tv_tab_name);
            final View bottomLineVw = (View) childView.findViewById(R.id.vw_bottom_line);
            // 给view设置一个位置标识
            childView.setTag(i);
            // 设置tab名称
            tabNameTv.setText(tabNameArray[i]);
            // 默认第一个item为选中
            if (i == 0) {
                bottomLineVw.setVisibility(View.VISIBLE);
                lastSelectPosition = i;
            }
            else
                bottomLineVw.setVisibility(View.GONE);
            // 设置监听
            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 当前点击位置
                    int currentPosition = (int) childView.getTag();
                    if (currentPosition != lastSelectPosition){
                        contentVp.setCurrentItem(currentPosition,false);
                        // 改变tab位置
                        changeTabByIndex(currentPosition);
                    }
                }
            });
            mChildViewList.add(childView);
        }
    }

    /**
     * 给顶部横向容器添加子view
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
