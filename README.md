<!-- Plugin description -->
## `Image Manager` can manage project images, such as compression, etc
<!-- Plugin description end -->

使用`Image Manager`插件来更好地管理和预览您项目中图片资源，具体效果如下(
以实际为主):

<img src="https://iflutter.toolu.cn/configs/res_manager_list.png" width="360"  alt="图片资源管理"/>

## `Image Manager` 可识别的图片资源:

- .jpg
- .jpeg
- .png
- .svg
- .webp

## 顶部工具栏说明(从左往右):

### 资源定位

在`Image Manager` 工具栏中快速定位当前IDEA打开的图片

### 搜索

在`Image Manager` 工具栏中搜索图片，并支持快捷键。(`ESC`: 退出搜索 `CTRL/COMMAND + F`: 搜索)

### 压缩

使用[Tinypng](https://tinypng.com/)对`Image Manager`中格式为`jpg`，`jpeg`、`png`、`webp`
的图片进行压缩，不过，在首次使用之前，需要先在[Tinypng Key](https://tinypng.com/developers)获取Api
Key。由于[Tinypng](https://tinypng.com/)每个月可免费压缩的图片数量是**有限**
的，当添加新的图片资源到项目时，请优先考虑通过**右键**的方式对单张图片进行压缩。

### 排序

- 文件更新时间排序 (默认)
- 文件名称排序
- 文件大小排序

---

<img src="https://iflutter.toolu.cn/configs/res_manager_menu.png" width="360"  alt="图片资源管理-右键"/>

## 右键菜单说明：

### 拷贝路径

直接复制对资源的引用(如`../../login_icon.png`)，方便开发使用。

### 压缩图片

该右键菜单选项仅对后缀为`jpg`，`jpeg`、`png`、`webp`的图片可用，借助[Tinypng](https://tinypng.com/)对图片进行压缩。

### 删除图片

在删除之前会分析文件的引用情况

### 打开图片

直接打开图片所在的系统目录，方便查看该图片更多属性，结合 `MediaFilePreviewer` 插件效果更佳！
