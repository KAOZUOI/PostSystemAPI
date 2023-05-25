### 开发成员信息

|学号|姓名|贡献|贡献比|
|-|-|-|-|
|12112123|杨宇坤|Restful API接口和测试|50%|
|12112106|李鸿列|高级功能实现和测试|50%|

### 基础功能实现：
Web接口的定义与说明：
##### 域名
`http://10.26.227.188:8080`
##### API规格
目录：
- [创建作者](#创建作者)

- [获取所有作者](#获取所有作者)

- [关注作者](#关注作者)

- [取消关注](#取消关注)

- [获取作者关注列表](#获取作者关注列表)

- [发布帖子](#发布帖子)

- [获取作者发布的帖子](#获取作者发布的帖子)

- [获取所有帖子](#获取所有帖子)

- [获取所有帖子排名](#获取所有帖子排名)

- [用户点赞，收藏和转发帖子](#用户点赞，收藏和转发帖子)

- [用户查看点赞，收藏和转发帖子](#用户查看点赞，收藏和转发帖子)

- [获取指定类别，发帖地点的帖子](#获取指定类别，发帖地点的帖子)

- [回复帖子](#回复帖子)

- [回复对帖子的回复或对回复的回复](#回复对帖子的回复或对回复的回复)

- [获取用户回复的所有帖子](#获取用户回复的所有帖子)

---
###### 创建作者
创建一个新的作者
- API 请求方法
`POST /authors`
- 请求体参数

|参数|类型|是否必须|描述|
|-|-|-|-|
|authorname|String|是|用户名|
|registrationTime|String|是|注册时间|
|phoneNumber|String|是|手机号| 

- 响应
**成功创建作者**：返回HTTP适当的状态码并且带有如下响应主体：`{"message": "Author created successfully"}`
**错误响应**：API返回一个适当的HTTP状态码和相应错误响应主体

---
###### 获取所有作者
获取已注册的所有用户列表
- API 请求方法
`GET /authors/allAuthors
- 响应

**成功获取作者列表**：返回响应成功HTTP状态码并且响应主体将是一个Author的JSON数组。

|属性|类型|描述|
|-|-|-|
|id|String|用户ID|
|authorname|String|用户名|
|registrationTime|String|注册时间|
|phoneNumber|String|手机号|

**错误响应**：API返回一个适当的HTTP状态码和相应错误响应主体

--- 
###### 关注作者
关注一个用户
- API 请求方法
`POST /author/{{authorId}}/followAuthor`
- URL 参数

|参数|是否必选|描述|
|-|-|-|
|authorId|是|关注者的ID|

- 请求体参数

|参数|类型|是否必须|描述|
|-|-|-|-|
|authorname|String|是|用户名|

- 响应
**成功关注作者**：返回响应成功HTTP状态码并且带有响应成功的message
**错误响应**：API返回一个适当的HTTP状态码和相应错误响应主体

---
###### 取消关注
取消关注一个作者
- API 请求方法
`POST /author/{authorId}/unfollowAuthor`
- URL 参数

|参数|是否必选|描述|
|-|-|-|
|authorId|是|关注者的ID|

- 请求体参数

|参数|类型|是否必须|描述|
|-|-|-|-|
|authorname|String|是|用户名|

- 响应
**成功取关作者**：返回响应成功HTTP状态码并且带有响应成功的message
**错误响应**：API返回一个适当的HTTP状态码和相应错误响应主体

---
###### 获取作者关注列表
获取指定作者的所关注作者的列表
- API 请求方法
`GET /author/{authorId}/getFollowingList`
- URL 参数

|参数|是否必选|描述|
|-|-|-|
|authorId|是|关注者的ID|

- 响应

**成功获取作者列表**：返回响应成功HTTP状态码并且带有所有作者信息的响应主体，响应主体将是一个作者的JSON数组。

|属性|类型|描述|
|-|-|-|
|id|String|用户ID|
|authorname|String|用户名|
|registrationTime|String|注册时间|
|phoneNumber|String|手机号|

**错误响应**：API返回一个适当的HTTP状态码和相应错误响应主体

---
###### 发布帖子
上传一个帖子
- API 请求方法
`POST /posts`
- 请求体参数

|参数|类型|是否必须|描述|
|-|-|-|-|
|title|String|是|标题|
|content|String|是|内容|
|authorId|String|否|作者名| 
|postingTime|String|否|发布时间|
|postingCity|String|否|发布城市|
|postingCountry|String|否|发布国家|

- 响应
**成功发布帖子：** 返回响应成功HTTP状态码并且带有上传Post对象信息的响应主体
**错误响应**：API返回一个适当的HTTP状态码和相应错误响应主体

---
###### 获取作者发布的帖子
根据作者ID返回帖子列表
- API 请求方法
`GET /posts/author/{{authorId}}`
- URL 参数

|参数|是否必选|描述|
|-|-|-|
|authorId|是|作者的ID|

- 响应

**成功根据作者ID获取帖子列表**：返回响应成功HTTP状态码并且带有所有作者发布的帖子信息的响应主体，响应主体将是一个帖子的JSON数组。

|属性|类型|描述|
|-|-|-|
|id|Long|帖子ID|
|title|String|用户名|
|content|String|内容|
|authorId|String|作者名| 
|postingTime|String|发布时间|
|postingCity|String|发布城市|
|postingCountry|String|发布国家|

**错误响应**：API返回一个适当的HTTP状态码和相应错误响应主体

---
###### 获取所有帖子
返回所有已被发布的帖子列表
- API 请求方法
`GET /posts/allPosts`
- 响应
**成功根据作者ID获取帖子列表**：返回响应成功HTTP状态码并且带有所有帖子信息的响应主体，响应主体将是一个帖子的JSON数组。
**错误响应**：API返回一个适当的HTTP状态码和相应错误响应主体

###### 获取所有帖子排名
返回根据帖子被点赞，收藏，转发总数加权平均计算得到的权重排名后的帖子列表
- API 请求方法
`GET /posts/sortedByWeight`
- 响应
**成功获取排名后的帖子列表**：返回响应成功HTTP状态码并且带有所有帖子信息的响应主体，响应主体将是一个帖子的JSON数组。
**错误响应**：API返回一个适当的HTTP状态码和相应错误响应主体

---
###### 用户点赞，收藏和转发帖子
- API 请求方法
点赞：`POST /posts/{postId}/like`
收藏：`POST /posts/{postId}/favorite`
转发：`POST /posts/{postId}/share`
- URL 参数

|参数|是否必选|描述|
|-|-|-|
|postId|是|帖子ID|

- 请求体参数

|参数|类型|是否必须|描述|
|-|-|-|-|
|authorname|String|是|用户名|

- 响应
**点赞成功：** 返回响应成功HTTP状态码并且带有`Post liked` 消息
**收藏成功：** 返回响应成功HTTP状态码并且带有`Post favorited`消息
**转发成功：** 返回响应成功HTTP状态码并且带有`Post shared`消息
**错误响应**：API返回一个适当的HTTP状态码和相应错误响应主体

---
###### 用户查看点赞，收藏和转发帖子
- API 请求方法
查看点赞过的帖子：`GET /posts/likes`
查看收藏过的帖子：`GET /posts/shares`
查看转发过的帖子：`GET /posts/favorites`

- 请求参数

|参数|类型|是否必须|描述|
|-|-|-|-|
|authorname|String|是|用户名|

- 响应
**成功获取用户点赞的帖子列表**：返回响应成功HTTP状态码并且带有所有用户点赞过的帖子信息的响应主体，响应主体将是一个帖子的JSON数组。
**成功获取用户的收藏帖子列表**：返回响应成功HTTP状态码并且带有所有用户收藏过的帖子信息的响应主体，响应主体将是一个帖子的JSON数组。
**成功获取用户转发的帖子列表**：返回响应成功HTTP状态码并且带有用户转发过的所有帖子信息的响应主体，响应主体将是一个帖子的JSON数组。
**错误响应**：API返回一个适当的HTTP状态码和相应错误响应主体

---
###### 获取指定类别，发帖地点的帖子
- API 请求方法
获取指定类别的帖子：`GET /posts/category/{{name}}`
- 请求参数

|参数|类型|是否必须|描述|
|-|-|-|-|
|category|String|是|类别|

- API 请求方法
获取指定发布城市的帖子：`GET /posts/city/{{city}}
- 请求参数

|参数|类型|是否必须|描述|
|-|-|-|-|
|city|String|是|发布城市|

- API 请求方法
获取指定发布国家的帖子：`GET /posts/country/{{country}}`
- 请求参数

|参数|类型|是否必须|描述|
|-|-|-|-|
|country|String|是|发布国家|

- API 请求方法
获取指定发布地点的帖子：`GET /posts/country/{{country}}/city/{{city}}`
- 请求参数

|参数|类型|是否必须|描述|
|-|-|-|-|
|country|String|是|发布国家|
|city|String|是|发布城市|

- 响应
**成功获取相应类别的帖子列表**：返回响应成功HTTP状态码并且带有相应类别的帖子信息的响应主体，响应主体将是一个帖子的JSON数组。
**成功获取相应城市的帖子列表**：返回响应成功HTTP状态码并且带有相应城市的帖子信息的响应主体，响应主体将是一个帖子的JSON数组。
**成功获取相应国家的帖子列表**：返回响应成功HTTP状态码并且带有相应国家的帖子信息的响应主体，响应主体将是一个帖子的JSON数组。
**成功获取相应城市和国家的帖子列表**：返回响应成功HTTP状态码并且带有相应城市和国家的帖子信息的响应主体，响应主体将是一个帖子的JSON数组。
**错误响应**：API返回一个适当的HTTP状态码和相应错误响应主体

---
###### 回复帖子
- API 请求方法
`POST /reply/{{postId}}/replyToPost`
- URL 参数

|参数|是否必选|描述|
|-|-|-|
|postId|是|帖子ID|

- 请求体参数

|参数|类型|是否必须|描述|
|-|-|-|-|
|authorname|String|是|用户名|
|content|String|是|回复内容|

- 响应
**成功回复帖子**：返回响应成功HTTP状态码并且重定向到`/user`页面
**错误响应**：API返回一个适当的HTTP状态码和相应错误响应主体

---
###### 回复对帖子的回复或对回复的回复
- API 请求方法
`POST /reply/{{replyId}}/replyToReply`
- URL 参数

|参数|是否必选|描述|
|-|-|-|
|replyId|是|回复ID|

- 请求体参数

|参数|类型|是否必须|描述|
|-|-|-|-|
|authorname|String|是|用户名|
|content|String|是|回复内容|

- 响应
**成功回复回复**：返回响应成功HTTP状态码并且重定向到`/user`页面
**错误响应**：API返回一个适当的HTTP状态码和相应错误响应主体

--- 
###### 获取用户回复的所有帖子
- API 请求方法
`GET /reply/repliedPosts`
- 请求参数

|参数|类型|是否必须|描述|
|-|-|-|-|
|authorname|String|是|用户名|

- 响应
**成功获取用户回复的所有帖子**：返回响应成功HTTP状态码并且带有用户回复的所有帖子信息的响应主体，响应主体将是一个帖子的JSON数组。
**错误响应**：API返回一个适当的HTTP状态码和相应错误响应主体


### 高级功能实现：
- open gauss
	在华为云服务器上部署open gauss数据库，导入官方JDBC jar包远程连接数据库，
- 更多的系统功能性需求
	-  匿名发⾔：发帖时选择是否匿名，如果选择匿名发帖，返回用户名为null
	- 热搜榜功能：根据post的like, favorite和share数量加权平均计算分数，按照权重分数综合对所有post排名。动态更新显示。
	- 多参数搜索功能：根据category搜索post，根据location（city, country）搜索post
- 封装并实现⼀个真正的后端服务器，提供HTTP/RESTful Web 服务。
	- Spring boot 
	- Spring MVC
	- ORM hibernate
- 使⽤数据库连接池
```
spring.datasource.hikari.maximum-pool-size=10  
spring.datasource.hikari.idle-timeout=30000  
spring.datasource.hikari.pool-name=MyDBConnectionPool
```
- ⻚⾯显示设计：用户通过前端Web页面交互，前端页面调用API实现相应功能，所有数据的输入和输出均在web页面完成。
