# **AppMarketHelper**
## **一个简单的在应用市场打开对应app的辅助工具类**

---
**功能：**

 - 可设定当前应用上传过的应用市场的package (AppMarket类中可进行选择，可设置多个)
 - 可设定备选网址（当设置的商城无效时跳转）
 - 可选设目标app的package (默认是当前app的package)
 - 可使用默认设置，默认打开所有商城进行选择

注意：当前工具类兼容支持： 三星市场、腾讯应用宝、乐视Tv Store，以及其它的常见应用市场。
 

---

**使用方法:**


 - 去应用宝，开启当前应用，备选地址为 www.baidu.com (单个市场)
 

```java
 AppMarketHelper.with(MainActivity.this).setAppMarket(AppMarket.TECENT_MARKET).setAppUrl("www.baidu.com").go();

```

- 去百度商店或者华为商店（按照此顺序依次选择，如果都没有则使用备选地址），设置备选地址为www.baidu.com  （多个市场）
     

```java
AppMarketHelper.with(MainActivity.this).setAppMarket(AppMarket.BAIDU_MARKET, AppMarket.HUAWEI_MARKET).setAppUrl("www.baidu.com").go();
```

- 开启所有可能的应用商店，如果一个都没有则开启备选地址 （所有可能的市场）
     

```java
AppMarketHelper.with(MainActivity.this).setAppMarket(AppMarket.ALL).setAppUrl("www.baidu.com").go();
```

---


**注意：**
 

 - 备选地址可以写成 `http://www.baidu.com` 或者`www.baidu.com` ，`https`也可以，写成其他类型可能会导致不识别，从而导致无响应。

 - 注意观察`Log.e`发出的日志，尤其当设置完成却无响应的时候，那里应该有你需要的信息。


---

**引入方法：**

 - 在你的Project的 build.gradle 按下面的操作配置仓库。
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

 - 然后在你对应的Modlule内的build.gradle内按下面的方式进行引入。

	

```
dependencies {
       compile 'com.github.guohaiyang1992:AppMarketHelper:1.0'
	}
```
