package cn.jianke.customtablayout.activity.order;

import android.support.v4.app.FragmentActivity;
import java.lang.ref.WeakReference;

/**
 * @className: OrderViewModel
 * @classDescription: order logical processing
 * @author: leibing
 * @createTime: 2017/3/1
 */
public class OrderViewModel {
    // activity weak refer
    private WeakReference<FragmentActivity> mActivityWeakRef;
    // order logical processing listener
    private ViewModelListener mViewModelListener;
    // order data instance
    private OrderModel mOrderModel;
    // order data listener
    private OrderModel.ModelListener mModelListener = new OrderModel.ModelListener() {
        @Override
        public void updateUI(Object object) {
            if (mViewModelListener != null)
                mViewModelListener.updateUI(object);
        }
    };

    /**
     * Constructor
     * @author leibing
     * @createTime 2016/5/10
     * @lastModify 2016/5/10
     * @param mContext activity refer
     * @param mViewModelListener order logical processing listener
     * @return
     */
    public OrderViewModel(FragmentActivity mContext, ViewModelListener mViewModelListener){
        // init activity weak refer instance
        this.mActivityWeakRef = new WeakReference<FragmentActivity>(mContext);
        // init order logical processing listener
        this.mViewModelListener = mViewModelListener;
    }

    /**
     * init order data
     * @author leibing
     * @createTime 2017/3/1
     * @lastModify 2017/3/1
     * @param mContext activity refer
     * @return
     */
    private void initOrderModel(FragmentActivity mContext){
        // init order data instance
        mOrderModel = new OrderModel(mContext, mModelListener);
        // order data init
        mOrderModel.dataInit();
    }

    /**
     * order logical init
     * @author leibing
     * @createTime 2017/3/1
     * @lastModify 2017/3/1
     * @param
     * @return
     */
    public void logicalInit(){
    }

    /**
     * @interfaceName: ViewModelListener
     * @interfaceDescription: order logical processing listener
     * @author: leibing
     * @createTime: 2017/3/1
     */
    public interface ViewModelListener extends CommonInterface{
    }
}
