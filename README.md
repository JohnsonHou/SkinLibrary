# SkinLibrary
#### 使用方式：
- Maven
````
<dependency>
  <groupId>com.jchou.android.skinlib</groupId>
  <artifactId>skinlibrary</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
````
- Gradle

````
compile 'com.jchou.android.skinlib:skinlibrary:1.0.1'
````

#### 简单用法：
- 初始化（建议在Application中完成）
````
private void initSkinLoader(Context ctx) {
        SkinManager.getInstance().init(ctx);
        SkinManager.getInstance().load();//读取皮肤资源
    }
````

- 在Activity中使用(可以建造一个基类)
````
public class SkinBaseActivity extends AppCompatActivity implements ISkinUpdate,IDynamicNewView{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mSkinInflaterFactory = new SkinInflaterFactory();
        LayoutInflaterCompat.setFactory(getLayoutInflater(), mSkinInflaterFactory);
        super.onCreate(savedInstanceState);
        SkinManager.getInstance().attach(this);
        changeStatusColor();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().detach(this);
        mSkinInflaterFactory.clean();
    }

    //是否需要自动切换StatusBar颜色,默认需要
    protected boolean isNeedStatusBarAutoChange(){
        return true;
    }
    
    // 当前Activity是否需要响应皮肤更改需求
    protected boolean isResponseOnSkinChanging(){
        return true;
    }
    private SkinInflaterFactory mSkinInflaterFactory;
    
      //更新主题
    @Override
    public void onThemeUpdate(){
        Log.i("SkinBaseActivity", "onThemeUpdate");
        if (!isResponseOnSkinChanging()) {
            return;
        }
        mSkinInflaterFactory.applySkin();
        changeStatusColor();
        
    }

    //改变状态栏着色，这里使用的是一个第三方https://jaeger.itscoder.com/android/2016/03/27/statusbar-util.html
    public void changeStatusColor() {
        
        if (!isNeedStatusBarAutoChange())return;
        int color = SkinManager.getInstance().getColorStatusBar();
        Log.i("statusBarColor",color+"");
        if (color != -1) {
            StatusBarUtil.setColor(this, color);
        }else {
            StatusBarUtil.setColor(this, Color.WHITE);
        }
    }


    /** 
     * 目前支持的属性：
     * background
     * textColor
     * tabIndicatorColor
     * contentScrimColor
     * backgroundTint
     * navigationViewMenu
     * src
     * thumb
     */
    
    //动态的添加有皮肤更改需求的View。
    @Override
    public void dynamicAddView(View view, List<DynamicAttr> pDAttrs) {
        mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, pDAttrs);
    }
    
    //动态的添加有皮肤更改需求的View。如：dynamicAddSkinEnableView(mToolbar, "background", R.color.colorPrimary);
    protected void dynamicAddSkinEnableView(View view, String attrName, int attrValueResId) {
        mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, attrName, attrValueResId);
    }

    protected void dynamicAddSkinEnableView(View view, List<DynamicAttr> pDAttrs) {
        mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, pDAttrs);
    }
}
````


- 在Fragment中使用(可以建造一个基类)
````
public class BaseFragment extends Fragment implements IDynamicNewView {

    //换肤使用
    private IDynamicNewView mIDynamicNewView;
    private LayoutInflater mLayoutInflater;
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mIDynamicNewView = (IDynamicNewView) activity;
        } catch (ClassCastException e) {
            mIDynamicNewView = null;
        }
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mLayoutInflater = getLayoutInflater(savedInstanceState);
        super.onCreateView(mLayoutInflater,container,savedInstanceState);
    }
    
    //换肤
    @Override
    public void dynamicAddView(View view, List<DynamicAttr> pDAttrs) {
        if (mIDynamicNewView == null) {
            throw new RuntimeException("IDynamicNewView should be implements !");
        } else {
            mIDynamicNewView.dynamicAddView(view, pDAttrs);
        }
    }

    public void dynamicAddSkinView(View view, String attrName, int attrValueResId) {
        List<DynamicAttr> pDAttrs = new ArrayList<>();
        pDAttrs.add(new DynamicAttr(attrName, attrValueResId));
        dynamicAddView(view, pDAttrs);
    }

    public LayoutInflater getLayoutInflater(Bundle savedInstanceState) {
        LayoutInflater result = getActivity().getLayoutInflater();
        return result;
    }
}
````

- 在xml中的使用
只要添加命名空间————xmlns:skin="http://schemas.android.com/skin" 
然后在布局中使用 skin:enable="true"
````
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:skin="http://schemas.android.com/skin"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:background="@color/white">

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/bg_btn_color"
        skin:enable="true" />

</LinearLayout>
````
- 皮肤包的使用

创建一个module，只存放res资源文件，然后打包成apk，放到项目中的assets/skin包下，最好将.apk后缀改名成.skin后缀，也可以使用下载功能创建
