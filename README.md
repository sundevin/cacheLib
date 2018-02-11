# 一个轻量的数据缓存库
---
[ ![Download](https://api.bintray.com/packages/sundevin/cacheLib/cache_lib/images/download.svg) ](https://bintray.com/sundevin/cacheLib/cache_lib/_latestVersion)  
## 说明  
缓存库是通过 [DBFlow](https://github.com/Raizlabs/DBFlow) 实现,内部生成一个简单的缓存表，用于管理缓存。  
由于 `DBFlow` 数据库创建方式的限制，暂不提供数据库升级。  
如果需要复杂的数据管理，需要自己集成相关的 ORM 框架。

## 使用  
### 集成  
1，在项目的 `build.gradle` 内加入 `maven` 地址，如下：
```
allProjects {
  repositories {
    // required to find the project's artifacts
    maven { url "https://www.jitpack.io" }
  }
}
```
2，在 `aap` 的 `build.gradle` 内添加依赖
```
def dbflow_version = "4.2.4"
dependencies {
    ....
    annotationProcessor "com.github.Raizlabs.DBFlow:dbflow-processor:${dbflow_version}"
    api "com.github.Raizlabs.DBFlow:dbflow-core:${dbflow_version}"
    api "com.github.Raizlabs.DBFlow:dbflow:${dbflow_version}"
    implementation 'com.google.code.gson:gson:2.8.2'
    //last_version 替换为最新版本号
    implementation 'com.sundevin:cache_lib:{last_version}'
}
```
### 初始化
在项目的 `Application` 进行初始化
```
    @Override
    public void onCreate() {
        super.onCreate();
        //参数2为数据库名称
        //参数3为缓存的有效期（毫秒），-1 表示永久有效，在存储数据时也可单独指定某条数据的有效期
        DBManager.init(this,"TestDB",-1);
    }
```
### 缓存管理
#### 保存数据
```
//在保存数据时，如果存在旧数据，则覆盖，覆盖数据时如果未指定有效时间，则会以覆盖时间为起点，以旧数据指定的有效时间重新计算到期时间；如果不存在，则新增数据。
CacheManager.save();
```
#### 删除数据
```
//删除指定的数据
CacheManager.delete();
//删除所有无效的数据，可以用来清理无效数据。
CacheManager.deleteAllInvalid();
```
#### 查询数据
```
//查询数据是否存在(记录在，但数据不一定是在有效期内)
CacheManager.exists();

//查询有效的数据是否存在
CacheManager.existsValid();

//查询并获取一条有效的数据，如果数据不存在或已过期，则返回null。
CacheManager.selectValid();
```






