package com.syxz.hbdroid.activitys.viewpager.splash

import android.support.v4.view.PagerAdapter
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup

/**
 * 引导页Adapter
 * 图片资源一般是本地资源
 */
abstract class SplashViewPagerAdapter(private var datas: List<Int>) : PagerAdapter() {

    var sparseArray: SparseArray<View> = SparseArray()//缓存视图
    var dataList = datas

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int {
        return dataList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        Log.d("Splash", "viewpager instantiateItem")
//        var view = sparseArray.get(position)
//        if (view == null) {
//            view = getView(position)
//            sparseArray.put(position, view)
//        }
        var view = getView(position)
        container.addView(view)
        return view
    }

    abstract fun getView(position: Int): View

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
        Log.d("Splash", "viewpager destroyItem")
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE //该值表示，当对ViewPager的数据集合操作时（添加、插入、删除），ViewPager会立即刷新数据
//        return PagerAdapter.POSITION_UNCHANGED//该值，不会立即刷新数据，而是滑动完数据后再次滑动到该位置时会更新数据
    }

    override fun startUpdate(container: ViewGroup) {
        super.startUpdate(container)
        Log.d("Splash", "viewpager startUpdate")
    }

    override fun finishUpdate(container: ViewGroup) {
        super.finishUpdate(container)
        Log.d("Splash", "viewpager finishUpdate")
    }

    interface SplashCallback {
        fun doClicked(type: ClickButtonType)
    }

    enum class ClickButtonType {
        BTN_JUMP, BTN_START
    }
}