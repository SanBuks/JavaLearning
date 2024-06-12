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
- 通配选择器: `* { ... }`
- 元素选择器: `p { ... }`
- 类名选择器: `.clazz { ... }`
- ID选择器: `#ant { ... }`
- 优先级 ID > 类 > 元素
        
# ch03 复合选择器
- 交集选择器: `.a.b.c { ... }` 
  - 如果有元素选择器，必须使用元素选择器开头
  - 不存在同时存在两个元素选择器作为交集 (div.span)
  - `class="a b"` 按照声明顺序渲染, 就近原则
- 并集选择器: `h1, span { color: green; }`
  - 可以把通用属性抽象出来, 缩小样式表体积 
- 后代选择器: `ul.sub li { ... }`
- 子代选择器: `div>.type { ... }`
- 兄弟选择器: 
  - `a+a { ... }` 紧紧相邻, 只往下看 
  - `a~a { ... }` 所有相邻, 只往下看
- 属性选择器: `div[...] { ... }`
  - 含有属性 
  - 属性值符合条件(=, ^, &, *)

# ch04 伪类选择器
- 伪类: 元素的一种状态
- 动态伪类: `xxx:xxxStatus` 
  - `<a>`保持 link, visited, hover, active 顺序
  - `<input>` 输入类才有 focus 
- 结构伪类: `x:first-child x:first-child-of-type(n)` ...
  - `div>p:first-child`
