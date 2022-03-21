# 一个对外界提供短网址服务的小demo

<p align="center">
<a href="https://img.shields.io/badge/Java-v1.8.0-red?style=plastic"><img src="https://img.shields.io/badge/Java-v1.8.0-red?style=plastic"></a>
<a href="https://img.shields.io/badge/Platform-Manjaro 19.0.1 Kyria-blue?style=plastic"><img src="https://img.shields.io/badge/Platform-Manjaro 19.0.1 Kyria-blue?style=plastic"></a>
<a href="http://47.100.76.82"><img src="https://img.shields.io/badge/blog-yc-yellow?style=plastic"></a>
<a href="mailto:yinchao.mail@foxmail.com"><img src="https://img.shields.io/badge/contact me-yinchao.mail@foxmail.com-brightgreen?style=plastic"></a>
</p>

## 项目说明

这个小demo旨在提供一个长链接变短链接服务,用户可以将原来的长链接转换为一个容易记住的短链接.

## 用法

1. 首先将项目运行起来,默认ip:localhost,端口:8901

    注意自行修改时同时要修改constant包里面的Constant.java文件的ip和端口号

2. 通过http://ip:port/short_url_service/create,创建一个短url,记录返回的短url,应该是类似 http://ip:port/short_url_service/find/{id},格式,id为每个不同的短链接数字

    前端发送参数格式为Json,方法为Post请求:
    ```json
    {"sourceUrl":"#{sourceUrl}"}
    ```
    (使用postman发送)

3. 通过直接在浏览器中输入生成的短链接,类似这样的格式: http://ip:port/short_url_service/find/{id},可以直接重定向到源链接

## 使用到的技术

1. Spring Boot

2. Mybatis数据库框架

3. Slf4j日志记录框架

## 代码说明

1. 整个小demo一共只有两个接口,其中生成短链接的接口对应short_url_service/create/.

    程序首先检查源链接是否是一个值得存储的链接,如果用户恶意将返回的短链继续再想生成短链接,就会造成垃圾数据占用空间,或者多个链接之前跳转降低性能的问题.

    然后判断数据库中是否已经存在过相同的源链接,如果有则返回转换后的链接.如果没有则将这个源链接存储到数据库里面,并返回数据库中表的主键id作为短链接的url的主要部分

2. 第二个接口对应short_url_service/find/{id}.

    中括号的id是上面所说的主键id

    这个接口会去找这个id对应的源链接是否存在,不存在提示错误,存在则直接302重定向到源链接
 
## 协议
<a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/"><img alt="知识共享许可协议" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png" /></a><br /><span xmlns:dct="http://purl.org/dc/terms/" property="dct:title">ShortUrlServiceDemo</span> 由 <a xmlns:cc="http://creativecommons.org/ns#" href="https://github.com/YinXunJIU/ShortUrlServiceDemo" property="cc:attributionName" rel="cc:attributionURL">https://github.com/YinXunJiu/JavaChatDemo </a> 采用 <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/">知识共享 署名-非商业性使用-相同方式共享 4.0 国际 许可协议</a>进行许可。
