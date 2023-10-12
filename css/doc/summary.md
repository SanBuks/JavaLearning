# ch01 basic
- CSS: 层叠样式表
- 行内样式: `<h1 style="color: red; font-size: 60px">` 
- 内部样式: 
```html
<head>
    <style>
        h1 {
            color: red;
            font-size: 60px
        }
    </style>
</head>
```
- 外部样式: `<link rel="stylesheet" href="01_basic.css">`
- 优先级: 行内 > 内部 == 外部, 相同优先级存在前后覆盖情况
- 语法规则: 选择器, 声明块, 声明, 属性名, 属性值

# ch02 基础选择器
- 通配选择器
- 元素选择器
- 类名选择器
- ID选择器

# ch03 复合选择器
- 交集选择器
- 并集选择器
- 后代选择器
- 子代选择器
- 兄弟选择器
- 属性选择器

# ch04 伪类选择器
- 伪类: 元素的一种状态
- 动态伪类
- 结构伪类
