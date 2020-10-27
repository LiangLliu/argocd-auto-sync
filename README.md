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

| 属性名              |    默认配置内容    |  说明  |
|:-------------------|:---------------:|:------|
| defaultBranch      | main            | 默认主分支                                      |
| newBranchName      | new-branch      | 新分支名称，用于新建PR，创建后悔删除                 |
| tempDirectoryPath  | temp-files/      | 临时文件缓存目录，用于保存临时处理的文件，处理后会被删除 |
| propertiesFilePath | -               | 需要做配置                                      |

* PullsProperties 相关配置，路径： gitops.pulls:   

| 属性名                  |    默认配置内容        |  说明          |
|:-----------------------|:-------------------:|:---------------|
| createPullRequestTitle | create-pull-request | 创建PR时的标题   |
| updatePullRequestTitle | update-pull-request | 更新PR时的标题   |
| mergeCommitMessage     | merge-message       | merge时的信息   |


* ContentProperties 相关配置，路径： gitops.content:   

| 属性名                  |    默认配置内容           |  说明               |
|:-----------------------|:----------------------:|:---------------------|
| target                 | tag:                   | 替换文件内容时的target  |
| updateContentMessage   | update file, modify tag | 替换文件时的信息        |

