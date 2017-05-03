package cn.jianke.customtablayout.activity.order;

import android.support.v4.app.FragmentActivity;
import java.lang.ref.WeakReference;

/**
 * @className: OrderModel
 * @classDescription: order data
 * @author: leibing
 * @createTime: 2017/3/1
 */
public class OrderModel {
    // activity weak refer
    private WeakReference<FragmentActivity> mActivityWeakRef;
    // order logical processing listener
    private ModelListener mModelListener;

    /**
     * Constructor
     * @author leibing
     * @createTime 2016/5/10
     * @lastModify 2016/5/10
     * @param mContext activity refer
     * @param mModelListener order data listener
     * @return
     */
    public OrderModel(FragmentActivity mContext, ModelListener mModelListener){
        this.mActivityWeakRef = new WeakReference<FragmentActivity>(mContext);
        this.mModelListener = mModelListener;
        // order data init
        dataInit();
    }

    /**
     * order data init
     * @author leibing
     * @createTime 2017/3/1
     * @lastModify 2017/3/1
     * @param
     * @return
     */
    private void dataInit(){

    }

    /**
     * @interfaceName: ModelListener
     * @interfaceDescription: order data listener
     * @author: leibing
     * @createTime: 2017/3/1
     */
    public interface ModelListener extends CommonInterface{
    }
}
