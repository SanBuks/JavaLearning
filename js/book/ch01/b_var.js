// #1. 函数中的 var 提升
function test() {
    console.log(x)
    var x = 3
}
test()

// #2. 全局中的 var 提升
console.log(y)
var y = 4

// #3. var 作用域范围
if (true) {
    var z = 4
}
console.log(z)


// #4. let 作用域范围
if (true) {
    let u = 3
}
// console.log(u)  // u is not defined


// #5. var 迭代中的奇特修改
//     结果是 5 5 5 5 5 需要改用 let
for (var k = 0; k < 5; ++k)
    // 这里的 k 都指向同一个对象
    // Mutable variable is accessible from closure
    setTimeout(()=>{console.log(k)}, 0)
for (let j = 0; j < 5; ++j)
    // 这里的 k 都指向不同的对象
    setTimeout(()=>{console.log(j)}, 0)

// #6. const 只限制指向变量的引用, 不可以改为对其他变量的引用
const kValue = 10
// kValue = 'xx'
