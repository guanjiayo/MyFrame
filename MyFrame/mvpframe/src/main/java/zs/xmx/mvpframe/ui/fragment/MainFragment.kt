package zs.xmx.mvpframe.ui.fragment

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.GridLayoutHelper
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseViewHolder
import com.sunfusheng.marqueeview.MarqueeView
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_main.*
import zs.xmx.mvpframe.R
import zs.xmx.mvpframe.adapter.BaseDelegateAdapter
import zs.xmx.mvpframe.base.fragment.BaseMvpFragment
import zs.xmx.mvpframe.constant.MyConstant
import zs.xmx.mvpframe.injection.component.DaggerMainFragmentComponent
import zs.xmx.mvpframe.injection.module.FragmentModule
import zs.xmx.mvpframe.presenter.MainFragPresent
import zs.xmx.mvpframe.utils.TempGlideImageLoader
import zs.xmx.mvpframe.utils.init.ProjectInit.getApplicationContext
import zs.xmx.mvpframe.view.impl.fragment.IMainView
import java.util.*


/*
 * @创建者     默小铭
 * @博客       http://blog.csdn.net/u012792686
 * @创建时间   2018/10/17 17:22
 * @本类描述	  商城首页
 * @内容说明
 * @补充内容
 *
 * ---------------------------------
 * @新增内容
 *
 */
class MainFragment : BaseMvpFragment<MainFragPresent>(), IMainView {

    //todo 这里目前是本地显示的图片,后面要全部改成网络获取的
    //todo 后面重新封装一下

    //不同item必须不同的viewtype
    internal var BANNER_VIEW_TYPE = 1
    internal var MENU_VIEW_TYPE = 2
    internal var NEWS_VIEW_TYPE = 3
    internal var TITLE_VIEW_TYPE = 4
    internal var GRID_VIEW_TYPE = 5
    //广告位
    internal var ITEM_URL = intArrayOf(R.mipmap.item1, R.mipmap.item2, R.mipmap.item3, R.mipmap.item4, R.mipmap.item5)
    //    应用位
    internal var ITEM_NAMES = arrayOf("天猫", "聚划算", "天猫国际", "外卖", "天猫超市", "充值中心", "飞猪旅行", "领金币", "拍卖", "分类")
    internal var IMG_URLS = intArrayOf(R.mipmap.ic_tian_mao, R.mipmap.ic_ju_hua_suan, R.mipmap.ic_tian_mao_guoji, R.mipmap.ic_waimai, R.mipmap.ic_chaoshi, R.mipmap.ic_voucher_center, R.mipmap.ic_travel, R.mipmap.ic_tao_gold, R.mipmap.ic_auction, R.mipmap.ic_classify)
    //    高颜值商品位
    internal var GRID_URL = intArrayOf(R.mipmap.flashsale1, R.mipmap.flashsale2, R.mipmap.flashsale3, R.mipmap.flashsale4)
    // private var mAdapters :MutableCollection<DelegateAdapter.Adapter<*>> //存放各个模块的适配器
    private var mAdapters: LinkedList<DelegateAdapter.Adapter<*>>? = null

    override fun initComponentInject() {
        DaggerMainFragmentComponent.builder()
                .activityComponent(mActivityComponent)
                .fragmentModule(FragmentModule())
                .build()
                .inject(this)
        mPresenter.mView = this
    }

    override fun setLayoutID(): Int {
        return R.layout.fragment_main
    }

    override fun initView(rootView: View) {

        mAdapters = LinkedList()

        //初始化
        val layoutManager = VirtualLayoutManager(activity!!)
        recycleView.layoutManager = layoutManager
        //设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）：
        val viewPool = RecyclerView.RecycledViewPool()
        recycleView.setRecycledViewPool(viewPool)
        viewPool.setMaxRecycledViews(0, 10)
        val delegateAdapter = DelegateAdapter(layoutManager, true)
        recycleView.adapter = delegateAdapter

        //banner
        val bannerAdapter = object : BaseDelegateAdapter(activity, LinearLayoutHelper(), R.layout.vlayout_banner, 1, BANNER_VIEW_TYPE) {

            override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
                val arrayList = ArrayList<String>()
                arrayList.add("http://bpic.wotucdn.com/11/66/23/55bOOOPIC3c_1024.jpg!/fw/780/quality/90/unsharp/true/compress/true/watermark/url/L2xvZ28ud2F0ZXIudjIucG5n/repeat/true")
                arrayList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505470629546&di=194a9a92bfcb7754c5e4d19ff1515355&imgtype=0&src=http%3A%2F%2Fpics.jiancai.com%2Fimgextra%2Fimg01%2F656928666%2Fi1%2FT2_IffXdxaXXXXXXXX_%2521%2521656928666.jpg")
                // 绑定数据
                val mBanner = holder.getView(R.id.banner) as Banner
                //设置banner样式
                mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                //设置图片加载器
                mBanner.setImageLoader(TempGlideImageLoader())
                //设置图片集合
                mBanner.setImages(arrayList)
                //设置banner动画效果
                mBanner.setBannerAnimation(Transformer.DepthPage)
                //设置标题集合（当banner样式有显示title时）
                //        mBanner.setBannerTitles(titles);
                //设置自动轮播，默认为true
                mBanner.isAutoPlay(true)
                //设置轮播时间
                mBanner.setDelayTime(5000)
                //设置指示器位置（当banner模式中有指示器时）
                mBanner.setIndicatorGravity(BannerConfig.CENTER)
                //banner设置方法全部调用完毕时最后调用
                mBanner.start()

                mBanner.setOnBannerListener {
                    Toast.makeText(getApplicationContext(), "banner点击了$position", Toast.LENGTH_SHORT).show()
                }
            }


        }


        //menu
        // 在构造函数设置每行的网格个数
        val gridLayoutHelper = GridLayoutHelper(5)
        gridLayoutHelper.setPadding(0, 16, 0, 16)
        gridLayoutHelper.setVGap(16)// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(0)// 控制子元素之间的水平间距
        gridLayoutHelper.bgColor = Color.WHITE

        val menuAdapter = object : BaseDelegateAdapter(activity, gridLayoutHelper, R.layout.vlayout_menu, 10, MENU_VIEW_TYPE) {
            override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
                holder.setText(R.id.tv_menu_title_home, ITEM_NAMES[position] + "")
                holder.setImageResource(R.id.iv_menu_home, IMG_URLS[position])
                (holder.getView(R.id.ll_menu_home) as LinearLayout).setOnClickListener {
                    Toast.makeText(getApplicationContext(), ITEM_NAMES[position], Toast.LENGTH_SHORT).show()
                }
            }
        }

        //news
        val newsAdapter = object : BaseDelegateAdapter(activity, LinearLayoutHelper(), R.layout.vlayout_news, 1, NEWS_VIEW_TYPE) {
            override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
                super.onBindViewHolder(holder, position)
                val marqueeView1 = holder.getView(R.id.marqueeView1) as MarqueeView
                val marqueeView2 = holder.getView(R.id.marqueeView2) as MarqueeView

                val info1 = ArrayList<String>()
                info1.add("天猫超市最近发大活动啦，快来抢")
                info1.add("没有最便宜，只有更便宜！")

                val info2 = ArrayList<String>()
                info2.add("这个是用来搞笑的，不要在意这写小细节！")
                info2.add("啦啦啦啦，我就是来搞笑的！")

                marqueeView1.startWithList(info1)
                marqueeView2.startWithList(info2)
                // 在代码里设置自己的动画
                marqueeView1.startWithList(info1, R.anim.anim_bottom_in, R.anim.anim_top_out)
                marqueeView2.startWithList(info2, R.anim.anim_bottom_in, R.anim.anim_top_out)

                marqueeView1.setOnItemClickListener { position, textView ->
                    Toast.makeText(getApplicationContext(), textView.text.toString(), Toast.LENGTH_SHORT).show()
                }
                marqueeView2.setOnItemClickListener { position, textView ->
                    Toast.makeText(getApplicationContext(), textView.text.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
        mAdapters!!.add(bannerAdapter)
        mAdapters!!.add(menuAdapter)
        mAdapters!!.add(newsAdapter)
        //这里我就循环item 实际项目中不同的ITEM 继续往下走就行
        for (i in ITEM_URL.indices) {

            //item1 title
            val titleAdapter = object : BaseDelegateAdapter(activity, LinearLayoutHelper(), R.layout.vlayout_title, 1, TITLE_VIEW_TYPE) {
                override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
                    super.onBindViewHolder(holder, position)
                    holder.setImageResource(R.id.iv, ITEM_URL[i])
                }
            }
            mAdapters!!.add(titleAdapter)
            //item1 gird
            val gridLayoutHelper1 = GridLayoutHelper(2)
            gridLayoutHelper.setMargin(0, 0, 0, 0)
            gridLayoutHelper.setPadding(0, 0, 0, 0)
            gridLayoutHelper.setVGap(0)// 控制子元素之间的垂直间距
            gridLayoutHelper.setHGap(0)// 控制子元素之间的水平间距
            gridLayoutHelper.bgColor = Color.WHITE
            gridLayoutHelper.setAutoExpand(true)//是否自动填充空白区域
            val girdAdapter = object : BaseDelegateAdapter(activity, gridLayoutHelper1, R.layout.vlayout_grid, 4, GRID_VIEW_TYPE) {
                override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
                    super.onBindViewHolder(holder, position)
                    val item = GRID_URL[position]
                    val iv = holder.getView(R.id.iv) as ImageView
                    Glide.with(getApplicationContext()).load(item).into(iv)

                    iv.setOnClickListener {
                        Toast.makeText(getApplicationContext(), "item$position", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            mAdapters!!.add(girdAdapter)
        }


        delegateAdapter.setAdapters(mAdapters)


    }


    override fun lazyLoad() {
        val message = TAG + (if (isInit) "已经初始并已经显示给用户可以加载数据" else "没有初始化不能加载数据") + ">>>>>>>>>>>>>>>>>>>"
        val params = HashMap<String, Any>()
        params["key"] = MyConstant.MOB_KEY
        mPresenter.showData("mainFrag", params, TAG)
        Log.d(TAG, message)
    }

    override fun onShowData(result: Any) {
        //todo 拿到数据后的后续操作
    }

    override fun stopLoad() {
        Log.d(TAG, TAG + "已经对用户不可见，可以停止加载数据")
    }


    override fun onDestroy() {
        super.onDestroy()
        //RXBus反注册
        mPresenter.unRegisterBus()
    }

}
