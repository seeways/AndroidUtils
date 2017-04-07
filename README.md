# AndroidUtils
[![](https://jitpack.io/v/seeways/AndroidUtils.svg)](https://jitpack.io/#seeways/AndroidUtils)

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
`compile 'com.github.seeways:AndroidUtils:1.2.0'`


# 2017.04.07

- 删除 `@mipmap` 文件夹

# 2017.3.27

#### 图片帮助类ImageUtils更新部分如下：

- bitmap转成png保存(New:自定义保存)
    + ~~saveImg(String savePath,Bitmap bitmap,String imgName)~~
    + 替代方案如下
    1. 自定义压缩格式(普通用户推荐 `CompressFormat.PNG` )
    2. 可选是否回收(不写此参数默认false)
    3. 文件名合并至路径中(亦可直接用File)
    ```java
    boolean save(Bitmap src, String filePath, CompressFormat format)

    boolean save(Bitmap src, File file, CompressFormat format)

    boolean save(Bitmap src, String filePath, CompressFormat format, boolean recycle)

    boolean save(Bitmap src, File file, CompressFormat format, boolean recycle)
    ```
---
- bitmap转byteArr `byte[] bitmap2Bytes(Bitmap bitmap, CompressFormat format)`
- byteArr转bitmap `Bitmap bytes2Bitmap(byte[] bytes)`
---
- drawable转bitmap `Bitmap drawable2Bitmap(Drawable drawable)`
- bitmap转drawable `Drawable bitmap2Drawable(Resources res, Bitmap bitmap)`
---
- drawable转byteArr `byte[] drawable2Bytes(Drawable drawable, CompressFormat format)`
- byteArr转drawable `Drawable bytes2Drawable(Resources res, byte[] bytes)`
---

- 各种单向获取bitmap() 
    + view `Bitmap view2Bitmap(View view)`
    + File `Bitmap getBitmap(File file)`
    + File `Bitmap getBitmap(File file, int maxWidth, int maxHeight)`
    + filePath `Bitmap getBitmap(String filePath)`
    + filePath `Bitmap getBitmap(String filePath, int maxWidth, int maxHeight)`
    + IO `Bitmap getBitmap(InputStream is)`
    + IO `Bitmap getBitmap(InputStream is, int maxWidth, int maxHeight)`
    + byte[] `Bitmap getBitmap(byte[] data, int offset)`
    + byte[] `Bitmap getBitmap(byte[] data, int offset, int maxWidth, int maxHeight)`
    + Res `Bitmap getBitmap(Resources res, int id)`
    + Res `Bitmap getBitmap(Resources res, int id, int maxWidth, int maxHeight)`
    + 描述 `Bitmap getBitmap(FileDescriptor fd)`
    + 描述 `Bitmap getBitmap(FileDescriptor fd, int maxWidth, int maxHeight) `

- 缩放图片
> newWidth,newHeight代表新值
> scaleWidth，scaleHeight代表基于原来的倍数

    + `Bitmap scale(Bitmap src, int newWidth, int newHeight) `
    + `Bitmap scale(Bitmap src, int newWidth, int newHeight, boolean recycle)`
    + `Bitmap scale(Bitmap src, float scaleWidth, float scaleHeight)`
    + `Bitmap scale(Bitmap src, float scaleWidth, float scaleHeight, boolean recycle)`
    
- 裁剪图片 `Bitmap clip(Bitmap src, int x, int y, int width, int height, boolean recycle)`
- 倾斜图片 `Bitmap skew(Bitmap src, float kx, float ky, boolean recycle)`
- 平移倾斜 `Bitmap skew(Bitmap src, float kx, float ky, float px, float py, boolean recycle)`
- 旋转图片 `Bitmap rotate(Bitmap src, int degrees, float px, float py, boolean recycle)`
- get旋转角度(90,180,270) `int getRotateDegree(String filePath)`
- 切为圆形 `Bitmap toRound(Bitmap src, boolean recycle)`
- 自定义圆角 `Bitmap toRoundCorner(Bitmap src, float radius, boolean recycle)`
- 添加边框 `Bitmap addFrame(Bitmap src, int borderWidth, int color, boolean recycle)`
- 添加文字水印 `Bitmap addTextWatermark(Bitmap src, String content, float textSize, int color, float x,float y, boolean recycle)`
- 添加图片水印 `Bitmap addImageWatermark(Bitmap src, Bitmap watermark, int x, int y, int alpha, boolean recycle)`

--- 

## 压缩相关
本类根据BlankJ的Image改的，压缩方法上与原作者就压缩格式有争议，所以将以下部分的压缩格式提取，由使用者选择。

- 按缩放压缩(原图缩放不涉及压缩格式)
    + 赋值 `Bitmap compressByScale(Bitmap src, int newWidth, int newHeight, boolean recycle)`
    + 倍数 `Bitmap compressByScale(Bitmap src, float scaleWidth, float scaleHeight, boolean recycle)`

- 按质量压缩
    + 质量 `Bitmap compressByQuality(Bitmap src,Bitmap.CompressFormat format, @IntRange(from = 0, to = 100) int quality, boolean recycle)`
    + 最大值 `Bitmap compressByQuality(Bitmap src, Bitmap.CompressFormat format,long maxByteSize, boolean recycle)`

- 按采样大小压缩
    + `Bitmap compressBySampleSize(Bitmap src, Bitmap.CompressFormat format,int sampleSize, boolean recycle)`
    


# 2017.3.22
新增FileUtils

- 文件工具类 FileUtils
    + 根据文件路径获取文件 `File getFileByPath(String filePath)`
    + 判断文件是否存在 `boolean isFileExists(String filePath)`
    + 重命名文件 `boolean renameFile(String filePath, String newName)`
    + 判断是否是目录 `boolean isDir(String dirPath)`
    + 判断是否是文件 `boolean isFile(String filePath)`
    + 如果目录不存在则创建 `boolean createOrExistsDir(String dirPath)`
    + 获取目录下制定后缀名的文件(忽略大小写) `List<File> listFilesInDirWithFilter(String dirPath, String suffix, boolean isRecursive)`
    + 文件转bytes `byte[] File2Bytes(String filePath)`
    + 获取文件大小 `String getFileSize(String PathOrURL)`
    + 获取文件长度 `long getFileLength(String filePath)`
    + 获取文件名 `String getFileName(String filePath)`
    + 获取拓展名 `String getFileExtension(String filePath)`



# 2017.3.20
新增EncodeUtils，EncryptUtils，StringUtils

- 字符串工具类 StringUtils
    + 判空操作 `boolean isEmpty(String s)`
    + 字符串是否相等(a String 1,b String2,isIgnoreCase是否忽略大小写)
    + `isEquals(String a, String b,boolean isIgnoreCase)`

- 编码工具类 EncodeUtils
    + 默认URL编码(UTF-8) `String urlDecode(String input)`
    + 自定义字符集编码 `String urlDecode(String input, String charset)`
    + Base64编码 `byte[] base64Encode(byte[] input)`
    + Base64解码 `byte[] base64Decode(byte[] input)`
    + Base64自定义flags编码 `byte[] base64Encode(byte[] input,int flags)`
    + Base64自定义flags解码 ` byte[] base64Decode(byte[] input,int flags)`
    + Html编码 `String htmlEncode(CharSequence input)`
    + Html解码 `CharSequence htmlDecode(String input)`

- 加密工具类 EncryptUtils 
    + MD5系列
        * String To MD5 `String String2Md5(String data)`
        * String To MD5 `String String2Md5(String data, String salt)`
        * byte To MD5 `String byte2Md5(byte[] data)`
        * byte To MD5 `String byte2Md5(byte[] data, byte[] salt)`
        * MD5 进行hash加密 `byte[] encryptMD5(byte[] data)`
    + AES系列
        * 关于AES要解释起来也不是一句两句能解释的，稍后我会写篇博客来阐述
        * 博客：https://seeways.github.io/
        * 简书：http://www.jianshu.com/p/648bac06e663
        * 
        * 配合Base64转码是常用的，转16进制可以理解为MD5运算
        * 
        * 默认AES加密 `byte[] AESEncrypt(byte[] data, byte[] key)`
        * AES加密后转为Base64编码 `byte[] AES2Base64(byte[] data, byte[] key)`
        * AES加密后转为16进制 `String AES2Hex(byte[] data, byte[] key)`
        * 默认AES解密 `byte[] AESDecrypt(byte[] data, byte[] key)`
        * AES解密Base64编码密文 `byte[] decryptBase64AES(byte[] data, byte[] key)`
        * AES解密16进制密文 `byte[] decryptHexAES(String data, byte[] key)`

    + SHA系列(SHA1,SHA256,SHA512)
        * SHA1加密 
            - `String SHA1Encrypt(String data)`
            - `String SHA1Encrypt(byte[] data)`
            - `byte[] SHA1Hash(byte[] data)`
        * SHA256加密
            - `String SHA256Encrypt(String data)`
            - `String SHA256Encrypt(byte[] data)`
            - `byte[] SHA256Hash(byte[] data)`
        * SHA512加密
            - `String SHA512Encrypt(String data)`
            - `String SHA512Encrypt(byte[] data)`
            - `byte[] SHA512Hash(byte[] data)`



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
- ~~根据路径得到原图的宽高~~
    + ~~getImgWidthAndHeight(String path)~~
    + ~~return int[]~~
    + ~~int width = int[0];int height = int[1];~~
- bitmap转成png保存到sd卡
    + ~~saveImg(String savePath,Bitmap bitmap,String imgName)~~
- 请看3.27ImageUtils更新日志

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
