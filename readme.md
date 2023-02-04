## 1.docker镜像加速

```
mkdir -p /etc/docker
tee /etc/docker/daemon.json <<-'EOF'
{
 "registry-mirrors": ["https://mirror.ccs.tencentyun.com"]
}
EOF
systemctl daemon-reload
systemctl restart docker
```



## 2.mysql

```
sudo docker run -d --restart=always --name mysql \
-v /home/ubuntu/data/docker/mysql/data:/var/lib/mysql \
-v /home/ubuntu/data/docker/mysql/log:/var/log/mysql \
-v /home/ubuntu/data/docker/mysql/conf:/etc/mysql/conf.d \
-p 3306:3306 \
-e TZ=Asia/Shanghai \
-e MYSQL_ROOT_PASSWORD=root \
mysql:5.7 \
--character-set-server=utf8mb4 \
--collation-server=utf8mb4_general_ci

GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'root'  
FLUSH PRIVILEGES;
```

## 3.redis

```
mkdir -p /home/ubuntu/data/docker/redis/conf
cd /home/ubuntu/data/docker/redis/conf
wget http://download.redis.io/redis-stable/redis.conf
修改如下内容

● bind 127.0.0.1 #注释掉这部分，这是限制redis只能本地访问
● protected-mode no #默认yes，开启保护模式，限制为本地访问
● daemonize no#默认no，改为yes意为以守护进程方式启动，可后台运行，除非kill进程（可选），改为yes会使配置文件方式启动redis失败
● appendonly yes #redis持久化
● requirepass root

docker run -p 6379:6379 --name redis -v /home/ubuntu/data/docker/redis/conf/redis.conf:/etc/redis/redis.conf -v /home/ubuntu/data/docker/redis/data:/data -d redis redis-server /etc/redis/redis.conf --appendonly yes
```



## 4.nacos

```
执行nacos数据库脚本

mkdir -p  /home/ubuntu/data/docker/nacos/init.d/
vim /home/ubuntu/data/docker/nacos/init.d/custom.properties
```

```
server.contextPath=/nacos
server.servlet.contextPath=/nacos
server.port=8848

spring.datasource.platform=mysql

db.num=1
db.url.0=jdbc:mysql://mysql:3306/ams_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
db.user=root
db.password=root


nacos.cmdb.dumpTaskInterval=3600
nacos.cmdb.eventTaskInterval=10
nacos.cmdb.labelTaskInterval=300
nacos.cmdb.loadDataAtStart=false

management.metrics.export.elastic.enabled=false

management.metrics.export.influx.enabled=false


server.tomcat.accesslog.enabled=true
server.tomcat.accesslog.pattern=%h %l %u %t "%r" %s %b %D %{User-Agent}i


nacos.security.ignore.urls=/,/**/*.css,/**/*.js,/**/*.html,/**/*.map,/**/*.svg,/**/*.png,/**/*.ico,/console-fe/public/**,/v1/auth/login,/v1/console/health/**,/v1/cs/**,/v1/ns/**,/v1/cmdb/**,/actuator/**,/v1/console/server/**
nacos.naming.distro.taskDispatchThreadCount=1
nacos.naming.distro.taskDispatchPeriod=200
nacos.naming.distro.batchSyncKeyCount=1000
nacos.naming.distro.initDataRatio=0.9
nacos.naming.distro.syncRetryDelay=5000
nacos.naming.data.warmup=true
nacos.naming.expireInstance=true
```

```
docker  run \
--name nacos -d \
-p 8848:8848 \
--privileged=true \
--restart=always \
--link mysql:mysql \
-e JVM_XMS=256m \
-e JVM_XMX=256m \
-e MODE=standalone \
-e PREFER_HOST_MODE=hostname \
-v /home/ubuntu/data/docker/nacos/logs:/home/nacos/logs \
-v /home/ubuntu/data/docker/nacos/init.d/custom.properties:/home/nacos/init.d/custom.properties \
nacos/nacos-server


账号：nacos
密码：nacos
端口：8848
访问地址：http://localhost:8848/nacos
```

