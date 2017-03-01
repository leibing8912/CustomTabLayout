package cn.jianke.customtablayout.activity.order;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.jianke.customtablayout.R;

/**
 * @className: OrderFragment
 * @classDescription: order ui presentation by fragment
 * @author: leibing
 * @createTime: 2017/2/28
 */
public class OrderFragment extends Fragment {
    // tab title key
    public final static String KEY_TAB_TITLE = "key_tab_title";
    // content txt
    private TextView contentTv;
    // content title
    private String contentTitle;
    // order logical processing instance
    private OrderViewModel mOrderViewModel;
    // order logical processing listener
    private OrderViewModel.ViewModelListener mViewModelListener
            = new OrderViewModel.ViewModelListener() {
        @Override
        public void updateUI(Object object) {

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, null);

        // findViewById
        contentTv = (TextView) view.findViewById(R.id.tv_content);
        // set value
        contentTv.setText(contentTitle);
        // init order order logical processing
        initOrderViewModel();

        return view;
    }

    /**
     * init order order logical processing
     * @author leibing
     * @createTime 2017/3/1
     * @lastModify 2017/3/1
     * @param
     * @return
     */
    private void initOrderViewModel(){
        // when activity refer is not null,invoke
        if (getActivity() != null) {
            // init order logical processing instance
            mOrderViewModel = new OrderViewModel(getActivity(), mViewModelListener);
            // order logical init
            mOrderViewModel.logicalInit();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        contentTitle = (String) getArguments().getSerializable(KEY_TAB_TITLE);
    }
}
