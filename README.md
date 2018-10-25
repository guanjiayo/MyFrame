# MyFrame
封装的一些项目框架

#### **MVPFrame改一份BaseLib,用于后面直接组件化开发**

# MVPFrame

## 这个框架用一个商城项目用来展示

**商城项目用到的知识点比较综合**

## 项目使用的技术

#### MVP+Kotlin+Retrofit+okHttp+Dagger+RxJava

#### 动态代理隔离第三方库

#### 两个版本的Retrofit封装(1.结合RxJava 2.接口回调)

#### 首页使用ViewPager+Fragment懒加载

#### 仿写EventBus(1.RxBus 2.反射实现的EventBus)

> **实际开发最好还是优先使用ViewModel > EventBus > 官方写的那个RxBus**

#### LazyViewPager+LazyFragment完美解决预加载问题

#### 自定义控件全部采用导入库的方式(方便后期维护)

1. 懒加载库 (ViewPager,Fragment)
2. BaseAdapter库 (RecycleView,ListView)
3. 工具类库
4. EditText 二次封装库
5. 权限库 AOP原理实现