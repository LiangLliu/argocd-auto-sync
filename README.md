# github ops小工具

使用github api 操作github。

## 构建jar包

* 构建jar包
```shell script
./gradlew fatJar
```

* 运行
```shell script
java -jar build/libs/git-ops-fatJar-0.0.1-SNAPSHOT.jar 
```  


## 配置参数
* 默认路径为：gitops ： 

| 属性名              |    示例    |  说明  |
|:-------------------|:---------------:|:------|
| userAndRepo        | user/repo           | 访问的github地址              |
| token              | xxxxxxxxxxx      | 访问github的令牌                 |
| filePath            | 配置文件所在的粒径   | 临时文件缓存目录，用于保存临时处理的文件，处理后会被删除 |
| replaceMap         | <key,value>       | 需要替换的内容                                      |

* 示例
```shell script
java -jar build/libs/git-ops-fatJar-0.0.1-SNAPSHOT.jar user/repo token filepath [image.tag=11111, replicaCount=5]
```
```shell script
java -jar build/libs/git-ops-fatJar-0.0.1-SNAPSHOT.jar user/repo token filepath \[image.tag=11111,replicaCount=5\]

```




## docker 打包


* 构建生成jar包并运行
```shell script
./gradlew clean && ./gradlew fatJar -x test && java -jar git-ops-fatJar-0.0.1-SNAPSHOT.jar

```

* 生成 docker image
```docker
docker build -t localhost/github-ops .
```


* 启动docker image
```docker
docker run --name github_ops -p 8080:8080 -t localhost/github-ops user/repo token filepath \[image.tag=11111,replicaCount=5\] 
```

* 进入docker container
```docker
docker exec -it github_ops sh
``` 

