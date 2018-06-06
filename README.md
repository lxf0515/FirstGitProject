

/********************新功能添加列表***********************/
1.身份认证页面添加 驳回信息的接口，只有在系统认证失败或者客服认证失败的时候才会去调用驳回信息接口


/********************Bug修改列表***********************/
1.修改SplashActivity 类中的Handle 内存溢出  采用静态内部类 + 弱引用  2018/6/1
2.修改引导页中GuideFragment的构造函数  以及采用静态工厂模式创建GuideFragment对象  2018/6/1
3.优化聊天页面  信息发送的   2018/6/1
    --- 1）点击提醒放币和提醒付款按钮 之后进入聊天页面自动发送消息
    --- 2）一旦自动发送付款信息之后，通知购买或者付款页面 将 strAuto 置空
4.优化 购买或者出售广告弹窗中的币种logo的图片加载引起的内存泄漏导致的异常   2018/6/4
5.修改 MyApplication 中NIMClient.init() 方法的位置，放置在添加在证书前面 2018/6/4
6.优化 HomeActivity 中 极光推送别名的设置---回调错误后用handle通知重新设置别名 2018/6/4
7.优化 FragmentDeal 中 onAttach 添加 mContext 的初始化 避免getActivity（）返回null 2018/6/4
8.调用TakePhotoActivity 中获取图片时，添加异常捕获 2018/6/4

9.SplashActivity 中 afterAppData 添加友盟自定义异常捕获  2018/6/5
10.ShenFenRenZhengActivity 中获取照片返回路径时添加异常捕获  2018/6/5
11.FragmentDeal 中onGetCoinPointData 添加友盟自定义异常捕获 2018/6/5
