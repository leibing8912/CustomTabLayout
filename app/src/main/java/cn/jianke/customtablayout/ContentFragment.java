package cn.jianke.customtablayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @className: ContentFragment
 * @classDescription: 内容页面
 * @author: leibing
 * @createTime: 2017/2/28
 */
public class ContentFragment extends Fragment {
    // tab title key
    public final static String KEY_TAB_TITLE = "key_tab_title";
    // content txt
    private TextView contentTv;
    // content title
    private String contentTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, null);

        // findViewById
        contentTv = (TextView) view.findViewById(R.id.tv_content);
        // set value
        contentTv.setText(contentTitle);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        contentTitle = (String) getArguments().getSerializable(KEY_TAB_TITLE);
    }
}
