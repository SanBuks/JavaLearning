# 全局作用域
- 全局作用域中调用: this 引用的是全局对象
- 普通函数中调用: this 相当于全局对象或 undefined（严格模式）
- 对象方法调用: this 指向该对象
- 构造函数调用: this 指向新创建的对象

- call 和 apply 方法调用: 如果使用 call 或 apply 方法来调用函数，则 this 可以被显式地指定为第一个参数。
- 箭头函数中 在箭头函数中，this 的值由外部上下文决定。如果箭头函数是在对象中定义的，则 this 指向该对象。
```javascript
function test() {
    console.log(this);
}
test.call("hello"); // 输出字符串 "hello"

// 示例：

let obj = {
    method: function() {
        let func = () => console.log(this);
        func();
    }
};
obj.method(); // 输出 obj 对象
```
