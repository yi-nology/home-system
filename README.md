# home-system
**开发要求：**<br />数据库默认访问为127.0.0.1:3306/test库，如果需要修改请修改application.properties中mysql访问地址。<br />数据库默认密码为123456[]()[，]()[]()[如果需要修改请修改application.properties中mysql密码。]()

缓存服务器默认密码为123456，如果需要修改请修改application.properties中mysql密码。<br />系统运行请先运行后端、再运行前端即可。<br />如果遇到端口占用，请修改对应端口，后端修改[]()[application.properties]()中port 对应端口信息。前端home-system-f2e中vue.config.Js下修改代理地址module.exports. devServer地址重写到对应的后端地址即可<br /> <br />后端开发要求如下：<br />首先准备阿里云账号<br />申请阿里云账号，然后开通OSS对象存储服务，[]()[之后将毕业设计中home-system中alioss.properties 相应的配置文件填写。]()

| endpoint=<br />accessKeyId=<br />accessKeySecret=<br />bucketName=<br />filedir= |
| --- |

其次申请百度云账号<br />申请百度云账号，开通云服务中的图像识别服务，之后将毕业设计中[]()[home-system]()中baidu.properties 相应的配置文件填写。

| #百度云ocrapi<br />baidu.app.id=<br />baidu.api.key=<br />baidu.secret.key= |
| --- |

    在对应的工程目录下，在命令行中使用mvn release:clean ，mvn deploy后项目即可运行。
