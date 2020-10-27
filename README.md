# github ops小工具

使用github api 操作github。

## 更新指定repo中的文件

```
put localhost:8080/gitops
```

```json
{
    "deploymentPath":"your deployment repo",
    "token":"your github token",
    "imageTag":"replace test"
}
``` 

* 需设置repo中修改文件的路径，以及默认分支名称
```yaml
gitops:
  properties-file-path: values-dev.yaml
  default-branch: master
```

## docker 打包

* 构建jar包
```shell script
./gradlew fatJar
```

* 运行
```shell script
java -jar build/libs/git-ops-fatJar-0.0.1-SNAPSHOT.jar 
```

* 构建生成jar包并运行
```shell script
./gradlew build -x test && java -jar build/libs/git-ops-0.0.1-SNAPSHOT.jar

```

* 生成 docker image
```docker
docker build -t localhost/github-ops .
```


* 启动docker image
```docker
docker run --name github_ops -p 8080:8080 -t localhost/github-ops
```

* 进入docker container
```docker
docker exec -it github_ops sh
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

