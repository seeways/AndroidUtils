# AndroidUtils
taoyuan's AndroidUtils
## 使用
1. project build.gradle下添加：

`maven { url 'https://jitpack.io' }`

如下：

```java
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2. app build.gradle下添加依赖
`compile 'com.github.seeways:AndroidUtils:1.1.6'`


# 2017.3.15
新增DeviceUtils

感谢Blankj，对Blankj的代码作了部分修改

1. `Utils.init(Context context);`
2. 使用

- 判断设备是否root
    + boolean isDeviceRooted()
- 获取设备系统版本号
    + int getSDKVersion()
- 获取设备AndroidID
    + String getAndroidID()
- 获取设备MAC地址
    + String getMacAddress()
- 获取设备厂商
    + String getManufacturer()
- 获取设备型号
    + String getModel()
- 包含了一个静态内部类ShellUtils
    + execCmd: 是否是在root下执行命令
    + 主要是用命令的，这个目前我几乎用不到，主要是当工具类用


    
# 2017.3.7
创建版本




### APP辅助类APPUtils
- 获取应用程序名称
    + getAppName(Context context)
    + return String
- 获取版本号        
    + getVersionName(Context context)
    + return String
- 获得版本码
    + getVersionCode(Context context)
    + return int



### Log管理类 L
- 用法1:L.e("test");(默认tag)
- 用法2:L.e("test","test");(自定义tag)

### Snackbar 管理类 S
- 普通提示Snack
    - showNormalSnack(View view,CharSequence text)
    - showNormalSnack(View view,int text)
- 响应式Snack
    - showActionSnack(View view,CharSequence text,CharSequence action,View.OnClickListener listener)
    - showActionSnack(View view,int text,CharSequence action,View.OnClickListener listener)
- Snackbar需要导入design包
- 均为短时间显示

### Toast管理类 T
- 短时间显示
- void showShort(Context context, int message)
- void showShort(Context context, CharSequence message)
- 长时间显示
- void showLong(Context context, CharSequence message)
- void showLong(Context context, int message)

### 转换类ConvertUtils
- BitMap to Bytes
    + Bitmap2Bytes(Bitmap bm)
    + return byte[]
- String to int
    + String2Int(String string)
    + return int

### 常用单位转换辅助类DensityUtils
- dp转px 
    + dp2px(Context context,float dpval)
    + return int

- sp转px 
    + sp2px(Context context,float spval)
    + return int

- px转dp 
    + px2dp(Context context,float dpval)
    + return float

- px转sp 
    + floatpx2sp(Context context,float pxval)
    + retrun float

### http请求工具类HttpUtils
- 异步的Get请求 
    + doGetAsyn(final String url,final CallBack callBack)
- 异步的Post请求 
    + doPostAsyn(final String url,final String params,final CallBack callBack)
- Get请求
    + doGet(String urlStr)
    + return string
- 有响应结果的Post
    + doPost(String urlStr, String params)
    + return String

    
### 图片帮助类ImageUtils
- 根据路径得到原图的宽高
    + getImgWidthAndHeight(String path)
    + return int[]
    + int width = int[0];int height = int[1];
- bitmap转成png保存到sd卡
    + saveImg(String savePath,Bitmap bitmap,String imgName)

### 软键盘辅助类KeyBoardUtils
- 打开软键盘 
    + openKeyBoard(EditText mEditText,Context mContext)
- 关闭软键盘
    + closeKeyBoard(EditText mEditText,Context mContext)

### 内存管理类MemoryInfo
- return 都是long
- 获得可用内存getAvailableInternalMemorySize()
- 获得总内存getTotalInternalMemorySize()
- 获得可用外存getAvailableExternalMemorySize()
- 获得总外存getTotalExternalMemorySize()

### 网络工具类NetUtils
- 判断网络是否连接 
    + isConnected(Context context)
    + return boolean
- 判断是否是wifi连接 
    + isWifi(Context context)
    + return boolean
- 打开网络设置界面
    + openSet(Activity activity)

### 屏幕管理类ScreenUtils
- 获得屏幕高度 
    + getScreenHeight(Context context)
    + return int

- 获得屏幕宽度
    + getScreenHeight(Context context)
    + return int

- 获得状态栏的高度 
    + getStatusHeight(Context context)
    + return int 

- 获取当前屏幕截图，包含状态栏 
    + snapShotWithStatusBar(Activity activity)
    + return Bitmap

- 获取当前屏幕截图，不包含状态栏 
    + snapShotWithoutStatusBar(Activity activity)
    + return Bitmap


---

> 由于我比较懒，所以以后更新部分返回值类型全部写在方法名前面

### SD卡辅助类SDcardUtils
- 判断SDCard是否可用 
    + boolean isSDCardEnable()
- 获取SD卡路径
    + String getSDCardPath()
- 获取SD卡的剩余容量 单位byte 
    + long getSDCardsize()
- 获取指定路径所在空间的剩余可用容量字节数
    + long getavailable (String filePath)
- 获取系统存储路径
    + String getRootDirectoryPath()
- 获取内部存储路径
    + String getDataDirectoryPath()

### SharedPreferences管理类SPUtils

- 定义文件名
    + FILE_NAME
- 保存数据
    + void put(Context context, String key, Object object)
- 得到数据
    + Object get(Context context, String key, Object defaultobject)
- Object为对应的数据类型String，Integer，Boolean，Float，Long，默认String
- 移除某个key值已经对应的值
    + void remove(Context context, String key)
- 清除所有数据
    + void clear(Context context)
- 查询某个key是否已经存在
    + boolean contains(Context context, String key)
- 返回所有的键值对
    + Map<String, ?> getAll(Context context)


### Time管理类TimeUtils
- 获取时间戳
    + String getTimeStemp()
- 获得当前时间
    + String getTime()
- 获取年，年月，年月日
    + String getDayTime()
    + String getLikeMonth()
    + String getLikeYear()


# 2017.3.14
- 增加2个等待进度条
- 增加国际化支持

### Loading帮助类ProgressDialog

- 菊花进度条
    + Dialog loadingDialog1(Context context, String msg,boolean isCancelable)
- 长条进度条
    + Dialog loadingDialog2(Context context, String msg, boolean isTransBg, boolean isCancelable)
- 关闭进度条
    + void closeDialog(Dialog dialog)
