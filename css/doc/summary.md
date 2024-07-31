# ch01 基础
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
- 优先级: 行内 > ID > 类 > 元素 > 通配, 同类型就近原则
        

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
- 结构伪类: `x:first-child`
  - 限定含义并不严格遵照从左至右, first-child 强调所有孩子 
  - `div>p:first-child`: 等价于 `div>:first-child` 且是 `<p>` 的元素
  - `div p:first-child`: 等价于 `div :first-child` 且是 `<p>` 的元素
- 序数类型伪类: 
  - first-child         (所有孩子中第一个)
  - last-child          (所有孩子中最后一个)
  - nth-child(3)        (所有孩子中第 n 个)
  - nth-child(even)     (所有孩子中第 偶数 个)
  - nth-child(odd)      (所有孩子中第 奇数 个)
  - nth-child(-n+5)     (所有孩子中前 5 个)
  - nth-child(an+b)     (所有孩子中从 b 个开始每次加上 a)
  - first-of-type       (同类型中第一个)
  - nth-of-type(2)      (同类型中第 n 个)
  - nth-last-of-type(2) (同类型中倒数第 n 个)
- 其他伪类: 
  - only-child 
  - only-of-type
  - root
  - empty
- 否定伪类: `div>p:not(:nth-child(odd))` (注意否定的选择器在 not 情境中, 可以简化)
- UI 伪类: 
  - input:checked :checkbox, radio 无法调试背景色
  - input:enabled / input:disabeld
- 目标伪类: `input:target{...}` (根据锚点变化)
- 伪元素: 元素中的特殊位置
  - `div::first-letter` 
  - `div::first-line` 
  - `div::first-line`
  - `input::placeholder` 
  - `p::before { content: ".." }`
  - `p::after { content: ".." }`
- 复杂选择器优先级: 
  - id; 类, 伪类, 属性; 元素, 伪元素
  - `div>p>span:first-child(1)`: (0, 1, 3)
  - `.con p>span:first-of-type`: (0, 2, 2)
  - 同优先级: 就近原则
- `span { color: blue !important; }`

# ch05 属性
- 三大特性
  - 层叠性: 样式冲突
  - 继承性: 优先继承父与祖先某些样式
  - 优先级: !important > 行内 > 行内 > ID > 类 > 元素 > 通配 > 继承, 分组选择器分别计算
- 颜色: 命名, rgba(255,255,255,1), hexa, hsla
- 字体: 
  - font-style: 是否斜体
  - font-weight: 字体粗细, 分档
  - font-size: 指定字体框的高度, 字体实际大小可能较大, 位置可能偏下
  - font-family: 字体, serif 表示衬线
  - font: 复合属性, 按上述顺序添加
- 文本: 
  - letter-spacing: 字母间隔 
  - word-spacing: 字间隔(空格识别)
  - text-decoration: 比如 `none dotted`, 可以用来去除 a 标签样式
  - text-indent: 缩进
  - text-align: 对齐方式
  - line-height: 行高, 一般为 1.5 倍字体大小
    - 过小可能
      - 与 font-size 一致可能重叠
      - 字体框始终居中行内空间, 过小上部分会被遮挡
    - 可继承 
  - height: 整体高度 
    - height < line-height: 会产生溢出
    - height = line-height: 会垂直居中
    - height = null: height = numRow x line-height
    - height = 0, 背景色会消失
  - 垂直对齐: 
    - 顶部: 默认 
    - 中部: line-height = height
    - 底部: line-height = height*2-font-size-x, x 是字体基线的偏移距离
  - vertical-align: 
    - top: 置顶; bottom: 置底; middle: 子元素与父元素的 X 字符中心点对齐
    - 如果子元素高度超父元素的行高, 可能会改变父元素的位置来达到对齐效果
    - 只修饰元素, 只修饰行内元素
    - 可以修饰表格中的 `<td>`
- 列表
  -  list-style-type: 序号类型
  -  list-style-position: 序号位置
  -  list-style-image: url("path") 序号图标
- 边框
  - border-width: 宽度
  - border-color: 颜色
  - border-style: 样式
- 表格 
  - table-layout: 是否平均
  - border-spacing: 单元格间隔
  - border-collapse: 是否合并单元格边框
  - caption-side: 标题位置
  - empty-cells: 是否隐藏空单元格
- 背景
  - background-color: 背景颜色
  - background-image: 图片地址
  - background-repeat: 是否重复, 重复方向
  - background-position: 原点位置
- 鼠标样式: `cursor: help`
- em: 字体宽度的倍数
